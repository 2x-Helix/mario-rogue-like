package game.items.equipments;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.UseAction;
import game.actors.friendlies.Player;
import game.items.Tickable;

import java.util.ArrayList;
import java.util.List;

public abstract class Equipment extends Item {

    private UseAction useAction = null;
    private final Integer coolDownDuration;
    private Integer coolDownCounter = 0;
    private boolean onCoolDown = false;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Equipment(String name, char displayChar, boolean portable, Integer coolDownDuration) {
        super(name, displayChar, portable);
        this.coolDownDuration = coolDownDuration;
        this.addAction(this.getUseAction());
    }

    /**
     * @return the single UseAction for this item
     */
    public UseAction getUseAction() {
        if (useAction == null)
            useAction = new UseAction(this);

        return useAction;
    }

    /**
     * Called whenever this equipment is used, provides effects to player or the maps
     * @param actor the actor using this equipment
     * @param map the GameMap actor is on
     * @throws Exception if this equipment is on cool down
     */
    public void onUse(Actor actor, GameMap map) throws Exception {
        if (isOnCoolDown())
            throw new Exception("This equipment is on cool down");

        onCoolDown = true;
        this.removeAction(this.getUseAction());
    }

    /**
     * @return a flag indicating if this equipment is on cool down (can't be used)
     */
    public boolean isOnCoolDown() {
        return onCoolDown;
    }

    /**
     * @return the remaining cool down of this item
     */
    public Integer remainingCoolDown() {
        return coolDownDuration - coolDownCounter;
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.tick(currentLocation);
        if (actor instanceof Player) {
            Player player = (Player) actor;
            if (!player.hasEquipment())
                player.setEquipment(this);
        }
    }

    @Override
    public void tick(Location currentLocation) {
        if (!isOnCoolDown()) {              // off cool down, no need to recharge
            // add UseAction if it doesn't have it
            if (!this.getAllowableActions().contains(this.getUseAction()))
                this.addAction(this.getUseAction());
        } else {                            // on cool down, recharge
            coolDownCounter += 1;           // recharge
            onCoolDown = !coolDownDuration.equals(coolDownCounter); // keep cooling down until coolDownCounter == coolDownDuration
            if (!isOnCoolDown())            // if done recharging
                coolDownCounter = 0;        // reset counter
        }
    }

    @Override
    public abstract String toString();

}
