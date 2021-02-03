import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Comparable<Order> {

    //    en klasse der rummer flere pizza x antal (svarer til en indkøbskurv) - og stempler med ordreinfo
    private int orderId;
    private List<OrderLine> orderLines;

    private OrderList orderlist;
    private int price;

    //    -1 så man kan teste om der er defineret et tidspunkt - og uden at fjerne muligheden for at kl. kunne være 0.
    private int afhentningstid = -1;
    private Date ordreTidspunkt;
    private boolean leveret = false;


    //    en constructor som tilføjer ordernr. og som består af ordrelinjer (hvorfor er ordrelisten med som argument? for at få id med
//    på den måde er det altid indlejret i den enkelte ordre hvilken ordreliste den tilhører. Og så kan vi trække på ordreListens data

    public Order(OrderList orderListeSomArgument) {
        this.orderLines = new ArrayList<>();
        this.ordreTidspunkt = new Date();
        this.orderlist= orderListeSomArgument; //Det er via denne at vi har adgang til ordrelistens data, via ordre-objekt, når vi sender som argument.
        this.orderId = orderListeSomArgument.generateOrderId();
    }

    public int getPrice() {
        return price;
    }

    //er det den bedste måde at gøre det på? pris som input? kræver det ikke at man allerede har beregnet den?
    public void setPrice(int price) {
        this.price = calculatePrice();
    }

    public boolean isLeveret() {
        return leveret;
    }

    public Date getOrdreTidspunkt() {
        return ordreTidspunkt;
    }

    public int getAfhentningstid() {
        return afhentningstid;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public OrderList getOrderlist() {
        return orderlist;
    }

    public void setOrderlist(OrderList orderlist) {
        this.orderlist = orderlist;
    }

    public void setOrdreTidspunkt(Date ordreTidspunkt) {
        this.ordreTidspunkt = new Date();
    }

    public void setAfhentningstid(int afhentningstid) {
        this.afhentningstid = afhentningstid;
    }

    public void setLeveret(boolean leveret) {
        this.leveret = leveret;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void addOrderLine(OrderLine orderLine) {
        orderLines.add(orderLine);
    }

    public int calculatePrice() {
        int res = 3;
        for (OrderLine oL : orderLines) {
            int oLPrice =
                    oL.getPizza().getPris() * oL.getAntal();
            res = res + oLPrice;
        }
        return res;
    }


    @Override
    public String toString() {
        String orderLineString = "";

        String afhentningstidString = "";

        for (OrderLine orderLine : orderLines) {
            orderLineString = orderLineString + orderLine.toString();
        }

        if (afhentningstid == -1) {
            afhentningstidString = "afhentningstid ikke defineret";
        }
        if (afhentningstid != -1) {
            afhentningstidString = "afhentningstid: " +
                    Input.getMinutesToTimeFormat(afhentningstid);
        }

        return "Ordre nr. " + orderId + ": " + "\n" + orderLineString +


                "ordretidspunkt: " + ordreTidspunkt + ", " + afhentningstidString +
                "\npris: " + price + " kr." +
                "\n\n";

    }

    @Override
    public int compareTo(Order o) {
        return this.afhentningstid - o.afhentningstid;
    }


}



