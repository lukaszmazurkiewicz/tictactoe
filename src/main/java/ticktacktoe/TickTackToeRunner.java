package ticktacktoe;

import javafx.application.Application;
import javafx.stage.Stage;

public class TickTackToeRunner extends Application {

    private Game game = new Game();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setResizable(false);

        game.gamePlay(primaryStage);


    }
}
