import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ObservationDAO dao = new ObservationDAO();
        NoiseAnalyzer analyzer = new NoiseAnalyzer();

        int choice = 0;

        do {

            System.out.println("\n================================");
            System.out.println("          QUIETSPOT");
            System.out.println("       Campus Noise Tracker");
            System.out.println("================================");

            System.out.println("1. Add Observation");
            System.out.println("2. View All Observations");
            System.out.println("3. Update Observation");
            System.out.println("4. Delete Observation");
            System.out.println("5. View Location Noise Summary");
            System.out.println("6. Find Quieter Study Hours");
            System.out.println("7. Exit");

            System.out.print("\nEnter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1: {
                        System.out.print("Enter location: ");
                        String location = sc.nextLine().trim();

                        System.out.print("Enter date (YYYY-MM-DD): ");
                        String date = sc.nextLine().trim();

                        System.out.print("Enter time (HH:MM): ");
                        String time = sc.nextLine().trim();

                        System.out.print(
                            "Noise level (1-Quiet, 2-Moderate, 3-Noisy): "
                        );
                        int noise = Integer.parseInt(sc.nextLine());

                        if (location.isEmpty() || noise < 1 || noise > 3) {
                            System.out.println("Invalid location or noise level.");
                            break;
                        }

                        Observation ob = new Observation(
                            0, location, date, time, noise
                        );

                        dao.addObservation(ob);
                        break;
                    }

                    case 2: {
                        dao.viewObservations();
                        break;
                    }

                    case 3: {
                        System.out.print("Enter Observation ID to update: ");
                        int id = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter new location: ");
                        String location = sc.nextLine().trim();

                        System.out.print("Enter new date (YYYY-MM-DD): ");
                        String date = sc.nextLine().trim();

                        System.out.print("Enter new time (HH:MM): ");
                        String time = sc.nextLine().trim();

                        System.out.print("Enter new noise level (1-3): ");
                        int noise = Integer.parseInt(sc.nextLine());

                        if (location.isEmpty() || noise < 1 || noise > 3) {
                            System.out.println("Invalid location or noise level.");
                            break;
                        }

                        Observation ob = new Observation(
                            id, location, date, time, noise
                        );

                        dao.updateObservation(ob);
                        break;
                    }

                    case 4: {
                        System.out.print("Enter Observation ID to delete: ");
                        int id = Integer.parseInt(sc.nextLine());

                        dao.deleteObservation(id);
                        break;
                    }

                    case 5: {
                        analyzer.locationSummary();
                        break;
                    }

                    case 6: {
                        System.out.print("Enter location: ");
                        String location = sc.nextLine().trim();

                        analyzer.findQuietHours(location);
                        break;
                    }

                    case 7: {
                        System.out.println("Thank you for using QuietSpot!");
                        break;
                    }

                    default: {
                        System.out.println("Invalid choice. Try again.");
                    }
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }

        } while (choice != 7);

        sc.close();
    }
}