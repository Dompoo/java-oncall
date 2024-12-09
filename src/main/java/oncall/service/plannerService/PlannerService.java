package oncall.service.plannerService;

import oncall.domain.OncallCalendar;
import oncall.domain.OncallPlanner;

import java.util.List;
import java.util.function.Supplier;

public interface PlannerService {
	
	OncallPlanner getPlanner(
			Supplier<List<String>> weekdayEmergencyWorkerNamesSupplier,
			Supplier<List<String>> holidayEmergencyWorkerNamesSupplier,
			OncallCalendar oncallCalendar
	);
}
