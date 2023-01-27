package com.example.myflappybird.interface_elements;

public class ScoreText extends TextObject {
    private int scoreCount = 0;
    public ScoreText(float x, float y) {
        super(x, y);
    }

    @Override
    public void changeText() {
        scoreCount += 1;

    }

    @Override
    public void setDefault() {
        scoreCount = 0;
    }

    public int getScoreCount() {
        return scoreCount;
    }
}
