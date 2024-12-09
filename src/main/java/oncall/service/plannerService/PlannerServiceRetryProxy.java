package oncall.service.plannerService;

import oncall.common.RetryHandler;
import oncall.common.dto.OncallPlanResult;
import oncall.domain.OncallCalendar;
import oncall.domain.OncallPlanner;

import java.util.List;
import java.util.function.Supplier;

public class PlannerServiceRetryProxy implements PlannerService {
	
	private final PlannerService plannerService;
	private final RetryHandler retryHandler;
	
	public PlannerServiceRetryProxy(PlannerService plannerService, RetryHandler retryHandler) {
		this.plannerService = plannerService;
		this.retryHandler = retryHandler;
	}
	
	@Override
	public OncallPlanner getPlanner(
			Supplier<List<String>> weekdayEmergencyWorkerNamesSupplier,
			Supplier<List<String>> holidayEmergencyWorkerNamesSupplier,
			OncallCalendar oncallCalendar
	) {
		return retryHandler.tryUntilSuccess(() -> plannerService.getPlanner(
				weekdayEmergencyWorkerNamesSupplier,
				holidayEmergencyWorkerNamesSupplier,
				oncallCalendar
		));
	}
}
