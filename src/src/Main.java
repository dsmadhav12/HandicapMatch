import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Input positive handicaps as negative numbers!");
        int numberOfPlayers = 1;
        int numberOfHoles =0;
        Scanner scanner = new Scanner(System.in);
        while(numberOfHoles !=9 && numberOfHoles !=18){
            System.out.print("Enter number of holes for the match (9/18): ");
            numberOfHoles = scanner.nextInt();
        }
        while (numberOfPlayers < 2 || numberOfPlayers > 4) {
            System.out.print("How many Players (2-4): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number between 2 and 4: ");
                scanner.next();
            }
            numberOfPlayers = scanner.nextInt();
        }
        scanner.nextLine();
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("What is player " + (i + 1) +"'s " +"name: " );
            String name = scanner.nextLine();
            System.out.print("What is player " + (i + 1) +"'s " +"handicap: " );
            double handicap = scanner.nextDouble();
            scanner.nextLine();
            players.add(new Player(name, handicap));
        }
        if(numberOfHoles ==9){
            for (int i=0; i<players.size(); i++){
                double handicap = players.get(i).getHandicap();
                handicap = (handicap/2);
                handicap = (double) Math.round(handicap);
                players.get(i).setHandicap(handicap);
                //System.out.println(handicap);
            }
        }
        sortByHandicap(players);
        //printPlayers(players);
        HashMap<String, Double> differences = handicapDifferences(players);
        //printHandicapDifferences(differences);
        HashMap<String, Integer> finalMap = algo(differences);
        printStrokeDifferences(finalMap);

    }
    public static void sortByHandicap(ArrayList<Player> players){
        players.sort(Comparator.comparingDouble(Player::getHandicap));
    }
    public static void printPlayers(ArrayList<Player> players) {
        System.out.println("Players:");
        System.out.printf("%-15s | %-10s%n", "Name", "Handicap");
        System.out.println("-------------------------------");

        for (Player player : players) {
            System.out.printf("%-15s | %-10.2f%n", player.getName(), player.getHandicap());
        }
    }
    public static HashMap<String, Double> handicapDifferences(ArrayList<Player> players) {
        HashMap<String, Double> handicapDifferences = new HashMap<>();
        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                Player player1 = players.get(i);
                Player player2 = players.get(j);
                double difference = Math.abs(player1.getHandicap() - player2.getHandicap());
                    String key = player1.getName()+"_"+player2.getName();
                    if (!handicapDifferences.containsKey(key) && !handicapDifferences.containsKey(player2.getName() +"_"+ player1.getName())) {
                        handicapDifferences.put(key, difference);
                    }
            }
        }
        return handicapDifferences;
    }
    public static void printHandicapDifferences(HashMap<String, Double> handicapDifferences) {
        System.out.println("Handicap Differences:");

        for (Map.Entry<String, Double> entry : handicapDifferences.entrySet()) {
            String key = entry.getKey();
            double difference = entry.getValue();

            String[] players = key.split("_");
            String player1 = players[0];
            String player2 = players[1];
            System.out.println(player1 + " vs " + player2 + ": " + difference);
        }
    }

    public static HashMap<String, Integer> algo(HashMap<String, Double> handicapDifferences) {
        HashMap<String, Integer> strokeDifferences = new HashMap<>();
        for (Map.Entry<String, Double> entry : handicapDifferences.entrySet()) {
            String key = entry.getKey();
            double difference = entry.getValue();
            double strokes = difference;
            if(strokes > 0){
                strokes = (int) Math.round(Math.abs(strokes) / 1.5);
            }
            else{strokes = 0;}

            strokeDifferences.put(key, (int) strokes);
        }

        return strokeDifferences;
    }

    public static void printStrokeDifferences(HashMap<String, Integer> strokeDifferences) {
        System.out.println("Stroke Differences:");

        for (Map.Entry<String, Integer> entry : strokeDifferences.entrySet()) {
            String key = entry.getKey();
            int strokes = entry.getValue();

            String[] players = key.split("_");
            String player1 = players[0];
            String player2 = players[1];

            System.out.println(player1 + " vs " + player2 + ": " + player1 +" gives " + strokes + " stroke(s) " + "to " +player2);
        }
    }
}