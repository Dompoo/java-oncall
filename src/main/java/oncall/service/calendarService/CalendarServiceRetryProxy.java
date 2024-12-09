package oncall.service.calendarService;

import oncall.common.RetryHandler;
import oncall.common.dto.MonthAndDayOfWeek;
import oncall.domain.OncallCalendar;

import java.util.function.Supplier;

public class CalendarServiceRetryProxy implements CalendarService {
	
	private final CalendarService calendarService;
	private final RetryHandler retryHandler;
	
	public CalendarServiceRetryProxy(CalendarService calendarService, RetryHandler retryHandler) {
		this.calendarService = calendarService;
		this.retryHandler = retryHandler;
	}
	
	@Override
	public OncallCalendar getCalendar(Supplier<MonthAndDayOfWeek> monthAndDayOfWeekSupplier) {
		return retryHandler.tryUntilSuccess(() -> calendarService.getCalendar(monthAndDayOfWeekSupplier));
	}
}
