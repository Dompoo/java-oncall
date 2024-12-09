package oncall.config;

import oncall.service.calendarService.CalendarService;
import oncall.service.calendarService.CalendarServiceRetryProxy;
import oncall.service.calendarService.DefaultCalendarService;

public class CalendarServiceConfig {
	
	private final CalendarService calendarService;
	
	public CalendarServiceConfig(RetryHandlerConfig retryHandlerConfig) {
		CalendarService proxyTarget = new DefaultCalendarService();
		this.calendarService = new CalendarServiceRetryProxy(
				proxyTarget,
				retryHandlerConfig.getRetryHandler()
		);
		
	}
	
	public CalendarService getCalendarService() {
		return calendarService;
	}
}
