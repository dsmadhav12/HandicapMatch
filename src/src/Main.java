import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("What is player one's name: ");
        Scanner scanner = new Scanner(System.in);
        String nameOne = scanner.nextLine();
        System.out.print("What is player one's handicap: ");
        double handicapOne = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("What is player two's name: ");
        String nameTwo = scanner.nextLine();
        System.out.print("What is player two's handicap: ");
        double handicapTwo = scanner.nextDouble();
        Player PlayerOne = new Player( nameOne, handicapOne);
        Player PlayerTwo = new Player( nameTwo, handicapTwo);
    }
}