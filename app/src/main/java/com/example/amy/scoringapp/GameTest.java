package com.example.amy.scoringapp;

import android.test.InstrumentationTestCase;

/**
 * Created by Mark on 6/10/2015.
 */
public class GameTest extends InstrumentationTestCase{
    public void testCourtNum(){
        Game game = new Game();

        game.setCourtNum(15);
        assertEquals (15, game.getCourtNum());
    }

    public void testTime(){
        Game game = new Game();

        assertTrue(game.time.toString().equals("The time is: 1:30 PM"));
    }


}
