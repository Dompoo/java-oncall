package oncall;

import oncall.config.*;
import oncall.config.reader.DefaultReaderConfig;
import oncall.config.reader.ReaderConfig;
import oncall.config.writer.DefaultWriterConfig;
import oncall.config.writer.WriterConfig;
import oncall.controller.OncallController;

public class OncallApplicatoin {
	
	private static OncallController oncallController;
	
	public static void run() {
		if (oncallController == null) {
			initController();
		}
		oncallController.run();
	}
	
	private static void initController() {
		WriterConfig writerConfig = new DefaultWriterConfig();
		RetryHandlerConfig retryHandlerConfig = new RetryHandlerConfig(writerConfig);
		oncallController = new OncallControllerConfig(
				new InputHandlerConfig(new DefaultReaderConfig(), writerConfig),
				new OutputHandlerConfig(writerConfig),
				new CalendarServiceConfig(retryHandlerConfig),
				new PlannerServiceConfig(retryHandlerConfig)
		).getOncallController();
	}
}
