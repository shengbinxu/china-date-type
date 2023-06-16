import io.github.albin504.ChinaDateType.DateTypeEnum;
import io.github.albin504.ChinaDateType.DateTypeService;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DateTypeServiceTest {
    @Test
    public void test() throws IOException, ParseException {
        // 2020年元旦只放假一天，这一天是法定节假日
        assertEquals((new DateTypeService()).getDateType(20200101), DateTypeEnum.LEGAL_HOLIDAY);
        // 20200102 得上班
        assertEquals((new DateTypeService()).getDateType(20200102), DateTypeEnum.WORK_DAY);

        // 23年端午节放假三天，2023年6月22是法定节假日（三倍工资），23、24是节假日调休（2倍工资）
        assertEquals((new DateTypeService()).getDateType(20230622), DateTypeEnum.LEGAL_HOLIDAY);
        assertEquals((new DateTypeService()).getDateType(20230623), DateTypeEnum.OTHER_HOLIDAY);
        assertEquals((new DateTypeService()).getDateType(20230624), DateTypeEnum.OTHER_HOLIDAY);

        // 20230625是周六，但是因为放假，需要调休，这一天也是工作日
        assertEquals((new DateTypeService()).getDateType(20230625), DateTypeEnum.WORK_DAY);
        assertEquals((new DateTypeService()).getDateType(20230626), DateTypeEnum.WORK_DAY);
        assertEquals((new DateTypeService()).getDateType(20230627), DateTypeEnum.WORK_DAY);

        assertEquals((new DateTypeService()).getDateType(20230701), DateTypeEnum.WEEKEND);
        assertEquals((new DateTypeService()).getDateType(20230702), DateTypeEnum.WEEKEND);

        // 批量获取日期类型
        Map<Integer, DateTypeEnum> result = (new DateTypeService()).batchGetDateType(new int[]{20230622, 20230623,
                20230624, 20230625});
        assertEquals(result.get(20230622), DateTypeEnum.LEGAL_HOLIDAY);
        assertEquals(result.get(20230623), DateTypeEnum.OTHER_HOLIDAY);
        assertEquals(result.get(20230624), DateTypeEnum.OTHER_HOLIDAY);
        assertEquals(result.get(20230625), DateTypeEnum.WORK_DAY);
        assertEquals(result.get(20230625).value, 1);
        assertEquals(result.get(20230625).desc, "工作日");

    }
}
