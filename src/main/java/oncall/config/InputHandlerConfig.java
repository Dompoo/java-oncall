package oncall.config;

import oncall.config.reader.ReaderConfig;
import oncall.config.writer.WriterConfig;
import oncall.io.input.InputHandler;
import oncall.io.input.InputParser;
import oncall.io.input.InputValidator;

public class InputHandlerConfig {
	
	private final InputHandler inputHandler;
	
	public InputHandlerConfig(ReaderConfig readerConfig, WriterConfig writerConfig) {
		this.inputHandler = new InputHandler(
				readerConfig.getReader(),
				writerConfig.getWriter(),
				new InputValidator(),
				new InputParser()
		);
	}
	
	public InputHandler getInputHandler() {
		return inputHandler;
	}
}
