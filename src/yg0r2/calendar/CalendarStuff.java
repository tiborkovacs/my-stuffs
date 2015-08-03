package yg0r2.calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

public class CalendarStuff {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		CalendarStuff calendarStuff = new CalendarStuff();

		//calendarStuff.incrementDate(new HashMap<String, String>());

		System.out.println(calendarStuff._getMonthName(11));
		System.out.println(calendarStuff._getMonthNumber("December"));

		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(2014, 11, 4);
		Calendar calendar2 = Calendar.getInstance();
		System.out.println();
		calendar2.set(2014, 11, 5);

		System.out.println(calendar1.before(calendar2));

		System.out.println(calendar1.get(Calendar.AM_PM));
	}

	protected void incrementDate(Map<String, String> context) throws Exception {

		boolean AMPM = true;

		// get increment values

		int incrementYear = 0;
		int incrementMonth = 0;
		int incrementDay = 0;
		int incrementHour = 0;
		int incrementMinute = 0;

		// Set a Calendar instance

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.YEAR, 2014);
		calendar.set(Calendar.DAY_OF_MONTH, 5);
		calendar.set(Calendar.MONTH, 11);
		if (AMPM) {
			calendar.set(Calendar.HOUR, 11);
		}
		else {
			calendar.set(Calendar.HOUR_OF_DAY, 23);
		}
		calendar.set(Calendar.MINUTE, 32);

		if (AMPM) {
			calendar.set(Calendar.AM_PM, 1);
		}

		// Increment the instance

		calendar.add(Calendar.YEAR, incrementYear);
		calendar.add(Calendar.MONTH, incrementMonth);
		calendar.add(Calendar.DATE, incrementDay);
		calendar.add(Calendar.HOUR, incrementHour);
		calendar.add(Calendar.MINUTE, incrementMinute);

		// Select new values

		System.out.println("year: " + calendar.get(Calendar.YEAR));
		System.out.println("month: " + new SimpleDateFormat("MMMM", Locale.ENGLISH).format(calendar.getTime()));
		System.out.println("day: " + calendar.get(Calendar.DAY_OF_MONTH));

		if (AMPM) {
			System.out.println("am/pm: " + calendar.get(Calendar.AM_PM));
			System.out.println("hour: " + calendar.get(Calendar.HOUR));
		}
		else {
			System.out.println("hour: " + calendar.get(Calendar.HOUR_OF_DAY));
		}

		System.out.println("minute: " + calendar.get(Calendar.MINUTE));

	}

	private String _getMonthName(int monthNumber) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, monthNumber - 1);

		String monthName = new SimpleDateFormat(
			"MMMM", Locale.ENGLISH).format(calendar.getTime());

		return monthName.substring(0, 1).toUpperCase() + monthName.substring(1);
	}

	private int _getMonthNumber(String monthName) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(
			new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(monthName));

		return calendar.get(Calendar.MONTH) + 1;
	}
}