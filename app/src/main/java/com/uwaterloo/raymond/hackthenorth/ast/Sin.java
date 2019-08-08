package com.uwaterloo.raymond.hackthenorth.ast;

/**
 * Created by Raymond on 2018-09-15.
 */

public class Sin extends UnaryOperation {
    public Sin(Operation op) {
        super(op);
    }

    public String toString() {
        return "sin(" + this.op.toString() + ")";
    }

    public Double getNumericResult(Double val) {
        return Double.valueOf(Math.sin(this.op.getNumericResult(val).doubleValue()));
    }

    @Override
    public Operation getDerivative() {
        return new Product(new Cos(op),op.getDerivative());
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Sin)) {
            return false;
        } else {
            Sin abs = (Sin)o;
            return this.op.equals(abs.op);
        }
    }

    public int hashCode() {
        return 47 * this.op.hashCode();
    }
}