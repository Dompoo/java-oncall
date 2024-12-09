package oncall.config.reader;

import oncall.io.reader.ConsoleReader;
import oncall.io.reader.Reader;

public class DefaultReaderConfig implements ReaderConfig {
	
	private final Reader reader;
	
	public DefaultReaderConfig() {
		this.reader = new ConsoleReader();
	}
	
	@Override
	public Reader getReader() {
		return reader;
	}
}
