package oncall.common.dto;

import oncall.domain.DayOfWeek;

import java.time.Month;

public record MonthAndDayOfWeek(
		Month month,
		DayOfWeek dayOfWeek
) {
}
