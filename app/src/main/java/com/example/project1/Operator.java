package com.example.project1;

import java.util.Random;

public class Operator {

    char operator;

    public char getOperator() {
        return operator;
    }

    public Operator(Random myRand) {
        this.operator = pickOperator(myRand);
    }

    public static char pickOperator(Random myRand)
    {
        int choice = myRand.nextInt(4);
        char[] choices = {'+','-','/','*','%'};
        return choices[choice];
    }
}
