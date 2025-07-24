package mission2.enums;

public enum CarInfo {
    Sedan,
    Truck,
    GM,
    TOYOTA,
    SUV,
    WIA,
    MANDO,
    CONTINENTAL,
    BOSCH,
    MOBIS,
    BROKEN_ENGINE("고장난 엔진");

    String option;

    CarInfo() {}

    CarInfo(String s) {
        this.option = s;
    }

    public String getOption() {
        return option;
    }
}
