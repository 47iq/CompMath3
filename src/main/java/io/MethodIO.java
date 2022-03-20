package io;

import model.AnswerDTO;
import model.InputDTO;

public interface MethodIO {
    InputDTO inputData() throws Exception;
    void printData(AnswerDTO answerDTO);
}
