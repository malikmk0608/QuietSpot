import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObservationDAO {

    // ADD OBSERVATION
    public void addObservation(Observation ob) {

        String sql = "INSERT INTO observations " +
                     "(location, observation_date, observation_time, noise_level) " +
                     "VALUES (?, ?, ?, ?)";

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, ob.location);
            ps.setString(2, ob.date);
            ps.setString(3, ob.time);
            ps.setInt(4, ob.noiseLevel);

            ps.executeUpdate();

            System.out.println("Observation added successfully!");

            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // VIEW ALL OBSERVATIONS
    public void viewObservations() {

        String sql = "SELECT * FROM observations ORDER BY id";

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                Observation ob = new Observation(
                    rs.getInt("id"),
                    rs.getString("location"),
                    rs.getString("observation_date"),
                    rs.getString("observation_time"),
                    rs.getInt("noise_level")
                );

                ob.display();
            }

            if (!found) {
                System.out.println("No observations found.");
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // UPDATE OBSERVATION
    public void updateObservation(Observation ob) {

        String sql = "UPDATE observations SET " +
                     "location=?, observation_date=?, " +
                     "observation_time=?, noise_level=? " +
                     "WHERE id=?";

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, ob.location);
            ps.setString(2, ob.date);
            ps.setString(3, ob.time);
            ps.setInt(4, ob.noiseLevel);
            ps.setInt(5, ob.id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Observation updated successfully!");
            } else {
                System.out.println("Observation ID not found.");
            }

            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    // DELETE OBSERVATION
    public void deleteObservation(int id) {

        String sql = "DELETE FROM observations WHERE id=?";

        try {
            Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Observation deleted successfully!");
            } else {
                System.out.println("Observation ID not found.");
            }

            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}