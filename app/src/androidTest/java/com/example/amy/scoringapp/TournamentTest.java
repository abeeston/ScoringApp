package com.example.amy.scoringapp;

import android.test.InstrumentationTestCase;

import java.util.Date;

/**
 * Created by Trevor on 6/13/2015.
 */
public class TournamentTest extends InstrumentationTestCase {
    public void testCity() {
        Tournament tourn = new Tournament();
        String temp = "Tahoe";

        assertEquals(null, tourn.getCity());

        tourn.setCity(temp);
        assertEquals(temp, tourn.getCity());
    }

    public void testDate() {
        Tournament tourn = new Tournament();
        String date = "01/22/89";

        tourn.setDate(date);
        assertEquals(date, tourn.getDate());
    }

}
