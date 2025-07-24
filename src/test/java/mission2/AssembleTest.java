package mission2;

import mission2.Questions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import static mission2.enums.QuestionType.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssembleTest {
    private ByteArrayOutputStream outputStreamCaptor;
    private PrintStream standardOut; // 표준 스트림

    @Spy
    Question question;
    @Mock
    CarManager carManager;
    @InjectMocks
    @Spy
    Assemble assemble;

    protected void systemIn(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    protected String getOutput() {
        return outputStreamCaptor.toString();
    }

    static Stream<Question> optionProvider() {
        return Stream.of(
                new CarTypeQuestion(), new EngineQuestion(), new BrakeQuestion(), new SteeringQuestion(), new RunTestQuestion()
        );
    }

    static Stream<Question> inputProvider() {
        return Stream.of(
                spy(CarTypeQuestion.class), spy(EngineQuestion.class), spy(BrakeQuestion.class), spy(SteeringQuestion.class), spy(RunTestQuestion.class)
        );
    }

    @BeforeEach
    void setUp() {
        standardOut = System.out; // 표준 스트림 초기화
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @ParameterizedTest
    @MethodSource("inputProvider")
    void Menu(Question selected){
        question = selected;

        selected.showMenu();


        verify(question).showMenu();

    }

    @Test
    void 차종_옵션_fail() {
        question = new CarTypeQuestion();

        boolean result = question.isValidOption(4);

        assertThat(result).isEqualTo(false);
    }

    @Test
    void 차종_옵션_success() {
        question = new CarTypeQuestion();

        boolean result = question.isValidOption(1);

        assertThat(result).isEqualTo(true);
    }

    @Test
    void 엔진_옵션_fail() {
        question = new EngineQuestion();

        boolean result = question.isValidOption(-1);

        assertThat(result).isEqualTo(false);
    }

    @Test
    void 엔진_옵션_success() {
        question = new EngineQuestion();

        boolean result = question.isValidOption(4);

        assertThat(result).isEqualTo(true);
    }

    @Test
    void 제동장치_옵션_fail() {
        question = new BrakeQuestion();

        boolean result = question.isValidOption(-1);

        assertThat(result).isEqualTo(false);
    }

    @Test
    void 제동장치_옵션_success() {
        question = new BrakeQuestion();

        boolean result = question.isValidOption(3);

        assertThat(result).isEqualTo(true);
    }

    @Test
    void 조향_옵션_fail() {
        question = new SteeringQuestion();

        boolean result = question.isValidOption(-1);

        assertThat(result).isEqualTo(false);
    }

    @Test
    void 조향_옵션_success() {
        question = new SteeringQuestion();

        boolean result = question.isValidOption(2);

        assertThat(result).isEqualTo(true);
    }

    @Test
    void option변경() {
        int ret = question.changeInputToInt("0");

        assertThat(ret).isEqualTo(0);
    }

    @Test
    void option변경_num오류() {
        int ret = question.changeInputToInt("n");

        assertThat(ret).isEqualTo(WRONG_ANSWER_FORMAT);
    }

    @ParameterizedTest
    @MethodSource("optionProvider")
    void 유효한Option(Question q) {
        question = q;
        boolean ret = question.isValidOption(1);

        assertThat(ret).isEqualTo(true);
    }

    @ParameterizedTest
    @MethodSource("optionProvider")
    void 유효하지않은Option(Question q) {
        question = q;
        boolean ret = question.isValidOption(-1);

        assertThat(ret).isEqualTo(false);
    }

    @ParameterizedTest
    @MethodSource("optionProvider")
    void 유효하지않은Option_2(Question q) {
        question = q;
        boolean ret = question.isValidOption(6);

        assertThat(ret).isEqualTo(false);
    }


    @Test
    void 뒤로가기_Run_Test() {
        int step = Run_Test;
        int expected = CarType_Q;

        int result = question.goBackStep(step);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 뒤로가기_Others() {
        int step = SteeringSystem_Q;
        int expected = BrakeSystem_Q;

        int result = question.goBackStep(step);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 뒤로가기_안함() {
        int step = CarType_Q;
        int expected = CarType_Q;

        int result = question.goBackStep(step);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void 차조립하기_실패(){
        assemble.assembleAndRunOrTestCar(-1, 1);

        verify(assemble).assembleCar(-1,1);
    }

    @Test
    void 차조립하기_타입별(){
        assemble.assembleAndRunOrTestCar(CarType_Q, 1);

        verify(assemble).assembleCar(CarType_Q,1);
        verify(carManager).selectCarType(anyInt());
    }

    @Test
    void 차조립하기_엔진별(){
        assemble.assembleAndRunOrTestCar(Engine_Q, 1);

        verify(assemble).assembleCar(Engine_Q,1);
        verify(carManager).selectEngine(anyInt());
    }

    @Test
    void 차조립하기_제동기기별(){
        assemble.assembleAndRunOrTestCar(BrakeSystem_Q, 1);

        verify(assemble).assembleCar(BrakeSystem_Q,1);
        verify(carManager).selectBrakeSystem(anyInt());
    }

    @Test
    void 차조립하기_조향별(){
        assemble.assembleAndRunOrTestCar(SteeringSystem_Q, 1);

        verify(assemble).assembleCar(SteeringSystem_Q,1);
        verify(carManager).selectSteeringSystem(anyInt());
    }

    @Test
    void 실행하기(){
        assemble.assembleAndRunOrTestCar(Run_Test, 1);

        verify(assemble).runOrTestCar(1);
        verify(carManager).runProducedCar();
    }

    @Test
    void 테스트하기(){
        assemble.assembleAndRunOrTestCar(Run_Test, 2);

        verify(assemble).runOrTestCar(2);
        verify(carManager).testProducedCar();
    }

    @Test
    void 실행_테스트_실패(){
        assemble.assembleAndRunOrTestCar(Run_Test, 3);

        verify(assemble).runOrTestCar(anyInt());
    }
}