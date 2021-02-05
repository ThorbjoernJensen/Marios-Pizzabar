import java.util.List;

public class OrderQueueObserver {
//    en klasse der (indirekte) implementerer et observerinterface
    private List<Order> orderQueue;








    public void displayQueueFixedTime(List<Order> orderQueue){
//        display alle ordrer med fixed time
        for (Order order:orderQueue){
            if(order.getAfhentningstid()!=-1){
//                så har vi en ordre med fastsat afhentningstidspunkt. De skal displayes sorteret og øverst

            }

        }
    }
}
