# china-date-type

# 目标
判断中国的某一天的日期类型（工作日、周末、法定节假日、节假日调休）。

# 使用场景
根据劳动法，工作日、周末、节假日加班的工资计算方式不一样。因此，往往需要判断某一天的日期类型。

# 概念说明
日期类型说明：
1. 工作日。法律上规定需要上班，都算工作日。举例：20230625是周六，但是因为端午放假，6.25号需要调休，这一天也是工作日
2. 法定节假日。 节假日中的法定节假日。法定节假日判断方式参考https://baike.baidu.com/item/%E6%B3%95%E5%AE%9A%E5%B9%B4%E8%8A%82%E5%81%87%E6%97%A5/50240532?fromtitle=%E6%B3%95%E5%AE%9A%E8%8A%82%E5%81%87%E6%97%A5&fromid=442486&fr=aladdin
3. 节假日调休。 节假日中除了法定节假日之外的日期。
4. 周末。 除1、2、3之外的日期，都是周末。


# 数据维护方式
1. 目前维护了19年到23年中国节假日数据。配置文件在https://github.com/albin504/china-date-type/blob/master/src/main/resources/holiday.json 中
2. 按照惯例每年11、12月份政府会公布下一年度的放假安排，然后我会相应更新下一年度的配置文件。
3. 该配置已经过大厂生产项目验证，数据很可靠。

# 使用方式
1、引入maven依赖
```
<dependency>
    <groupId>io.github.albin504</groupId>
    <artifactId>china-date-type</artifactId>
    <version>1.1-SNAPSHOT</version>
</dependency>
```

2、调用方式[参考](https://github.com/albin504/china-date-type/blob/master/src/test/java/DateTypeServiceTest.java),支持批量获取一批日期的日期类型

