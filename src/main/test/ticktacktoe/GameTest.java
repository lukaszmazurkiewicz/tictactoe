package ticktacktoe;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void gamePlay() {
    }

    @Test
    public void handleButtonClick() {
    }

    @Test
    public void showScore() {
    }

    @Test
    public void newGame() {
    }

    @Test
    public void handleUserClick() {
    }

    @Test
    public void zeroingScore() {
        //Given
        Game game = new Game();
        Users users = new Users();

        users.setUserScore(21);
        users.setCompScore(321);
        //When
        zeroingScore();
        //Then
        Assert.assertEquals(21, users.getUserScore());
        Assert.assertEquals(321, users.getCompScore());
    }
}