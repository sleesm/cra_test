package mission2;

import static mission2.enums.QuestionType.*;

class Assemble {

    private final CarManager carManager;

    public Assemble() {
        carManager = null;
    }

    public Assemble(CarManager carManager) {
        this.carManager = carManager;
    }

    int assembleAndRunOrTestCar(int step, int selectedOption) {
        if(step != Run_Test){
            assembleCar(step, selectedOption);
            delay(800);
            return step + 1;
        }else{
            runOrTestCar(selectedOption);
            delay(2000);
            return step;
        }
    }

    void assembleCar(int step, int selectedOption) {
        switch (step) {
            case CarType_Q:
                carManager.selectCarType(selectedOption);
                break;
            case Engine_Q:
                carManager.selectEngine(selectedOption);
                break;
            case BrakeSystem_Q:
                carManager.selectBrakeSystem(selectedOption);
                break;
            case SteeringSystem_Q:
                carManager.selectSteeringSystem(selectedOption);
                break;
        }
    }

    void runOrTestCar(int selectedOption) {
        if (selectedOption == 1) {
            carManager.runProducedCar();
        } else if (selectedOption == 2) {
            System.out.println("Test...");
            delay(1500);
            carManager.testProducedCar();
        }
    }

    void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
        }
    }
}

