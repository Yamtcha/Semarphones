import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yamkela Venfolo
 */
public class Taxi {

    private int semaphore;
    private ArrayList<ArrayDeque<Person>> passengers;
    private boolean[] stops;
    private boolean outbound;
    private int stop;
    private Timer timer;

    public Taxi(int people, int branches, Timer timer) {
        semaphore = 0;
        stop = 0;
        passengers = new ArrayList(branches);
        outbound = false;
        stops = new boolean[branches];
        for (int i = 0; i < branches; i++) {
            passengers.add(i, new ArrayDeque());
            stops[i] = false;
        }
        this.timer = timer;
    }

    public int getCurrentStop() {
        return stop;
    }

    public void addStop(int branch) {
        stops[branch] = true;
    }

    public void removeStop(int branch) {
        stops[branch] = false;
    }

    public int nextStop() {
        boolean check = stops[stop];
        do {
            if (outbound) {
                if (stop == stops.length - 1) {
                    stop--;
                    outbound = false;

                } else {
                    stop++;
                }
            } else if (stop == 0) {
                stop++;
                outbound = true;
            } else {
                stop--;
            }
            check = stops[stop];
        } while (check == false && Thread.activeCount() > 1);
        return stop;
    }

    public void hail(Person passenger, int branch) {
        semaphore--;
        if (semaphore < 0) {
            block(passenger, branch);
        }
    }

    public boolean[] getStops() {
        return stops;
    }

    public void block(Person passenger, int branch) {

        ArrayDeque<Person> temp = passengers.get(branch);
        temp.add(passenger);
        addStop(branch);
        passenger.Linda();
    }

    public void signal(int arrivalBranch) {

        semaphore++;
        if (semaphore <= 0) {
            for (Person p : passengers.get(arrivalBranch)) {
                p.wakeUp();
            }
            passengers.get(arrivalBranch).clear();
            removeStop(arrivalBranch);
        }
    }

    public void request(Person passenger, int branch) {
        semaphore--;
        if (semaphore < 0) {
            block(passenger, branch);
        }
    }

    public boolean gotStops() {
        for (int i = 0; i < stops.length; i++) {
            if (stops[i]) {
                return true;
            }
        }
        return false;
    }

    public int getSemaphore() {
        return semaphore;
    }
}
