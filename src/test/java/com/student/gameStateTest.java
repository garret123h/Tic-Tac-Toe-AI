package com.student;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import model.State;

/**
 * Unit test for simple App.
 */
public class gameStateTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public gameStateTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( gameStateTest.class );
    }
  
    /**
     *Tests for the checkWonState method.
     * */
    public void testGameState()
    {
      int[][] pass1 = {{1,0,1},{-1,0,1},{0,0,1}};
      int[][] pass2 = {{0,0,1},{-1,1,0},{1,-1,-1}};
      int[][] fail1 = {{1,-1,0},{0,0,0},{1,1,0}};
      int[][] fail2 = {{0,0,0},{0,0,0},{-1,-1,0}};
      State passOneState = new State(pass1);
      State passTwoState = new State(pass2);
      State failOneState = new State(fail1);
      State failTwoState = new State(fail2);

      assertTrue(passOneState.checkWonState());
      assertTrue(passOneState.checkWonState());
      assertTrue(!failOneState.checkWonState());
      assertTrue(!failTwoState.checkWonState());

    }
}
