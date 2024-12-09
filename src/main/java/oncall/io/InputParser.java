package oncall.io;

import oncall.common.dto.MonthAndDayOfWeek;
import oncall.domain.DayOfWeek;

import java.time.Month;

public class InputParser {
	
	public MonthAndDayOfWeek parseMonthAndDayOfWeek(String input) {
		String[] inputs = input.split(",");
		Month month = Month.of(Integer.parseInt(inputs[0]));
		DayOfWeek dayOfWeek = DayOfWeek.from(inputs[1]);
		return new MonthAndDayOfWeek(month, dayOfWeek);
	}
}
