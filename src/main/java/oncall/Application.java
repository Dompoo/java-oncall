package oncall;

import oncall.common.RetryHandler;
import oncall.common.exception.ExceptionHandler;
import oncall.controller.OncallController;
import oncall.io.input.InputHandler;
import oncall.io.input.InputParser;
import oncall.io.input.InputValidator;
import oncall.io.output.OutputHandler;
import oncall.io.output.OutputParser;
import oncall.io.reader.ConsoleReader;
import oncall.io.writer.ConsoleWriter;
import oncall.io.writer.Writer;

public class Application {
    public static void main(String[] args) {
        OncallApplicatoin.run();
    }
}
