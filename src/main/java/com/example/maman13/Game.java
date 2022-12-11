package com.example.maman13;

import java.util.Locale;

public class Game {
    private int stage;
    private String currentGuess;
    private String targetWord;
    private boolean isStarted;

    public Game() {
        this.stage = 0;
        this.targetWord = "Hello";
        this.isStarted = false;
        this.currentGuess = "*****";
    }

    public void updateGuess(char c, int j) {
        try {
            for (int i = j; i < this.currentGuess.length(); i++) {
                if (this.targetWord.toUpperCase().charAt(i) == c) {
                    this.currentGuess = currentGuess.substring(0, i) + c + currentGuess.substring(i + 1);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            System.out.println("The arrays should be the same length, please check");
        }
    }

    public int getStage() {
        return this.stage;
    }

    public String getTargetWord() {
        return this.targetWord;
    }
    private void resetCurrentGuess(){
        this.currentGuess = "*".repeat(this.targetWord.length());
    }

    public boolean nextStage() {
        if (stage < 6) {
            this.stage++;
            return true;
        } else {
            if (stage == 6) {
                this.stage++;
            }
            return false;
        }
    }

    public void startOver() {
        this.stage = 0;
        this.resetCurrentGuess();
        this.isStarted = true;

    }

    public boolean getIsStarted() {
        return isStarted;
    }

    public void setIsStrated(boolean inp) {
        this.isStarted = inp;
    }

    public String getCurrentGuess() {
        return this.currentGuess;
    }

    public boolean checkWin() {
        return (this.targetWord.toUpperCase()).equals(this.currentGuess.toUpperCase());
    }
}
