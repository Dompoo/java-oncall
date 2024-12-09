package oncall.domain;

import oncall.common.CustomExceptions;

import java.util.List;
import java.util.Objects;

public class EmergencyWorkers {
	
	private final List<EmergencyWorker> emergencyWorkers;
	
	public EmergencyWorkers(List<EmergencyWorker> emergencyWorkers) {
		Objects.requireNonNull(emergencyWorkers);
		validate(emergencyWorkers);
		this.emergencyWorkers = emergencyWorkers;
	}
	
	private static void validate(List<EmergencyWorker> emergencyWorkers) {
		if (getNoDuplicatedNameSize(emergencyWorkers) != emergencyWorkers.size()) {
			throw CustomExceptions.ILLEGAL_ARGUMENT.get();
		}
	}
	
	private static long getNoDuplicatedNameSize(List<EmergencyWorker> emergencyWorkers) {
		return emergencyWorkers.stream()
				.map(EmergencyWorker::getName)
				.distinct()
				.count();
	}
}
