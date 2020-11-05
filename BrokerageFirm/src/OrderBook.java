import java.util.ArrayList;
import java.util.HashMap;

//list of all the VALID orders
public class OrderBook{
    private final ArrayList<Order> orderbook;
    Scrips scrips;

    public OrderBook(){
        super();
        this.orderbook = new ArrayList<>();
        this.scrips = new Scrips();

    }

    //an order is only added to the orderbook if it satisfies the upper circuit and lower circuit requirements, and buyer has sufficient funds
    public void add(User user, String type, String scrip, int quantity, double rate, Scrip lol) {
        if (type.equalsIgnoreCase("sell")) {//a user can sell irregardless of his funds
            orderbook.add(new Order(user, type, scrip, quantity, rate, lol));
            System.out.println("order placed for user: " + user.getName()+ ", type: sell, scrip: "+ scrip+ ", qty: "+ quantity+ ", rate: +"+ rate);
        }
        while (type.equalsIgnoreCase("buy")) {
            if (lol.getLower() > rate) {
                //checks for lower circuit violation
                System.out.println("order rejected for user "+ user.getName()+ ", type: buy, scrip: "+ scrip+ ", qty: "+ quantity+ ", rate: +"+ rate+ " due to lower circuit violation");
                break;
            }
            if (lol.getUpper() < rate) {
                //checks for upper circuit violation
                System.out.println("order rejected for user "+ user.getName()+", type: buy, scrip: "+ scrip+ ", qty: "+ quantity+ ", rate: +"+ rate+ " due to upper circuit violation");
                break;
            }
            if (user.getFunds() < quantity * rate) {
                //checks if the buyer has sufficient funds
                System.out.println("order rejected for user "+ user.getName()+ ", type: buy, scrip: "+ scrip+ ", qty: "+ quantity+ ", rate: +"+ rate+" due to insufficient funds");
                break;

            } else if (user.getFunds() >= quantity * rate) {
                //if all cases are satisfied, it places the order
                this.orderbook.add(new Order(user, type, scrip, quantity, rate, lol));
                System.out.println("order placed for user: " + user.getName()+ ", type: buy, scrip: "+ scrip+ ", qty: "+ quantity+ ", rate: +"+ rate);
                break;
            }
            System.out.println();
        }
    }
    //prints the orderbook- list of all open orders
    public void getOrderBook () {
        for (Order order : orderbook) {
            System.out.println(order.toString());
        }
    }

    public void execute(){
        System.out.println("executed transactions: ");
            int quantity=0;
        double ask; //ask price
        double bid; //bid price

        User seller;//User object for the seller
        User buyer;//User object for the buyer

        for(int i=0; i< orderbook.size(); i++){
            for(int j=0; j< orderbook.size(); j++){
                //checking if a buy order matches a sell order, and if they are for the same scrip
                if( i!= j && orderbook.get(i).getType().equalsIgnoreCase("sell") && orderbook.get(j).getType().equalsIgnoreCase("buy") && orderbook.get(i).getScrip().equalsIgnoreCase(orderbook.get(j).getScrip())){
                    //sets quantity of the shares bought and sold based on the requirements and availability of stock
                    if(orderbook.get(i).getQuantity()>orderbook.get(j).getQuantity()){
                        quantity = orderbook.get(i).getQuantity()-orderbook.get(j).getQuantity();
                    }else if(orderbook.get(i).getQuantity()<orderbook.get(j).getQuantity()){
                        quantity =orderbook.get(i).getQuantity();
                    }
                    //scrip of the stock being bought and sold
                    String scrip = orderbook.get(i).getScrip();

                    ask = orderbook.get(i).getRate(); //ask price, price at which the seller is ready to sell
                    bid = orderbook.get(j).getRate(); //bid price, price at which the buyer is ready to buy

                    //condition for an order to be executed is if bid price exceeds ask price
                    if(ask<bid){

                        seller = orderbook.get(i).getUser();
                        buyer = orderbook.get(j).getUser();

                        //printing the details of the order
                        System.out.println(quantity+ " qty of scrip: "+ scrip+ " sold for INR "+ ask);
                        System.out.println("seller:"+seller.getName());
                        System.out.println("buyer:"+ buyer.getName());

                        //updating the buyer and seller's funds after the transactions
                        seller.setFunds(seller.getFunds()+ ask* quantity);
                        buyer.setFunds(buyer.getFunds()-ask* quantity);

                        //previous quantity of stock owned by the seller
                        int prevSell = orderbook.get(i).getQuantity();

                        //hashmap of the seller's holdings
                        HashMap<String, Integer> sellHold = seller.getHoldings();

                        //if the seller sells all his stock of a particular scrip, the scrip is removed from his holdings
                        if((prevSell - quantity) == 0){
                            sellHold.remove(scrip);
                        }else {
                            sellHold.replace(scrip, (prevSell - quantity));
                            //otherwise, the depleted stock is updated
                        }

                        //hashmap of the buyer's holdings
                        HashMap<String, Integer> buyHold = buyer.getHoldings();

                        //previous quantity of stock owned by the buyer
                        int prevBuy = orderbook.get(j).getQuantity();

                        //checks if buyer already owns stock of the particular scrip
                        //adds or replaces accordingly
                        if(!buyHold.containsKey(scrip)){
                            buyHold.put(scrip, quantity);
                        }else if(buyHold.containsKey(scrip)){
                            buyHold.replace(scrip, (prevBuy+quantity));
                        }
                        //sets both the orders as filled, instead of open
                        orderbook.get(i).setFilled(true);
                        orderbook.get(j).setFilled(true);
                    }
                }
            }
        }
        System.out.println();
    }
}

