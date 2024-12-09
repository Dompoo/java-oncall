package oncall.domain;

import oncall.common.exception.CustomExceptions;

import java.util.Objects;

public class EmergencyWorker {
	
	private static final int MAX_NAME_LENGTH = 5;
	
	private final String name;
	
	public EmergencyWorker(String name) {
		Objects.requireNonNull(name);
		validate(name);
		this.name = name;
	}
	
	private static void validate(String name) {
		if (name.length() > MAX_NAME_LENGTH) {
			throw CustomExceptions.ILLEGAL_ARGUMENT.get();
		}
	}
	
	public String getName() {
		return name;
	}
}
