package ticktacktoe;

import org.junit.Assert;
import org.junit.Test;

public class UsersResultsTest {

    @Test
    public void testIsUserWin() {
        //Given
        UsersResults usersResults = new UsersResults();
        //When
        boolean result = usersResults.isUserWin();
        //Then
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsCompWin() {
        //Given
        UsersResults usersResults = new UsersResults();
        //When
        boolean result = usersResults.isCompWin();
        //Then
        Assert.assertEquals(false, result);
    }

    @Test
    public void testIsDraw() {
        //Given
        UsersResults usersResults = new UsersResults();
        //When
        boolean result = usersResults.isDraw();
        //Then
        Assert.assertEquals(false, result);
    }

    @Test
    public void testGetUserScore() {
        //Given
        UsersResults usersResults = new UsersResults();
        usersResults.setUserScore(21);
        //When
        int result = usersResults.getUserScore();
        //Then
        Assert.assertEquals(21, result);
    }

    @Test
    public void testGetCompScore() {
        //Given
        UsersResults usersResults = new UsersResults();
        usersResults.setCompScore(7);
        //When
        int result = usersResults.getCompScore();
        //Then
        Assert.assertEquals(7, result);
    }

    @Test
    public void testSetUserWin() {
        //Given
        UsersResults usersResults = new UsersResults();
        //When
        usersResults.setUserWin(true);
        boolean result = usersResults.isUserWin();
        //Then
        Assert.assertEquals(true, result);
    }

    @Test
    public void testSetCompWin() {
        //Given
        UsersResults usersResults = new UsersResults();
        //When
        usersResults.setCompWin(true);
        boolean result = usersResults.isCompWin();
        //Then
        Assert.assertEquals(true, result);
    }

    @Test
    public void testSetDraw() {
        //Given
        UsersResults usersResults = new UsersResults();
        //When
        usersResults.setDraw(true);
        boolean result = usersResults.isDraw();
        //Then
        Assert.assertEquals(true, result);
    }

    @Test
    public void setUserScore() {
        //Given
        UsersResults usersResults = new UsersResults();
        //When
        usersResults.setUserScore(33);
        //Then
        Assert.assertEquals(33, usersResults.getUserScore());
    }

    @Test
    public void setCompScore() {
        //Given
        UsersResults usersResults = new UsersResults();
        //When
        usersResults.setUserScore(89);
        //Then
        Assert.assertEquals(89, usersResults.getUserScore());
    }
}