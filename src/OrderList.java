import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderList {
//    arkiv af orders - svarer til ordrebog

    private List<Order> orderList;
    private OrderQueue orderQueue;

    public OrderList() {
        this.orderList = new ArrayList<>();
    }

//    skal vi have køen som argument?
//    public void notifyOrder(OrderQueue orderQueue){
//        orderQueue.getNewOrder();
//         }

    public void addOrder(Order order, OrderQueue orderQueue) {
        orderList.add(order);
        orderQueue.handleNewOrder(order);
    }

    // sæt order.leveret = true og fjern ordre fra orderqueue
    public void deliverOrder(int ordreValg, OrderQueue oQ) {
        int i = 0;
        if (!orderList.isEmpty()) {
            System.out.println("listen er ikke tom");
            for (Order o : orderList) {

                if (orderList.get(i).getOrderId() == ordreValg) {

                    System.out.println("så er der bid");

                    orderList.get(i).setLeveret(true);
//                    vi har ikke instantieret - eller sendt ordrekøen med som argument.
                    oQ.deliverOrder(o);
                    System.out.println("Ordren blev fjernet fra køen og registreret som leveret.\n");

                    return;
                }
            }
            System.out.println("din indtastning svarede ikke til et ordreNr");

        }

    }
//    public void deliverOrder(Order order) {
////        Det skal fremsøges på ordrenummer fra bruger input
//        order.setLeveret(true);
//        orderQueue.removeOrder(order);


    public int generateOrderId() {
        if (orderList == null) {
            return 1;
        }
        return orderList.size() + 1;


    }

    @Override
    public String toString() {
        String orderListtext = "";
        for (Order order : orderList) {
            orderListtext = orderListtext + order.toString();

        }
        return orderListtext;
    }

    public void saveOrderDetailsToFile() {
    }
//     try (PrintWriter printWriter = new PrintWriter(new File(orderDetails.CSV))) {
//        for (Order o : orderList) {
//            for (OrderLine oL : o.getOrderLines()) {
//                printWriter.print(o.getOrderId());
//                printWriter.print(o.getOrderLines());
//                printWriter.print(";");

    public void saveOrderlistToFile() {
        try (PrintWriter writer = new PrintWriter(new File("orderList.CSV"))) {
            writer.print("OrdreId");
            writer.print(";");
            writer.print("Ordretidspunkt");
            writer.print(";");
            writer.print("Pris");
            writer.print(";");
            writer.println("Leveringsstatus");

            for (Order o : orderList) {
                writer.print(o.getOrderId());
                writer.print(";");
                writer.print(o.getOrdreTidspunkt());
                writer.print(";");
                writer.print(o.getPrice());
                writer.print(";");
                if (o.isLeveret() == true) {
                    writer.println("leveret");
                }
                if (o.isLeveret() == false) {
                    writer.println("ikke-leveret");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("filen kunne ikke skrives");
        }
    }
}