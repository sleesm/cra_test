package mission2;

import mission2.Questions.Question;
import mission2.enums.QuestionType;

import java.util.Scanner;

class User {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int step = QuestionType.CarType_Q;
        CarManager carManager = new CarManager();
        Assemble assemble = new Assemble(carManager);

        while (true) {
            System.out.print(QuestionType.CLEAR_SCREEN);
            System.out.flush();

            Question question = Factory.getQuestion(step);
            question.showMenu();

            System.out.print("INPUT > ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("바이바이");
                break;
            }

            int selectedOption = question.changeInputToInt(input);
            if (!question.isValidOption(selectedOption)) continue;

            if (selectedOption == 0) {
                step = question.goBackStep(step);
                continue;
            }

            step = assemble.assembleAndRunOrTestCar(step, selectedOption);

        }

        sc.close();
    }

}