package oncall.controller;

import oncall.common.dto.OncallPlanResult;
import oncall.domain.OncallCalendar;
import oncall.domain.OncallPlanner;
import oncall.io.input.InputHandler;
import oncall.io.output.OutputHandler;
import oncall.service.calendarService.CalendarService;
import oncall.service.plannerService.PlannerService;

public class OncallController {
	
	private final InputHandler inputHandler;
	private final OutputHandler outputHandler;
	private final CalendarService calendarService;
	private final PlannerService plannerService;
	
	public OncallController(
			InputHandler inputHandler,
			OutputHandler outputHandler,
			CalendarService calendarService,
			PlannerService plannerService
	) {
		this.inputHandler = inputHandler;
		this.outputHandler = outputHandler;
		this.calendarService = calendarService;
		this.plannerService = plannerService;
	}
	
	public void run() {
		OncallCalendar oncallCalendar = calendarService.getCalendar(inputHandler::readMonthAndDayOfWeek);
		OncallPlanner oncallPlanner = plannerService.getPlanner(inputHandler::readWeekdayEmergencyWorkerNames, inputHandler::readHolidayEmgergencyWorkerNames, oncallCalendar);
		OncallPlanResult result = oncallPlanner.plan();
		outputHandler.writeOncallPlanResult(result);
	}
}
