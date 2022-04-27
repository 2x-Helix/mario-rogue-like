package game.status;

import java.util.HashMap;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A singleton to manage statuses with duration for all actors
 * @author ChunKau Mok
 */
public class StatusManager {

    private static StatusManager statusManager = null;                          // the singleton object
    private HashMap<Actor, HashMap<Status, Integer>> actorStatusDurationMap;    // a Map with Actor as key, a Map as value, which
                                                                                    // uses Status as key, Int(Duration) as value
    private StatusManager() {}

    /**
     * Static method to get the StatusManager
     * @return the StatusManager singleton
     */
    public static StatusManager getStatusManager() {
        if (statusManager == null) {
            statusManager = new StatusManager();
        }
        return statusManager;
    }

    /**
     * Return a Status-Duration map of the given actor
     * Return an empty HashMap if the actor doesn't have any status
     * @param actor is the key of the map
     * @return a HashMap<Status, Integer> 
     */
    public HashMap<Status, Integer> getStatusDuration(Actor actor) {
        if (this.actorStatusDurationMap.containsKey(actor)) {
            return this.actorStatusDurationMap.get(actor);
        }
        return new HashMap<Status, Integer>();
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
            this.actorStatusDurationMap.put(actor, new HashMap<Status, Integer>());
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

        if (duration >= 1) {                                                // update status with new duration
            this.actorStatusDurationMap.get(actor).put(status, duration);
        } else {                                                            // remove status 
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
    }
    
    /**
     * Remove an actor from the hashmap
     * Use this if the actor is unconscious
     * @param actor is the unconscious actor
     */
    public void removeActor(Actor actor) {
        this.actorStatusDurationMap.remove(actor);
    }
}
