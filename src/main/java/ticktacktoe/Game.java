package ticktacktoe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Optional;

import static ticktacktoe.Graphics.*;
import static ticktacktoe.Graphics.ANIMATION_FOR_X;

public class Game {

    private PawnContainer pawnContainer;
    private Button exitButton, newGameButton, saveGameButton, loadGameButton;
    private VBox buttons;

    private HBox topScoreBoard;
    private Text playerOneName, playerOneScore, playerTwoName, playerTwoScore;

    private String playerName;
    private int numberOfRounds;
    private DifficultyLevel level;

    private UsersResults user = new UsersResults();
    private SavingService save = new SavingService();

    public Game() {
        pawnContainer = new PawnContainer(this, user);
    }

    public void gamePlay(Stage primaryStage) {

        exitButton = new Button("Exit");
        exitButton.setMinSize(200, 30);
        exitButton.setOnMouseClicked(e -> handleButtonClick(primaryStage, e));

        newGameButton = new Button("New game");
        newGameButton.setMinSize(200, 30);
        newGameButton.setOnMouseClicked(e -> handleButtonClick(primaryStage, e));

        saveGameButton = new Button("Save Game");
        saveGameButton.setMinSize(200,30);
        saveGameButton.setOnMouseClicked(e -> handleButtonClick(primaryStage, e));

        loadGameButton = new Button("Load Game");
        loadGameButton.setMinSize(200,30);
        loadGameButton.setOnMouseClicked(e -> handleButtonClick(primaryStage, e));

        buttons = new VBox(exitButton, newGameButton, saveGameButton, loadGameButton);
        buttons.setSpacing(20);
        buttons.setPadding(new Insets(20, 20, 20, 20));

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Imię.");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter your name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            playerName = result.get();
        } else {
            System.exit(0);
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Difficulty");
        alert.setHeaderText("How tough your opponent should be?");
        alert.setContentText("Choose your option.");

        ButtonType buttonTypeEasy = new ButtonType("Easy");
        ButtonType buttonTypeMedium = new ButtonType("Medium");
        ButtonType buttonTypeHard = new ButtonType("Hard");
        ButtonType buttonTypeCancel = new ButtonType("Exit", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeEasy, buttonTypeMedium, buttonTypeHard, buttonTypeCancel);

        Optional<ButtonType> result1 = alert.showAndWait();
        if (result1.get() == buttonTypeEasy){
            level = DifficultyLevel.EASY;
        } else if (result1.get() == buttonTypeMedium) {
            level = DifficultyLevel.MEDIUM;
        } else if (result1.get() == buttonTypeHard) {
            level = DifficultyLevel.HARD;
        } else {
            System.exit(0);
        }

        howManyRounds();

        playerOneName = new Text(playerName);
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

            zeroingScore();
            newGame(primaryStage);

        } else if (event.getSource().equals(saveGameButton)) {

            save.results.put(playerName,Integer.toString(user.getUserScore()) + "-" + Integer.toString(user.getCompScore()) + "-" + Integer.toString(numberOfRounds) + "-" + level);
            save.saveMap();

        } else if (event.getSource().equals(loadGameButton)) {

            save.loadMap();
            if (!save.results.get(playerName).isEmpty()) {

                String gameSpecifics = save.results.get(playerName);
                String gameSpecificsDivided[] = gameSpecifics.split("-");
                user.setUserScore(Integer.valueOf(gameSpecificsDivided[0]));
                user.setCompScore(Integer.valueOf(gameSpecificsDivided[1]));
                numberOfRounds = Integer.valueOf(gameSpecificsDivided[2]);
                level = DifficultyLevel.valueOf(gameSpecificsDivided[3]);

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


    }

    public void showScore(Stage primaryStage) {
        if (user.getUserScore() >= numberOfRounds || user.getCompScore() >= numberOfRounds) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("End of the Game!");
            alert.setHeaderText(null);
            if (user.getUserScore() == numberOfRounds) {
                alert.setContentText("You just won the game!");
                //alert.showAndWait();
            } else if (user.getCompScore() == numberOfRounds) {
                alert.setContentText("You just lost the game!");
                //alert.showAndWait();
            }

            ButtonType buttonNewGame = new ButtonType("New Game");
            ButtonType buttonExitGame = new ButtonType("Exit Game");

            alert.getButtonTypes().setAll(buttonNewGame, buttonExitGame);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonNewGame) {
                zeroingScore();
                newGame(primaryStage);
            } else if (result.get() == buttonExitGame) {
                System.exit(0);
            }
        } else {
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
    }

    public void newGame(Stage primaryStage) {
        pawnContainer.setMoveCounter(0);
        BackgroundSize backgroundSize = new BackgroundSize(200, 200, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(IMAGE_FOR_BACKGROUND, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane newGameBoardPane = new GridPane();
        newGameBoardPane.setCursor(new ImageCursor(IMAGE_FOR_CURSOR));
        newGameBoardPane.setAlignment(Pos.CENTER);

        pawnContainer.pawnSetting(primaryStage, newGameBoardPane);

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

            pawnContainer.verifyIfFinish();
            if (user.isUserWin()) {
                user.setUserScore(user.getUserScore()+1);
                showScore(primaryStage);
                user.setUserWin(false);
                newGame(primaryStage);
            } else if (user.isDraw()) {
                showScore(primaryStage);
                newGame(primaryStage);
                user.setDraw(false);
            }

            if (level == DifficultyLevel.EASY) {

                pawnContainer.handleEasyComputerClick();

            } else if (level == DifficultyLevel.MEDIUM){

                pawnContainer.handleMediumComputerClick();

            } else if (level == DifficultyLevel.HARD) {

                pawnContainer.handleHardComputerClick();

            }
            pawnContainer.verifyIfFinish();

            if (user.isCompWin()) {
                user.setCompScore(user.getCompScore()+1);
                showScore(primaryStage);
                user.setCompWin(false);
                newGame(primaryStage);
            } else if (user.isDraw()) {
                showScore(primaryStage);
                newGame(primaryStage);
                user.setDraw(false);
            }
        } else {
            Messages messages = new Messages("Error",null,"You cannot do that!");
            messages.showMessage();
            view.setOnMouseClicked(event -> handleUserClick(primaryStage, event, col, row));
        }

    }

    public void zeroingScore() {
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
    }

    private void howManyRounds(){
        TextInputDialog dialog2 = new TextInputDialog("");
        dialog2.setTitle("Number of rounds.");
        dialog2.setHeaderText(null);
        dialog2.setContentText("Please enter how many rounds do you want to play:");
        Optional<String> result2 = dialog2.showAndWait();
        if (result2.isPresent()) {
            try {
                numberOfRounds = Integer.parseInt(result2.get());
            } catch (NumberFormatException e) {
                howManyRounds();
            }
        }
    }

}
