import java.util.HashMap;
import java.util.UUID;

//this user is nothing but a trader in the stock exchange
public class User{
    private String name;
    private double funds;
    private final UUID id;//this assigns a unique identifier to every user
    private HashMap<String, Integer> holdings;//a hashmap containing the user's holdings: scrip, quantity

    public User(String name, double funds, HashMap<String,Integer> holdings){
        super();
        this.name = name;
        this.funds = funds;
        this.id = UUID.randomUUID();//retrieves a type 4 UUID
        this.holdings = holdings;
    }

    public User(String name){
        this.id = UUID.randomUUID();
        this.name= name;

    }
    //getters and setters for the above variables, and most of them are used in other classes
    //this helps us achieve encapsulation
    public HashMap<String, Integer> getHoldings() {
        return holdings;
    }

    public void setHoldings(HashMap<String, Integer> holdings) {
        this.holdings = holdings;
    }

    public String getName() {
        return name;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double result){
        this.funds = result;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "User: " + name + ", id: " + id + "  funds: " + funds  +
                ", holdings: " + (holdings == null ? "None" : holdings);
    }
}
