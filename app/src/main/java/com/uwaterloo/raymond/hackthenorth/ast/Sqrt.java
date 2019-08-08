package com.uwaterloo.raymond.hackthenorth.ast;

/**
 * Created by Raymond on 2018-09-15.
 */

public class Sqrt extends UnaryOperation {
    public Sqrt(Operation op) {
        super(op);
    }

    public String toString() {
        return "sqrt(" + this.op.toString() + ")";
    }

    public Double getNumericResult(Double val) {
        return Double.valueOf(Math.sqrt(this.op.getNumericResult(val).doubleValue()));
    }

    @Override
    public Operation getDerivative() {
        return new Division(op.getDerivative(),
                new Product(new Constant("2"),new Sqrt(op)));
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Sqrt)) {
            return false;
        } else {
            Sqrt abs = (Sqrt) o;
            return this.op.equals(abs.op);
        }
    }

    public int hashCode() {
        return 53 * this.op.hashCode();
    }
}

