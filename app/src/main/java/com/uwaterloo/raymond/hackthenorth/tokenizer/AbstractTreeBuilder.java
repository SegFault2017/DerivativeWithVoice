package com.uwaterloo.raymond.hackthenorth.tokenizer;

/**
 * Created by Raymond on 2018-09-15.
 */

import com.uwaterloo.raymond.hackthenorth.ast.Addition;

import com.uwaterloo.raymond.hackthenorth.ast.Constant;
import com.uwaterloo.raymond.hackthenorth.ast.Cos;
import com.uwaterloo.raymond.hackthenorth.ast.Division;
import com.uwaterloo.raymond.hackthenorth.ast.Exp;
import com.uwaterloo.raymond.hackthenorth.ast.Log;
import com.uwaterloo.raymond.hackthenorth.ast.Operation;
import com.uwaterloo.raymond.hackthenorth.ast.Pow;
import com.uwaterloo.raymond.hackthenorth.ast.Product;
import com.uwaterloo.raymond.hackthenorth.ast.SimpleVar;
import com.uwaterloo.raymond.hackthenorth.ast.Sin;
import com.uwaterloo.raymond.hackthenorth.ast.Sqrt;
import com.uwaterloo.raymond.hackthenorth.ast.Subtraction;
import com.uwaterloo.raymond.hackthenorth.ast.Tan;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.objecthunter.exp4j.tokenizer.FunctionToken;
import net.objecthunter.exp4j.tokenizer.NumberToken;
import net.objecthunter.exp4j.tokenizer.OperatorToken;
import net.objecthunter.exp4j.tokenizer.Token;


public class AbstractTreeBuilder {
    private String f;
    private static Iterator<Token> it = null;

    public AbstractTreeBuilder(String f) {
        if(f == null) {
            throw new IllegalArgumentException("Invalid expression");
        } else {
            this.f = f;
            this.init();
        }
    }

    private void init() {
        ExpressionParser expBuilder = new ExpressionParser(this.f);
        List<Token> li = Arrays.asList(expBuilder.variable("x").build());
        Collections.reverse(li);
        it = li.iterator();
    }

    private Operation getFunction(Token t) throws TokenizerException {
        FunctionToken token = (FunctionToken)t;
        String var3;
        switch((var3 = token.getFunction().getName()).hashCode()) {
            case 98695:
                if(var3.equals("cos")) {
                    return new Cos(this.getTree());
                }
                break;
            case 100893:
                if(var3.equals("exp")) {
                    return new Exp(this.getTree());
                }
                break;
            case 107332:
                if(var3.equals("log")) {
                    return new Log(this.getTree());
                }
                break;
            case 113880:
                if(var3.equals("sin")) {
                    return new Sin(this.getTree());
                }
                break;
            case 114593:
                if(var3.equals("tan")) {
                    return new Tan(this.getTree());
                }
                break;
            case 3538208:
                if(var3.equals("sqrt")) {
                    return new Sqrt(this.getTree());
                }
        }

        throw new TokenizerException("Function error");
    }

    private Operation getOperator(Token t) throws TokenizerException {
        Operation right = this.getTree();
        Operation left = this.getTree();
        String var4;
        switch((var4 = ((OperatorToken)t).getOperator().getSymbol()).hashCode()) {
            case 42:
                if(var4.equals("*")) {
                    return new Product(left, right);
                }
                break;
            case 43:
                if(var4.equals("+")) {
                    return new Addition(left, right);
                }
                break;
            case 45:
                if(var4.equals("-")) {
                    return new Subtraction(left, right);
                }
                break;
            case 47:
                if(var4.equals("/")) {
                    return new Division(left, right);
                }
                break;
            case 94:
                if(var4.equals("^")) {
                    return new Pow(left, right);
                }
        }

        throw new TokenizerException("Function error");
    }

    public Operation getTree() throws TokenizerException {
        Token t = (Token)it.next();
        switch(t.getType()) {
            case 1:
                return new Constant("" + ((NumberToken)t).getValue());
            case 2:
                return this.getOperator(t);
            case 3:
                return this.getFunction(t);
            case 4:
                return this.getTree();
            case 5:
                return this.getTree();
            case 6:
                return new SimpleVar();
            default:
                throw new TokenizerException("Invalid expression");
        }
    }
}
