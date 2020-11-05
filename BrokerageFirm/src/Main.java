import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        BufferedReader reader;
        System.out.println("Enter complete file path: ");
        Scanner scanner = new Scanner(System.in);
        String file = scanner.nextLine();
        //C:\Users\ramya\IdeaProjects\BrokerageFirm\src\input.txt
        String[] lines = new String[200];

        try {
            reader = new BufferedReader(new FileReader(file));
            int x=0;
            String s;
            while((s = reader.readLine()) != null){
                lines[x] = s;
                x++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }


        Scrips scrips = new Scrips();
        OrderBook orderBook = new OrderBook();
        Users users = new Users();


        int orderCount=0;

        for(String st: lines) {
            String[] parts = st.split(":");
            int size = parts.length;
            String command = parts[0];

            if (command.equals("Add scrip")) {
                scrips.add(
                        parts[1].split(",")[0].trim(),
                        parts[2].split(",")[0].trim(),
                        Integer.parseInt(parts[3].split(",")[0].trim()),
                        Integer.parseInt(parts[4].split(",")[0].trim()),
                        Integer.parseInt(parts[5].split(",")[0].trim()),
                        Integer.parseInt(parts[6].split(",")[0].trim())
                );

            }
            if(command.equalsIgnoreCase("Add user")){
                String pieces = parts[2].split(" ")[0];
                HashMap<String, Integer> holdings = new HashMap<>();

                if(size==4){
                    holdings.put("", 0);
                }
                if(size==5){
                    String bit = parts[3].substring(2);
                    int num = Integer.parseInt(parts[4].substring(0, parts[4].length()-1));
                    holdings.put(bit, num);

                }
                if(size==6){
                    String bit1 = parts[3].substring(2);
                    int num1 = Integer.parseInt(parts[4].split(",")[0]);
                    String bit2 = parts[4].split(",")[1].trim();
                    int num2 = Integer.parseInt(parts[5].substring(0, parts[5].length()-1));
                    holdings.put(bit1, num1);
                    holdings.put(bit2, num2);

                }
                if(size==7){
                    String bit1 = parts[3].substring(2);
                    int num1 = Integer.parseInt(parts[4].split(",")[0]);
                    String bit2 = parts[4].split(", ")[1];
                    int num2 = Integer.parseInt(parts[5].split(", ")[0]);
                    String bit3 = parts[5].split(",")[1].trim();
                    int num3 = Integer.parseInt(parts[6].substring(0, parts[6].length()-1));

                    holdings.put(bit1, num1);
                    holdings.put(bit2, num2);
                    holdings.put(bit3, num3);

                }
                User user = new User(parts[1].split(",")[0].trim(), Integer.parseInt(pieces), holdings );
                users.addUser(user);
            }
            if(command.equalsIgnoreCase("Place order, user")){
                if(orderCount==0){
                    System.out.println();
                    System.out.println("market opens");
                    System.out.println();
                }
                 User user = users.getPerson(parts[1].trim().split(", ")[0]);
                 String type = parts[2].trim().split(",")[0];
                Scrip hello = scrips.getPlease(parts[3].trim().split(",")[0]);
                String scrip = parts[3].trim().split(",")[0];
                 int quantity = Integer.parseInt(parts[4].trim().split(",")[0]);
                 int rate = Integer.parseInt(parts[5].trim());
                 orderBook.add(user, type, scrip, quantity, rate, hello);
                 orderCount++;
            }
            if(st.trim().equalsIgnoreCase("Show Orderbook")){
                System.out.println();
                System.out.println("showing orderbook: ");
                orderBook.getOrderBook();
                System.out.println();
            }
            if(command.equalsIgnoreCase("Show sector")){
                String sector = parts[1].trim();
                scrips.showSector(sector);
                System.out.println();
            }
            if(command.equalsIgnoreCase("Delete scrip")){
                String company = parts[1].trim();
                scrips.deleteScrip(company);
            }
            if(command.equalsIgnoreCase("Delete User")){
                String username = parts[1].trim();
                users.deleteUser(username);
                System.out.println();
            }

            if (st.trim().equalsIgnoreCase("Show Scrips")) {
                System.out.println("scrips: ");
                scrips.show();
                System.out.println();
            }
            if (st.trim().equalsIgnoreCase("Show Users")) {
                users.showUsers();
                //users.print();
                System.out.println();
            }
            if(st.trim().equalsIgnoreCase("Exit")){
                System.out.println("market closed");
                break;
            }
            if(st.trim().equalsIgnoreCase("Execute")){
                orderBook.execute();
            }
        }
    }
}
