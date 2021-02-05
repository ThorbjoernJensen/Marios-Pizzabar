import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderQueue {
//    observer af Orderlist. alle ændringer i orderlist bliver via update meldt til OrderQueue
//    Listen orderQueue indeholder de ordrer fra orderList der har leveret = false

    List<Order> orderQueue;
    OrderList orderList;

    List<Order> orderQueueFixedtime;
    List<Order> orderQueueNoTimeLimit;

//    det kunne også bare være en display funktion af orderqueue der kører alle ordrer igennem.


    public OrderQueue() {
        this.orderQueue = new ArrayList<>();
        this.orderQueueFixedtime = new ArrayList<>();
        this.orderQueueNoTimeLimit = new ArrayList<>();
    }


    public void update(Order order) { //ordren er allerede mættet med al den data vi behøver: enten skal ordren tilføjes eller også skal den sættes til leveret.
        boolean allReadyInQueue = false;
        for (Order o : orderQueue) {
            if (o.getOrderId() == order.getOrderId()) {
                allReadyInQueue = true;
                deliverOrder(order);
//                return;//kan man godt komme ud af en funktion sådan?
            }
        }
        if (!allReadyInQueue) {
            handleNewOrder(order);
        }

    }

    public void handleNewOrder(Order order) {// svarer til en update
        orderQueue.add(order);
        splitOrderQueue(order);
        sortOrderQueues();
    }

    public void deliverOrder(Order order) {
        orderQueue.remove(order);
        orderQueueFixedtime.remove(order);
        orderQueueNoTimeLimit.remove(order);
//        det gør tilsyneladende ikke noget at man remover et objekt der ikke er i listen. Ellers kunne man betinge remove med (if list.contains(order))
    }

    public void splitOrderQueue(Order order) {
        if (order.getAfhentningstid() == -1) {
            orderQueueNoTimeLimit.add(order);
        }

        if (order.getAfhentningstid() != -1) { //den vil kun være forskellig fra -1 hvis den aktivt er blevet ændret.
            orderQueueFixedtime.add(order);
        }
    }

    private void sortOrderQueues() {
        Collections.sort(orderQueueFixedtime);
    }

    @Override
    public String toString() {
        String stringFixedTime = "";
        String stringFloatingTime = "";

        if (orderQueueFixedtime.isEmpty()) {
            stringFixedTime = "denne kø er tom";
        }
        if (!orderQueueFixedtime.isEmpty()) {
            for (Order order : orderQueueFixedtime) {
                stringFixedTime = stringFixedTime + order.toString();
            }
        }

        if (orderQueueNoTimeLimit.isEmpty()) {
            stringFloatingTime = "denne kø er tom";
        }
        if (!orderQueueNoTimeLimit.isEmpty()) {
            for (Order order : orderQueueNoTimeLimit) {
                stringFloatingTime = stringFloatingTime + order.toString();
            }
        }

        return "Ordrekø \n\n" +
                "Ordrer med fast leveringstidspunkt: \n" + stringFixedTime + "\n\n\n" +
                "Ordrer uden fast leveringstidspunkt: \n" + stringFloatingTime + "\n\n";
    }
}






