package com.example.amy.scoringapp;

import android.test.InstrumentationTestCase;

/**
 * Created by Amy on 6/10/2015.
 */
public class TeamTest extends InstrumentationTestCase{

    public void testName() {
        Team team1 = new Team();
        String temp = "basketball";

        assertEquals(null, team1.getName());

        team1.setName(temp);
        assertEquals(temp, team1.getName());
    }

    public void testScore() {
        Team team2 = new Team();
        int temp = 7;

        assertEquals(0, team2.getScore());

        team2.setScore(temp);
        assertEquals(temp, team2.getScore());
    }

}
