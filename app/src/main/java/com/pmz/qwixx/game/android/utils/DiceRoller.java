package com.pmz.qwixx.game.android.utils;

import java.util.Random;

/**
 * Created by pasko on 21-Jan-18.
 */

public class DiceRoller {

    private int redDie;
    private int yellowDie;
    private int greenDie;
    private int blueDie;
    private int whiteDie1;
    private int whiteDie2;
    private Random rand;

    public DiceRoller() {
        rand = new Random();
        rollDice();
    }

    public void rollDice() {
       setRedDie(generateRandomNumber());
       setYellowDie(generateRandomNumber());
       setGreenDie(generateRandomNumber());
       setBlueDie(generateRandomNumber());
       setWhiteDie1(generateRandomNumber());
       setWhiteDie2(generateRandomNumber());
    }

    public int generateRandomNumber() {
        return rand.nextInt(6) + 1;
    }

    public int getRedDie() {
        return redDie;
    }

    public void setRedDie(int redDie) {
        this.redDie = redDie;
    }

    public int getYellowDie() {
        return yellowDie;
    }

    public void setYellowDie(int yellowDie) {
        this.yellowDie = yellowDie;
    }

    public int getGreenDie() {
        return greenDie;
    }

    public void setGreenDie(int greenDie) {
        this.greenDie = greenDie;
    }

    public int getBlueDie() {
        return blueDie;
    }

    public void setBlueDie(int blueDie) {
        this.blueDie = blueDie;
    }

    public int getWhiteDie1() {
        return whiteDie1;
    }

    public void setWhiteDie1(int whiteDie1) {
        this.whiteDie1 = whiteDie1;
    }

    public int getWhiteDie2() {
        return whiteDie2;
    }

    public void setWhiteDie2(int whiteDie2) {
        this.whiteDie2 = whiteDie2;
    }
}
