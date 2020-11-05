public class Order {
    private User user;
    private String type;
    private String scrip;
    private Scrip obj;
    private int quantity;
    private double rate;
    private boolean isFilled;

    //all the components of a specific order given by a user
    public Order(User user, String type, String scrip, int quantity, double rate, Scrip obj) {
        this.user = user;
        this.type = type;
        this.scrip = scrip;
        this.quantity = quantity;
        this.rate = rate;
        isFilled = false;
        this.obj = obj;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
    }

    public User getUser() {
        return user;
    }

    public String getType() {
        return type;
    }

    public String getScrip() {
        return scrip;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return getType() + " order " + ":" + getQuantity() + " at " + getRate();
    }
}
