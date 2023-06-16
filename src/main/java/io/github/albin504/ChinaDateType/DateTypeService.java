package io.github.albin504.ChinaDateType;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateTypeService {

    public DateTypeConf conf;

    private void loadConf() throws IOException {
        if (conf == null) {
            ObjectMapper mapper = new ObjectMapper();
            InputStream input = DateTypeService.class.getClassLoader().getResourceAsStream("holiday.json");
            conf = mapper.readValue(input, DateTypeConf.class);
        }
    }

    public DateTypeService() throws IOException {
        loadConf();
    }


    /**
     * 获取某一天的日期类型：工作日、周末、节假日调休、法定节假日
     *
     * @param date
     * @return
     */
    public DateTypeEnum getDateType(int date) throws ParseException {
        if (date < 20190101) {
            throw new InvalidParameterException("date must be greater than 20190101");
        }
        // 法定节假日日期列表
        List<Integer> legalHolidays = conf.getLegalHolidays();
        // 节假日列表（包含法定节假日和节假日调休）
        List<Integer> holidays = conf.getHolidays();
        // 原本是周末，由于节假日调休，导致需要上班的日期列表
        List<Integer> workDays = conf.getWorkDays();
        if (legalHolidays.contains(date)) {
            return DateTypeEnum.LEGAL_HOLIDAY;
        } else if (holidays.contains(date)) {
            return DateTypeEnum.OTHER_HOLIDAY;
        } else {
            // 判断date是周几
            Date dateObj = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH).parse(Integer.toString(date));
            Calendar c = Calendar.getInstance();
            c.setTime(dateObj);
            int weekday = c.get(Calendar.DAY_OF_WEEK);
            // 是周末，并且不需要调休。那么，这天的日期类型就是周末
            if ((weekday == Calendar.SATURDAY || weekday == Calendar.SUNDAY) && !workDays.contains(date)) {
                return DateTypeEnum.WEEKEND;
            } else {
                // 是工作日（法律规定，这天需要上班）
                return DateTypeEnum.WORK_DAY;
            }
        }
    }

    /**
     * 批量获取一批日期的类型：工作日、周末、节假日调休、法定节假日
     *
     * @param dateList
     * @return
     */
    public Map<Integer, DateTypeEnum> batchGetDateType(int[] dateList) throws ParseException {
        Map<Integer, DateTypeEnum> result = new HashMap<>();
        for (int date : dateList) {
            result.put(date, getDateType(date));
        }
        return result;
    }
}
