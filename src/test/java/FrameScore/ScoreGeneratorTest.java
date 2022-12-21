package FrameScore;

import org.example.FrameScore.FrameScoreGeneratorImpl;
import org.example.FrameScore.ScoreGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ScoreGeneratorTest {

    ScoreGenerator scoreGenerator = new FrameScoreGeneratorImpl();
    @Nested
    class FirstStrikeTests{
        @Test
        void give30WhenCurrentStrikeAndNextTwoStrikes() {
            String[] frames = {"X","X","X","X","X","X","X","X","X","X"};

            Integer actualScore = scoreGenerator.giveScore(1, frames, "");

            Integer expectedScore = 30;

            assertEquals(expectedScore, actualScore);

        }

        @Test
        void give25WhenCurrentStrikeAndOneStrikeAnd5() {
            String[] frames = {"X","X","5-","X","X","X","X","X","X","X"};

            Integer actualScore = scoreGenerator.giveScore(1, frames, "");

            Integer expectedScore = 25;

            assertEquals(expectedScore, actualScore);

        }
        @Test
        void give20WhenCurrentStrikeAndOneStrikeAnd0() {
            String[] frames = {"X","X","-5","X","X","X","X","X","X","X"};

            Integer actualScore = scoreGenerator.giveScore(1, frames, "");

            Integer expectedScore = 20;

            assertEquals(expectedScore, actualScore);

        }
        @Test
        void give20WhenCurrentStrikeAndNextSpare() {
            String[] frames = {"X","5/","-5","X","X","X","X","X","X","X"};

            Integer actualScore = scoreGenerator.giveScore(1, frames, "");

            Integer expectedScore = 20;

            assertEquals(expectedScore, actualScore);

        }
        @Test
        void give15WhenCurrentStrikeAnd5And0() {
            String[] frames = {"X","5-","-5","X","X","X","X","X","X","X"};

            Integer actualScore = scoreGenerator.giveScore(1, frames, "");

            Integer expectedScore = 15;

            assertEquals(expectedScore, actualScore);

        }
        @Test
        void give10WhenCurrentStrikeAnd0And0() {
            String[] frames = {"X","--","-5","X","X","X","X","X","X","X"};

            Integer actualScore = scoreGenerator.giveScore(1, frames, "");

            Integer expectedScore = 10;

            assertEquals(expectedScore, actualScore);

        }
    }

    @Nested
    class FirstSpareTests{
        @Test
        void give20WhenCurrentSpareAndStrikeNext() {
            String[] frames = {"1/","X","-5","X","X","X","X","X","X","X"};

            Integer actualScore = scoreGenerator.giveScore(1, frames, "");

            Integer expectedScore = 20;

            assertEquals(expectedScore, actualScore);
        }

        @Test
        void give15WhenCurrentSpareAnd5() {
            String[] frames = {"1/","5-","-5","X","X","X","X","X","X","X"};

            Integer actualScore = scoreGenerator.giveScore(1, frames, "");

            Integer expectedScore = 15;

            assertEquals(expectedScore, actualScore);
        }
        @Test
        void give10WhenCurrentSpareAnd0() {
            String[] frames = {"1/","--","-5","X","X","X","X","X","X","X"};

            Integer actualScore = scoreGenerator.giveScore(1, frames, "");

            Integer expectedScore = 10;

            assertEquals(expectedScore, actualScore);
        }
    }

    @Nested
    class DigitScoreTests{
        @Test
        void give9When4and5(){
            String[] frames = {"54","--","-5","X","X","X","X","X","X","X"};

            Integer actualScore = scoreGenerator.giveScore(1, frames, "");

            Integer expectedScore = 9;

            assertEquals(expectedScore, actualScore);
        }
    }

    @Nested
    class Frame9and10Tests{
        @Test
        void give30for9Strike10StrikeAndBonusStrike(){
            String[] frames = {"54","--","-5","X","X","X","X","X","X","X"};

            Integer actualScore = scoreGenerator.giveScore(9, frames, "X");

            Integer expectedScore = 30;

            assertEquals(expectedScore, actualScore);
        }
        @Test
        void give20for9Strike10SpareAndBonusStrike(){
            String[] frames = {"54","--","-5","X","X","X","X","X","X","5/"};

            Integer actualScore = scoreGenerator.giveScore(9, frames, "X");

            Integer expectedScore = 20;

            assertEquals(expectedScore, actualScore);
        }
        @Test
        void give15for9Strike10Hit5AndBonusStrike(){
            String[] frames = {"54","--","-5","X","X","X","X","X","X","5-"};

            Integer actualScore = scoreGenerator.giveScore(9, frames, "X");

            Integer expectedScore = 15;

            assertEquals(expectedScore, actualScore);
        }
        @Test
        void give20for9Spare10StrikeAndBonusStrike(){
            String[] frames = {"54","--","-5","X","X","X","X","X","5/","X"};

            Integer actualScore = scoreGenerator.giveScore(9, frames, "X");

            Integer expectedScore = 20;

            assertEquals(expectedScore, actualScore);
        }
        @Test
        void give15for9Spare10Hit5AndBonusStrike(){
            String[] frames = {"54","--","-5","X","X","X","X","X","5/","5-"};

            Integer actualScore = scoreGenerator.giveScore(9, frames, "X");

            Integer expectedScore = 15;

            assertEquals(expectedScore, actualScore);
        }
        @Test
        void give15for9Spare10SpareAndBonusStrike(){
            String[] frames = {"54","--","-5","X","X","X","X","X","5/","5/"};

            Integer actualScore = scoreGenerator.giveScore(9, frames, "X");

            Integer expectedScore = 15;

            assertEquals(expectedScore, actualScore);
        }
        @Test
        void give9for9Hit5and4AndBonusStrike(){
            String[] frames = {"54","--","-5","X","X","X","X","X","54","5-"};

            Integer actualScore = scoreGenerator.giveScore(9, frames, "X");

            Integer expectedScore = 9;

            assertEquals(expectedScore, actualScore);
        }

        @Test
        void give30for10StrikeAndBonus2Strikes(){
            String[] frames = {"54","--","-5","X","X","X","X","X","X","X"};

            Integer actualScore = scoreGenerator.giveScore(10, frames, "XX");

            Integer expectedScore = 30;

            assertEquals(expectedScore, actualScore);
        }
        @Test
        void give20for10StrikeAndBonusSpare(){
            String[] frames = {"54","--","-5","X","X","X","X","X","X","X"};

            Integer actualScore = scoreGenerator.giveScore(10, frames, "7/");

            Integer expectedScore = 20;

            assertEquals(expectedScore, actualScore);
        }
        @Test
        void give17for10StrikeAndBonus5and2(){
            String[] frames = {"54","--","-5","X","X","X","X","X","X","X"};

            Integer actualScore = scoreGenerator.giveScore(10, frames, "52");

            Integer expectedScore = 17;

            assertEquals(expectedScore, actualScore);
        }
        @Test
        void give20for10SpareAndBonus1Strikes(){
            String[] frames = {"54","--","-5","X","X","X","X","X","X","2/"};

            Integer actualScore = scoreGenerator.giveScore(10, frames, "X");

            Integer expectedScore = 20;

            assertEquals(expectedScore, actualScore);
        }
        @Test
        void give15for10SpareAndBonus5(){
            String[] frames = {"54","--","-5","X","X","X","X","X","X","2/"};

            Integer actualScore = scoreGenerator.giveScore(10, frames, "5");

            Integer expectedScore = 15;

            assertEquals(expectedScore, actualScore);
        }
        @Test
        void give7for10Hits5and2(){
            String[] frames = {"54","--","-5","X","X","X","X","X","X","52"};

            Integer actualScore = scoreGenerator.giveScore(10, frames, "");

            Integer expectedScore = 7;

            assertEquals(expectedScore, actualScore);
        }

        @Test
        void throwExceptionWhenStrikeOrSpareAndBonusNull(){
            String[] frames = {"54","--","-5","X","X","X","X","X","X","X"};

            RuntimeException runtimeException = assertThrows(RuntimeException.class,
                    () -> scoreGenerator.giveScore(10, frames, ""));

            assertEquals("Bonus Frame required as 10th ball Strike/Spare", runtimeException.getMessage());
        }
    }
}