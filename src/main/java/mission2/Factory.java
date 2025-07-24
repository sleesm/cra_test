package mission2;

import mission2.Questions.*;
import mission2.enums.QuestionType;

import static mission2.enums.QuestionType.*;
import static mission2.enums.QuestionType.Run_Test;

public class Factory {
    static Question getQuestion(int step) {
        Question question = new CarTypeQuestion();
        if (step == CarType_Q)
            question = new CarTypeQuestion();
        if (step == Engine_Q)
            question = new EngineQuestion();
        if (step == BrakeSystem_Q)
            question = new BrakeQuestion();
        if (step == SteeringSystem_Q)
            question = new SteeringQuestion();
        if(step == Run_Test)
            question = new RunTestQuestion();
        return question;
    }
}
