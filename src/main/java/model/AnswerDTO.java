package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnswerDTO {
    double answer;
    double inaccuracy;
    int N_RANGES;
    Method method;
}
