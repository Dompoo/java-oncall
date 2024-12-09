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
	
	public static boolean isWeekday(int targetDay, DayOfWeek monthStartDayOfWeek) {
		DayOfWeek dayOfWeek = monthStartDayOfWeek.add(targetDay % 7);
		return dayOfWeek != 토 && dayOfWeek != 일;
	}
	
	public DayOfWeek add(int day) {
		return DayOfWeek.values()[this.ordinal() + day];
	}
}
