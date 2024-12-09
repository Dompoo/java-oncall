package oncall.io.output;

import oncall.common.dto.OncallDayPlan;
import oncall.common.dto.OncallPlanResult;

public class OutputParser {
	
	public String parseOncallPlanResult(OncallPlanResult oncallPlanResult) {
		StringBuilder stringBuilder = new StringBuilder();
		for (OncallDayPlan oncallDayPlan : oncallPlanResult.oncallDayPlans()) {
			stringBuilder.append(parseOncallDayPlan(oncallPlanResult, oncallDayPlan)).append("\n");
		}
		return stringBuilder.toString();
	}
	
	private static String parseOncallDayPlan(OncallPlanResult oncallPlanResult, OncallDayPlan oncallDayPlan) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(oncallPlanResult.month()).append("월 ");
		stringBuilder.append(oncallDayPlan.calendarDay().day()).append("일 ");
		stringBuilder.append(oncallDayPlan.calendarDay().dayOfWeek().name());
		if (oncallDayPlan.calendarDay().isLegalHoliday() && oncallDayPlan.calendarDay().isWeekDay()) {
			stringBuilder.append("(휴일)");
		}
		stringBuilder.append(" ").append(oncallDayPlan.emergencyWorkerName());
		return stringBuilder.toString();
	}
}
