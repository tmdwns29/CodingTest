import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String name = input.nextLine();

        if (name.startsWith("N")) {
            System.out.println("North London Collegiate School");
        } else if (name.startsWith("B")) {
            System.out.println("Branksome Hall Asia");
        } else if (name.startsWith("K")) {
            System.out.println("Korea International School");
        } else if (name.startsWith("S")) {
            System.out.println("St. Johnsbury Academy");
        }
    }
}