package oncall.config;

import oncall.config.writer.WriterConfig;
import oncall.io.output.OutputHandler;
import oncall.io.output.OutputParser;

public class OutputHandlerConfig {
	
	private final OutputHandler outputHandler;
	
	public OutputHandlerConfig(WriterConfig writerConfig) {
		this.outputHandler = new OutputHandler(
				writerConfig.getWriter(),
				new OutputParser()
		);
	}
	
	public OutputHandler getOutputHandler() {
		return outputHandler;
	}
}
