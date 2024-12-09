package oncall.domain;

import oncall.common.dto.MonthAndDayOfWeek;
import oncall.domain.vo.CalendarDay;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OncallCalendar {
	
	private final Month month;
	private final DayOfWeek monthStartDayOfWeek;
	
	public OncallCalendar(Month month, DayOfWeek monthStartDayOfWeek) {
		Objects.requireNonNull(month);
		Objects.requireNonNull(monthStartDayOfWeek);
		this.month = month;
		this.monthStartDayOfWeek = monthStartDayOfWeek;
	}
	
	public List<CalendarDay> getCalendarDays() {
		int lenghtOfMonth = calculateLenghtOfMonth();
		List<CalendarDay> calendarDays = new ArrayList<>();
		for (int day = 1; day <= lenghtOfMonth; day++) {
			calendarDays.add(new CalendarDay(
					day,
					monthStartDayOfWeek.add(day),
					!isHoliday(day),
					LegalHoliday.isLegalHolidayOnWeekday(month.getValue(), day, monthStartDayOfWeek))
			);
		}
		return calendarDays;
	}
	
	private boolean isHoliday(int day) {
		boolean isHoliday = LegalHoliday.isLegalHoliday(month.getValue(), day);
		boolean isWeekDay = DayOfWeek.isWeekday(day, monthStartDayOfWeek);
		return !isWeekDay && isHoliday;
	}
	
	private int calculateLenghtOfMonth() {
		int monthValue = month.getValue();
		if (monthValue == 2) return 28;
		if (monthValue == 4 || monthValue == 6 || monthValue == 9 || monthValue == 11) return 30;
		return 31;
	}
	
	public int getMonthValue() {
		return month.getValue();
	}
}
