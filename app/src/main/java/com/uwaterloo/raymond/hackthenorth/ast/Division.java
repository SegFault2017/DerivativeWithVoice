package com.uwaterloo.raymond.hackthenorth.ast;

/**
 * Created by Raymond on 2018-09-15.
 */

public class Division extends BinaryOperation {
    public Division(Operation left, Operation right) {
        super(left, right);
    }

    public Operation getLeft() {
        return this.left;
    }

    public Operation getRight() {
        return this.right;
    }

    public String toString() {
        return "(" + this.left.toString() + ")/(" + this.right.toString() + ")";
    }

    public Double getNumericResult(Double val) {
        return Double.valueOf(this.left.getNumericResult(val).doubleValue() / this.right.getNumericResult(val).doubleValue());
    }

    @Override
    public Operation getDerivative() {
        Operation numberator = new Subtraction(new Product(left.getDerivative(),right),
                new Product(left,right.getDerivative()));
        Operation denominator = new Pow(right,new Constant("2"));
        return  new Division(numberator,denominator);

    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Division)) {
            return false;
        } else {
            Division abs = (Division)o;
            return this.left.equals(abs.left) && this.right.equals(abs.right);
        }
    }

    public int hashCode() {
        return 67 * (this.left.hashCode() + this.right.hashCode());
    }
}
