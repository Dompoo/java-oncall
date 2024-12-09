package oncall.common.dto;

import java.util.List;

public record OncallPlanResult(
		int month,
		List<OncallPlanDay> oncallPlanDays
) {
}
