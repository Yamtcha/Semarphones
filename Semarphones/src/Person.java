import java.util.ArrayDeque;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Yamkela Venfolo
 */
public class Person extends Thread{
    private int personNumber;
    private ArrayDeque<Trips> trips;
    Taxi taxi;
    private int currentBranch;
    public Person(int personNumber,ArrayDeque<Trips> trips,Taxi ta) {
        this.personNumber = personNumber;
        this.trips=new ArrayDeque(trips);
        currentBranch=0;
        taxi=ta;
    }
    public int getPersonNumber() {
        return personNumber;
    }
    public int getCurrentBranch()
    {
        return currentBranch;
    }
    public void run()
    {
        
        while(trips.size()>0)
        {
            try {
                //hail taxi
                System.out.println(Clock.getTime()+" branch "+getCurrentBranch()+": person "+getPersonNumber()+" hail");
                Simulator.n++;
                taxi.hail(this,getCurrentBranch());
                
                //get next trip and make request to destination
                Trips temp=trips.poll();
                System.out.println(Clock.getTime()+" branch "+getCurrentBranch()+" person "+getPersonNumber()+" request "+temp.getBranch());
                taxi.request(this,temp.getBranch());
                
                //spend <duration> time at branch
                this.currentBranch=temp.getBranch();
                Thread.sleep(temp.getDuration()*33);
            } catch (InterruptedException ex) {
                Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    public void wakeUp()
    {
        synchronized(this)
        {
            this.notify();
        }
    }
    public void Linda()
    {
        synchronized(this)
        {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
