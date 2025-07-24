package mission1;

import java.util.Scanner;

class Assemble {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";

    private static final int CarType_Q = 0;
    private static final int Engine_Q = 1;
    private static final int BrakeSystem_Q = 2;
    private static final int SteeringSystem_Q = 3;
    private static final int Run_Test = 4;

    private static final int SEDAN = 1, SUV = 2, TRUCK = 3;
    private static final int GM = 1, TOYOTA = 2, WIA = 3;
    private static final int MANDO = 1, CONTINENTAL = 2, BOSCH_B = 3;
    private static final int BOSCH_S = 1, MOBIS = 2;
    public static final int WRONG_ANSWER_FORMAT = -1;

    private static int[] car = new int[5];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int step = CarType_Q;

        while (true) {
            System.out.print(CLEAR_SCREEN);
            System.out.flush();

            showMenuByStep(step);

            System.out.print("INPUT > ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("바이바이");
                break;
            }

            int selectedOption = changeInputToInt(input);

            if (selectedOption == WRONG_ANSWER_FORMAT) continue;
            if (!isValidRange(step, selectedOption)) {
                delay(800);
                continue;
            }
            if (selectedOption == 0) {
                step = goBackStep(step);
                continue;
            }

            if(step == Run_Test){
                runOrTestCar(selectedOption);
                continue;
            }

            step = setupCar(step, selectedOption);

        }

        sc.close();
    }

    private static void showMenuByStep(int step) {
        switch (step) {
            case CarType_Q:
                showCarTypeMenu();
                break;
            case Engine_Q:
                showEngineMenu();
                break;
            case BrakeSystem_Q:
                showBrakeMenu();
                break;
            case SteeringSystem_Q:
                showSteeringMenu();
                break;
            case Run_Test:
                showRunTestMenu();
                break;
        }
    }

    private static void showCarTypeMenu() {
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

    private static void showEngineMenu() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. GM");
        System.out.println("2. TOYOTA");
        System.out.println("3. WIA");
        System.out.println("4. 고장난 엔진");
        System.out.println("===============================");
    }

    private static void showBrakeMenu() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. MANDO");
        System.out.println("2. CONTINENTAL");
        System.out.println("3. BOSCH");
        System.out.println("===============================");
    }

    private static void showSteeringMenu() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. BOSCH");
        System.out.println("2. MOBIS");
        System.out.println("===============================");
    }

    private static void showRunTestMenu() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("===============================");
    }

    private static boolean isValidRange(int step, int option) {
        switch (step) {
            case CarType_Q:
                if (option < 1 || option > 3) {
                    System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case Engine_Q:
                if (option < 0 || option > 4) {
                    System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
                    return false;
                }
                break;
            case BrakeSystem_Q:
                if (option < 0 || option > 3) {
                    System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case SteeringSystem_Q:
                if (option < 0 || option > 2) {
                    System.out.println("ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능");
                    return false;
                }
                break;
            case Run_Test:
                if (option < 0 || option > 2) {
                    System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
                    return false;
                }
                break;
        }
        return true;
    }

    private static int changeInputToInt(String buf) {
        int answer;
        try {
            answer = Integer.parseInt(buf);
        } catch (NumberFormatException e) {
            System.out.println("ERROR :: 숫자만 입력 가능");
            delay(800);
            return WRONG_ANSWER_FORMAT;
        }
        return answer;
    }

    private static boolean isValidCheck() {
        if (car[CarType_Q] == SEDAN && car[BrakeSystem_Q] == CONTINENTAL) return false;
        if (car[CarType_Q] == SUV && car[Engine_Q] == TOYOTA) return false;
        if (car[CarType_Q] == TRUCK && car[Engine_Q] == WIA) return false;
        if (car[CarType_Q] == TRUCK && car[BrakeSystem_Q] == MANDO) return false;
        if (car[BrakeSystem_Q] == BOSCH_B && car[SteeringSystem_Q] != BOSCH_S) return false;
        return true;
    }

    private static int goBackStep(int step) {
        if (step == Run_Test) {
            step = CarType_Q;
        } else if (step > CarType_Q) {
            step--;
        }
        return step;
    }

    private static int setupCar(int step, int selectedOption) {
        switch (step) {
            case CarType_Q:
                selectCarType(selectedOption);
                delay(800);
                step = Engine_Q;
                break;
            case Engine_Q:
                selectEngine(selectedOption);
                delay(800);
                step = BrakeSystem_Q;
                break;
            case BrakeSystem_Q:
                selectBrakeSystem(selectedOption);
                delay(800);
                step = SteeringSystem_Q;
                break;
            case SteeringSystem_Q:
                selectSteeringSystem(selectedOption);
                delay(800);
                step = Run_Test;
                break;
        }
        return step;
    }

    private static void selectCarType(int option) {
        car[CarType_Q] = option;
        String carType = option == 1 ? "Sedan" : option == 2 ? "SUV" : "Truck";
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", carType);
    }

    private static void selectEngine(int option) {
        car[Engine_Q] = option;
        String engine = option == 1 ? "GM" : option == 2 ? "TOYOTA" : option == 3 ? "WIA" : "고장난 엔진";
        System.out.printf("%s 엔진을 선택하셨습니다.\n", engine);
    }

    private static void selectBrakeSystem(int option) {
        car[BrakeSystem_Q] = option;
        String brakeSystem = option == 1 ? "MANDO" : option == 2 ? "CONTINENTAL" : "BOSCH";
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", brakeSystem);
    }

    private static void selectSteeringSystem(int option) {
        car[SteeringSystem_Q] = option;
        String steeringSystem = option == 1 ? "BOSCH" : "MOBIS";
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", steeringSystem);
    }

    private static void runOrTestCar(int selectedOption) {
        if (selectedOption == 1) {
            runProducedCar();
            delay(2000);
        } else if (selectedOption == 2) {
            System.out.println("Test...");
            delay(1500);
            testProducedCar();
            delay(2000);
        }
    }

    private static void runProducedCar() {
        if (!isValidCheck()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }

        if (car[Engine_Q] == 4) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }

        String[] carNames = {"", "Sedan", "SUV", "Truck"};
        String[] engNames = {"", "GM", "TOYOTA", "WIA"};
        System.out.printf("Car Type : %s\n", carNames[car[CarType_Q]]);
        System.out.printf("Engine   : %s\n", engNames[car[Engine_Q]]);
        System.out.printf("Brake    : %s\n",
                car[BrakeSystem_Q] == 1 ? "Mando" :
                        car[BrakeSystem_Q] == 2 ? "Continental" : "Bosch");
        System.out.printf("Steering : %s\n",
                car[SteeringSystem_Q] == 1 ? "Bosch" : "Mobis");
        System.out.println("자동차가 동작됩니다.");
    }

    private static void testProducedCar() {
        if (car[CarType_Q] == SEDAN && car[BrakeSystem_Q] == CONTINENTAL) {
            fail("Sedan에는 Continental제동장치 사용 불가");
        } else if (car[CarType_Q] == SUV && car[Engine_Q] == TOYOTA) {
            fail("SUV에는 TOYOTA엔진 사용 불가");
        } else if (car[CarType_Q] == TRUCK && car[Engine_Q] == WIA) {
            fail("Truck에는 WIA엔진 사용 불가");
        } else if (car[CarType_Q] == TRUCK && car[BrakeSystem_Q] == MANDO) {
            fail("Truck에는 Mando제동장치 사용 불가");
        } else if (car[BrakeSystem_Q] == BOSCH_B && car[SteeringSystem_Q] != BOSCH_S) {
            fail("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
        } else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
    }

    private static void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }

    private static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}