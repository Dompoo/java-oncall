package oncall.io.input;

import oncall.common.exception.CustomExceptions;

import java.util.regex.Pattern;

public class InputValidator {
	
	private static final Pattern MONTH_AND_DAY_OF_WEEK_FORMAT = Pattern.compile("[1-9][12]?,.");
	private static final Pattern EMERGENCY_WORKER_NAMES_FORMAT = Pattern.compile("(.+,)*.+");
	
	public void validateMonthAndDayOfWeek(String input) {
		if (!MONTH_AND_DAY_OF_WEEK_FORMAT.matcher(input).matches()) {
			throw CustomExceptions.ILLEGAL_ARGUMENT.get();
		}
	}
	
	public void validateEmergencyWorkerNames(String input) {
		if (!EMERGENCY_WORKER_NAMES_FORMAT.matcher(input).matches()) {
			throw CustomExceptions.ILLEGAL_ARGUMENT.get();
		}
	}
}
