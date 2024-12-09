package oncall.config;

import oncall.controller.OncallController;

public class OncallControllerConfig {
	
	private final OncallController oncallController;
	
	public OncallControllerConfig(
			InputHandlerConfig inputHandlerConfig,
			OutputHandlerConfig outputHandlerConfig,
			CalendarServiceConfig calendarServiceConfig,
			PlannerServiceConfig plannerServiceConfig
	) {
		this.oncallController = new OncallController(
				inputHandlerConfig.getInputHandler(),
				outputHandlerConfig.getOutputHandler(),
				calendarServiceConfig.getCalendarService(),
				plannerServiceConfig.getPlannerService()
		);
	}
	
	public OncallController getOncallController() {
		return oncallController;
	}
}
