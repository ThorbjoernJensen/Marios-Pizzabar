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
                // hvis konverterinngen lykkedes s� returnere vi. Hvis det ikke lykkedes
                // s� smides der er fejl og gribes af min catch og l�kken k�re igenn.
                return Integer.parseInt(Input.getString(question));
            } catch (Exception e) {
                System.out.println("det m� ikke v�re et tal ord");
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
                System.out.println("Fejl i tidsformat. Det skal v�re p� formen hh.mm");
            }
        }
    }


}
