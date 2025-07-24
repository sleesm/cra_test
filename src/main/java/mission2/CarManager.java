package mission2;

import mission2.enums.CarInfo;

import static mission2.enums.CarInfo.*;

public class CarManager {

    CarInfo carType;
    CarInfo engine;
    CarInfo brakeSystem;
    CarInfo steeringSystem;


    public CarManager() {
    }

    public void selectSteeringSystem(int option) {
        steeringSystem = option == 1 ? BOSCH : MOBIS;
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", steeringSystem);
    }

    public void selectBrakeSystem(int option) {
        brakeSystem = option == 1 ? MANDO : option == 2 ? CONTINENTAL : BOSCH;
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", brakeSystem);
    }

    public void selectEngine(int option) {
        engine = option == 1 ? GM : option == 2 ? TOYOTA : option == 3 ? WIA : BROKEN_ENGINE;
        System.out.printf("%s 엔진을 선택하셨습니다.\n", engine);
    }

    public void selectCarType(int option) {
        carType = option == 1 ? Sedan : option == 2 ? SUV : Truck;
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", carType);
    }

    public void runProducedCar() {
        if (!isValidCheck()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }

        if (engine.equals(BROKEN_ENGINE)) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }

        System.out.printf("Car Type : %s\n", carType);
        System.out.printf("Engine   : %s\n", engine);
        System.out.printf("Brake    : %s\n", brakeSystem);
        System.out.printf("Steering : %s\n", steeringSystem);
        System.out.println("자동차가 동작됩니다.");
    }

    public void testProducedCar() {
        if (isInValidBrakeSystem()) {
            fail(carType + "에는 " + brakeSystem + "제동장치 사용 불가");
        } else if (isInValidEngine()) {
            fail(carType + "에는 " + engine + "엔진 사용 불가");
        } else if (isInValidSteeringSystem()) {
            fail(carType + "제동장치에는 " + steeringSystem + "조향장치 이외 사용 불가");
        } else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
    }

    public boolean isValidCheck() {
        if (isInValidBrakeSystem()) return false;
        if (isInValidEngine()) return false;
        if (isInValidSteeringSystem()) return false;
        return true;
    }

    public boolean isInValidSteeringSystem() {
        return carType.equals(BOSCH) && !steeringSystem.equals(BOSCH);
    }

    public boolean isInValidEngine() {
        if (carType.equals(SUV) && engine.equals(TOYOTA)) return true;
        if (carType.equals(Truck) && engine.equals(WIA)) return true;
        return false;
    }

    public boolean isInValidBrakeSystem() {
        if (carType.equals(Sedan) && brakeSystem.equals(CONTINENTAL)) return true;
        if (carType.equals(Truck) && brakeSystem.equals(MANDO)) return true;
        return false;
    }

    private static void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }
}
