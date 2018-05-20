import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yamkela Venfolo
 */
public class Simulator {

    private Taxi taxi;
    private ArrayList<Person> people;
    public static int counter = 0;
    public static int n = 0;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        new Simulator("bin/file.txt");
    }

    public Simulator(String filename) throws FileNotFoundException, IOException {
        //read from file
        final BufferedReader reader = new BufferedReader(new FileReader(filename));
        int people = Integer.parseInt(reader.readLine());
        int branches = Integer.parseInt(reader.readLine());

        //start timer
        Clock.init();

        //create taxi
        Timer timer = new Timer();
        taxi = new Taxi(people, branches, timer);
        timer.setOff(taxi);

        this.people = new ArrayList(people);
        String line = reader.readLine().trim();
        while (reader.ready()) {
            initPerson(line);
            line = reader.readLine().trim();
        }
        
        initPerson(line);
        for (Person p : this.people) {
            p.start();
        }

        while (n < people) {
            System.out.print("");
        }
        counter = people;

        //taxi takeoff
        timer.run();
    }

    public void initPerson(String line) {
        
        int index = line.indexOf(" ");
        int personNumber = Integer.parseInt(line.substring(0, index));
        ArrayDeque<Trips> trips = new ArrayDeque();
        boolean flag = true;

        while (flag) {
            index = line.indexOf("(");
            int index2 = line.indexOf(")");
            String temp = line.substring(index + 1, index2);
            try {
                line = line.substring(index2 + 2);
            } catch (StringIndexOutOfBoundsException e) {
                flag = false;
            }

            String[] parts = temp.split(" ");
            int destination = Integer.parseInt(parts[0].substring(0, parts[0].indexOf(",")));
            int duration = Integer.parseInt(parts[1]);
            trips.add(new Trips(destination, duration));
        }
        //add person to list of passengers
        people.add(new Person(personNumber, trips, taxi));
    }
}
