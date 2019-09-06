package com.example.menschargerdichnicht;

import java.util.Random;

public class AIFactory {

    /**
     * This method creates and returns an Artifical Opponent
     * @return An Artifical Opponent
     */
    public AI getAI(){
        Random randomNumber= new Random();
        int aiType= randomNumber.nextInt(3);
        aiType=aiType+1;

        if (aiType==1) return new AITypeOne(); //Return Type 1
        else if (aiType==2)return new AITypeTwo(); //Return Type2
        else return new AITypeThree(); //Return Type3
    }

}
