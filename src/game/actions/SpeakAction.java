package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Talkable;

public class SpeakAction extends Action {

    private Talkable speaker;

    /**
     * Constructor
     * @param speaker Object to give monologue
     */
    public SpeakAction(Talkable speaker) {
        this.speaker = speaker;
    }

    /**
     * Displays menu description for action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return menu description String of action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return menuDescription(actor);
    }

    /***
     * Get monologue from speaker to display as the menu description
     * @param actor The actor performing the action.
     * @return Monologue String of speaker to display.
     */
    @Override
    public String menuDescription(Actor actor) {
        return speaker.getMonologue(actor);
    }
}
