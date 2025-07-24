package mission2.Questions;

public class RunTestQuestion implements Question {
    @Override
    public void showMenu() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("===============================");
    }

    @Override
    public boolean isValidRange(int option) {
        if (option < 0 || option > 2) {
            System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
            return false;
        }
        return true;
    }
}
