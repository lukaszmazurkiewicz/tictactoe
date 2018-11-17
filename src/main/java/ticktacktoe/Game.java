package ticktacktoe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import static ticktacktoe.Graphics.*;
import static ticktacktoe.Graphics.ANIMATION_FOR_X;

public class Game {

    private Pawn pawn;
    private Button exitButton, newGameButton;
    private VBox buttons;

    private HBox topScoreBoard;
    private Text playerOneName, playerOneScore, playerTwoName, playerTwoScore;

    private Users user = new Users();
    //private Pawn pawn;

    public Game() {
        pawn = new Pawn(this);
    }

    public void gamePlay(Stage primaryStage) {
        exitButton = new Button("Exit");
        exitButton.setMinSize(200, 30);
        exitButton.setOnMouseClicked(e -> handleButtonClick(primaryStage, e));

        newGameButton = new Button("New game");
        newGameButton.setMinSize(200, 30);
        newGameButton.setOnMouseClicked(e -> handleButtonClick(primaryStage, e));

        buttons = new VBox(exitButton, newGameButton);
        buttons.setSpacing(20);
        buttons.setPadding(new Insets(20, 20, 20, 20));

        playerOneName = new Text("Player One: ");
        playerOneName.setFont(Font.font("Verdana", 30));
        playerOneName.setFill(Color.AQUA);

        playerOneScore = new Text("0");
        playerOneScore.setFont(Font.font("Verdana", 30));
        playerOneScore.setFill(Color.AQUA);

        playerTwoName = new Text("Computer: ");
        playerTwoName.setFont(Font.font("Verdana", 30));
        playerTwoName.setFill(Color.AQUA);

        playerTwoScore = new Text("0");
        playerTwoScore.setFont(Font.font("Verdana", 30));
        playerTwoScore.setFill(Color.AQUA);

        topScoreBoard = new HBox(playerOneName, playerOneScore, playerTwoName, playerTwoScore);
        topScoreBoard.setSpacing(10);

        newGame(primaryStage);
    }

    public void handleButtonClick(Stage primaryStage, MouseEvent event) {

        if (event.getSource().equals(exitButton)) {
            System.exit(0);
        } else if (event.getSource().equals(newGameButton)) {

            user.setUserScore(0);
            user.setCompScore(0);

            playerOneScore = new Text(Integer.toString(user.getUserScore()));
            playerOneScore.setFont(Font.font("Verdana", 30));
            playerOneScore.setFill(Color.AQUA);

            playerTwoScore = new Text(Integer.toString(user.getCompScore()));
            playerTwoScore.setFont(Font.font("Verdana", 30));
            playerTwoScore.setFill(Color.AQUA);

            topScoreBoard = new HBox(playerOneName, playerOneScore, playerTwoName, playerTwoScore);
            topScoreBoard.setSpacing(10);

            newGame(primaryStage);
        }

    }

    public void showScore() {
        Messages message = new Messages("Scorebox", "Human versus Machine", "Score" + user.getUserScore() + ":" + user.getCompScore());
        message.showMessage();
        playerOneScore = new Text(Integer.toString(user.getUserScore()));
        playerOneScore.setFont(Font.font("Verdana", 30));
        playerOneScore.setFill(Color.AQUA);

        playerTwoScore = new Text(Integer.toString(user.getCompScore()));
        playerTwoScore.setFont(Font.font("Verdana", 30));
        playerTwoScore.setFill(Color.AQUA);

        topScoreBoard = new HBox(playerOneName, playerOneScore, playerTwoName, playerTwoScore);
        topScoreBoard.setSpacing(10);
    }

    public void newGame(Stage primaryStage) {
        pawn.setMoveCounter(0);
        BackgroundSize backgroundSize = new BackgroundSize(200, 200, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(IMAGE_FOR_BACKGROUND, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane newGameBoardPane = new GridPane();
        newGameBoardPane.setCursor(new ImageCursor(IMAGE_FOR_CURSOR));
        newGameBoardPane.setAlignment(Pos.CENTER);

        pawn.pawnSetting(primaryStage, newGameBoardPane);

        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(background);
        borderPane.setCenter(newGameBoardPane);
        borderPane.setLeft(buttons);
        borderPane.setTop(topScoreBoard);

        topScoreBoard.setAlignment(Pos.CENTER);

        Scene scene = new Scene(borderPane, 600, 600, Color.BLACK);

        primaryStage.setTitle("Tick Tack Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void handleUserClick(Stage primaryStage, MouseEvent e, int col, int row) {

        ImageView view = (ImageView) e.getSource();
        if (view.getImage() != ANIMATION_FOR_X && view.getImage() != ANIMATION_FOR_Y) {
            view.setImage(ANIMATION_FOR_X);
            System.out.println("Player Col " + col + " Row " + row);

            pawn.verifyIfFinish();

            if (user.isUserWin()) {
                user.setUserScore(user.getUserScore() + 1);
                showScore();
                user.setUserWin(false);
                newGame(primaryStage);
            } else if (user.isDraw()) {
                showScore();
                newGame(primaryStage);
                user.setUserWin(false);
            }

            pawn.handleComputerClick();
            pawn.verifyIfFinish();

            if (user.isCompWin()) {
                user.setCompScore(user.getCompScore() + 1);
                showScore();
                user.setCompWin(false);
                newGame(primaryStage);
            } else if (user.isDraw()) {
                showScore();
                newGame(primaryStage);
                user.setDraw(false);
            }
        } else {
            Messages message = new Messages("Error", "Something went wrong.", "You made an illegal move!");
            message.showMessage();
            view.setOnMouseClicked(event -> handleUserClick(primaryStage, event, col, row));
        }
    }
}
