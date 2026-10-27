import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NoiseAnalyzer {

    // VIEW AVERAGE NOISE LEVEL BY LOCATION
    public void locationSummary() {

        String sql = "SELECT location, " +
                     "AVG(noise_level) AS average_noise, " +
                     "COUNT(*) AS total " +
                     "FROM observations " +
                     "GROUP BY location " +
                     "ORDER BY average_noise";

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== LOCATION NOISE SUMMARY =====");

            boolean found = false;

            while (rs.next()) {

                found = true;

                String location = rs.getString("location");
                double avg = rs.getDouble("average_noise");
                int total = rs.getInt("total");

                System.out.println("\nLocation: " + location);
                System.out.printf("Average Noise: %.2f%n", avg);
                System.out.println("Total Observations: " + total);

                if (total < 3) {
                    System.out.println("Insufficient data");
                }
                else if (avg < 1.5) {
                    System.out.println("Noise Category: Quiet");
                }
                else if (avg < 2.5) {
                    System.out.println("Noise Category: Moderate");
                }
                else {
                    System.out.println("Noise Category: Noisy");
                }
            }

            if (!found) {
                System.out.println("No observations available.");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // FIND QUIETER HOURS AT A LOCATION
    public void findQuietHours(String location) {

        String sql = "SELECT HOUR(observation_time) AS hour, " +
                     "AVG(noise_level) AS average_noise, " +
                     "COUNT(*) AS total " +
                     "FROM observations " +
                     "WHERE LOWER(location) = LOWER(?) " +
                     "GROUP BY HOUR(observation_time) " +
                     "ORDER BY average_noise, hour";

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, location);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== QUIETER HOURS =====");
            System.out.println("Location: " + location);

            boolean found = false;
            boolean reliable = false;

            while (rs.next()) {

                found = true;

                int hour = rs.getInt("hour");
                double avg = rs.getDouble("average_noise");
                int total = rs.getInt("total");

                System.out.println("\nTime: " + hour + ":00 - " +
                                   (hour + 1) + ":00");

                System.out.printf("Average Noise: %.2f%n", avg);
                System.out.println("Observations: " + total);

                if (total < 3) {
                    System.out.println("Insufficient data");
                }
                else {
                    reliable = true;
                    System.out.println("Enough observations for comparison");
                }
            }

            if (!found) {
                System.out.println("No observations found for this location.");
            }
            else if (!reliable) {
                System.out.println("\nNot enough data to recommend a quiet hour.");
            }
            else {
                System.out.println(
                    "\nChoose the hour with the lowest average noise " +
                    "among those with at least 3 observations."
                );
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}