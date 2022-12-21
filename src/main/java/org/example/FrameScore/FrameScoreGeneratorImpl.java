package org.example.FrameScore;

public class FrameScoreGeneratorImpl implements ScoreGenerator {

    public Integer giveScore(Integer frameNumber, String[] frames, String bonusFrame) {

        Integer score = 0;
        if (frameNumber <= 8) {
            score = giveScoreWithoutCheckingForBonusThrow(frameNumber, frames);
        }
        else {
            score = giveScoreWithBonusThrow(frameNumber, frames, bonusFrame);
        }
        return score;
    }

    private Integer giveScoreWithBonusThrow(Integer frameNumber, String[] frames, String bonusFrame) {
        Integer currentScore = 0;
        if (frameNumber == 9) {
            currentScore = giveNinthFrameScore(frameNumber, frames, bonusFrame);
        } else if (frameNumber == 10) {
            currentScore = giveTenthFrameScore(frameNumber, frames, bonusFrame);
        }
        return currentScore;
    }

    private Integer giveTenthFrameScore(Integer frameNumber, String[] frames, String bonusFrame) {
        Integer currentScore = 0;

        if (checkStrike(frameNumber, frames)){
            currentScore =
                    currentScore
                    + 10
                    + giveBonusFrameScore(bonusFrame, 0)
                    + giveBonusFrameScore(bonusFrame, 1);
        }
        else if (checkSpare(frameNumber, frames)) {
            currentScore =
                    currentScore
                            + 10
                            + giveBonusFrameScore(bonusFrame, 0);
        }
        else {
            currentScore =
                    currentScore
                            + giveDigitScore(frameNumber, frames,0)
                            + giveDigitScore(frameNumber, frames, 1);
        }
        return currentScore;
    }

    private Integer giveNinthFrameScore(Integer frameNumber, String[] frames, String bonusFrame) {
        Integer currentScore = 0;
        if (checkStrike(frameNumber, frames)){
            currentScore = processCurrentStrikeForNinthFrameWithBonus(frameNumber, frames, bonusFrame, currentScore);
        }else if (checkSpare(frameNumber, frames)) {
            currentScore = processCurrentSpareForNinthFrameWithBonus(frameNumber, frames, currentScore);
        }
        else {
            currentScore =
                    currentScore
                    + giveDigitScore(frameNumber, frames, 0)
                    + giveDigitScore(frameNumber, frames, 1);
        }
        return currentScore;
    }

    private Integer processCurrentSpareForNinthFrameWithBonus(Integer frameNumber, String[] frames, Integer currentScore) {
        currentScore += 10;
        int nextFrame = frameNumber + 1;
        if(checkStrike(nextFrame, frames)){
            currentScore += 10;
        }
        else {
            currentScore += giveDigitScore(nextFrame, frames, 0);
        }
        return currentScore;
    }

    private Integer processCurrentStrikeForNinthFrameWithBonus(Integer frameNumber, String[] frames, String bonusFrame, Integer currentScore) {
        currentScore += 10;
        int nextFrame = frameNumber + 1;
        if (checkStrike(nextFrame, frames)){
            currentScore += 10;
            currentScore += giveBonusFrameScore(bonusFrame, 0);
        } else if (checkSpare(nextFrame, frames)) {
            currentScore += 10;
        }
        else {
            currentScore =
                    currentScore
                            + giveDigitScore(nextFrame, frames, 0)
                            + giveDigitScore(nextFrame, frames, 1);
        }
        return currentScore;
    }

    private Integer giveBonusFrameScore(String bonusFrame, int chanceNumber) {

        if(bonusFrame == "" || bonusFrame == null){
            throw new RuntimeException("Bonus Frame required as 10th ball Strike/Spare");
        }
        char[] scoresForFrame = bonusFrame.toCharArray();

        if (scoresForFrame[chanceNumber] == 'X'){
            return 10;
        } else if (scoresForFrame[chanceNumber] == '/'){
            int firstBonusBallScore = Integer.parseInt(String.valueOf(scoresForFrame[chanceNumber - 1]));
            return  10 - firstBonusBallScore;
        }
        else {
            return Integer.parseInt(String.valueOf(scoresForFrame[chanceNumber]));
        }
    }

    private Integer giveScoreWithoutCheckingForBonusThrow(Integer frameNumber, String[] frames) {
        Integer score = 0;
        String currentFrame = frames[frameNumber - 1];

        char[] scoreOfEachRound = currentFrame.toCharArray();

        if (scoreOfEachRound[0] == 'X'){
            score = processStrike(frameNumber, frames);
        }
        else if (checkSpare(frameNumber, frames)) {
            score = processSpare(frameNumber, frames);
        }
        else {
           score = giveDigitScore(frameNumber, frames, 0)
                   +
                   giveDigitScore(frameNumber, frames, 1);
        }
        return score;
    }

    private Integer processSpare(Integer frameNumber, String[] frames) {
        Integer currentScore = 10;

        int nextFrame = frameNumber + 1;

        if (checkStrike(nextFrame, frames)){
            currentScore += 10;
        }
        else {
            currentScore += giveDigitScore(nextFrame, frames, 0);
        }

        return currentScore;
    }

    private Integer processStrike(Integer frameNumber, String[] frames) {
        Integer currentScore = 10;

        int nextFrame = frameNumber + 1;
        if(checkStrike(nextFrame, frames)){
            currentScore = processNextFrameStrike(frameNumber, frames, currentScore);
        }
        else {
            if(checkSpare(nextFrame, frames)){
                currentScore += 10;
            }
            else {
                currentScore += giveDigitScore(nextFrame, frames, 0)
                                +
                                giveDigitScore(nextFrame, frames, 1);
            }
        }
        return currentScore;
    }

    private Integer processNextFrameStrike(Integer frameNumber, String[] frames, Integer currentScore) {
        currentScore += 10;
        int secondFrameAfter = frameNumber + 2;
        if (checkStrike(secondFrameAfter, frames)){
            currentScore +=10;
        } else  {
            currentScore += giveDigitScore(secondFrameAfter, frames, 0);
        }
        return currentScore;
    }

    private boolean checkSpare(int frameNumber, String[] frames) {
        String currentFrame = frames[frameNumber - 1];
        return currentFrame.contains("/");
    }

    private Integer giveDigitScore(int frameNo, String[] frames, int chanceNumber) {
        String currentFrame = frames[frameNo - 1];

        char[] scoreOfEachRound = currentFrame.toCharArray();

        if (scoreOfEachRound[chanceNumber] >= '0' && scoreOfEachRound[chanceNumber]<= '9'){
            return Integer.parseInt(String.valueOf(scoreOfEachRound[chanceNumber]));
        }
        
        return 0; //Return 0 when not a digit ( '-' given)
    }

    private boolean checkStrike(int frameNo, String[] frames) {
        String currentFrame = frames[frameNo - 1 ];

        char[] scoreOfEachRound = currentFrame.toCharArray();

        return scoreOfEachRound[0]=='X';

    }
}
