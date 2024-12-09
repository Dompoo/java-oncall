package oncall.domain.vo;

import oncall.domain.DayOfWeek;

public record CalendarDay(
		int day,
		DayOfWeek dayOfWeek,
		boolean isHoliday,
		boolean isLegalHolidayOnWeekday
) {
}
