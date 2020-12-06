import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuCard {
    List<Pizza> menuOkt20;

    public MenuCard() {
        this.menuOkt20 = new ArrayList<Pizza>();
    }


    public void showMenu() {

        if (menuOkt20 != null) {
            System.out.println("Menukort for Marios pizzaria-bar");
            for (Pizza pizza : menuOkt20) {
                System.out.println(pizza.getNr() + ". " + pizza.getNavn() + ": " + pizza.getIngredienser() + "....." + pizza.getPris() + "kr.");

            }
        } else {
            System.out.println("listen med pizzaer er blevet væk");
        }
    }

    public void writeMenuToFile() {
// vi har en arrayliste af Pizza-objekter vi skal skrive til en fil på en struktureret måde
//        File file= new File("menuNy.csv");


    }


    public void readMenuFromFile() {
//Denne funktion skal kunne hente indhold fra en txt.fil ind i arraylisten.
//        Vi henter det fra en CSV-fil som er tekst struktureret med ; og linjeskift

        File file;
        file = new File("menukort.CSV");

        String line;
        String[] lineSplit;

        try {
            Scanner scanner;
            scanner = new Scanner(file);
            System.out.println(scanner.nextLine());

            //et while-loop der løber igennem indtil vi når pizza nr. 1
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                if (line.contains("1")) {
// når vi finder nr 1 laver vi et array af den første linje udfra ; og gemmer det i vores arraylist
                    lineSplit = line.split(";");
                    System.out.println("gemmer pizza nr. " + lineSplit[0] + " med navnet " + lineSplit[1]);
                    menuOkt20.add(new Pizza(Integer.parseInt(lineSplit[0]), lineSplit[1], lineSplit[2], Integer.parseInt(lineSplit[3])));

                    while (scanner.hasNext()) {
                        line = scanner.nextLine();
                        lineSplit = line.split(";");
                        System.out.println("gemmer pizza nr. " + lineSplit[0] + " med navnet " + lineSplit[1]);
                        menuOkt20.add(new Pizza(Integer.parseInt(lineSplit[0]), lineSplit[1], lineSplit[2], Integer.parseInt(lineSplit[3])));
                    }

                    System.out.println("menukortet er indlæst. \n");
                }
            }
        } catch (
                Exception e) {
            System.out.println("Indlæsning af filen mislykkedes.");
        }


    }
}



