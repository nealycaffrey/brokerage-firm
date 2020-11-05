import java.util.ArrayList;

public class Scrips extends Scrip{
    private final ArrayList<Scrip> scrips;

    public Scrips(){
        this.scrips = new ArrayList<>();
    }
    //to add a scrip to the arraylist of scrips
    public void add( String company, String sector, int open, int high, int low, int close){
        System.out.println("added scrip: " + company + " with a new instantiation of Scrip");
        this.scrips.add(new Scrip(company,sector, open, high, low, close));
    }
    //for the 'Show Scrips' function, prints all the scrips
    public void show(){
        for(Scrip scrip : scrips){
            System.out.println((scrip.toString()));
        }
    }
    //prints all scrips belonging to a certain sector
    public void showSector(String sector){
        System.out.println("scrips listed in sector: "+ sector);
            for (Scrip scrip : scrips) {
                if (scrip.getSector().equals(sector)) {
                    System.out.println(scrip.getCompany());
                    System.out.println("OHLC= <"+ scrip.getOpen() + ", "+ scrip.getHigh()+ ", "+ scrip.getLow()+ ", "+ scrip.getClose()+ ">");
                }
            }
    }
    //returns the scrip object when called with the name of the company
    public Scrip getPlease(String name){
        Scrip scripper = new Scrip();
        for(Scrip scrip: scrips) {
            if (scrip.getCompany().equals(name)) {
                scripper = scrip;
            }
        }
        return scripper;
    }
    //using the removeIf command because using remove function gives concurrent modification exception
    public void deleteScrip(String company){
        System.out.println("deleted scrip: "+ company);
        scrips.removeIf(scrip -> scrip.getCompany().equalsIgnoreCase(company));//using lambda expression
    }

}
