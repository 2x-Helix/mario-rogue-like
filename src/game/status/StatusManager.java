package game.status;

import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A singleton to manage statuses with duration for all actors
 * @author ChunKau Mok
 * @version 1.0
 */
public class StatusManager {

    private static StatusManager statusManager = null;                                  // the singleton object
    private HashMap<Actor, ConcurrentHashMap<Status, Integer>> actorStatusDurationMap;  // a Map with Actor as key, a Map as value, which uses Status as key, Int(Duration) as value
                                                                                        // Concurrent to avoid java.util.ConcurrentModificationException
    private StatusManager() {}

    /**
     * Static method to get the StatusManager
     * @return the StatusManager singleton
     */
    public static StatusManager getStatusManager() {
        if (statusManager == null) {
            statusManager = new StatusManager();
            statusManager.actorStatusDurationMap = new HashMap<>();
        }
        return statusManager;
    }

    /**
     * Return the duration of a given status of a given actor
     * Return 0 if the actor doesn't have any status
     * @param actor is the key of the map
     * @return a HashMap<Status, Integer> 
     */
    public Integer getStatusDuration(Actor actor, Status status) {
        if (this.actorStatusDurationMap.containsKey(actor)) {
            return this.actorStatusDurationMap.get(actor).get(status);
        }
        return 0;
    }

    /**
     * Insert a new status with duration to the actor
     * Use this if the actor isn't affected by the status
     * @param actor is the actor affected by the status
     * @param status is the status affecting the actor
     * @param duration is the duration of the status
     * @throws Exception if the duration is less then 1
     */
    public void insertStatusDuration(Actor actor, Status status, Integer duration) throws Exception {

        if (duration < 1) {
            throw new Exception("duration should be at least 1");
        }  

        if (this.actorStatusDurationMap.containsKey(actor)) {
            this.actorStatusDurationMap.get(actor).put(status, duration);
        } else {
            this.actorStatusDurationMap.put(actor, new ConcurrentHashMap<Status, Integer>());
            this.actorStatusDurationMap.get(actor).put(status, duration);
        }
    }

    /**
     * Update an existing actor with an existing status with new duration
     * Use this if the actor is already affected by the status
     * @param actor is an existing actor in the hashmap
     * @param status is an existing status affecting the actor
     * @param duration is the new duration
     * @throws Exception if Actor is not affected by any statuses or the given status
     */
    public void updateStatusDuration(Actor actor, Status status, Integer duration) throws Exception {

        if (!this.actorStatusDurationMap.containsKey(actor)) {
            throw new Exception("Actor is not affected by any statuses");
        }

        if (!this.actorStatusDurationMap.get(actor).containsKey(status)) {
            throw new Exception("Actor is not affected by this status");
        }

        if (duration > 0) {                                                 // update status with new duration
            this.actorStatusDurationMap.get(actor).put(status, duration);
        } else {                                                            // remove expired status
            this.removeStatus(actor, status);
        }

    }

    /**
     * Remove a status from actor 
     * Use this if the status is no longer affecting the actor
     * @param actor is the actor no longer affected by the status
     * @param status is the status no longer affecting the actor
     * @throws Exception if actor isn't affected by a status to begin with, or the given status is not affecting the actor
     */
    public void removeStatus(Actor actor, Status status) throws Exception {

        if (!this.actorStatusDurationMap.containsKey(actor)) {
            throw new Exception("Actor is not affected by any statuses");
        }

        if (!this.actorStatusDurationMap.get(actor).containsKey(status)) {
            throw new Exception("Actor is not affected by this status");
        }

        this.actorStatusDurationMap.get(actor).remove(status);
        actor.removeCapability(status);
    }
    
    /**
     * Remove an actor from the hashmap
     * Use this if the actor is unconscious
     * @param actor is the unconscious actor
     */
    public void removeActor(Actor actor) {
        this.actorStatusDurationMap.remove(actor);
    }

    /**
     * Informs the manage the passage of time
     * Reduce all statuses' duration by 1, remove statuses if durations become 0
     */
    public void tick() {

        Iterator<Status> statusIterator;    // to avoid java.util.ConcurrentModificationException

        for (Actor actor : this.actorStatusDurationMap.keySet()) {
            statusIterator = this.actorStatusDurationMap.get(actor).keySet().iterator();
            while (statusIterator.hasNext()) {
                Status status = statusIterator.next();
                Integer duration = this.actorStatusDurationMap.get(actor).get(status) - 1;
                try {
                    this.updateStatusDuration(actor, status, duration);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
