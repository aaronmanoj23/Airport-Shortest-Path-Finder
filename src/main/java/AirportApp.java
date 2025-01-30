import java.io.*;
import java.util.*;

public class AirportApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DirectedGraph<String> graph = new DirectedGraph<>();
        HashedDictionary<String, String> airportDictionary = new HashedDictionary<>();

        try {
            // Load the airport and route data from CSV files
            loadAirports(airportDictionary, "US_Airport_Codes.csv");
            loadRoutes(graph, "US_Airports_Routes.csv");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
            return;
        }

        // Menu-driven application
        while (true) {
            System.out.println("Command? H/Q/D/E");
            String command = scanner.nextLine().trim().toUpperCase();

            switch (command) {
                case "H":
                    displayHelp();
                    break;

                case "Q":
                    queryAirportInfo(scanner, airportDictionary);
                    break;

                case "D":
                    findShortestDistance(scanner, graph, airportDictionary);
                    break;

                case "E":
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid command. Type 'H' for help.");
            }
        }
    }

    private static void displayHelp() {
        System.out.println("Commands:");
        System.out.println("Q: Query airport info");
        System.out.println("D: Find shortest distance between two airports");
        System.out.println("H: Display this help message");
        System.out.println("E: Exit the program");
    }

    private static void queryAirportInfo(Scanner scanner, HashedDictionary<String, String> airportDictionary) {
        System.out.print("Airport code? ");
        String code = scanner.nextLine().trim().toUpperCase();

        if (airportDictionary.contains(code)) {
            System.out.println(airportDictionary.getValue(code));
        } else {
            System.out.println("Airport code unknown.");
        }
    }

    private static void findShortestDistance(Scanner scanner, DirectedGraph<String> graph, HashedDictionary<String, String> airportDictionary) {
        System.out.print("Airport codes (from to)? ");
        String[] codes = scanner.nextLine().trim().toUpperCase().split("\\s+");

        if (codes.length != 2) {
            System.out.println("Invalid input. Please enter two airport codes separated by a space.");
            return;
        }

        String from = codes[0];
        String to = codes[1];

        if (!airportDictionary.contains(from) || !airportDictionary.contains(to)) {
            System.out.println("One or both airport codes are unknown.");
            return;
        }

        // Find the shortest path
        List<String> path = graph.getShortestPath(from, to);
        if (path == null || path.isEmpty()) {
            System.out.println("Airports not connected.");
        } else {
            double totalDistance = calculatePathDistance(graph, path);
            System.out.println("The shortest distance is " + totalDistance + ":");
            for (String airport : path) {
                System.out.println(airport + " (" + airportDictionary.getValue(airport) + ")");
            }
        }
    }

    private static double calculatePathDistance(DirectedGraph<String> graph, List<String> path) {
        double totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String from = path.get(i);
            String to = path.get(i + 1);
            totalDistance += graph.getEdgeWeight(from, to);
        }
        return totalDistance;
    }

    private static void loadAirports(HashedDictionary<String, String> airportDictionary, String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",", 2); // Split on the first comma
            if (parts.length == 2) {
                String code = parts[0].trim();
                String name = parts[1].trim();
                airportDictionary.add(code, name);
            }
        }

        reader.close();
    }

    private static void loadRoutes(DirectedGraph<String> graph, String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(","); // Split on commas
            if (parts.length == 3) {
                String from = parts[0].trim();
                String to = parts[1].trim();
                double distance = Double.parseDouble(parts[2].trim());

                graph.addVertex(from);
                graph.addVertex(to);
                graph.addEdge(from, to, distance);
            }
        }

        reader.close();
    }
}
