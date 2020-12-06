import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderQueue {
//    observer af Orderlist. alle ændringer i orderlist bliver meldt til OrderQueue
//    indeholder en liste af de ordre der har leveret = false

    // skal sortere på tid (altså de første skal stå øverst - men dem med tilføjede tidspunkter skal stå allerøverst
    // de skal også sorteres. måske skal de have hver deres liste. en med fast leveringstidspunkt og en med "hurtigst muligt"
// egentligt skulle de nok have hver deres klasse.

    List<Order> orderQueue;
    OrderList orderList; // ikke så vi kan kalde funktionerne i orderList, men så vi har den data der er i ordreliste.
//    når vi kun har brug for funktionerne behøver vi ikke at have det specifikke objekt med.

    //    2 sorteringer af orderQueue
    List<Order> orderQueueFixedtime = new ArrayList<>();
    List<Order> orderQueueNoTimeLimit = new ArrayList<>();


    public OrderQueue(OrderList orderList) {
        this.orderQueue = new ArrayList<>();
        this.orderList = orderList;
    }

    public void handleNewOrder(Order order) {
        orderQueue.add(order);
        splitOrderQueue(order);
        sortOrderQueues();
    }

    //    fjerner ordren fra køen og arrangerer de to sorteringer igen
    public void deliverOrder(Order order) {
        orderQueue.remove(order);
//        det gør tilsyneladende ikke noget at man remover et objekt der ikke er i listen.
        orderQueueFixedtime.remove(order);
        orderQueueNoTimeLimit.remove(order);
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


    // en metode der adder til den tidsfixederede kø hvis den ikke er leveret
    public void splitOrderQueue(Order o) {
//        for (Order o : orderQueue) {
//            vi skal kun have ordre med i vores kø som ikke er leverede
//            måske er det overflødigt, da de altid er ikke-leverede når de addes
        if (o.isLeveret() == false) {
            if (o.getAfhentningstid() == -1) {
                orderQueueNoTimeLimit.add(o);
            }
            if (o.getAfhentningstid() != -1) {
                orderQueueFixedtime.add(o);
            }
        }
    }


}


//måske skal vi alligevel bruge observerpattern når vi har 2 køer- vi har 2 kø-lister.
// men det er vel samlet i et kø- objekt. men man kunne lave 2 kø-objekter ligesom med orderLines.
//    kunne man lave et interface.

