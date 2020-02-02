package model;

import javafx.scene.paint.Color;
import rules.Rules;

public class Cell {
    private Cell[] myNeighbors; // Indexed starting with 'up' neighbor and moving clockwise.
    private Rules myRules;

    private int myState; // convert to enum
    private int myNextState;
    private Color myColor;

    public Cell(Rules ruleType, int state){
        myRules = ruleType;
        myState = state;
        myColor = myRules.getStateColor(state);
        myNeighbors = new Cell[8];
    }

    // 0: up, 1: up-right, 2: right, 3: down-right, 4: down, 5: down-left; 6: left; 7: up-left
    public void setNeighbor(int index, Cell pointer) {
        myNeighbors[index] = pointer;
    }

    public void getNextState(){
        int[] neighborStates = new int[myNeighbors.length];
        for (int i = 0; i < myNeighbors.length; i++) {
            if (myNeighbors[i] != null) {
                neighborStates[i] = myNeighbors[i].myState;
            }
        }

        myNextState = myRules.calculateNewState(myState, neighborStates);
    }

    public void updateState(){
        if (myNextState < 0) {
            if (myNeighbors[-1*myNextState].myState == 0) {
                myNeighbors[-1*myNextState].myState = myNextState;
                myNeighbors[-1*myNextState].myColor = myRules.getStateColor(myState);
                myState = 0;
            }
        } else {
            myState = myNextState;
            myColor = myRules.getStateColor(myState);
        }
    }

    public int getState() {
        return myState;
    }

    public Color getColor() {
        return myColor;
    }
}

