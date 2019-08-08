package com.uwaterloo.raymond.hackthenorth.ast;

import java.util.Objects;

/**
 * Created by Raymond on 2018-09-15.
 */

public abstract class UnaryOperation implements Operation {
    protected final Operation op;

    public UnaryOperation(Operation op) {
        this.op = (Operation) Objects.requireNonNull(op);
    }

    public Operation getOp() {
        return this.op;
    }
}