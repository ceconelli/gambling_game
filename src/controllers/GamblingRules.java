package controllers;

import constants.CommonValues;
import constants.Direction;
import domain.Player;
import repository.IPlayersCircle;

public class GamblingRules {
    private IPlayersCircle playersCircle;
    private int clockWiseSteps;
    private int antiClockWiseSteps;
    private int numberOfPlayers;
    private StringBuilder resultString;

    /**
     * Use dependency injection to pass playerCircle
     * @param p_playersCircle
     * @param p_clockWiseSteps
     * @param p_antiClockWiseSteps
     * @param p_numberOfPlayers
     */
    public GamblingRules(IPlayersCircle p_playersCircle,int p_clockWiseSteps, int p_antiClockWiseSteps,int p_numberOfPlayers) {
        this.playersCircle = p_playersCircle;
        this.clockWiseSteps = p_clockWiseSteps;
        this.antiClockWiseSteps = p_antiClockWiseSteps;
        this.numberOfPlayers = p_numberOfPlayers;
        this.resultString = new StringBuilder();
    }

    /**
     * Add players to the circle. The number of players to be added is passed at the constructor
     */
    public void initializeCircle() {
        for (int i = CommonValues.START_FROM_INDEX; i <= this.numberOfPlayers; i++) {
            this.playersCircle.addPlayerToCircle(new Player(i));
        }
    }

    /**
     * Implements the game logic
     */
    public void playGame() {
        String v_suffix = CommonValues.COMMA;
        Player v_antiClockWiseChosedPlayer = this.playersCircle.getLastPlayer();
        Player v_clockWiseChosedPlayer = this.playersCircle.getRootPlayer();

        while (!v_clockWiseChosedPlayer.isPlayerOut()) {
            v_antiClockWiseChosedPlayer = this.playersCircle.walkAntiClockWise(v_antiClockWiseChosedPlayer, this.antiClockWiseSteps);
            v_clockWiseChosedPlayer = this.playersCircle.walkClockWise(v_clockWiseChosedPlayer, this.clockWiseSteps);

            this.appendResults(this.resultString,v_clockWiseChosedPlayer,v_antiClockWiseChosedPlayer,v_suffix);

            v_antiClockWiseChosedPlayer = this.playersCircle.removePlayer(v_antiClockWiseChosedPlayer, Direction.ANTI_CLOCKWISE);
            if (v_antiClockWiseChosedPlayer.equals(v_clockWiseChosedPlayer)) {
                // This is the case where both players chosed to be removed are side by side.
                v_antiClockWiseChosedPlayer = v_clockWiseChosedPlayer.getPreviousPlayer();
            }
            v_clockWiseChosedPlayer = this.playersCircle.removePlayer(v_clockWiseChosedPlayer, Direction.CLOCKWISE);
        }
        // show result
        System.out.println(this.formatResultString(this.resultString));
    }

    /**
     * format result to have the right number of spaces depending on the number of digits of the player position
     * @param p_player player that position will be used to calculate the formatted string
     * @return formatted player position
     */
    public String getFormattedPlayerPosition(Player p_player) {
        return new String(new char[CommonValues.NUMBER_OF_SPACES - String.valueOf(p_player.getPlayerPosition()).length()]).replace("\0", CommonValues.SPACE) + String.valueOf(p_player.getPlayerPosition());
    }

    /**
     * Trim comma from the end of the result string
     * @param p_result stringBuilder that will be trimmed
     * @return Trimmed string
     */
    public String formatResultString(StringBuilder p_result) {
        return p_result.toString().replaceAll("["+ CommonValues.COMMA + "]$", CommonValues.EMPTY_STRING);
    }

    /**
     * Append results to the final result string considering the two players chose to be removed
     * @param p_result the result string
     * @param p_clockWiseChosedPlayer player chosed clockwisely
     * @param p_antiClockWiseChosedPlayer player chosed anti clockwisely
     * @param p_separator results separator
     */
    public void appendResults(StringBuilder p_result,Player p_clockWiseChosedPlayer,Player p_antiClockWiseChosedPlayer, String p_separator) {
        String v_partialResult;
        if (p_antiClockWiseChosedPlayer.equals(p_clockWiseChosedPlayer)) {
            v_partialResult = this.getFormattedPlayerPosition(p_clockWiseChosedPlayer) + p_separator;
        } else {
            v_partialResult = this.getFormattedPlayerPosition(p_clockWiseChosedPlayer) + this.getFormattedPlayerPosition(p_antiClockWiseChosedPlayer) + p_separator;
        }
        p_result.append(v_partialResult);
    }
}
