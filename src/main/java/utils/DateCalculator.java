package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date Calculator.
 */
public final class DateCalculator {

  private DateCalculator() {
  }

  /**
   * Generates the date to be selected in yyy-MM-dd format.
   *
   * @param daysFromToday The number of days from today.
   * @return The date to be selected.
   */
  public static String dateGenerator(int daysFromToday) {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.DATE, daysFromToday);
    Date d = c.getTime();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    return format.format(d);
  }

  /**
   * Gets today's date with yyyy-MM-dd'T'HH:mm:ss format.
   *
   * @return String
   */
  public static String getTodayDateFixedFormat() {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.DATE, 0);
    Date d = c.getTime();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    return format.format(d);
  }
}
