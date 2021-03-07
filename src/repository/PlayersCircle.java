package repository;

import constants.CommonValues;
import constants.Direction;
import domain.Player;

public class PlayersCircle implements IPlayersCircle {

    private Player rootPlayer;
    private Player lastPlayer;

    public PlayersCircle() {
    }

    public void addPlayerToCircle(Player p_newPlayer){
        if (this.rootPlayer == null) {
            this.rootPlayer = p_newPlayer;
            this.lastPlayer = p_newPlayer;
            this.rootPlayer.setNextPlayer(this.rootPlayer);
            this.rootPlayer.setPreviousPlayer(this.rootPlayer);
        } else {
            this.rootPlayer.getPreviousPlayer().setNextPlayer(p_newPlayer);
            p_newPlayer.setPreviousPlayer(this.rootPlayer.getPreviousPlayer());
            p_newPlayer.setNextPlayer(this.rootPlayer);
            this.rootPlayer.setPreviousPlayer(p_newPlayer);
            this.lastPlayer = p_newPlayer;
        }
    }

    public Player walkClockWise(Player p_startCountingFromPlayer,int p_numberOfSteps) {
        Player v_chosenPlayer = p_startCountingFromPlayer;
        for (int i = CommonValues.START_FROM_INDEX; i < p_numberOfSteps; i++) {
            v_chosenPlayer = v_chosenPlayer.getNextPlayer();
        }
        v_chosenPlayer.setPlayerOut(true);
        return v_chosenPlayer;
    }

    public Player walkAntiClockWise(Player p_startCountingFromPlayer,int p_numberOfSteps) {
        Player v_chosenPlayer = p_startCountingFromPlayer;
        for (int i = CommonValues.START_FROM_INDEX; i < p_numberOfSteps; i++) {
            v_chosenPlayer = v_chosenPlayer.getPreviousPlayer();
        }
        v_chosenPlayer.setPlayerOut(true);
        return v_chosenPlayer;
    }

    public Player getRootPlayer() {
        return this.rootPlayer;
    }

    public Player getLastPlayer() {
        return this.lastPlayer;
    }

    public Player removePlayer(Player p_playerToBeRemoved, Direction p_direction) {
        p_playerToBeRemoved.getPreviousPlayer().setNextPlayer(p_playerToBeRemoved.getNextPlayer());
        p_playerToBeRemoved.getNextPlayer().setPreviousPlayer(p_playerToBeRemoved.getPreviousPlayer());
        return p_playerToBeRemoved.getNextPlayer(p_direction);
    }

}
