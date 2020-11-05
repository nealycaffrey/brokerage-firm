public class Scrip {
    public String sector;
    public String company;
    public int open;
    public int high;
    public int low;
    public int close;

    //A scrip consists of the company name, sector, and it's prices in the stock market
    public Scrip(String company,String sector, int open, int high, int low, int close) {
        this.sector = sector;
        this.company = company;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }
    //getters for the prices
    public int getOpen() {
        return open;
    }

    public int getHigh() {
        return high;
    }

    public int getLow() {
        return low;
    }
    public int getClose(){
        return close;
    }

    //upper circuit: the maximum price at which a transaction can possibly take place on a given day
    public double getUpper(){
        return (close*1.1);//Last Close Price +10%
    }

    //lower circuit:  the minimum price at which a transaction can possibly take place on a given day
    public double getLower(){
        return (close*9.0)/10.0;//Last Close Price −10%.
    }

    public Scrip() {
    }
    //getters for the company name and the sector name
    public String getCompany() {
        return company;
    }

    public String getSector() {
        return sector;
    }

    @Override
    public String toString() {
        return "scrip: {" +
                "company: " + company  +
                ", sector: "+sector +
                ", O:" + open +
                ", H:" + high +
                ", L:" + low +
                ", C:" + close +
                '}';
    }

}
