package main;

import constants.CommonValues;
import controllers.GamblingRules;
import repository.PlayersCircle;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Gambling {
    public static void main(String[] args) {
        int numberOfPlayers = 1, clockWiseSteps = 1, antiClockWiseSteps = 1;
        Scanner in = new Scanner(System.in);
        String inputString;
        //loop while user input is not equal to 0 0 0
        while (!(numberOfPlayers == CommonValues.INFERIOR_LIMIT) && !(clockWiseSteps == CommonValues.INFERIOR_LIMIT) && !(antiClockWiseSteps == CommonValues.INFERIOR_LIMIT)) {
            // The code below assures that the user input is correct
            inputString = in.nextLine();
            if (inputString.split("[ ]+").length != CommonValues.USER_INPUT) {
                return;
            }
            String[] v_splitedInputValues = inputString.split("[ ]+");
            try {
                numberOfPlayers = parseInt(v_splitedInputValues[0]);
                clockWiseSteps = parseInt(v_splitedInputValues[1]);
                antiClockWiseSteps = parseInt(v_splitedInputValues[2]);
            } catch (Exception ex) {
                return;
            }

            if (numberOfPlayers >= CommonValues.LIMIT_OF_PLAYERS || numberOfPlayers <= CommonValues.INFERIOR_LIMIT || clockWiseSteps <= CommonValues.INFERIOR_LIMIT || antiClockWiseSteps <= CommonValues.INFERIOR_LIMIT) {
                return;
            }
            //start gambling
            GamblingRules gamblingRules = new GamblingRules(new PlayersCircle(),clockWiseSteps,antiClockWiseSteps,numberOfPlayers);
            gamblingRules.initializeCircle();
            gamblingRules.playGame();
        }
    }
}
