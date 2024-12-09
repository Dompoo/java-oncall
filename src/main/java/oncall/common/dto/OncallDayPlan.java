package oncall.common.dto;

import oncall.domain.vo.CalendarDay;

public record OncallDayPlan(
		CalendarDay calendarDay,
		String emergencyWorkerName
) {
}
