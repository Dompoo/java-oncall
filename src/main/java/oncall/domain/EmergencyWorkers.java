package oncall.domain;

import oncall.common.exception.CustomExceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmergencyWorkers {
	
	private static final int MIN_EMERGENCY_WORKER_SIZE = 5;
	private static final int MAX_EMERGENCY_WORKER_SIZE = 35;
	
	private final List<EmergencyWorker> emergencyWorkers;
	
	public EmergencyWorkers(List<EmergencyWorker> emergencyWorkers) {
		Objects.requireNonNull(emergencyWorkers);
		validate(emergencyWorkers);
		this.emergencyWorkers = new ArrayList<>(emergencyWorkers);
	}
	
	private static void validate(List<EmergencyWorker> emergencyWorkers) {
		if (getNoDuplicatedNameSize(emergencyWorkers) != emergencyWorkers.size()) {
			throw CustomExceptions.ILLEGAL_ARGUMENT.get();
		}
		if (MIN_EMERGENCY_WORKER_SIZE > emergencyWorkers.size() || MAX_EMERGENCY_WORKER_SIZE < emergencyWorkers.size()) {
			throw CustomExceptions.ILLEGAL_ARGUMENT.get();
		}
	}
	
	public static EmergencyWorkers from(List<String> emergencyWorkerNames) {
		List<EmergencyWorker> workers = emergencyWorkerNames.stream()
				.map(EmergencyWorker::new)
				.toList();
		
		return new EmergencyWorkers(workers);
	}
	
	public boolean hasSameWorkers(EmergencyWorkers other) {
		for (EmergencyWorker emergencyWorker : emergencyWorkers) {
			if (!other.hasWorkerName(emergencyWorker.getName())) {
				return false;
			}
		}
		return true;
	}
	
	private boolean hasWorkerName(String name) {
		return emergencyWorkers.stream()
				.anyMatch(emergencyWorker -> emergencyWorker.getName().equals(name));
	}
	
	private static long getNoDuplicatedNameSize(List<EmergencyWorker> emergencyWorkers) {
		return emergencyWorkers.stream()
				.map(EmergencyWorker::getName)
				.distinct()
				.count();
	}
	
	public String getNameOf(int index) {
		EmergencyWorker emergencyWorker = emergencyWorkers.get(index % emergencyWorkers.size());
		return emergencyWorker.getName();
	}
}
