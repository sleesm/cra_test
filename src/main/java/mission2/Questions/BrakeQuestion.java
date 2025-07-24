package mission2.Questions;

public class BrakeQuestion implements Question {
    @Override
    public void showMenu() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. MANDO");
        System.out.println("2. CONTINENTAL");
        System.out.println("3. BOSCH");
        System.out.println("===============================");
    }

    @Override
    public boolean isValidRange(int option) {
        if (option < 0 || option > 3) {
            System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
            return false;
        }
        return true;
    }
}
