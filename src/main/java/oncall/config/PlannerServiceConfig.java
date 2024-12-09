package oncall.config;

import oncall.service.plannerService.DefaultPlannerService;
import oncall.service.plannerService.PlannerService;
import oncall.service.plannerService.PlannerServiceRetryProxy;

public class PlannerServiceConfig {
	
	private final PlannerService plannerService;
	
	public PlannerServiceConfig(RetryHandlerConfig retryHandlerConfig) {
		PlannerService proxyTarget = new DefaultPlannerService();
		this.plannerService = new PlannerServiceRetryProxy(
				proxyTarget,
				retryHandlerConfig.getRetryHandler()
		);
	}
	
	public PlannerService getPlannerService() {
		return plannerService;
	}
}
