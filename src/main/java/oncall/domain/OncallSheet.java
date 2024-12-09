package oncall.domain;

import oncall.common.CustomExceptions;

import java.util.Objects;

public class OncallSheet {
	
	private final OncallCalendar oncallCalendar;
	private final EmergencyWorkers weekdayEmergencyWorkers;
	private final EmergencyWorkers holidayEmergencyWorkers;
	
	public OncallSheet(OncallCalendar oncallCalendar, EmergencyWorkers weekdayEmergencyWorkers, EmergencyWorkers holidayEmergencyWorkers) {
		Objects.requireNonNull(oncallCalendar);
		Objects.requireNonNull(weekdayEmergencyWorkers);
		Objects.requireNonNull(holidayEmergencyWorkers);
		validate(weekdayEmergencyWorkers, holidayEmergencyWorkers);
		this.oncallCalendar = oncallCalendar;
		this.weekdayEmergencyWorkers = weekdayEmergencyWorkers;
		this.holidayEmergencyWorkers = holidayEmergencyWorkers;
	}
	
	private void validate(EmergencyWorkers weekdayEmergencyWorkers, EmergencyWorkers holidayEmergencyWorkers) {
		if (!weekdayEmergencyWorkers.hasSameWorkers(holidayEmergencyWorkers)) {
			throw CustomExceptions.ILLEGAL_ARGUMENT.get();
		}
	}
}
