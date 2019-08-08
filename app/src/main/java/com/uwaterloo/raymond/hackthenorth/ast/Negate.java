package com.uwaterloo.raymond.hackthenorth.ast;

/**
 * Created by Raymond on 2018-09-15.
 */

public class Negate extends UnaryOperation {
    public Negate(Operation op) {
        super(op);
    }

    public String toString() {
        return "-" + this.op.toString();
    }

    public Double getNumericResult(Double val) {
        return Double.valueOf(-this.op.getNumericResult(val).doubleValue());
    }

    @Override
    public Operation getDerivative() {
        return new Negate(op.getDerivative());
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Negate)) {
            return false;
        } else {
            Negate abs = (Negate)o;
            return this.op.equals(abs.op);
        }
    }

    public int hashCode() {
        return 41 * this.op.hashCode();
    }
}
