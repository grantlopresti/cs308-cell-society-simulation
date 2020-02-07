package simulation.rules;

import javafx.scene.paint.Color;

public class GameOfLifeRule implements Rule {

  private static final int DEAD = 0;
  private static final int ALIVE = 1;

  public GameOfLifeRule(double[] variables) { }

  /**
   * @param currentState
   * @param neighborStates
   * @return
   */
  @Override
  public int calculateNewState(int currentState, int[] neighborStates) {
    int numNeighbors = sum(neighborStates);
    if (currentState == DEAD) {
      return (numNeighbors == 3) ? ALIVE : DEAD;
    }
    return (numNeighbors <= 1 || numNeighbors >= 4) ? DEAD : ALIVE;
  }

  private int sum(int[] intArray) {
    int sum = 0;
    for (int i : intArray) {
      sum += i;
    }
    return sum;
  }

  @Override
  public Color getStateColor(int state) {
    return (state == ALIVE) ? Color.BLACK : Color.WHITE;
  }
}