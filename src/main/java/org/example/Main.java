package org.example;

import org.example.FrameScore.FrameScoreGeneratorImpl;

public class Main {
    public static void main(String[] args) {

//        String[] frames = {"X","X","X","X","X","X","X","X","X","X"};
//        String bonusFrames = "XX";

//        String[] frames = {"9-","9-","9-","9-","9-","9-","9-","9-","9-","9-"};
//        String bonusFrames = "";

//        String[] frames = {"9/","9/","9/","9/","9/","9/","9/","9/","9/","9/"};
//        String bonusFrames = "9";

        String[] frames = {"X","7/","9-","X","-8","8/","-6","X","X","X"};
        String bonusFrames = "81";
        FrameScoreGeneratorImpl fsg= new FrameScoreGeneratorImpl();
        int finalScore = 0;
        for (int i=1; i<11; i++){
            finalScore += fsg.giveScore(i, frames, bonusFrames);
        }

        System.out.println("finalScore = " + finalScore);
    }
}