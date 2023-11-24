package com.zhr.wps.excel.easyexcel.read;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiaju Zhuang
 */
public class ConverterDataListener extends AnalysisEventListener<ConverterReadData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConverterDataListener.class);
    private final List<ConverterReadData> list = new ArrayList<>();

    @Override
    public void invoke(ConverterReadData data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        Assert.assertEquals(list.size(), 1);
        
        ConverterReadData data = list.get(0);
        Assert.assertEquals(TestUtil.TEST_DATE, data.getDate());
        Assert.assertEquals(TestUtil.TEST_LOCAL_DATE, data.getLocalDate());
        Assert.assertEquals(TestUtil.TEST_LOCAL_DATE_TIME, data.getLocalDateTime());
        Assert.assertEquals(data.getBooleanData(), Boolean.TRUE);
        Assert.assertEquals(data.getBigDecimal().doubleValue(), BigDecimal.ONE.doubleValue(), 0.0);
        Assert.assertEquals(data.getBigInteger().intValue(), BigInteger.ONE.intValue(), 0.0);
        Assert.assertEquals(data.getLongData(), 1L);
        Assert.assertEquals((long)data.getIntegerData(), 1L);
        Assert.assertEquals((long)data.getShortData(), 1L);
        Assert.assertEquals((long)data.getByteData(), 1L);
        Assert.assertEquals(data.getDoubleData(), 1.0, 0.0);
        Assert.assertEquals(data.getFloatData(), (float)1.0, 0.0);
        Assert.assertEquals(data.getString(), "测试");
        Assert.assertEquals(data.getCellData().getStringValue(), "自定义");
        LOGGER.debug("First row:{}", JSON.toJSONString(list.get(0)));
    }
}
