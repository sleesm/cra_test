package mission2.Questions;

import static mission2.enums.QuestionType.*;

public interface Question {
    public void showMenu();

    public boolean isValidRange(int option);

    public default boolean isValidOption(int option) {
        if (option == WRONG_ANSWER_FORMAT)
            return false;
        if (!isValidRange(option)) {
            delay(800);
            return false;
        }
        return true;
    }

    public default int goBackStep(int step) {
        if (step == Run_Test) {
            return CarType_Q;
        } else if (step > CarType_Q) {
            return step - 1;
        }
        return step;
    }

    public default int changeInputToInt(String option) {
        int selectedOption;
        try {
            selectedOption = Integer.parseInt(option);
        } catch (NumberFormatException e) {
            System.out.println("ERROR :: 숫자만 입력 가능");
            delay(800);
            return WRONG_ANSWER_FORMAT;
        }
        return selectedOption;
    }

    public default void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}
