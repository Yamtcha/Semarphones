import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Yamkela Venfolo
 */
public class Clock {

    private static int hour;
    private static int minute;
    static GregorianCalendar cal;

    public static void init() {
        cal = new GregorianCalendar();
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);
    }
    public static String getTime() {
        return String.format("%02d:%02d", hour, minute);
    }
    public static void advanceTime(int i) {
        minute += i;
        if (minute >= 60) {
            hour++;
            minute = minute - 60;
        }
    }
    public static int getCurrentMin() {
        return minute;
    }
}
