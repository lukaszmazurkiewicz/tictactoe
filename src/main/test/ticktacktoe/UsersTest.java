package ticktacktoe;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UsersTest {

    @Test
    public void testIsUserWin() {
        //Given
        Users users = new Users();
        //When
        boolean result = users.isUserWin();
        //Then
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsCompWin() {
        //Given
        Users users = new Users();
        //When
        boolean result = users.isCompWin();
        //Then
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsDraw() {
        //Given
        Users users = new Users();
        //When
        boolean result = users.isDraw();
        //Then
        Assert.assertEquals(false, result);
    }

    @Test
    public void testGetUserScore() {
        //Given
        Users users = new Users();
        users.setUserScore(21);
        //When
        int result = users.getUserScore();
        //Then
        Assert.assertEquals(21, result);
    }

    @Test
    public void testGetCompScore() {
        //Given
        Users users = new Users();
        users.setCompScore(7);
        //When
        int result = users.getCompScore();
        //Then
        Assert.assertEquals(7, result);
    }

    @Test
    public void testSetUserWin() {
        //Given
        Users users = new Users();
        //When
        users.setUserWin(true);
        boolean result = users.isUserWin();
        //Then
        Assert.assertEquals(true, result);
    }

    @Test
    public void testSetCompWin() {
        //Given
        Users users = new Users();
        //When
        users.setCompWin(true);
        boolean result = users.isCompWin();
        //Then
        Assert.assertEquals(true, result);
    }

    @Test
    public void testSetDraw() {
        //Given
        Users users = new Users();
        //When
        users.setDraw(true);
        boolean result = users.isDraw();
        //Then
        Assert.assertEquals(true, result);
    }

    @Test
    public void setUserScore() {
        //Given
        Users users = new Users();
        //When
        users.setUserScore(33);
        //Then
        Assert.assertEquals(33, users.getUserScore());
    }

    @Test
    public void setCompScore() {
        //Given
        Users users = new Users();
        //When
        users.setUserScore(89);
        //Then
        Assert.assertEquals(89, users.getUserScore());
    }
}