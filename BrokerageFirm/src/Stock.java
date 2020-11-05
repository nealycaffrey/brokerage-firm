public class Stock {
    private double open;
    private double high;
    private double low;
    private double close;
    private String ticker;
    private String stockEx;
    private String sector;
    private String id;
    private double upperCircuit;
    private double lowerCircuit;

    public Stock(double open, double high, double low, double close, String ticker, String stockEx, String sector, String id, int lastClose) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.ticker = ticker;
        this.stockEx = stockEx;
        this.sector = sector;
        this.id = id;
    }

    public Stock(String ticker, String stockEx, String sector, String id){
        this.ticker = ticker;
        this.stockEx = stockEx;
        this.sector = sector;
        this.id = id;
    }

    public Stock() {
    }

    @Override
    public String toString() {
        return "Stock{" +
                "ticker='" + ticker + '\'' +
                ", stockEx='" + stockEx + '\'' +
                ", sector='" + sector + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
