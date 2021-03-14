package com.alibaba.datax.transformer.maskingMethods.dataTransformation;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.*;

/**
 * 原Duration数据类型转换的String对象转换成数字
 */
public class DurationTransformationImpl extends DataTransformtionMasking{

    static final String[] timeUnit = {"hour", "minute", "s", "ms", "us", "ns"};
    static final double[] timeRatio = {3600.0, 60.0, 1.0, 1e-3, 1e-6, 1e-9};

    public double execute(double d) throws Exception {
        return 0;
    }

    /**
     * 对Duration转换的String对象执行数据转换
     * @param originData
     * @return
     * @throws Exception
     */
    public Double execute(String originData, double ratio) throws NoSuchAlgorithmException {
        // TODO： udf中的实现
    }

    public void mask() throws Exception {

    }
}