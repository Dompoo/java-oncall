package oncall.domain;

import oncall.common.CustomExceptions;
import oncall.common.dto.OncallPlanDay;
import oncall.common.dto.OncallPlanResult;
import oncall.domain.vo.CalendarDay;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OncallPlanner {
	
	private final OncallCalendar oncallCalendar;
	private final EmergencyWorkers weekdayEmergencyWorkers;
	private final EmergencyWorkers holidayEmergencyWorkers;
	
	public OncallPlanner(OncallCalendar oncallCalendar, EmergencyWorkers weekdayEmergencyWorkers, EmergencyWorkers holidayEmergencyWorkers) {
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
	
	public OncallPlanResult plan() {
		//TODO : 연속 근무시 순서 변경 로직 작성 필요
		List<OncallPlanDay> oncallPlanDays = new ArrayList<>();
		int weekdayIndex = 0;
		int holidayIndex = 0;
		for (CalendarDay calendarDay : oncallCalendar.getCalendarDays()) {
			String emergencyWorkerName = getName(weekdayIndex++, holidayIndex++, calendarDay);
			oncallPlanDays.add(new OncallPlanDay(calendarDay, emergencyWorkerName));
		}
		return new OncallPlanResult(oncallCalendar.getMonthValue(), oncallPlanDays);
	}
	
	private String getName(int weekdayIndex, int holidayIndex, CalendarDay calendarDay) {
		if (calendarDay.isHoliday()) {
			return holidayEmergencyWorkers.getNameOf(holidayIndex);
		}
		return weekdayEmergencyWorkers.getNameOf(weekdayIndex);
	}
}
