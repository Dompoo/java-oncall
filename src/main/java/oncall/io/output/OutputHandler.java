package oncall.io.output;

import oncall.common.dto.OncallPlanResult;
import oncall.io.writer.Writer;

public class OutputHandler {
	
	private final Writer writer;
	private final OutputParser outputParser;
	
	public OutputHandler(Writer writer, OutputParser outputParser) {
		this.writer = writer;
		this.outputParser = outputParser;
	}
	
	public void writeOncallPlanResult(OncallPlanResult oncallPlanResult) {
		String result = outputParser.parseOncallPlanResult(oncallPlanResult);
		writer.write(result);
	}
}
