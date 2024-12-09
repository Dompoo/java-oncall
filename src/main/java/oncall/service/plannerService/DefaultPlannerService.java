package oncall.service.plannerService;

import oncall.common.dto.OncallPlanResult;
import oncall.domain.EmergencyWorkers;
import oncall.domain.OncallCalendar;
import oncall.domain.OncallPlanner;

import java.util.List;
import java.util.function.Supplier;

public class DefaultPlannerService implements PlannerService {
	
	@Override
	public OncallPlanner getPlanner(
			Supplier<List<String>> weekdayEmergencyWorkerNamesSupplier,
			Supplier<List<String>> holidayEmergencyWorkerNamesSupplier,
			OncallCalendar oncallCalendar
	) {
		List<String> weekdayEmergencyWorkerNames = weekdayEmergencyWorkerNamesSupplier.get();
		List<String> holidayEmergencyWorkerNames = holidayEmergencyWorkerNamesSupplier.get();
		return new OncallPlanner(
				oncallCalendar,
				EmergencyWorkers.from(weekdayEmergencyWorkerNames),
				EmergencyWorkers.from(holidayEmergencyWorkerNames)
		);
	}
}
