package oncall.domain;

import oncall.common.dto.CalendarDay;
import oncall.common.dto.OncallDayPlan;
import oncall.common.dto.OncallPlanResult;
import oncall.common.exception.CustomExceptions;

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
	
	/*
	평일-휴일 연속이면 다음 휴일 근무자와 교체
	휴일-평일 연속이면 다음 평일 근무자와 교체
	 */
	public static void handleShift(List<OncallDayPlan> oncallDayPlans) {
		String prevEmergencyWorkerName = "";
		for (int i = 0; i < oncallDayPlans.size(); i++) {
			String currEmergencyWorkerName = oncallDayPlans.get(i).emergencyWorkerName();
			if (prevEmergencyWorkerName.equals(currEmergencyWorkerName)) {
				if (oncallDayPlans.get(i).calendarDay().isWeekDay()) {
					//다음 평일 근무자와 교체
					switchWithNextWeekDayWorker(oncallDayPlans, i);
				}
				//다음 휴일 근무자와 교체
				switchWithNextHolidayWorker(oncallDayPlans, i);
			}
			prevEmergencyWorkerName = oncallDayPlans.get(i).emergencyWorkerName();
		}
	}
	
	private static void switchWithNextHolidayWorker(List<OncallDayPlan> oncallDayPlans, int curr) {
		for (int i = curr + 1; i < oncallDayPlans.size(); i++) {
			if (!oncallDayPlans.get(i).calendarDay().isWeekDay()) {
				switchWork(oncallDayPlans, curr, i);
				return;
			}
		}
	}
	
	private static void switchWithNextWeekDayWorker(List<OncallDayPlan> oncallDayPlans, int curr) {
		for (int i = curr + 1; i < oncallDayPlans.size(); i++) {
			if (oncallDayPlans.get(i).calendarDay().isWeekDay()) {
				switchWork(oncallDayPlans, curr, i);
				return;
			}
		}
	}
	
	private static void switchWork(List<OncallDayPlan> oncallDayPlans, int curr, int next) {
		String currEmergencyWorkerName = oncallDayPlans.get(curr).emergencyWorkerName();
		String nextEmergencyWorkerName = oncallDayPlans.get(next).emergencyWorkerName();
		OncallDayPlan newNextPlan = oncallDayPlans.remove(next).emergencyWorkerNameOf(currEmergencyWorkerName);
		OncallDayPlan newCurrPlan = oncallDayPlans.remove(curr).emergencyWorkerNameOf(nextEmergencyWorkerName);
		oncallDayPlans.add(next, newNextPlan);
		oncallDayPlans.add(curr, newCurrPlan);
	}
}
