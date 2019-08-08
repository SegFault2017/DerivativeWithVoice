package com.uwaterloo.raymond.hackthenorth.tokenizer;

/**
 * Created by Raymond on 2018-09-15.
 */

public class TokenizerException extends Exception {
    private static final long serialVersionUID = -3068001314505174585L;

    public TokenizerException() {
    }

    public TokenizerException(String message) {
        super(message);
    }
}