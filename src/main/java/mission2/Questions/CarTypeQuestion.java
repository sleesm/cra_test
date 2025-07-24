package mission2.Questions;

public class CarTypeQuestion implements Question {
    @Override
    public void showMenu() {
        System.out.println("        ______________");
        System.out.println("       /|            |");
        System.out.println("  ____/_|_____________|____");
        System.out.println(" |                      O  |");
        System.out.println(" '-(@)----------------(@)--'");
        System.out.println("===============================");
        System.out.println("어떤 차량 타입을 선택할까요?");
        System.out.println("1. Sedan");
        System.out.println("2. SUV");
        System.out.println("3. Truck");
        System.out.println("===============================");
    }

    @Override
    public boolean isValidRange(int option) {
        if (option < 1 || option > 3) {
            System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
            return false;
        }
        return true;
    }
}
