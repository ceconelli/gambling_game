package domain;

import constants.Direction;

public class Player {
    private Player nextPlayer;
    private Player previousPlayer;
    private int playerPosition;
    private boolean playerOut;

    public boolean isPlayerOut() {
        return playerOut;
    }

    public void setPlayerOut(boolean playerOut) {
        this.playerOut = playerOut;
    }

    public Player(int p_playerPosition) {
        this.playerPosition = p_playerPosition;
        this.playerOut = false;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public Player getPreviousPlayer() {
        return previousPlayer;
    }

    public void setPreviousPlayer(Player previousPlayer) {
        this.previousPlayer = previousPlayer;
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(int playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Player getNextPlayer(Direction p_direction) {
        if (p_direction.equals(Direction.ANTI_CLOCKWISE)) {
            return this.getPreviousPlayer();
        }
        return this.getNextPlayer();
    }
}
