package com.uwaterloo.raymond.hackthenorth.ast;

/**
 * Created by Raymond on 2018-09-15.
 */

public class Tan extends UnaryOperation {
    public Tan(Operation op) {
        super(op);
    }

    public String toString() {
        return "tan(" + this.op.toString() + ")";
    }

    public Double getNumericResult(Double val) {
        return Double.valueOf(Math.tan(this.op.getNumericResult(val).doubleValue()));
    }

    @Override
    public Operation getDerivative() {
        return new Division(op.getDerivative(), new Pow(new Cos(op),new Constant("2")));
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Tan)) {
            return false;
        } else {
            Tan abs = (Tan)o;
            return this.op.equals(abs.op);
        }
    }

    public int hashCode() {
        return 59 * this.op.hashCode();
    }
}