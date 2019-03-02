package com.example.project1;

import java.util.Random;

public class Operation implements Cloneable{

    private static float x;
    private static float y;
    private static Operator myOperator;
    private static float result;
    private static float answer;
    private static String response;
    private static String operation;

    public static boolean isCorrect() {
        return isCorrect;
    }

    private static boolean isCorrect;


    public float getAnswer() {
        return answer;
    }

    public void setAnswer(float answer) {
        this.answer = answer;
    }

    public static float getResult() {
        return result;
    }



    public Operation(Random myRand,boolean hardMode) {
        x = generateOperand(myRand,hardMode);
        y = generateOperand(myRand,hardMode);
        myOperator = new Operator(myRand);
        result = OperationResult();
        operation = GenerateOperation();
        answer = 0;
        response = "Your answer is ";
    }

    @Override
    public String toString() {
        return "\n"+operation +" = " + answer +"\n"+response+"\n"+"---------------------------------\n" ;
    }

    public static String getOperation() {
        return operation;
    }

    public static Boolean CheckResult()
    {
        if (answer==result)
        {
            response += "Correct!";
            isCorrect = true;
            return true;
        }
            response +=" Incorrect!\n Correct answer is: "+ result;
            isCorrect = false;
            return false;
    }

    public static float generateOperand(Random myRand, boolean hardMode)
    {
        float randomNumber;
        if (hardMode){
            randomNumber = myRand.nextFloat();
        }
        else{
            randomNumber = myRand.nextInt(9);
        }
        return  randomNumber;
    }

    private static String GenerateOperation()
    {
        switch (myOperator.getOperator())
        {
            case '+':
                operation = x +" + " + y;
                break;
            case '-':
                operation = x +" - " + y;
                break;
            case '/':
                operation = x +" / " + y;
                break;
            case '*':
                operation = x +" * " + y;
                break;
            case '%':
                operation = x +" % " + y;
                break;
        }
        return operation;
    }


    private static float OperationResult()
    {
        switch (myOperator.operator)
        {
            case '+':
                result = x + y;
                break;
            case '-':
                result = x - y;
                break;
            case '/':
                result = x / y;
                break;
            case '*':
                result = x * y;
                break;
            case '%':
                result = x % y;
                break;
        }
        return result;
    }

}
