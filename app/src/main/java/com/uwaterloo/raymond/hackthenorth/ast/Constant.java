package com.uwaterloo.raymond.hackthenorth.ast;

/**
 * Created by Raymond on 2018-09-15.
 */

public class Constant implements Operation {
    private String c;

    public Constant(String c) {
        this.c = c;
    }

    public String toString() {
        return this.c;
    }

    public Double getNumericResult(Double val) {
        return Double.valueOf(Double.parseDouble(this.c));
    }

    @Override
    public Operation getDerivative() {
        return new Constant("0");
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(!(o instanceof Constant)) {
            return false;
        } else {
            Constant abs = (Constant)o;
            return this.c.equals(abs.c);
        }
    }

    public int hashCode() {
        return 23 * (int)Double.parseDouble(this.c);
    }
}