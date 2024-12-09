package oncall.domain;

import java.util.Arrays;
import java.util.Optional;

public enum LegalHoliday {
	
	신정(1, 1),
	삼일절(3, 1),
	어린이날(5, 5),
	현충일(6, 6),
	광복절(8, 15),
	개천절(10, 3),
	한글날(10, 9),
	성탄절(12, 25),
	;
	
	private final int month;
	private final int day;
	
	LegalHoliday(int month, int day) {
		this.month = month;
		this.day = day;
	}
	
	public static boolean isLegalHoliday(int month, int day) {
		return Arrays.stream(LegalHoliday.values())
				.anyMatch(legalHoliday -> legalHoliday.month == month && legalHoliday.day == day);
	}
	
	public static boolean isLegalHolidayOnWeekday(int month, int day, DayOfWeek monthStartDayOfWeek) {
		Optional<LegalHoliday> holiday = Arrays.stream(LegalHoliday.values())
				.filter(legalHoliday -> legalHoliday.month == month && legalHoliday.day == day)
				.findFirst();
		if (holiday.isEmpty()) {
			return false;
		}
		return DayOfWeek.isWeekday(day, monthStartDayOfWeek);
	}
}
