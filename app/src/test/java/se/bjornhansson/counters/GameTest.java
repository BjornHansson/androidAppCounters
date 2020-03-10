package se.bjornhansson.counters;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GameTest {
    @Test
    public void testThatGameGeneratesNumber() {
        Game game = new Game();
        assertTrue(game.getMyTargetNumber() >= 100);
    }
}