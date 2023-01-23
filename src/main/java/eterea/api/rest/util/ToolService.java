/**
 * 
 */
package eterea.api.rest.util;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author daniel
 *
 */
public class ToolService {

	public static OffsetDateTime stringDDMMYYYY2OffsetDateTime(String date) {
		Integer day = Integer.valueOf(date.substring(0, 2));
		Integer month = Integer.valueOf(date.substring(2, 4));
		Integer year = Integer.valueOf(date.substring(4, 8));
		return OffsetDateTime.of(year, month, day, 0, 0, 0, 0, ZoneOffset.UTC);
	}

	public static OffsetDateTime hourAbsoluteArgentina() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.setTime(new Date());
		return calendar.getTime().toInstant().atOffset(ZoneOffset.UTC);
	}

	public static OffsetDateTime dateAbsoluteArgentina() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().toInstant().atOffset(ZoneOffset.UTC);
	}

	public static OffsetDateTime lastDayOfMonth(Integer anho, Integer mes) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.set(anho, mes - 1, 1, 0, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime().toInstant().atOffset(ZoneOffset.UTC);
	}

	public static OffsetDateTime firstTime(OffsetDateTime date) {
		return OffsetDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 0, 0, 0, 0,
				ZoneOffset.UTC);
	}

	public static OffsetDateTime lastTime(OffsetDateTime date) {
		return OffsetDateTime.of(date.getYear(), date.getMonthValue(), date.getDayOfMonth(), 23, 59, 59, 999,
				ZoneOffset.UTC);
	}

	public static OffsetDateTime dateToOffsetDateTime(Date date) {
		return date.toInstant().atOffset(ZoneOffset.UTC);
	}

}
