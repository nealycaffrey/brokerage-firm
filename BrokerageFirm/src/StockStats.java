import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class StockStats {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        //arraylists for close price and open price of 15 days
        ArrayList<Double> close15 = new ArrayList<>();
        ArrayList<Double> open15 = new ArrayList<>();


        try(Scanner fileReader = new Scanner(new File("C:\\Users\\ramya\\IdeaProjects\\BrokerageFirm\\src\\data1.csv"))){
            //read file
            while(fileReader.hasNextLine()){
                String line = fileReader.nextLine();
                String[] parts = line.split(",");

                String ticker = parts[0];
                String date = parts[1];
                double prevClose = Double.parseDouble(parts[2]);
                double open = Double.parseDouble(parts[3]);
                open15.add(open);
                //adding open prices to open arraylist
                double high = Double.parseDouble(parts[4]);
                double low = Double.parseDouble(parts[5]);
                double last = Double.parseDouble(parts[6]);
                double close = Double.parseDouble(parts[7]);
                close15.add(close);
                //adding close prices to close arraylist
            }
        } catch (Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
        //type <average> or <max drawdown> or <max return potential>
        System.out.println("Commands:\n"
            + "average - finds the average price\n"
            + "max drawdown - finds the max drawdown\n"
            + "max return potential - finds the max return potential of a stock, and the max percentage return potential");

        while(true){
            System.out.println("Enter command: ");
            String command = scanner.nextLine();
            //taking command input from the user

            if (command.equals("stop")) {
                break;
            }

            if (command.equals("average")){
                System.out.println("Average price: ");
                double sum=0;
                double average=0;
                //enhanced for loop traversing over the entire data of all the close prices of each day
                for(Double day : close15) {
                    sum = sum + day;
                }
                average = sum/15.0;
                System.out.printf("%.2f\n", average);
            }
            if(command.equals("max drawdown")){
                System.out.println("Maximum drawdown is: ");

                double maxDd = 0;

                //keeping track of the highest price seen so far
                for(int j=0; j< close15.size();j++){
                    for (Double aDouble : close15) {
                        maxDd = Math.max(maxDd, close15.get(j) - aDouble);
                    }
                }
                System.out.printf("%.2f\n", maxDd);
            }

            if (command.equals("max return potential")){
                double profit=0;
                System.out.println("max return potential is: ");
                //traversing two loops simultaneously to calculate profit
                for (Double value : close15) {
                    for (Double aDouble : open15) {
                        if (aDouble < value) {
                            profit += value - aDouble;
                        } else if (aDouble > value) {
                            profit += aDouble - value;
                        }
                    }
                }
                System.out.printf("%.2f\n", profit);
                System.out.println("Max percentage return potential is: ");
                double mprp = (100.0* profit)/open15.get(0);//formula of max percentage return potential
                System.out.printf("%.2f\n", mprp);
            }
        }
    }
}
