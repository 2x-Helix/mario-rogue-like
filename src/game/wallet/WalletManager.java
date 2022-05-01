package game.wallet;

import java.util.HashMap;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A singleton object to manage the wallet for all actors
 * @author ChunKau Mok (Peter)
 * @version 1.0
 */
public class WalletManager {

    private static WalletManager manager = null;
    private HashMap<Actor, Wallet> mapper;

    /**
     * Private constructor for this singleton
     */
    private WalletManager() {
        mapper = new HashMap<>();
    }

    /**
     * @return the only instance of WalletManager
     */
    public static WalletManager getInstance() {
        if (manager == null) {
            manager = new WalletManager();
        }
        return manager;
    }

    /**
     * Create a new wallet for the actor
     * @param actor is the actor to be given a wallet
     */
    public void createWallet(Actor actor) {
        this.mapper.put(actor, new Wallet());
    }

    /**
     * Return the credit of a given actor
     * @param actor is the target actor
     * @return the credit of the given actor
     * @throws Exception if the actor does not exist in the manager
     */
    public Integer getCredit(Actor actor) throws Exception {
        if (!this.mapper.containsKey(actor))
            throw new Exception("create wallet to actor first");

        return this.mapper.get(actor).getCredit();
    }

    /**
     * Add credit by the given amount to the given actor
     * @param actor is the target actor
     * @param credit is the amount of credit
     * @throws Exception if amount is negative
     */
    public void addCredit(Actor actor, Integer credit) throws Exception{
        if (!this.mapper.containsKey(actor))
            throw new Exception("create wallet to actor first");

        if (credit < 0)
            throw new Exception("amount can not be negative");

        this.mapper.get(actor).addCredit(credit);
    }

    /**
     * Add credit by the given amount to the given actor
     * @param actor is the target actor
     * @param credit is the amount of credit
     * @throws Exception if the actor does not exist in the manager, or amount is negative
     */
    public void subCredit(Actor actor, Integer credit) throws Exception {
        if (!this.mapper.containsKey(actor))
            throw new Exception("create wallet to actor first");

        if (credit < 0)
            throw new Exception("amount can not be negative");

        this.mapper.get(actor).subCredit(credit);
    }

    /**
     * Remove an actor's wallet
     * @param actor the target actor
     */
    public void removeWallet(Actor actor) {
        mapper.remove(actor);
    }

    /**
     * Check if an actor can afford to pay the given amount
     * @param actor is the target actor
     * @param amount is the amount to be checked
     * @return if an actor can afford to pay the given amount
     * @throws Exception if amount is negative
     */
    public boolean canAfford(Actor actor, Integer amount) throws Exception {
        if (!this.mapper.containsKey(actor))
            throw new Exception("create wallet to actor first");

        if (amount < 0)
            throw new Exception("amount can not be negative");

        return this.getCredit(actor) >= amount;
    }
}
