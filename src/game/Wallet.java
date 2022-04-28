package game;

public class Wallet {
    
    private Integer credit;

    public Wallet() {
        this.credit = 0;
    }

    public void addCredit(Integer amount) {
        this.credit += amount;
    }

    public void subCredit(Integer amount) {
        this.credit -= amount;
    }

    public boolean isAffordable(Integer amount) {
        return this.credit >= amount;
    }
    
}
