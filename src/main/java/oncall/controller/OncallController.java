package oncall.controller;

import oncall.common.RetryHandler;
import oncall.common.dto.MonthAndDayOfWeek;
import oncall.common.dto.OncallPlanResult;
import oncall.domain.EmergencyWorkers;
import oncall.domain.OncallCalendar;
import oncall.domain.OncallPlanner;
import oncall.io.input.InputHandler;
import oncall.io.output.OutputHandler;

import java.util.List;

public class OncallController {
	
	private final InputHandler inputHandler;
	private final OutputHandler outputHandler;
	private final RetryHandler retryHandler;
	
	public OncallController(InputHandler inputHandler, OutputHandler outputHandler, RetryHandler retryHandler) {
		this.inputHandler = inputHandler;
		this.outputHandler = outputHandler;
		this.retryHandler = retryHandler;
	}
	
	public void run() {
		OncallCalendar oncallCalendar = retryHandler.tryUntilSuccess(() -> {
			MonthAndDayOfWeek monthAndDayOfWeek = inputHandler.readMonthAndDayOfWeek();
			return new OncallCalendar(monthAndDayOfWeek.month(), monthAndDayOfWeek.dayOfWeek());
		});
		
		OncallPlanner oncallPlanner = retryHandler.tryUntilSuccess(() -> {
			List<String> weekdayEmergencyWorkerNames = inputHandler.readWeekdayEmergencyWorkerNames();
			List<String> holidayEmgergencyWorkerNames = inputHandler.readHolidayEmgergencyWorkerNames();
			return new OncallPlanner(
					oncallCalendar,
					EmergencyWorkers.from(weekdayEmergencyWorkerNames),
					EmergencyWorkers.from(holidayEmgergencyWorkerNames)
			);
		});
		
		OncallPlanResult result = oncallPlanner.plan();
		outputHandler.writeOncallPlanResult(result);
	}
}
