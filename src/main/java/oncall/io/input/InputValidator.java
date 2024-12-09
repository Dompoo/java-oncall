package oncall.io.input;

import oncall.common.exception.CustomExceptions;

import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {
	
	private static final List<String> VALID_MONTHS = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
	private static final Pattern EMERGENCY_WORKER_NAMES_FORMAT = Pattern.compile("(.+,)*.+");
	
	public void validateMonthAndDayOfWeek(String input) {
		String[] inputs = input.split(",");
		if (!VALID_MONTHS.contains(inputs[0])) {
			throw CustomExceptions.ILLEGAL_ARGUMENT.get();
		}
	}
	
	public void validateEmergencyWorkerNames(String input) {
		if (!EMERGENCY_WORKER_NAMES_FORMAT.matcher(input).matches()) {
			throw CustomExceptions.ILLEGAL_ARGUMENT.get();
		}
	}
}
