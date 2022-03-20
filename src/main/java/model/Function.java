package model;

import lombok.Data;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@Data
public class Function {
    String stringExpression;
    Expression expression;

    public Function(String stringExpression) {
        this.stringExpression = stringExpression;
        this.expression = new ExpressionBuilder(stringExpression).variables("x").build();
    }
}
