package com.uwaterloo.raymond.hackthenorth.ast;

/**
 * Created by Raymond on 2018-09-15.
 */

public class Subtraction extends BinaryOperation {
    public Subtraction(Operation left, Operation right) {
        super(left, right);
    }

    public Operation getLeft() {
        return this.left;
    }

    public Operation getRight() {
        return this.right;
    }

    public String toString() {
        return this.left.toString() + "-" + this.right.toString();
    }

    public Double getNumericResult(Double val) {
        return Double.valueOf(this.left.getNumericResult(val).doubleValue() - this.right.getNumericResult(val).doubleValue());
    }

    @Override
    public Operation getDerivative() {
        return new Subtraction(left.getDerivative(),right.getDerivative());
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Subtraction)) {
            return false;
        } else {
            Subtraction abs = (Subtraction)o;
            return this.left.equals(abs.left) && this.right.equals(abs.right);
        }
    }

    public int hashCode() {
        return 79 * (this.left.hashCode() + this.right.hashCode());
    }
}