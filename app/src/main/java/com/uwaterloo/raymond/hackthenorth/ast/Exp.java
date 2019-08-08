package com.uwaterloo.raymond.hackthenorth.ast;

/**
 * Created by Raymond on 2018-09-15.
 */

public class Exp extends UnaryOperation {
    public Exp(Operation op) {
        super(op);
    }

    public String toString() {
        return "e^(" + this.op.toString() + ")";
    }

    public Double getNumericResult(Double val) {
        return Double.valueOf(Math.exp(this.op.getNumericResult(val).doubleValue()));
    }

    @Override
    public Operation getDerivative() {
        return new Product(new Exp(op),op.getDerivative());
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Exp)) {
            return false;
        } else {
            Exp abs = (Exp)o;
            return this.op.equals(abs.op);
        }
    }

    public int hashCode() {
        return 31 * this.op.hashCode();
    }
}