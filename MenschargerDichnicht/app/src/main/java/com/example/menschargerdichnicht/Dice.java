package com.example.menschargerdichnicht;

import java.util.Random;

public class Dice {

    public Dice(){
    }

    public int roll(){
        Random roll= new Random();
        return roll.nextInt(6)+1;
    }

}
