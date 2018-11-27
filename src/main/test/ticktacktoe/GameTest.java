package ticktacktoe;

import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

public class GameTest extends ApplicationTest {

    Game game = new Game();

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setResizable(false);

        game.gamePlay(primaryStage);


    }

    @After
    public void tearDown () throws Exception {
        FxToolkit.hideStage();
        release(new KeyCode[]{});
        release(new MouseButton[]{});
    }



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
    public void testZeroingScore() {
        //Given
        Game game = new Game();
        UsersResults usersResults = new UsersResults();

        usersResults.setUserScore(21);
        usersResults.setCompScore(321);
        //When
        //game.gamePlay(primaryStage);

        game.zeroingScore();
        //Then
        Assert.assertEquals(0, usersResults.getUserScore());
        Assert.assertEquals(0, usersResults.getCompScore());
    }
}