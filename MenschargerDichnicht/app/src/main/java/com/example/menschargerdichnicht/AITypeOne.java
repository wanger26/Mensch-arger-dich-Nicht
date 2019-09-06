package com.example.menschargerdichnicht;

/**
 * This player likes to move one piece at a time to finish line if possible before attempting to move any other pieces
 */
public class AITypeOne implements AI{
    private int playerFrontDoor; //Stores the out location index for the player

    @Override
    public MoveTriplet aiTurn(int []mainBoardlocations, int [][] homeLocations, int [][]finalLocations, int roll, int player) {

        if (player == 2)
            this.playerFrontDoor = 2; //If this player is player 2 then its out location index is 2
        else if (player == 3)
            this.playerFrontDoor = 22; //If this player is player 3 then its out location index is 22
        else
            this.playerFrontDoor = 12; //If this player is player 4 then its out location index is 12

        if (roll == 6) { //If the roll is 6 then the AI will be allowed to roll the dice again
            if (mainBoardlocations[this.playerFrontDoor] == player && mainBoardlocations[this.playerFrontDoor + roll] != player && !homeEmpty(homeLocations, player)) { //if the out location has the player board piece on it, and its home is not empty and it can move the board piece it must do so
                return new MoveTriplet(this.playerFrontDoor, this.playerFrontDoor + roll, 'a');
            } else if (mainBoardlocations[this.playerFrontDoor] != player && !homeEmpty(homeLocations, player)) { //else, if the out location is not filled with the players game piece and the home is not empty then move game piece from home out to the out spot
                for (int x = 0; x < homeLocations[player-1].length; x++) {
                    if (homeLocations[player-1][x] == player) {
                        return new MoveTriplet(x, this.playerFrontDoor, 'b');
                    }
                }
            } else { //Else if that's also not the case then the player can move any piece they would like
                int tempIndex = this.playerFrontDoor - 1;

                for (int x = 0; x < mainBoardlocations.length; x++) {
                    if (mainBoardlocations[Math.floorMod(tempIndex,40)]==player) { //if the current spot has the players game piece on it then we can continue and check to see if it can be moved
                        if (player != 2 && Math.floorMod(tempIndex, 40) < this.playerFrontDoor && Math.floorMod(tempIndex+roll, 40) >= this.playerFrontDoor) //if the game piece is before the out spot and after the roll it would be equal to or past the out spot then it must be that the move has to be into the finish area
                        {
                            if ((Math.floorMod(tempIndex+roll, 40) - this.playerFrontDoor) >= 0 && (Math.floorMod(tempIndex+roll, 40) - this.playerFrontDoor) < 4 && finalLocations[player - 1][Math.floorMod(tempIndex+roll, 40) - this.playerFrontDoor] == 0) {
                                return new MoveTriplet(Math.floorMod(tempIndex, 40), Math.floorMod(tempIndex+roll, 40) - this.playerFrontDoor, 'c');
                            }
                        } else if (player==2 && (Math.floorMod(tempIndex, 40)>36 || Math.floorMod(tempIndex,40)<this.playerFrontDoor) && Math.floorMod(tempIndex+roll,40)>=this.playerFrontDoor) //if the game piece is before the out spot and after the roll it would be equal to or past the out spot then it must be that the move has to be into the finish area
                        {
                            if ((Math.floorMod(tempIndex+roll, 40) - this.playerFrontDoor) >= 0 && (Math.floorMod(tempIndex+roll, 40) - this.playerFrontDoor) < 4 && finalLocations[player - 1][Math.floorMod(tempIndex+roll, 40) - this.playerFrontDoor] == 0) {
                                return new MoveTriplet(Math.floorMod(tempIndex, 40), Math.floorMod(tempIndex+roll, 40) - this.playerFrontDoor, 'c');
                            }
                        } else { //Else it is a regular move from one board location to another
                            if (mainBoardlocations[Math.floorMod(Math.floorMod(tempIndex,40)+roll, 40)] != player){
                                return new MoveTriplet(Math.floorMod(tempIndex, 40), Math.floorMod(tempIndex+roll,40) , 'a');
                            }
                        }
                    }

                    tempIndex--;
                }
            }
        } else { //else the player did not roll a 6 so therefore will not be allowed to roll again after its turn
            if (mainBoardlocations[this.playerFrontDoor] == player && mainBoardlocations[this.playerFrontDoor + roll] != player && !homeEmpty(homeLocations, player)) { //if the out location has the player board piece on it, and its home is not empty and it can move the board piece it must do so
                return new MoveTriplet(this.playerFrontDoor, this.playerFrontDoor + roll, 'r');
            }
            else { //Else if that's also not the case then the player can move any piece they would like
                int tempIndex = this.playerFrontDoor - 1;

                for (int x = 0; x < mainBoardlocations.length; x++) {

                    if (mainBoardlocations[Math.floorMod(tempIndex,40)]==player) { //if the current spot has the players game piece on it then we can continue and check to see if it can be moved
                        if (player != 2 && Math.floorMod(tempIndex, 40) < this.playerFrontDoor && Math.floorMod(tempIndex+roll, 40) >= this.playerFrontDoor) //if the game piece is before the out spot and after the roll it would be equal to or past the out spot then it must be that the move has to be into the finish area
                        {
                            if ((Math.floorMod(tempIndex+roll, 40) - this.playerFrontDoor) >= 0 && (Math.floorMod(tempIndex+roll, 40) - this.playerFrontDoor) < 4 && finalLocations[player - 1][Math.floorMod(tempIndex+roll, 40) - this.playerFrontDoor] == 0) { //If the roll is results between 0 and 3, and that spot is empty in the finish area then move it in
                                return new MoveTriplet(Math.floorMod(tempIndex, 40), Math.floorMod(tempIndex+roll, 40) - this.playerFrontDoor, 'e');
                            }
                        }
                        else if (player==2 && (Math.floorMod(tempIndex, 40)>36 || Math.floorMod(tempIndex,40)<this.playerFrontDoor) && Math.floorMod(tempIndex+roll,40)>=this.playerFrontDoor) //if the game piece is before the out spot and after the roll it would be equal to or past the out spot then it must be that the move has to be into the finish area
                        {
                            if ((Math.floorMod(tempIndex+roll, 40) - this.playerFrontDoor) >= 0 && (Math.floorMod(tempIndex+roll, 40) - this.playerFrontDoor) < 4 && finalLocations[player - 1][Math.floorMod(tempIndex+roll, 40) - this.playerFrontDoor] == 0) { //If the roll is results between 0 and 3, and that spot is empty in the finish area then move it in
                                return new MoveTriplet(Math.floorMod(tempIndex, 40), Math.floorMod(tempIndex+roll, 40) - this.playerFrontDoor, 'e');
                            }
                        }
                        else { //Else it is a regular move from one board location to snother
                            if (mainBoardlocations[Math.floorMod(Math.floorMod(tempIndex,40)+roll, 40)] != player){ //If the location where the roll would make it end up is not the players then it can be moved
                                return new MoveTriplet(Math.floorMod(tempIndex, 40), Math.floorMod(tempIndex+ roll,40), 'r');
                            }
                        }
                    }
                    tempIndex--;
                }
            }
        }
        return null; //else the ai cant
    }


    /**
     * This method checks if the main board is empty or not for a specific player
     * @param mainBoardLocations the main board location array
     * @param player the player which to check the board for
     * @return true- if the board is empty for the specified player, false otherwise
     */
    private boolean boardEmpty(int [] mainBoardLocations, int player){
        for (int x=0; x< mainBoardLocations.length; x++){
            if (mainBoardLocations[x]==player) return false;
        }
        return true;
    }

    /**
     * This method checks if the home is empty or not for a specific player
     * @param homeLocations the array storing the home locations
     * @return true- if the board is empty for the specified player, false otherwise
     */
    private boolean homeEmpty(int [][] homeLocations, int player){
        for (int x=0; x< homeLocations[player-1].length; x++){
            if (homeLocations[player-1][x]==player) return false;
        }
        return true;
    }

}
