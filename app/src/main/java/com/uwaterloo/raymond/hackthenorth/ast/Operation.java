package com.uwaterloo.raymond.hackthenorth.ast;

/**
 * Created by Raymond on 2018-09-15.
 */

public interface Operation {
    Double getNumericResult(Double var1);
    Operation getDerivative();
}
