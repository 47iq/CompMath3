package compution;

import model.AnswerDTO;
import model.InputDTO;
import model.Method;
import model.RectangleMethodType;

public class RectangleMethod implements IntegralMethod {

    final int MAX_N_RANGES = 10000000;
    int N_RANGES = 4;

    FunctionCalculator functionCalculator;

    public RectangleMethod(FunctionCalculator functionCalculator) {
        this.functionCalculator = functionCalculator;
    }

    @Override
    public AnswerDTO getIntegral(InputDTO inputDTO) throws Exception {
        double left = inputDTO.getLeftBound(), right = inputDTO.getRightBound();
        double h, x, eps = Double.MAX_VALUE, sum = 0, prevSum;
        boolean firstIteration = true;
        while (N_RANGES < MAX_N_RANGES && eps > inputDTO.getInaccuracy()) {
            prevSum = sum;
            sum = 0;
            h = Math.abs(left - right) / N_RANGES;
            for (int i = 0; i < N_RANGES; i++) {
                x = left + getPointByType(inputDTO.getType(), h, i);
                sum += functionCalculator.getExpressionValue(inputDTO.getFunction(), x) * h;
            }
            N_RANGES = N_RANGES * 2;
            if(!firstIteration)
                eps = Math.abs(sum - prevSum) / 3;
            firstIteration = false;
        }
        if(N_RANGES >= MAX_N_RANGES)
            throw new Exception("Ошибка. Точность не достигнута за максимальное число операций.");
        return new AnswerDTO(sum, eps, N_RANGES, Method.RECTANGLE);
    }

    private double getPointByType(RectangleMethodType type, double h, int i) {
        switch (type) {
            case LEFT -> {
                return h * i;
            }
            case RIGHT -> {
                return h * (i + 1);
            }
            default -> {
                return h / 2 + h * i;
            }
        }
    }
}
