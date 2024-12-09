package oncall.io.writer;

public class ConsoleWriter implements Writer {
	
	@Override
	public void write(String value) {
		System.out.print(value);
	}
}
