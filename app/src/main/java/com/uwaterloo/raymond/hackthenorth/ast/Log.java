package com.uwaterloo.raymond.hackthenorth.ast;

/**
 * Created by Raymond on 2018-09-15.
 */

public class Log extends UnaryOperation {
    public Log(Operation op) {
        super(op);
    }

    public String toString() {
        return "log(" + this.op.toString() + ")";
    }

    public Double getNumericResult(Double val) {
        return Double.valueOf(Math.log(this.op.getNumericResult(val).doubleValue()));
    }

    @Override
    public Operation getDerivative() {
        return new Division(op.getDerivative(),op);
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Log)) {
            return false;
        } else {
            Log abs = (Log)o;
            return this.op.equals(abs.op);
        }
    }

    public int hashCode() {
        return 37 * this.op.hashCode();
    }
}