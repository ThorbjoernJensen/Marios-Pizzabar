import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderQueue {
//    observer af Orderlist. alle �ndringer i orderlist bliver via update meldt til OrderQueue
//    indeholder en liste af de ordrer der har leveret = false

    // skal sortere p� tid (alts� de f�rste skal st� �verst - men dem med tilf�jede tidspunkter skal st� aller�verst
    // de skal ogs� sorteres. m�ske skal de have hver deres liste. en med fast leveringstidspunkt og en med "hurtigst muligt"
// egentligt skulle de nok have hver deres klasse.

    List<Order> orderQueue;
    OrderList orderList;
    // dette objekt er med ikke s� vi kan kalde funktionerne i OrderList, men s� vi har den data der er i ordreliste-objektet.
//    n�r vi kun har brug for funktionerne beh�ver vi ikke at have det specifikke objekt med,
//    men her har vi brug for dataen i orderList. og s� skal den specifikke ordreliste sendes med som argument.

    //    2 sorteringer af orderQueue
    List<Order> orderQueueFixedtime = new ArrayList<>();
    List<Order> orderQueueNoTimeLimit = new ArrayList<>();

//    det kunne ogs� bare v�re en display funktion af orderqueue der k�rer alle ordrer igennem.


    public OrderQueue(OrderList orderList) {
        this.orderQueue = new ArrayList<>();
        this.orderList = orderList;


    }


    public void update(Order order) { //ordren er allerede m�ttet med al den data vi beh�ver. vi beh�ver m�ske ikke orderList objekt
//        enten skal ordren tilf�jes eller ogs� skal den s�ttes til leveret.
        boolean allReadyInQueue = false;
        for (Order o : orderQueue) {
            if (o.getOrderId() == order.getOrderId()) {
                allReadyInQueue = true;
                deliverOrder(order);
                return;//kan man godt komme ud af en funktion s�dan?
            }
        }
        if (!allReadyInQueue){
            handleNewOrder(order);
        }

    }

    public void handleNewOrder(Order order) {// svarer til en update
        orderQueue.add(order);
        splitOrderQueue(order);
        sortOrderQueues();
    }

    //    fjerner ordren fra k�en og arrangerer de to sorteringer igen
    public void deliverOrder(Order order) {
        orderQueue.remove(order);
//        det g�r tilsyneladende ikke noget at man remover et objekt der ikke er i listen.
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
            stringFixedTime = "denne k� er tom";
        }
        if (!orderQueueFixedtime.isEmpty()) {
            for (Order order : orderQueueFixedtime) {
                stringFixedTime = stringFixedTime + order.toString();
            }
        }

        if (orderQueueNoTimeLimit.isEmpty()) {
            stringFloatingTime = "denne k� er tom";
        }
        if (!orderQueueNoTimeLimit.isEmpty()) {
            for (Order order : orderQueueNoTimeLimit) {
                stringFloatingTime = stringFloatingTime + order.toString();
            }
        }

        return "Ordrek� \n\n" +
                "Ordrer med fast leveringstidspunkt: \n" + stringFixedTime + "\n\n\n" +
                "Ordrer uden fast leveringstidspunkt: \n" + stringFloatingTime + "\n\n";
    }


    // en metode der adder til den tidsfixederede k� hvis den ikke er leveret
    public void splitOrderQueue(Order o) {
//        for (Order o : orderQueue) {
//            vi skal kun have ordre med i vores k� som ikke er leverede
//            m�ske er det overfl�digt, da de altid er ikke-leverede n�r de addes
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


//m�ske skal vi alligevel bruge observerpattern n�r vi har 2 k�er- vi har 2 k�-lister.
// men det er vel samlet i et k�- objekt. men man kunne lave 2 k�-objekter ligesom med orderLines.
//    kunne man lave et interface.

