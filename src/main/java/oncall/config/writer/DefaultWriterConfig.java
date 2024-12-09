package oncall.config.writer;

import oncall.io.writer.ConsoleWriter;
import oncall.io.writer.Writer;

public class DefaultWriterConfig implements WriterConfig {
	
	private final Writer writer;
	
	public DefaultWriterConfig() {
		this.writer = new ConsoleWriter();
	}
	
	@Override
	public Writer getWriter() {
		return writer;
	}
}
