import compution.*;
import io.MethodIOImpl;
import model.AnswerDTO;
import model.InputDTO;

public class Main {
    public static void main(String[] args) {
        try {

            MethodIOImpl methodIO = new MethodIOImpl();
            FunctionCalculator functionCalculator = new FunctionCalculatorImpl();
            IntegralMethod rectangleMethod = new RectangleMethod(functionCalculator);
            IntegralMethod simpsonMethod = new SimpsonMethod(functionCalculator);
            IntegralMethod trapezoidMethod = new TrapezoidMethod(functionCalculator);
            InputDTO inputDTO = methodIO.inputData();
            AnswerDTO answerDTO;
            switch (inputDTO.getMethod()) {
                case SIMPSON -> {
                    answerDTO = simpsonMethod.getIntegral(inputDTO);
                }
                case RECTANGLE -> {
                    answerDTO = rectangleMethod.getIntegral(inputDTO);
                }
                case TRAPEZOID -> {
                    answerDTO = trapezoidMethod.getIntegral(inputDTO);
                }
                default -> throw new RuntimeException();
            }
            methodIO.printData(answerDTO);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
