package com.example.menschargerdichnicht;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PlayActivity extends AppCompatActivity {
    private Dice dice= new Dice();
    private Button diceBTN;
    private int[] boardPieceLocations;
    private int [][] homeLocations;
    private int [][] finishLocations;
    private Button[] buttons;
    private Button[][] homeButtons;
    private Button[][] finishButtons;
    private Drawable[] finishButtonDrawablesEmpty;
    private Drawable[] finishButtonDrawablesFull;
    private Drawable[] outButtonDrawables;
    private int roll=0;
    private int playerTurn=1;
    private int [] colors;
    private int rolls=0;
    private AI ai[];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        this.homeLocations= new int[4][4];
        this.finishButtons=new Button [4][4];
        this.buttons =new Button[40];
        this.boardPieceLocations= new int[40];
        this.finishLocations=new int [4][4];
        this.homeButtons= new Button[4][4];
        this.colors= new int [4];
        this.finishButtonDrawablesEmpty= new Drawable[4];
        this.finishButtonDrawablesFull= new Drawable[4];
        this.outButtonDrawables= new Drawable[4];
        this.diceBTN=findViewById(R.id.btnDice);
        this.ai= new AI[3];


        AIFactory aiFactory= new AIFactory();
        this.ai[0]= aiFactory.getAI();
        this.ai[1]= aiFactory.getAI();
        this.ai[2]= aiFactory.getAI();


        // Initializing the home fields as full
        for (int player=0; player<homeLocations.length; player++){
            for (int piece=0;piece<homeLocations[player].length; piece++){
                this.homeLocations[player][piece]=player+1;
                finishLocations[player][piece]=0;
            }
        }

        this.colors[0]=getResources().getColor(R.color.redColor);
        this.colors[1]=getResources().getColor(R.color.blueColor);
        this.colors[2]=getResources().getColor(R.color.greenColor);
        this.colors[3]=getResources().getColor(R.color.blackColor);

        finishButtonDrawablesEmpty[0]=getDrawable(R.drawable.player1homempty);
        finishButtonDrawablesEmpty[1]=getDrawable(R.drawable.player2homempty);
        finishButtonDrawablesEmpty[2]=getDrawable(R.drawable.player3homempty);
        finishButtonDrawablesEmpty[3]=getDrawable(R.drawable.player4homempty);

        finishButtonDrawablesFull[0]=getDrawable(R.drawable.player1homefull);
        finishButtonDrawablesFull[1]=getDrawable(R.drawable.player2homefull);
        finishButtonDrawablesFull[2]=getDrawable(R.drawable.player3homefull);
        finishButtonDrawablesFull[3]=getDrawable(R.drawable.player4homefull);

        outButtonDrawables[0]= getDrawable(R.drawable.player1out);
        outButtonDrawables[1]= getDrawable(R.drawable.player2out);
        outButtonDrawables[2]= getDrawable(R.drawable.player3out);
        outButtonDrawables[3]= getDrawable(R.drawable.player4out);


        this.buttons[0]=findViewById(R.id.btnBoard1);
        this.buttons[1]=findViewById(R.id.btnBoard2);
        this.buttons[2]=findViewById(R.id.btnBoard3);
        this.buttons[3]=findViewById(R.id.btnBoard4);
        this.buttons[4]=findViewById(R.id.btnBoard5);
        this.buttons[5]=findViewById(R.id.btnBoard6);
        this.buttons[6]=findViewById(R.id.btnBoard7);
        this.buttons[7]=findViewById(R.id.btnBoard8);
        this.buttons[8]=findViewById(R.id.btnBoard9);
        this.buttons[9]=findViewById(R.id.btnBoard10);
        this.buttons[10]=findViewById(R.id.btnBoard11);
        this.buttons[11]=findViewById(R.id.btnBoard12);
        this.buttons[12]=findViewById(R.id.btnBoard13);
        this.buttons[13]=findViewById(R.id.btnBoard14);
        this.buttons[14]=findViewById(R.id.btnBoard15);
        this.buttons[15]=findViewById(R.id.btnBoard16);
        this.buttons[16]=findViewById(R.id.btnBoard17);
        this.buttons[17]=findViewById(R.id.btnBoard18);
        this.buttons[18]=findViewById(R.id.btnBoard19);
        this.buttons[19]=findViewById(R.id.btnBoard20);
        this.buttons[20]=findViewById(R.id.btnBoard21);
        this.buttons[21]=findViewById(R.id.btnBoard22);
        this.buttons[22]=findViewById(R.id.btnBoard23);
        this.buttons[23]=findViewById(R.id.btnBoard24);
        this.buttons[24]=findViewById(R.id.btnBoard25);
        this.buttons[25]=findViewById(R.id.btnBoard26);
        this.buttons[26]=findViewById(R.id.btnBoard27);
        this.buttons[27]=findViewById(R.id.btnBoard28);
        this.buttons[28]=findViewById(R.id.btnBoard29);
        this.buttons[29]=findViewById(R.id.btnBoard30);
        this.buttons[30]=findViewById(R.id.btnBoard31);
        this.buttons[31]=findViewById(R.id.btnBoard32);
        this.buttons[32]=findViewById(R.id.btnBoard33);
        this.buttons[33]=findViewById(R.id.btnBoard34);
        this.buttons[34]=findViewById(R.id.btnBoard35);
        this.buttons[35]=findViewById(R.id.btnBoard36);
        this.buttons[36]=findViewById(R.id.btnBoard37);
        this.buttons[37]=findViewById(R.id.btnBoard38);
        this.buttons[38]=findViewById(R.id.btnBoard39);
        this.buttons[39]=findViewById(R.id.btnBoard40);

        this.finishButtons[0][0]=findViewById(R.id.btnHome11);
        this.finishButtons[0][1]=findViewById(R.id.btnHome12);
        this.finishButtons[0][2]=findViewById(R.id.btnHome13);
        this.finishButtons[0][3]=findViewById(R.id.btnHome14);
        this.finishButtons[1][0]=findViewById(R.id.btnHome21);
        this.finishButtons[1][1]=findViewById(R.id.btnHome22);
        this.finishButtons[1][2]=findViewById(R.id.btnHome23);
        this.finishButtons[1][3]=findViewById(R.id.btnHome24);
        this.finishButtons[2][0]=findViewById(R.id.btnHome31);
        this.finishButtons[2][1]=findViewById(R.id.btnHome32);
        this.finishButtons[2][2]=findViewById(R.id.btnHome33);
        this.finishButtons[2][3]=findViewById(R.id.btnHome34);
        this.finishButtons[3][0]=findViewById(R.id.btnHome41);
        this.finishButtons[3][1]=findViewById(R.id.btnHome42);
        this.finishButtons[3][2]=findViewById(R.id.btnHome43);
        this.finishButtons[3][3]=findViewById(R.id.btnHome44);


        this.homeButtons[0][0]=findViewById(R.id.btnPlayer11);
        this.homeButtons[0][1]=findViewById(R.id.btnPlayer12);
        this.homeButtons[0][2]=findViewById(R.id.btnPlayer13);
        this.homeButtons[0][3]=findViewById(R.id.btnPlayer14);
        this.homeButtons[1][0]=findViewById(R.id.btnPlayer21);
        this.homeButtons[1][1]=findViewById(R.id.btnPlayer22);
        this.homeButtons[1][2]=findViewById(R.id.btnPlayer23);
        this.homeButtons[1][3]=findViewById(R.id.btnPlayer24);
        this.homeButtons[2][0]=findViewById(R.id.btnPlayer31);
        this.homeButtons[2][1]=findViewById(R.id.btnPlayer32);
        this.homeButtons[2][2]=findViewById(R.id.btnPlayer33);
        this.homeButtons[2][3]=findViewById(R.id.btnPlayer34);
        this.homeButtons[3][0]=findViewById(R.id.btnPlayer41);
        this.homeButtons[3][1]=findViewById(R.id.btnPlayer42);
        this.homeButtons[3][2]=findViewById(R.id.btnPlayer43);
        this.homeButtons[3][3]=findViewById(R.id.btnPlayer44);

    }

    /**
     * This method is activiated when the dice is pressed on the game screen and starts the users turn.
     * @param view
     */
    public void onClickRoll(View view){
        diceRoll();
        disableBoard();
        enableBoard(1);
    }

    public void onClickOnBoard(View view){
        disableBoard();
        this.rolls=0;
        for (int index=0; index<buttons.length; index++){

            if (buttons[index].getId()==view.getId() && ((index+this.roll)<=31 || index>=32)){//Means the next move will still be on the board and not in the finish
                boardPieceLocations[index]=0;
                if (boardPieceLocations[(index+this.roll)%40]!=0){ //if this happens that means the user is jumping on another players game piece. Therefore move oponenet's game piece back to his home
                    returnPieceHome(boardPieceLocations[(index+this.roll)%40]);
                }
                boardPieceLocations[(index+this.roll)%40]=1;
                if (index==32) this.buttons[index].setBackground(outButtonDrawables[0]);
                else if (index==2) this.buttons[index].setBackground(outButtonDrawables[1]);
                else if (index== 22) this.buttons[index].setBackground(outButtonDrawables[2]);
                else if (index==12) this.buttons[index].setBackground(outButtonDrawables[3]);
                else this.buttons[index].setBackgroundColor(getResources().getColor(R.color.colorHoles));
                buttons[(index+this.roll)%40].setBackgroundColor(getResources().getColor(R.color.redColor));
            }
            else if (buttons[index].getId()==view.getId()){ //Means the next move will be into the finishing squares
                boardPieceLocations[index]=0;
                finishLocations[0][(index+this.roll)-32]=1;
                buttons[index].setBackgroundColor(getResources().getColor(R.color.colorHoles));
                finishButtons[0][(index+this.roll)-32].setBackground(finishButtonDrawablesFull[0]);
            }
        }
        if (this.roll!=6){
            findViewById(R.id.btnChangeTurn).setVisibility(View.VISIBLE);
        }
        else this.diceBTN.setEnabled(true);

        checkGameOver(); //checking if the game is now over after the last move
    }
    public void onClickFinish (View view){
        disableBoard();
        this.rolls=0;
        for (int index=0; index<finishButtons[0].length; index++) {
            if (finishButtons[0][index].getId() == view.getId()) {
                finishLocations[0][index]= 0;
                finishLocations[0][(index + this.roll)] = 1;
                finishButtons[0][index].setBackground(finishButtonDrawablesEmpty[0]);
                finishButtons[0][(index + this.roll)].setBackground(finishButtonDrawablesFull[0]);
            }
        }
        if (this.roll!=6) findViewById(R.id.btnChangeTurn).setVisibility(View.VISIBLE);
        else this.diceBTN.setEnabled(true);
    }


    public void onClickHomeBTN (View view){
        disableBoard();
        this.rolls=0;
        for (int index=0; index<homeButtons[0].length; index++){
            if (homeButtons[0][index].getId()==view.getId()){
                homeLocations[0][index]=0;
                if (boardPieceLocations[32]!=0){ //if this happens that means the user is jumping on another players game piece. Therefore move oponenet's game piece back to his home
                    returnPieceHome(boardPieceLocations[32]);
                }
                boardPieceLocations[32]=1;
                homeButtons[0][index].setBackgroundColor(getResources().getColor(R.color.colorHoles));
                buttons[32].setBackgroundColor(getResources().getColor(R.color.redColor));
                disableBoard();
            }
        }
        if (this.roll!=6) findViewById(R.id.btnChangeTurn).setVisibility(View.VISIBLE);
        else this.diceBTN.setEnabled(true);
    }

    public void onClickNextTurn(View view){
        final Handler handler = new Handler();
        boolean turnFinished;
        findViewById(R.id.btnChangeTurn).setVisibility(View.INVISIBLE);

        while (this.playerTurn<4) {
            this.playerTurn++;
            turnFinished=false;
            while (!turnFinished) {

                diceRoll();
                int secs = 2; // Delay in seconds


                MoveTriplet result = ai[playerTurn-2].aiTurn(this.boardPieceLocations, this.homeLocations, this.finishLocations, this.roll, playerTurn);
                if (result != null) {
                    if (result.getMoveType() == 'a') { //The Artificial Opponent is moving from one board location to another AND gets to roll again
                        this.boardPieceLocations[result.getPreviousIndex()] = 0;
                        if (this.boardPieceLocations[result.getNextIndex()] != 0)
                            returnPieceHome(this.boardPieceLocations[result.getNextIndex()]);
                        this.boardPieceLocations[result.getNextIndex()] = this.playerTurn;
                        if (result.getPreviousIndex() == 32)
                            this.buttons[result.getPreviousIndex()].setBackground(outButtonDrawables[0]);
                        else if (result.getPreviousIndex() == 2)
                            this.buttons[result.getPreviousIndex()].setBackground(outButtonDrawables[1]);
                        else if (result.getPreviousIndex() == 22)
                            this.buttons[result.getPreviousIndex()].setBackground(outButtonDrawables[2]);
                        else if (result.getPreviousIndex() == 12)
                            this.buttons[result.getPreviousIndex()].setBackground(outButtonDrawables[3]);
                        else
                            this.buttons[result.getPreviousIndex()].setBackgroundColor(getResources().getColor(R.color.colorHoles));
                        this.buttons[result.getNextIndex()].setBackgroundColor(colors[playerTurn - 1]);
                    } else if (result.getMoveType() == 'b') { //The Artificial Opponent is moving from one home location to the board AND gets to roll again
                        this.homeLocations[playerTurn - 1][result.getPreviousIndex()] = 0;
                        if (this.boardPieceLocations[result.getNextIndex()] != 0)
                            returnPieceHome(this.boardPieceLocations[result.getNextIndex()]);
                        this.boardPieceLocations[result.getNextIndex()] = this.playerTurn;
                        this.homeButtons[playerTurn - 1][result.getPreviousIndex()].setBackgroundColor(getResources().getColor(R.color.colorHoles));
                        this.buttons[result.getNextIndex()].setBackgroundColor(colors[playerTurn - 1]);
                    } else if (result.getMoveType() == 'c') { //The Artificial Opponent is moving from one board location to finish location AND gets to roll again
                        this.boardPieceLocations[result.getPreviousIndex()] = 0;
                        this.finishLocations[playerTurn - 1][result.getNextIndex()] = this.playerTurn;
                        this.buttons[result.getPreviousIndex()].setBackgroundColor(getResources().getColor(R.color.colorHoles));
                        this.finishButtons[playerTurn - 1][result.getNextIndex()].setBackground(finishButtonDrawablesFull[playerTurn - 1]);
                    } else if (result.getMoveType() == 'd') { //The Artificial Opponent is moving from one finish location to another AND gets to roll again
                        this.finishLocations[playerTurn - 1][result.getPreviousIndex()] = 0;
                        this.finishLocations[playerTurn - 1][result.getNextIndex()] = this.playerTurn;
                        this.finishButtons[playerTurn - 1][result.getPreviousIndex()].setBackground(finishButtonDrawablesEmpty[playerTurn - 1]);
                        this.finishButtons[playerTurn - 1][result.getNextIndex()].setBackground(finishButtonDrawablesFull[playerTurn - 1]);
                    } else if (result.getMoveType() == 'e') { //The Artificial Opponent is moving from one board location to finish location
                        this.boardPieceLocations[result.getPreviousIndex()] = 0;
                        this.finishLocations[playerTurn - 1][result.getNextIndex()] = this.playerTurn;
                        this.buttons[result.getPreviousIndex()].setBackgroundColor(getResources().getColor(R.color.colorHoles));
                        this.finishButtons[playerTurn - 1][result.getNextIndex()].setBackground(finishButtonDrawablesFull[playerTurn - 1]);
                        turnFinished = true;
                    } else if (result.getMoveType() == 'f') { //The Artificial Opponent is moving from one finish location to another
                        this.finishLocations[playerTurn - 1][result.getPreviousIndex()] = 0;
                        this.finishLocations[playerTurn - 1][result.getNextIndex()] = this.playerTurn;
                        this.finishButtons[playerTurn - 1][result.getPreviousIndex()].setBackground(finishButtonDrawablesEmpty[playerTurn - 1]);
                        this.finishButtons[playerTurn - 1][result.getNextIndex()].setBackground(finishButtonDrawablesFull[playerTurn - 1]);
                        turnFinished = true;
                    } else if (result.getMoveType() == 'h') { //The Artificial Opponent is moving from one home location to the board
                        this.homeLocations[playerTurn - 1][result.getPreviousIndex()] = 0;
                        if (this.boardPieceLocations[result.getNextIndex()] != 0)
                            returnPieceHome(this.boardPieceLocations[result.getNextIndex()]);
                        this.boardPieceLocations[result.getNextIndex()] = this.playerTurn;
                        this.homeButtons[playerTurn - 1][result.getPreviousIndex()].setBackgroundColor(getResources().getColor(R.color.colorHoles));
                        this.buttons[result.getNextIndex()].setBackgroundColor(colors[playerTurn - 1]);
                        turnFinished = true;
                    } else if (result.getMoveType() == 'r') { //The Artificial Opponent is moving from one board location to another
                        this.boardPieceLocations[result.getPreviousIndex()] = 0;
                        if (this.boardPieceLocations[result.getNextIndex()] != 0)
                            returnPieceHome(this.boardPieceLocations[result.getNextIndex()]);
                        this.boardPieceLocations[result.getNextIndex()] = this.playerTurn;
                        if (result.getPreviousIndex() == 32)
                            this.buttons[result.getPreviousIndex()].setBackground(outButtonDrawables[0]);
                        else if (result.getPreviousIndex() == 2)
                            this.buttons[result.getPreviousIndex()].setBackground(outButtonDrawables[1]);
                        else if (result.getPreviousIndex() == 22)
                            this.buttons[result.getPreviousIndex()].setBackground(outButtonDrawables[2]);
                        else if (result.getPreviousIndex() == 12)
                            this.buttons[result.getPreviousIndex()].setBackground(outButtonDrawables[3]);
                        else
                            this.buttons[result.getPreviousIndex()].setBackgroundColor(getResources().getColor(R.color.colorHoles));
                        this.buttons[result.getNextIndex()].setBackgroundColor(colors[playerTurn - 1]);
                        turnFinished = true;
                    }
                } else turnFinished = true;
                //Else to just roll again with no movement

            }
            checkGameOver(); //checking if the game is now over after the last move
        }

        this.diceBTN.setEnabled(true);
        this.playerTurn = 1;
    }


    public void onClickHome(View view){
        Intent intent= new Intent();
        startActivity(new Intent(PlayActivity.this, MainActivity.class));
    }

    public void onClickPlayAgain(View view){
        Intent intent= new Intent();
        startActivity(new Intent(PlayActivity.this, PlayActivity.class));
    }



    private void enableBoard(int player){
        boolean enabled=false;
        this.diceBTN.setEnabled(false);
        if (this.boardPieceLocations[32] == player && !(this.boardPieceLocations[32 + this.roll] == player) && !homeEmpty(1)) {//Meaning the out is full with own player. So need to disable all buttons but the one on the out
            disableBoard();
            enabled=true;
            buttons[32].setEnabled(true);
        } else if (this.roll == 6 && this.boardPieceLocations[32] != player && !homeEmpty(1)) { //Only enable the home buttons
            enabled=true;
            for (int locations = 0; locations < this.homeButtons[player].length; locations++) {
                if (this.homeLocations[0][locations]==1){
                    this.homeButtons[0][locations].setEnabled(true);
                }
            }
        } else { //else only enable what can be moved so long as the board is not empty
            for (int locations = 0; locations < this.buttons.length; locations++) {
                if (this.boardPieceLocations[locations] == player && this.boardPieceLocations[(locations + this.roll) % 40] != player && ((locations + this.roll) <= 31 || locations >= 32)) { //If this is the case then the move would from 1 regular space to another
                    this.buttons[locations].setEnabled(true);
                    enabled=true;
                } else if (this.boardPieceLocations[locations] == player && (locations + this.roll) > 31 && (locations + this.roll) <= 35 && finishLocations[0][(locations + this.roll) - 32] != 1) { //Else if this is the case then the move is allowed and its from a regular space to the finish
                    this.buttons[locations].setEnabled(true);
                    enabled=true;
                }
            }
            for (int x = 0; x < this.finishButtons[0].length; x++) {
                if (this.finishLocations[0][x] == 1 && (this.roll + x) < 4 && this.finishLocations[0][this.roll + x] != 1) {
                    this.finishButtons[0][x].setEnabled(true);
                    enabled = true;
                }
            }
        }
        if (!enabled){
            findViewById(R.id.btnChangeTurn).setVisibility(View.VISIBLE);
            disableBoard();
        }
    }

    /**
     * This method checks to see if the specified player has any pieces active in play
     * @param player the player for which to check his board for
     * @return true- if the player has no active pieces on the board. False otherwise
     */
    private boolean boardEmpty(int player) {
        for (int location = 0; location < this.boardPieceLocations.length; location++) {
            if (this.boardPieceLocations[location] == player) return false;
        }
        return true;
    }

    /**
     * This method checks to see if the specified player has any playing pieces in their home location left
     * @param player the player for which to check their home
     * @return true if the home contains no playing pieces, false otherwise
     */
    private boolean homeEmpty (int player){
        for (int i=0; i<homeLocations[player-1].length; i++){
            if (homeLocations[player-1][i]==player) return false;
        }
        return true;
    }

    private void disableBoard(){
        for (int location = 0; location < this.boardPieceLocations.length; location++) {
            if (this.boardPieceLocations[location]==1) {
                this.buttons[location].setEnabled(false);
            }
        }
        for (int location=0; location<homeButtons[0].length; location++){
            if (this.homeLocations[0][location]==1) {
                this.homeButtons[0][location].setEnabled(false);
            }
        }
        for (int location=0; location<finishButtons[0].length; location++){
            if (this.finishLocations[0][location]==1) {
                this.finishButtons[0][location].setEnabled(false);
            }
        }
    }

    private boolean returnPieceHome(int playerRemoved){
        for (int x=0; x<homeLocations[playerRemoved-1].length ;x++){
            if (homeLocations[playerRemoved-1][x]==0){
                homeLocations[playerRemoved-1][x]= playerRemoved;
                homeButtons[playerRemoved-1][x].setBackgroundColor(colors[playerRemoved-1]);
                return true;
            }
        }
        return false;
    }

    private void diceRoll(){
        this.roll = this.dice.roll();
        this.rolls++;
        if (this.roll == 1) {
            findViewById(R.id.btnDice).setBackgroundResource(R.drawable.dice1);
        } else if (this.roll == 2) {
            findViewById(R.id.btnDice).setBackgroundResource(R.drawable.dice2);
        } else if (this.roll == 3) {
            findViewById(R.id.btnDice).setBackgroundResource(R.drawable.dice3);
        } else if (this.roll == 4) {
            findViewById(R.id.btnDice).setBackgroundResource(R.drawable.dice4);
        } else if (this.roll == 5) {
            findViewById(R.id.btnDice).setBackgroundResource(R.drawable.dice5);
        } else {
            findViewById(R.id.btnDice).setBackgroundResource(R.drawable.dice6);
        }
    }

    /**
     * This method checks to see if the game is over. I.e. someone has won
     */
    private void checkGameOver(){
        for (int x=0; x<finishLocations.length ;x++){
            boolean finished=true;
            for (int y=0; y< finishLocations[x].length; y++){
                if (finishLocations[x][y]==0) finished=false;
            }
            if (finished)gameOver(x+1);
        }
    }

    /**
     * This method will disable eveything, and create and call on the game over activity
     * @param winner the winner of the game
     */
    private void gameOver(int winner){
        disableBoard();

        this.diceBTN.setVisibility(View.INVISIBLE);
        findViewById(R.id.btnChangeTurn).setVisibility(View.INVISIBLE);
        findViewById(R.id.btnGoHome).setVisibility(View.VISIBLE);
        findViewById(R.id.btnPlayAgain).setVisibility(View.VISIBLE);

        TextView tempText= findViewById(R.id.txtPlayersTurn);
        tempText.setTextColor(colors[winner-1]);
        tempText.setTextSize(25);

        if (winner==1) tempText.setText("You won!!!");
        else tempText.setText("Player "+ winner+" won!!!");

    }

}
