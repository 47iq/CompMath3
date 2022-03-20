package compution;

import model.AnswerDTO;
import model.InputDTO;

public interface IntegralMethod {
    AnswerDTO getIntegral(InputDTO inputDTO) throws Exception;
}
