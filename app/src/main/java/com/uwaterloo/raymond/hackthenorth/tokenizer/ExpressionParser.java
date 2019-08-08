package com.uwaterloo.raymond.hackthenorth.tokenizer;

/**
 * Created by Raymond on 2018-09-15.
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.function.Functions;
import net.objecthunter.exp4j.operator.Operator;
import net.objecthunter.exp4j.shuntingyard.ShuntingYard;
import net.objecthunter.exp4j.tokenizer.Token;

public class ExpressionParser {
    private final String expression;
    private final Map<String, Function> userFunctions;
    private final Map<String, Operator> userOperators;
    private final Set<String> variableNames;
    private boolean implicitMultiplication = true;

    public ExpressionParser(String expression) {
        if(expression != null && expression.trim().length() != 0) {
            this.expression = expression;
            this.userOperators = new HashMap(4);
            this.userFunctions = new HashMap(4);
            this.variableNames = new HashSet(4);
        } else {
            throw new IllegalArgumentException("Expression can not be empty");
        }
    }

    public ExpressionParser function(Function function) {
        this.userFunctions.put(function.getName(), function);
        return this;
    }

    public ExpressionParser functions(Function... functions) {
        Function[] var5 = functions;
        int var4 = functions.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            Function f = var5[var3];
            this.userFunctions.put(f.getName(), f);
        }

        return this;
    }

    public ExpressionParser functions(List<Function> functions) {
        Iterator var3 = functions.iterator();

        while(var3.hasNext()) {
            Function f = (Function)var3.next();
            this.userFunctions.put(f.getName(), f);
        }

        return this;
    }

    public ExpressionParser variables(Set<String> variableNames) {
        this.variableNames.addAll(variableNames);
        return this;
    }

    public ExpressionParser variables(String... variableNames) {
        Collections.addAll(this.variableNames, variableNames);
        return this;
    }

    public ExpressionParser variable(String variableName) {
        this.variableNames.add(variableName);
        return this;
    }

    public ExpressionParser implicitMultiplication(boolean enabled) {
        this.implicitMultiplication = enabled;
        return this;
    }

    public ExpressionParser operator(Operator operator) {
        this.checkOperatorSymbol(operator);
        this.userOperators.put(operator.getSymbol(), operator);
        return this;
    }

    private void checkOperatorSymbol(Operator op) {
        String name = op.getSymbol();
        char[] var6;
        int var5 = (var6 = name.toCharArray()).length;

        for(int var4 = 0; var4 < var5; ++var4) {
            char ch = var6[var4];
            if(!Operator.isAllowedOperatorChar(ch)) {
                throw new IllegalArgumentException("The operator symbol '" + name + "' is invalid");
            }
        }

    }

    public ExpressionParser operator(Operator... operators) {
        Operator[] var5 = operators;
        int var4 = operators.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            Operator o = var5[var3];
            this.operator(o);
        }

        return this;
    }

    public ExpressionParser operator(List<Operator> operators) {
        Iterator var3 = operators.iterator();

        while(var3.hasNext()) {
            Operator o = (Operator)var3.next();
            this.operator(o);
        }

        return this;
    }

    public Token[] build() {
        if(this.expression.length() == 0) {
            throw new IllegalArgumentException("The expression can not be empty");
        } else {
            this.variableNames.add("pi");
            this.variableNames.add("Ï€");
            this.variableNames.add("e");
            this.variableNames.add("Ï†");
            Iterator var2 = this.variableNames.iterator();

            String var;
            do {
                if(!var2.hasNext()) {
                    Token[] a = ShuntingYard.convertToRPN(this.expression, this.userFunctions, this.userOperators, this.variableNames, this.implicitMultiplication);
                    return a;
                }

                var = (String)var2.next();
            } while(Functions.getBuiltinFunction(var) == null && !this.userFunctions.containsKey(var));

            throw new IllegalArgumentException("A variable can not have the same name as a function [" + var + "]");
        }
    }
}

