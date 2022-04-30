package game;

import java.util.HashMap;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A singleton object to manage the wallet for all actors
 * @author ChunKau Mok (Peter)
 * @version 1.0
 */
public class WalletManager {
    
    private static WalletManager walletManager = null;
    private HashMap<Actor, Wallet> actorWalletMap;

    /**
     * Private constructor for this singleton
     */
    private WalletManager() { 
        actorWalletMap = new HashMap<>();
    }

    /**
     * @return the only instance of WalletManager
     */
    public static WalletManager getInstance() {
        if (walletManager == null) {
            walletManager = new WalletManager();
        }
        return walletManager;
    }

    /**
     * Create a new wallet for the actor
     * @param actor is the actor to be given a wallet
     */
    public void newActorWallet(Actor actor) {
        this.actorWalletMap.put(actor, new Wallet());
    }

    /**
     * Add credit by the given amount to the given actor
     * @param actor is the target actor
     * @param amount is the amount of credit
     * @throws Exception if amount is negative
     */
    public void addActorCredit(Actor actor, Integer amount) throws Exception{
        if (amount < 0) 
            throw new Exception("amount can not be negative");
        
        if (!this.actorWalletMap.containsKey(actor))
            this.newActorWallet(actor);

        this.actorWalletMap.get(actor).addCredit(amount);
    }

    /**
     * Add credit by the given amount to the given actor
     * @param actor is the target actor
     * @param amount is the amount of credit
     * @throws Exception if the actor does not exist in the manager, or amount is negative
     */
    public void subActorCredit(Actor actor, Integer amount) throws Exception {
        if (amount < 0) 
            throw new Exception("amount can not be negative");
    
        if (!this.actorWalletMap.containsKey(actor))
            this.newActorWallet(actor);

        this.actorWalletMap.get(actor).subCredit(amount);
    }

    /**
     * Check if an actor can afford to pay the given amount
     * @param actor is the target actor
     * @param amount is the amount to be checked
     * @return if an actor can afford to pay the given amount
     * @throws Exception if amount is negative
     */
    public boolean canActorAfford(Actor actor, Integer amount) throws Exception {
        if (amount < 0) 
            throw new Exception("amount can not be negative");
        
        if (!this.actorWalletMap.containsKey(actor))
            this.newActorWallet(actor);

        return this.getActorCredit(actor) >= amount;
    }

    /**
     * Return the credit of a given actor
     * @param actor is the target actor
     * @return the credit of the given actor
     * @throws Exception if the actor does not exist in the manager
     */
    public Integer getActorCredit(Actor actor) throws Exception {
        if (!this.actorWalletMap.containsKey(actor))
            this.newActorWallet(actor);

        return this.actorWalletMap.get(actor).getCredit();
    }
    
}
