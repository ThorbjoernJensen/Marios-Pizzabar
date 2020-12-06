import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        MenuCard menuCard = new MenuCard();
        System.out.println("menukort indlæses.");
        menuCard.readMenuFromFile();

        OrderList orderList = new OrderList();
        OrderQueue orderQueue = new OrderQueue(orderList);


//        OrderLine orderLine = new OrderLine(new Pizza(1), 4);
//        System.out.println(orderLine.toString());

        boolean programRunning = true;
        while (programRunning) {
            int valg = menu();


            switch (valg) {
                case 1:
                    menuCard.showMenu();
                    break;
                case 2:
                    System.out.println("du har valgt: " + valg + ": indtast ordre.");
                    programRunning = true;
                    Order order = new Order(orderList);
                    String svar;

//                    Laver et loop der gentager add af ordrelinjer indtil det stoppes med userinput
                    boolean orderRunning = true;
                    while (orderRunning) {
                        int pizzaNummer = (getInt("Indtast pizzanummer: ") - 1);
                        Pizza pizza = menuCard.menuOkt20.get(pizzaNummer);
                        //det kunne gøres mere sikkert ved at søge på pizza med nummer
                        System.out.println("du har valgt pizza nr. " + pizza.getNr() + ": " + pizza.getNavn());
                        int pizzaAntal = getInt("antal: ");
                        OrderLine orderLine = new OrderLine(order, pizza, pizzaAntal);
                        System.out.println(pizzaAntal + " x " + pizza.getNavn() + " blev tilføjet.");
                        order.addOrderLine(orderLine);
// her skal ordrelinjer gemmes til fil
                        orderLine.saveOrderlinesToFile();

                        svar = getString("ønsker du at tilføje pizzaer? j/n: ");
                        if (svar.equalsIgnoreCase("n")) {

                            svar = getString("ønsker du at tilføje afhentningstid? j/n: ");
                            if (svar.equalsIgnoreCase("j")) {
                                order.setAfhentningstid(Input.getTimeInMinutes("hvilket klokkeslet skal ordren leveres?"));
                                System.out.println("afhentningstid blev sat til " + Input.getMinutesToTimeFormat(order.getAfhentningstid()));

                            }
                            orderRunning = false;
                            break;
                        }
//                       hvis der hverken er indtastet n eller j
                        System.out.println("Fejl i indtastning. Tastede du n eller j?");
                    }

// beregn pris
                    order.setPrice(order.calculatePrice());
                    orderList.addOrder(order, orderQueue);
                    System.out.println("Ordren blev gemt" + "\n");

// gem ordre i en CSV fil
                    orderList.saveOrderlistToFile();
                    break;

                case 3:
                    System.out.println("her printes ordrelisten \n");
                    System.out.println(orderList.toString());
                    System.out.println("\n");
                    break;
                case 4:
                    System.out.println("Liste over ordrer der ikke er leverede: ");// = orderQueue

                case 5:
                    System.out.println(orderQueue.toString());
                    int ordreValg;
                    ordreValg = Input.getInt("Hvilken ordre vil du fjerne? indtast ordrenummer: ");

//                    tilgå den ordre der har ordrenummeret ordreValg i ordreListen og set

                    orderList.deliverOrder(ordreValg, orderQueue);
                    orderList.saveOrderlistToFile();
//                    todo tilføj leveringstidspunkt til ordreliste
                    break;


//                    lever ordre: fjern ordre fra orderQueue-listen. sæt leveret=true i orderList.
                case 7:

                    System.out.println(orderQueue.toString());
                    break;

                case 8:
                    programRunning = false;
                    break;

            }
        }
    }

    private static int menu() {
        System.out.println("vælg en af følgende muligheder:");
        System.out.println("-------------------------------");
        System.out.println("1 - vis menukort");
        System.out.println("2 - indtast ordre");
        System.out.println("3 - vis ordreliste");
        System.out.println("4 - lever ordre");
        System.out.println("5 - fjern ordre");
        System.out.println("6 - vis statistik");
        System.out.println("7 - vis ordre - kø");
        System.out.println("8 - afslut");

        return getInt("indtast dit valg: ");

    }

//


    //hjælpefunktioner kan lægges i en util-klasse
    private static String getString(String question) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(question);
        return scanner.nextLine();

    }

    private static int getInt(String question) {
        while (true) {
            try {
                return Integer.parseInt(getString(question));
            } catch (Exception e) {
                System.out.println("noget gik galt. Indtastede du et tal? ");

            }
        }


    }


}
//Todo lav en kø-klasse der viser de pizzaer der ikke er leveret