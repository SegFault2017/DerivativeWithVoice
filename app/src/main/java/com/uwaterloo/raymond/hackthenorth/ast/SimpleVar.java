package com.uwaterloo.raymond.hackthenorth.ast;

/**
 * Created by Raymond on 2018-09-15.
 */

public class SimpleVar implements Operation {
    public SimpleVar() {
    }

    public String toString() {
        return "x";
    }

    public Double getNumericResult(Double val) {
        if(val == null) {
            throw new NullPointerException("Variable set to null!");
        } else {
            return val;
        }
    }

    @Override
    public Operation getDerivative() {
        return new Constant("1");
    }

    public boolean equals(Object o) {
        return this == o?true:o instanceof SimpleVar;
    }

    public int hashCode() {
        return 43;
    }
}
