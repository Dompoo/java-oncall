package oncall.io;

import oncall.common.CustomExceptions;
import oncall.common.dto.MonthAndDayOfWeek;

import java.util.regex.Pattern;

public class InputValidator {
	
	private static final Pattern MONTH_AND_DAY_OF_WEEK_FORMAT = Pattern.compile("[1-9][12]?,.");
	
	public void validateMonthAndDayOfWeek(String input) {
		if (!MONTH_AND_DAY_OF_WEEK_FORMAT.matcher(input).matches()) {
			throw CustomExceptions.ILLEGAL_ARGUMENT.get();
		}
	}
}
