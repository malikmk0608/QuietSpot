public class Observation {

    int id;
    String location;
    String date;
    String time;
    int noiseLevel;

    Observation(int id, String location, String date,
                String time, int noiseLevel) {

        this.id = id;
        this.location = location;
        this.date = date;
        this.time = time;
        this.noiseLevel = noiseLevel;
    }

    void display() {
        System.out.println("------------------------");
        System.out.println("ID: " + id);
        System.out.println("Location: " + location);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Noise Level: " + noiseLevel);
    }
}