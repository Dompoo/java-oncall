package oncall.common.dto;

import oncall.domain.DayOfWeek;
import oncall.domain.vo.CalendarDay;

public record OncallPlanDay(
		CalendarDay calendarDay,
		String emergencyWorkerName
) {
}
