package oncall.domain;

import oncall.common.exception.CustomExceptions;
import oncall.common.dto.OncallDayPlan;
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
		if (!holidayEmergencyWorkers.hasSameWorkers(weekdayEmergencyWorkers)) {
			throw CustomExceptions.ILLEGAL_ARGUMENT.get();
		}
	}
	
	public OncallPlanResult plan() {
		//TODO : 코드 최적화 및 10줄 제한 반영
		List<OncallDayPlan> oncallDayPlans = new ArrayList<>();
		int weekdayIndex = 0;
		int holidayIndex = 0;
		for (CalendarDay calendarDay : oncallCalendar.getCalendarDays()) {
			if (calendarDay.isWeekDay() && !calendarDay.isLegalHoliday()) {
				oncallDayPlans.add(new OncallDayPlan(calendarDay, weekdayEmergencyWorkers.getNameOf(weekdayIndex++)));
				continue;
			}
			oncallDayPlans.add(new OncallDayPlan(calendarDay, holidayEmergencyWorkers.getNameOf(holidayIndex++)));
		}
		handleShift(oncallDayPlans);
		return new OncallPlanResult(oncallCalendar.getMonthValue(), oncallDayPlans);
	}
	
	public static void handleShift(List<OncallDayPlan> oncallDayPlans) {
		String prevEmergencyWorkerName = "";
		for (int i = 0; i < oncallDayPlans.size(); i++) {
			String currEmergencyWorkerName = oncallDayPlans.get(i).emergencyWorkerName();
			if (prevEmergencyWorkerName.equals(currEmergencyWorkerName)) {
				switchCurrentWorkerAndNextWorker(oncallDayPlans, i);
			}
			prevEmergencyWorkerName = oncallDayPlans.get(i).emergencyWorkerName();
		}
	}
	
	private static void switchCurrentWorkerAndNextWorker(List<OncallDayPlan> oncallDayPlans, int currentIndex) {
		String currEmergencyWorkerName = oncallDayPlans.get(currentIndex).emergencyWorkerName();
		String nextEmergencyWorkerName = oncallDayPlans.get(currentIndex + 1).emergencyWorkerName();
		OncallDayPlan currPlan = oncallDayPlans.remove(currentIndex).emergencyWorkerNameOf(nextEmergencyWorkerName);
		OncallDayPlan nextPlan = oncallDayPlans.remove(currentIndex).emergencyWorkerNameOf(currEmergencyWorkerName);
		oncallDayPlans.add(currentIndex, nextPlan);
		oncallDayPlans.add(currentIndex, currPlan);
	}
}
