package oncall.domain;

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
			throw CustomExceptions.OVER_MAX_EMERGENCY_WORKER_NAME.get(MAX_NAME_LENGTH);
		}
	}
}
