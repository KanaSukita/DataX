package com.alibaba.datax.transformer;

import com.alibaba.datax.common.element.Column;
import com.alibaba.datax.common.element.LongColumn;
import com.alibaba.datax.common.element.DoubleColumn;
import com.alibaba.datax.common.element.Record;
import com.alibaba.datax.common.element.StringColumn;
import com.alibaba.datax.common.exception.DataXException;
import com.alibaba.datax.transformer.maskingMethods.dataTransformation.DurationTransformationImpl;

import java.util.Arrays;

/**
 * Created by Wenjun Zhang on 2021/3/14.
 */
public class DurationTransformer extends Transformer {
    private Object masker;
    int columnIndex;
    double ratio;

    public DurationTransformer(){
        setTransformerName("dx_duration_transformer");
        System.out.println("Using Duration Datatype transformer");
    }

    @Override
    public Record evaluate(Record record, Object... paras) {
        try {
            if (paras.length < 2) {
                throw new RuntimeException("dx_duration_transformer transformer缺少参数");
            }
            columnIndex = (Integer) paras[0];
            ratio = (Double) paras[1];
        } catch (Exception e) {
            throw DataXException.asDataXException(TransformerErrorCode.TRANSFORMER_ILLEGAL_PARAMETER, "paras:" + Arrays.asList(paras).toString() + " => " + e.getMessage());
        }
        Column column = record.getColumn(columnIndex);
        try {
            String oriValue = column.asString();
            if (oriValue == null) {
                return record;
            }
            if(column.getType() == Column.Type.STRING) {
                DurationTransformationImpl masker = new DurationTransformationImpl();
                Double newValue = masker.execute(oriValue, ratio);
                record.setColumn(columnIndex, new DoubleColumn(newValue));
            }
        } catch (Exception e) {
            throw DataXException.asDataXException(TransformerErrorCode.TRANSFORMER_RUN_EXCEPTION, e.getMessage(), e);
        }
        return record;
    }
}
