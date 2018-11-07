package ticktacktoe;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class TickTackToeRunner extends Application {

    private static final Random RANDOM = new Random();
    private static final double PAWN_SIZE_MULTIPLIER = 0.30;
    private static final Image IMAGE_FOR_BACKGROUND = new Image("Graphics/background.jpg");
    private static final Image ANIMATION_FOR_X = new Image("Graphics/cross.png");
    private static final Image ANIMATION_FOR_Y = new Image("Graphics/circle.png");
    private static final Image IMAGE_FOR_CURSOR = new Image("Graphics/cursorIcon.png");
    private static final Image IMAGE_FOR_EMPTY_FIELD = new Image("Graphics/transparent.png");

    private Button exitButton, newGameButton;
    private VBox buttons;

    private ImageView pawn11;
    private ImageView pawn12;
    private ImageView pawn13;
    private ImageView pawn21;
    private ImageView pawn22;
    private ImageView pawn23;
    private ImageView pawn31;
    private ImageView pawn32;
    private ImageView pawn33;

    private Map<String, ImageView> pawns = new HashMap<>();

    private HBox topScoreBoard;
    private Text playerOneName, playerOneScore, playerTwoName, playerTwoScore;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setResizable(false);

        exitButton = new Button("Exit");
        exitButton.setMinSize(200, 30);
        exitButton.setOnMouseClicked(e -> handleButtonClick(e));

        newGameButton = new Button("New game");
        newGameButton.setMinSize(200, 30);
        newGameButton.setOnMouseClicked(e -> handleButtonClick(e));

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

        BackgroundSize backgroundSize = new BackgroundSize(200, 200, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(IMAGE_FOR_BACKGROUND, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane gameBoardPane = new GridPane();
        gameBoardPane.setCursor(new ImageCursor(IMAGE_FOR_CURSOR));
        gameBoardPane.setAlignment(Pos.CENTER);

        pawn11 = newPawn(gameBoardPane, 1, 1);
        gameBoardPane.add(pawn11, 1, 1);
        pawns.put("11", pawn11);

        pawn21 = newPawn(gameBoardPane, 2, 1);
        gameBoardPane.add(pawn21, 2, 1);
        pawns.put("21", pawn21);

        pawn31 = newPawn(gameBoardPane, 3, 1);
        gameBoardPane.add(pawn31, 3, 1);
        pawns.put("31", pawn31);

        pawn12 = newPawn(gameBoardPane, 1, 2);
        gameBoardPane.add(pawn12, 1, 2);
        pawns.put("12", pawn12);

        pawn22 = newPawn(gameBoardPane, 2, 2);
        gameBoardPane.add(pawn22, 2, 2);
        pawns.put("22", pawn22);

        pawn32 = newPawn(gameBoardPane, 3, 2);
        gameBoardPane.add(pawn32, 3, 2);
        pawns.put("32", pawn32);

        pawn13 = newPawn(gameBoardPane, 1, 3);
        gameBoardPane.add(pawn13, 1, 3);
        pawns.put("13", pawn13);

        pawn23 = newPawn(gameBoardPane, 2, 3);
        gameBoardPane.add(pawn23, 2, 3);
        pawns.put("23", pawn23);

        pawn33 = newPawn(gameBoardPane, 3, 3);
        gameBoardPane.add(pawn33, 3, 3);
        pawns.put("33", pawn33);


        BorderPane borderPane = new BorderPane();
        borderPane.setBackground(background);
        borderPane.setCenter(gameBoardPane);
        borderPane.setLeft(buttons);
        borderPane.setTop(topScoreBoard);
        topScoreBoard.setAlignment(Pos.CENTER);

        Scene scene = new Scene(borderPane, 600, 600, Color.BLACK);

        primaryStage.setTitle("Tick Tack Toe");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private ImageView newPawn(GridPane gameBoardPane, int col, int row) {
        ImageView imageView = new ImageView(IMAGE_FOR_EMPTY_FIELD);
        imageView.fitWidthProperty().bind(gameBoardPane.widthProperty().multiply(PAWN_SIZE_MULTIPLIER));
        imageView.fitHeightProperty().bind(gameBoardPane.widthProperty().multiply(PAWN_SIZE_MULTIPLIER));
//        imageView.setOnMouseEntered(e -> handleMouseEntersCell(e));
//        imageView.setOnMouseExited(e -> handleMouseExitsCell(e));
        imageView.setOnMouseClicked(e -> handleUserClick(e, col, row));

        return imageView;
    }

    private void handleUserClick(MouseEvent e, int col, int row) {

        ImageView view = (ImageView) e.getSource();
        view.setImage(ANIMATION_FOR_X);
        System.out.println("Player Col " + col + " Row " + row);

        verifyIfFinish();

        handleComputerClick();
    }

    private void verifyIfFinish() {


    }


    private void handleComputerClick() {
        int col = RANDOM.nextInt(3) + 1;
        int row = RANDOM.nextInt(3) + 1;
        System.out.println("Computer Col " + col + " Row " + row);

        String key = String.valueOf(col) + String.valueOf(row);
        ImageView imageView = pawns.get(key);

        if (imageView.getImage() != ANIMATION_FOR_Y && imageView.getImage() != ANIMATION_FOR_X) {
            imageView.setImage(ANIMATION_FOR_Y);
        } else {
            System.out.println("Field is taked");
            handleComputerClick();
        }
    }

    private void handleButtonClick(MouseEvent event) {

        if (event.getSource().equals(exitButton)) {
            System.exit(0);
        }

    }

    private void handleMouseEntersCell(MouseEvent event) {

        ImageView eventObject = (ImageView) event.getSource();

        if (eventObject.getImage().equals(IMAGE_FOR_EMPTY_FIELD)) {
            eventObject.setImage(ANIMATION_FOR_X);
        }
    }

    private void handleMouseExitsCell(MouseEvent event) {

        ImageView eventObject = (ImageView) event.getSource();

        if (eventObject.getImage().equals(ANIMATION_FOR_X)) {
            eventObject.setImage(IMAGE_FOR_EMPTY_FIELD);
        }
    }
}
