package oncall.config;

import oncall.common.RetryHandler;
import oncall.common.exception.ExceptionHandler;
import oncall.config.writer.WriterConfig;

public class RetryHandlerConfig {
	
	private final RetryHandler retryHandler;
	
	public RetryHandlerConfig(WriterConfig writerConfig) {
		this.retryHandler = new RetryHandler(new ExceptionHandler(writerConfig.getWriter()));
	}
	
	public RetryHandler getRetryHandler() {
		return retryHandler;
	}
}
