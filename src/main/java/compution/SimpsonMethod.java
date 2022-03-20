package compution;

import model.AnswerDTO;
import model.InputDTO;
import model.Method;

public class SimpsonMethod implements IntegralMethod {

    final int MAX_N_RANGES = 10000000;
    int N_RANGES = 4;

    FunctionCalculator functionCalculator;

    public SimpsonMethod(FunctionCalculator functionCalculator) {
        this.functionCalculator = functionCalculator;
    }

    @Override
    public AnswerDTO getIntegral(InputDTO inputDTO) throws Exception {
        double left = inputDTO.getLeftBound(), right = inputDTO.getRightBound();
        double h, x = 0, eps = Double.MAX_VALUE, sum = 0, prevSum;
        boolean firstIteration = true;
        while (N_RANGES < MAX_N_RANGES && eps > inputDTO.getInaccuracy()) {
            h = Math.abs(left - right) / N_RANGES;
            prevSum = sum;
            sum = (functionCalculator.getExpressionValue(inputDTO.getFunction(), left) +
                    functionCalculator.getExpressionValue(inputDTO.getFunction(), right));
            for (int i = 1; i < N_RANGES - 1; i++) {
                x = left + i * h;
                if(i % 2 == 0)
                    sum += 2 * functionCalculator.getExpressionValue(inputDTO.getFunction(), x);
                else
                    sum += 4 * functionCalculator.getExpressionValue(inputDTO.getFunction(), x);
            }
            sum *= h / 3;
            N_RANGES = N_RANGES * 2;
            if(!firstIteration)
                eps = Math.abs(sum - prevSum) / 7;
            firstIteration = false;
        }
        if(N_RANGES >= MAX_N_RANGES)
            throw new Exception("Ошибка. Точность не достигнута за максимальное число операций.");
        return new AnswerDTO(sum, eps, N_RANGES, Method.SIMPSON);
    }
}
