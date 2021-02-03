import java.util.Random;
import java.util.Scanner;

public class Input {

    public Input() {
    }

    public static String getString(String question) {
        System.out.print(question);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static int getInt(String question) {
        while (true) {
            try {
                // hvis konverterinngen lykkedes så returnere vi. Hvis det ikke lykkedes
                // så smides der er fejl og gribes af min catch og løkken køre igenn.
                return Integer.parseInt(Input.getString(question));
            } catch (Exception e) {
                System.out.println("det må ikke være et tal ord");
            }
        }
    }

    public static String getMinutesToTimeFormat(int minutes) {
        String hoursText = String.valueOf(minutes / 60);
        String minutesText = String.valueOf(minutes % 60);
        return hoursText + "." + minutesText;
    }

    public static int getTimeInMinutes(String question) {
        while (true) {
            System.out.print(question);
            Scanner scanner = new Scanner(System.in);
            String timeAsString = scanner.nextLine();
            String[] strings = timeAsString.split("\\.");
            try {
                int hoursInMinutes = Integer.parseInt(strings[0]) * 60;
                int minutes = Integer.parseInt(strings[1]);
                return hoursInMinutes + minutes;
            } catch (NumberFormatException e) {
                System.out.println("Fejl i tidsformat. Det skal være på formen hh.mm");
            }
        }
    }

    public static void generateOrders(MenuCard menuCard, OrderList orderList, int antalOrdrer) {
//        generer antalOrdrer tilfældige ordrer med mellem 1 og 5 ordrelinjer og tilfældige pizzaer (uden tid)
        Random random = new Random();

        for (int i = 0; i < antalOrdrer; i++) {
            Order order = new Order(orderList);

            for (int j = 0; j < random.nextInt(5) + 1; j++) {
                Pizza pizza = menuCard.menuOkt20.get(random.nextInt(menuCard.menuOkt20.size()));
                int antal = random.nextInt(3) + 1;
                OrderLine orderLine = new OrderLine(order, pizza, antal);
                order.addOrderLine(orderLine);

            }
            orderList.addOrder(order);


        }


    }
