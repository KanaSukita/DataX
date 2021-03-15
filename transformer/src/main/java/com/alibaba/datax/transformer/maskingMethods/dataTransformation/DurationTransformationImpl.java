package com.alibaba.datax.transformer.maskingMethods.dataTransformation;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.*;

/**
 * 原Duration数据类型转换的String对象转换成数字
 */
public class DurationTransformationImpl extends DataTransformationMasking{

    static final String[] timeUnit = {"y", "mo", "d", "h", "m", "s", "ms", "us", "ns"};
    static final double[] timeRatio = {3.1536e7, 2.592e6, 8.64e4, 3.6e3, 60.0, 1.0, 1e-3, 1e-6, 1e-9};

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
        Double res = 0.0;
        String stringNum = "";

        for (int i = 0 ; i < timeUnit.length; i++) {
            String pattern = "[0-9]+" + timeUnit[i];
            Pattern timePattern = Pattern.compile(pattern);
            Matcher timeMacher = timePattern.matcher(originData);

            if (timeMacher.find() == true) {
                stringNum = timeMacher.group();
                stringNum = stringNum.substring(0, stringNum.length() - timeUnit[i].length());
                res += Double.parseDouble(stringNum) * timeRatio[i];
            }
        }

        return res * ratio;
    }

    public void mask() throws Exception {

    }
}