package compution;

import model.Function;

public class FunctionCalculatorImpl implements FunctionCalculator{

    @Override
    public double getExpressionValue(Function function, double x) {
        return function.getExpression().setVariable("x", x).evaluate();
    }
}
