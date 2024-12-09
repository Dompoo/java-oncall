package oncall.domain;

import oncall.common.CustomExceptions;

import java.util.Arrays;

public enum DayOfWeek {
	
	월,
	화,
	수,
	목,
	금,
	토,
	일,
	;
	
	public static DayOfWeek from(String value) {
		return Arrays.stream(DayOfWeek.values())
				.filter(dayOfWeek -> dayOfWeek.name().equals(value))
				.findFirst()
				.orElseThrow(CustomExceptions.ILLEGAL_ARGUMENT::get);
	}
}
