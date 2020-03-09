package com.newland.dg.model.number;

import com.newland.dg.model.AbstractFieldInfo;
import com.newland.dg.model.FieldType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author huangxw
 * @date 2019-11-17
 */
@ToString
public class SequenceField extends AbstractFieldInfo {
    private static final Logger logger = LoggerFactory.getLogger(SequenceField.class);

    @Setter
    @Getter
    private Long minValue = 1L;

    @Setter
    @Getter
    private Long maxValue = 100L;

    @Setter
    @Getter
    private Integer stepValue = 2;

    private AtomicLong atomicLong;

    public SequenceField() {
        super.type = FieldType.SEQUENCE.getName();
    }

    @Builder(toBuilder = true)
    public SequenceField(Long minValue, Long maxValue, Integer stepValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.stepValue = stepValue;
        super.type = FieldType.SEQUENCE.getName();
    }

    @Override
    public void init() {
        atomicLong = new AtomicLong(minValue);
    }

    @Override
    protected String handle() {
        synchronized (atomicLong) {
            long value = atomicLong.addAndGet(stepValue);
            if (value > maxValue) {
                atomicLong.set(minValue);
            }
        }

        return atomicLong.toString();
    }
}
