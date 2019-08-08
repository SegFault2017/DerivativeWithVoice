package com.uwaterloo.raymond.hackthenorth.ast;

import java.util.Objects;

/**
 * Created by Raymond on 2018-09-15.
 */

public abstract class BinaryOperation implements Operation {
    protected final Operation left;
    protected final Operation right;

    public BinaryOperation(Operation left, Operation right) {
        this.left = (Operation)Objects.requireNonNull(left);
        this.right = (Operation) Objects.requireNonNull(right);
    }

    public Operation getLeft() {
        return this.left;
    }

    public Operation getRight() {
        return this.right;
    }
}
