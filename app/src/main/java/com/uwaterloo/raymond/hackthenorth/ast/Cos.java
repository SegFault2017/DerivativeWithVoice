package com.uwaterloo.raymond.hackthenorth.ast;

/**
 * Created by Raymond on 2018-09-15.
 */

public class Cos extends UnaryOperation {
    public Cos(Operation op) {
        super(op);
    }

    public String toString() {
        return "cos(" + this.op.toString() + ")";
    }

    public Double getNumericResult(Double val) {
        return Double.valueOf(Math.cos(this.op.getNumericResult(val).doubleValue()));
    }


    @Override
    public Operation getDerivative() {
        return new Negate(new Product(new Sin(op),op.getDerivative()));
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Cos)) {
            return false;
        } else {
            Cos abs = (Cos)o;
            return this.op.equals(abs.op);
        }
    }

    public int hashCode() {
        return 29 * this.op.hashCode();
    }
}