/*
 * FileName         : DateUtil 
 * File Description : To get access of Date Utils
 * Company          : GAVS
 * Author           : UdayBhaskar Donda
 * Manager          : Anand Kumar M C
 * 
 */

package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class DateUtil {
	//static Logger log = Logger.getLogger(DateUtil.class.getName());

	public static String getCurrentDate(String strFormat) {
		DateFormat dateFormat = new SimpleDateFormat(strFormat);
		Date date = new Date();
		return dateFormat.format(date);
	}


	public static long compareDate(String strDate1, String strDate2,
								   String strFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		java.util.Date d1 = null;
		java.util.Date d2 = null;

		try {
			d1 = sdf.parse(strDate1);
			d2 = sdf.parse(strDate2);
		} catch (ParseException e) {
			//log.error(e.getMessage());
		}
		return d1.getTime() - d2.getTime();
	}


	public static boolean IsDate(String strDate, String strFormat) {
		boolean blResult = false;
		SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(strDate);
			blResult = true;
		} catch (ParseException e) {
			//log.error(e.getMessage());
			blResult = false;
		}
		return blResult;
	}

	public static long changeDateToMilliseconds(String date, String strFormat) {
		long millisecond = 0;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(strFormat);
			Date formattedDate = formatter.parse(date);
			millisecond = formattedDate.getTime();
		} catch (ParseException e)  {
			e.printStackTrace();
		}
		return millisecond;
	}

	public static String getTimeInDifferentZone(String ExpectedTimeZone, String strFormat) {
		String time = "";
		try {
			DateFormat formatter = new SimpleDateFormat(strFormat);
			formatter.setTimeZone(TimeZone.getTimeZone(ExpectedTimeZone));
			Date date=new Date();
			time =formatter.format(date);

		} catch (Exception e)  {
			e.printStackTrace();
		}
		return time;
	}
	public static String getPastDate(String strFormat, int days){
		String date = "";
		try{
			DateFormat dateFormat = new SimpleDateFormat(strFormat);
			Date systemDate = new Date();
			//String todate = dateFormat.format(systemDate); //2014/08/06 15:59:48
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -days);
			Date todate1 = cal.getTime();
			date = dateFormat.format(todate1);

		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
	}
}
