package repository;

import constants.Direction;
import domain.Player;

public interface IPlayersCircle {
    /**
     * Add player to the circle
     * @param p_newPlayer player to be added to the circle
     **/
    void addPlayerToCircle(Player p_newPlayer);

    /**
     * Iterate clockWisely from player passed by param and chose a player to be removed.
     * @param p_startCountingFromPlayer counting start from this player
     * @param p_numberOfSteps number of steps that will be taken to chose a player
     * @return chosen player
     */
    Player walkClockWise(Player p_startCountingFromPlayer,int p_numberOfSteps);
    /**
     * Iterate AnticlockWisely from player passed by param and chose a player to be removed.
     * @param p_startCountingFromPlayer counting start from this player
     * @param p_numberOfSteps number of steps that will be taken to chose a player
     * @return chosen player
     */
    Player walkAntiClockWise(Player p_startCountingFromPlayer,int p_numberOfSteps);

    /**
     * Get first player from circle
     * @return first player
     */
    Player getRootPlayer();
    /**
     * Get last player from circle
     * @return first player
     */
    Player getLastPlayer();

    /**
     * Remove player passed by param from circle
     * @param p_playerToBeRemoved player that will be removed
     * @param p_direction the direction (CLOCKWISE or ANTI_CLOCKWISE) that will be used to chose the next player
     * @return next player that counting will start from
     */
    Player removePlayer(Player p_playerToBeRemoved, Direction p_direction);
}
