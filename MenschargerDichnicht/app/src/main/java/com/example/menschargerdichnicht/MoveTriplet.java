package com.example.menschargerdichnicht;

public class MoveTriplet {

    private int previousIndex;
    private int nextIndex;
    private char moveType; //h- from home to regular board, e-from board to final, r-from one board location to another, f- finish from finish, a- get another roll board to board, b-het another roll home to board, c-get another roll home to finish, d- get another finish to finish, t- get another roll no movement

    public MoveTriplet(int previousSpot, int nextSpot, char moveType){
        this.previousIndex=previousSpot;
        this.nextIndex=nextSpot;
        this.moveType=moveType;
    }

    public int getPreviousIndex(){
        return this.previousIndex;
    }

    public int getNextIndex(){
        return this.nextIndex;
    }

    public char getMoveType() {
        return moveType;
    }
}
