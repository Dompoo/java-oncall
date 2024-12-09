package oncall.service.calendarService;

import oncall.common.dto.MonthAndDayOfWeek;
import oncall.domain.OncallCalendar;

import java.util.function.Supplier;

public class DefaultCalendarService implements CalendarService {
	
	@Override
	public OncallCalendar getCalendar(Supplier<MonthAndDayOfWeek> monthAndDayOfWeekSupplier) {
		MonthAndDayOfWeek monthAndDayOfWeek = monthAndDayOfWeekSupplier.get();
		return new OncallCalendar(monthAndDayOfWeek.month(), monthAndDayOfWeek.dayOfWeek());
	}
}
