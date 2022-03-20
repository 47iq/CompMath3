package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InputDTO {
    double inaccuracy;
    Function function;
    double leftBound;
    double rightBound;
    Method method;
    RectangleMethodType type;
}
