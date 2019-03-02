package com.example.project1;

import java.util.ArrayList;

public class UserLog {

    private static class Register{
        boolean isCorrect;
        String result;

        @Override
        public String toString() {
            return result;
        }
    }

    private static ArrayList<Register> regList;
    private static int correctAnswers;

    private static float accuracy;

    public static float getAccuracy() {
        return accuracy;
    }


    public UserLog() {
        regList = new ArrayList<>();
        correctAnswers=0;

    }

    public static void addOperation(Operation myOp) {
        Register myReg = new Register();
        myReg.result = myOp.toString();
        myReg.isCorrect = myOp.isCorrect();
        regList.add(myReg);
        if (myOp.isCorrect()){
            correctAnswers++;
        }
        accuracy = (correctAnswers*100)/regList.size();
    }

    @Override
    public String toString() {

        return  regList.toString().replaceAll("\\[|\\]|\\,", "")+ "SCORE:\n"+
                accuracy+"% CORRECT ANSWERS\n"+
                (100-accuracy)+"% INCORRECT ANSWERS";
    }


}
