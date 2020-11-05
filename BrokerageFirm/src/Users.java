import java.util.ArrayList;

public class Users{

    private final ArrayList<User> users;//arraylist of User objects

    public Users() {
        this.users = new ArrayList<>();//initializing the arraylist
    }

    //adds user to the arraylist of all users
    public void addUser(User user){
        System.out.println("added user: "+ user.getName()+ " with a new instantiation of User");
        users.add(user);
    }

    //deletes a user from the system. Such things happen in the stock market.
    //notice how we have used the removeIf function instead of a standard remove function
    //this is to not face the concurrent modification exception
    public void deleteUser(String username){
        System.out.println("deleted user: "+ username);
        users.removeIf(user -> user.getName().equals(username));
    }

    //returns the User object when we enter the name of the user
    public User getPerson(String inputName){
        User getUser = new User();
        for(User user : users){
            if(user.getName().equalsIgnoreCase(inputName)){
                getUser = user;
            }
        }
        return getUser;
    }

    //function for the Show Users option available in the program
    public void showUsers(){
        for(User user : users){
            System.out.println(user.toString());
        }
    }

    @Override
    public String toString() {
        return "Users{" +
                "users=" + users +
                '}'+ "\n";
    }
}
