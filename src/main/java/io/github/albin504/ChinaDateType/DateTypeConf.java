package io.github.albin504.ChinaDateType;

import java.util.Arrays;
import java.util.List;

public class DateTypeConf {
    public Integer[] work_days;
    public Integer[] holidays;
    public Integer[] legal_holidays;

    public List<Integer> getWorkDays() {
        return List.of(work_days);
    }

    public List<Integer> getHolidays() {
        return List.of(holidays);
    }

    public List<Integer> getLegalHolidays() {
        return List.of(legal_holidays);
    }
}
