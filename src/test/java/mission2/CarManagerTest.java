package mission2;

import mission2.enums.CarInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static mission2.enums.CarInfo.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CarManagerTest {

    @Spy
    CarManager carManager;

    @Test
    void 차량타입선택() {
        carManager.selectCarType(1);
        CarInfo ret = carManager.carType;

        assertThat(ret).isEqualTo(Sedan);

        carManager.selectCarType(2);
        ret = carManager.carType;

        assertThat(ret).isEqualTo(SUV);

        carManager.selectCarType(3);
        ret = carManager.carType;

        assertThat(ret).isEqualTo(Truck);
    }

    @Test
    void 엔진선택() {

        carManager.selectEngine(1);
        CarInfo ret = carManager.engine;

        assertThat(ret).isEqualTo(GM);

        carManager.selectEngine(2);
        ret = carManager.engine;

        assertThat(ret).isEqualTo(TOYOTA);

        carManager.selectEngine(3);
        ret = carManager.engine;

        assertThat(ret).isEqualTo(WIA);


        carManager.selectEngine(4);
        ret = carManager.engine;

        assertThat(ret).isEqualTo(BROKEN_ENGINE);
    }

    @Test
    void 제동장치선택() {

        carManager.selectBrakeSystem(1);
        CarInfo ret = carManager.brakeSystem;

        assertThat(ret).isEqualTo(MANDO);

        carManager.selectBrakeSystem(2);
        ret = carManager.brakeSystem;

        assertThat(ret).isEqualTo(CONTINENTAL);

        carManager.selectBrakeSystem(3);
        ret = carManager.brakeSystem;

        assertThat(ret).isEqualTo(BOSCH);
    }

    @Test
    void 조향장치선택() {

        carManager.selectSteeringSystem(1);
        CarInfo ret = carManager.steeringSystem;

        assertThat(ret).isEqualTo(BOSCH);

        carManager.selectSteeringSystem(2);
        ret = carManager.steeringSystem;

        assertThat(ret).isEqualTo(MOBIS);
    }

    @Test
    void 자동차실행_타입_유효_확인1() {
        carManager.carType = Sedan;
        carManager.brakeSystem = CONTINENTAL;

        carManager.runProducedCar();

        verify(carManager).isValidCheck();
    }

    @Test
    void 자동차실행_타입_유효_확인2() {

        carManager.carType = Truck;
        carManager.brakeSystem = MANDO;

        carManager.runProducedCar();

        verify(carManager).isValidCheck();
    }

    @Test
    void 자동차실행_타입_유효_확인3() {
        carManager.carType = BOSCH;
        carManager.brakeSystem = MANDO;
        carManager.steeringSystem = BOSCH;
        carManager.engine = BROKEN_ENGINE;

        carManager.runProducedCar();

        verify(carManager).isValidCheck();
    }

    @Test
    void 자동차실행_엔진_고장_확인() {
        carManager.carType = BOSCH;
        carManager.steeringSystem = BOSCH;
        carManager.engine = BROKEN_ENGINE;
        carManager.brakeSystem = CONTINENTAL;

        carManager.runProducedCar();

        verify(carManager).runProducedCar();
    }

    @Test
    void 자동차실행_성공_확인() {
        carManager.carType = BOSCH;
        carManager.steeringSystem = BOSCH;
        carManager.engine = TOYOTA;
        carManager.brakeSystem = CONTINENTAL;

        carManager.runProducedCar();

        verify(carManager).runProducedCar();
    }


    @Test
    void 자동차테스트_타입_유효_확인() {
        carManager.carType = Sedan;
        carManager.brakeSystem = CONTINENTAL;

        carManager.testProducedCar();

        verify(carManager).isInValidBrakeSystem();
    }

    @Test
    void 자동차테스트_타입_유효_확인2() {
        carManager.carType = Truck;
        carManager.brakeSystem = MANDO;

        carManager.testProducedCar();

        verify(carManager).isInValidBrakeSystem();
    }

    @Test
    void 자동차테스트_엔진_유효_확인() {
        carManager.carType = SUV;
        carManager.engine = TOYOTA;

        carManager.testProducedCar();

        verify(carManager).isInValidEngine();
    }

    @Test
    void 자동차테스트_엔진_유효_확인2() {
        carManager.brakeSystem = BOSCH;
        carManager.steeringSystem = BOSCH;
        carManager.carType = Truck;
        carManager.engine = WIA;

        carManager.testProducedCar();

        verify(carManager).isInValidEngine();
    }

    @Test
    void 자동차테스트_조향_유효_확인() {
        carManager.carType = BOSCH;
        carManager.steeringSystem = BOSCH;
        carManager.engine = TOYOTA;
        carManager.brakeSystem = MANDO;

        carManager.testProducedCar();

        verify(carManager).isInValidSteeringSystem();
    }

    @Test
    void 자동차테스트_조향_유효_확인2() {
        carManager.carType = BOSCH;
        carManager.steeringSystem = MOBIS;
        carManager.engine = TOYOTA;
        carManager.brakeSystem = MANDO;

        carManager.testProducedCar();

        verify(carManager).isInValidSteeringSystem();
    }
}