package com.example.menschargerdichnicht;

/**
 * This interface is used to create a Factory Design Method to create Artifical Oppoenents
 */
public interface AI {

    /**
     * This method will give the Artifical Oppoenent its turn to play
     */
    MoveTriplet aiTurn(int []mainBoardlocations, int [][] homeLocations, int [][]finalLocations, int roll, int player);

}
