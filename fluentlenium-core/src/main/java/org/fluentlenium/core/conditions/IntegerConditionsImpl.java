package org.fluentlenium.core.conditions;

import com.google.common.base.Predicate;

/**
 * Conditions implementation for Integer.
 */
public class IntegerConditionsImpl extends AbstractObjectConditions<Integer> implements IntegerConditions {
    public IntegerConditionsImpl(Integer integer) {
        super(integer);
    }

    public IntegerConditionsImpl(Integer object, boolean negation) {
        super(object, negation);
    }

    @Override
    protected AbstractObjectConditions<Integer> newInstance(boolean negationValue) {
        return new IntegerConditionsImpl(object, negationValue);
    }

    @Override
    @Negation
    public IntegerConditionsImpl not() {
        return (IntegerConditionsImpl) super.not();
    }

    @Override
    public boolean equalTo(final int value) {
        return verify(new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return input.equals(value);
            }
        });
    }

    @Override
    public boolean lessThan(final int value) {
        return verify(new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return input < value;
            }
        });
    }

    @Override
    public boolean lessThanOrEqualTo(final int value) {
        return verify(new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return input <= value;
            }
        });
    }

    @Override
    public boolean greaterThan(final int value) {
        return verify(new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return input > value;
            }
        });
    }

    @Override
    public boolean greaterThanOrEqualTo(final int value) {
        return verify(new Predicate<Integer>() {
            @Override
            public boolean apply(Integer input) {
                return input >= value;
            }
        });
    }
}
