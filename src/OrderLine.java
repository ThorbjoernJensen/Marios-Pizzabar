import java.util.Date;

public class OrderLine {

    private Pizza pizza;
    private int antal;

    //    Todo generer en id der er unik for alle ordrelinier i ordreListen...
    private int orderLineId;

    //    ordrelinje-objektet skal have en reference til ordre-objektet for at vi ved hvilken ordre den tilhører, og dermed kan beregne hvilken ordre den tilhører.
    private Order order;


    public OrderLine(Order order, Pizza pizza, int antal) {
        this.pizza = pizza;
        this.antal = antal;
        this.order = order;
        this.orderLineId= generateOrderLineId(order);
    }

//    private int generateOrderLineId(Order order) {
//        return order.getOrderLines().size() + 1;
//    }

    public int generateOrderLineId(Order order) {

        int startingPoint = order.getOrderlist().generateOrderLineIdStartingPoint();
        if (order.getOrderLines().isEmpty()) {
            return startingPoint + 1;
        } else {
            return startingPoint + order.getOrderLines().size()+1;
        }

    }


    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    //    todo lav en funktion der gemmer ordrelinjer i en fil som svarer til sql-øvelsesdatabase
    public void saveOrderlinesToFile() {


    }

    @Override
    public String toString() {
        return "ordrelinje " + orderLineId + ": " + pizza.toString() + ", antal = " + antal + "\n";
    }
}
