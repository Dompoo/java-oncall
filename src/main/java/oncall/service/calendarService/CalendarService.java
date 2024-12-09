package oncall.service.calendarService;

import oncall.common.dto.MonthAndDayOfWeek;
import oncall.domain.OncallCalendar;

import java.util.function.Supplier;

public interface CalendarService {

	OncallCalendar getCalendar(Supplier<MonthAndDayOfWeek> monthAndDayOfWeekSupplier);
}
