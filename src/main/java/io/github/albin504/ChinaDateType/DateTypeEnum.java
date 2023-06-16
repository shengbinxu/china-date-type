package io.github.albin504.ChinaDateType;

public enum DateTypeEnum {
    WORK_DAY(1, "工作日"), WEEKEND(2, "周末"), LEGAL_HOLIDAY(3, "法定节假日"), OTHER_HOLIDAY(4, "节假日调休");

    public final int value;
    public final String desc;

    DateTypeEnum(int val, String desc) {
        this.value = val;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
