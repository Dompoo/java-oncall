package oncall.common.dto;

public record OncallDayPlan(
		CalendarDay calendarDay,
		String emergencyWorkerName
) {
	public OncallDayPlan emergencyWorkerNameOf(String emergencyWorkerName) {
		return new OncallDayPlan(calendarDay, emergencyWorkerName);
	}
}
