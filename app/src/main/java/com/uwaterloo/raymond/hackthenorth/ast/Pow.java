package com.uwaterloo.raymond.hackthenorth.ast;

/**
 * Created by Raymond on 2018-09-15.
 */

public class Pow extends BinaryOperation {
    public Pow(Operation left, Operation right) {
        super(left, right);
    }

    public String toString() {
        return "(" + this.left.toString() + ")^(" + this.right.toString() + ")";
    }

    public Double getNumericResult(Double val) {
        return Double.valueOf(Math.pow(this.left.getNumericResult(val).doubleValue(), this.right.getNumericResult(val).doubleValue()));
    }

    @Override
    public Operation getDerivative() {
        Operation firstTerm = new Pow(left,right);
        Operation secondTerm = new Addition(new Product(right.getDerivative(),new Log(left)),
                new Division(new Product(right,left.getDerivative()),left));
        return new Product(firstTerm,secondTerm);
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Pow)) {
            return false;
        } else {
            Pow abs = (Pow)o;
            return this.left.equals(abs.left) && this.right.equals(abs.right);
        }
    }

    public int hashCode() {
        return 71 * (this.left.hashCode() + this.right.hashCode());
    }
}
