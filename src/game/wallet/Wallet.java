package game.wallet;

/**
 * A Wallet System for Players
 */
public class Wallet {

    private Integer credit;

    /**
     * Public Constructor, init credit to 0
     */
    public Wallet() {
        this.credit = 0;
    }

    /**
     * Increase the credit by the given amount
     * @param amount is the value to increase
     */
    public void addCredit(Integer amount) {
        this.credit += amount;
    }

    /**
     * Subtract the credit by the given amount
     * @param amount is the value to subtract
     */
    public void subCredit(Integer amount) {
        this.credit -= amount;
    }

    /**
     *  @return if there's enough credit to afford the given amount
     */
    public boolean isAffordable(Integer amount) {
        return this.credit >= amount;
    }

    /**
     * @return the credit in the wallet
     */
    public Integer getCredit() {
        return this.credit;
    }

}
