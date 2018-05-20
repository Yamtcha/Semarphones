import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yamkela Venfolo
 */
public class Timer extends Thread {

    private Taxi taxi;
    boolean first = true;

    public void run() {

        try {

            while (Thread.activeCount() > 1) {

                System.out.println(Clock.getTime() + " branch :" + taxi.getCurrentStop() + " taxi arrive");
                taxi.signal(taxi.getCurrentStop());
                Thread.sleep(33);
                Clock.advanceTime(1);
                if (taxi.gotStops()) {

                    System.out.println(Clock.getTime() + " branch " + taxi.getCurrentStop() + " taxi depart");
                    System.out.print("");

                }

                Thread.sleep(33 * 2);
                Clock.advanceTime(2);
                taxi.nextStop();
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Timer() {
        taxi = null;
    }

    public void setOff(Taxi taxi) {
        this.taxi = taxi;
    }
}
