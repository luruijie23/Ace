package boggleViews;

import boggle.BoggleGame;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.concurrent.atomic.AtomicBoolean;

public class BoggleView {
    // attempting to put together a proper looking grid, trial and error
    private BoggleGame game;

   private BorderPane borderPane = new BorderPane();
    private GridPane gridPane = new GridPane();
    private String choice;
    private String output;
    private TextField input = new TextField();

    private Text text = new Text();
    private String letters;

    private boolean first_round = true;

    private Button[] buttons = new Button[16];

    public BoggleView() {
        this.game = new BoggleGame();
    }


    public BorderPane createBoard() {

        borderPane.setStyle("-fx-background-color: #ffd0fe;");
        borderPane.setPadding(new Insets(10, 10, 10, 10));

        Button nightmode = new Button("NightMode");
        Button hints = new Button("Hints");
        Button timeRush = new Button("Time Rush");
        Button surpriseMechanic = new Button("Surprise Mechanic");
        hints.setFont(new Font(14));
        timeRush.setFont(new Font(14));
        surpriseMechanic.setFont(new Font(14));
        nightmode.setFont(new Font(14));

        hints.setPrefWidth(Integer.MAX_VALUE);
        timeRush.setPrefWidth(Integer.MAX_VALUE);
        surpriseMechanic.setPrefWidth(Integer.MAX_VALUE);
        nightmode.setPrefWidth(Integer.MAX_VALUE);

        HBox controls = new HBox(10, hints, timeRush, surpriseMechanic, nightmode);
        controls.setPadding(new Insets(10, 10, 10, 10));
        controls.setAlignment(Pos.TOP_CENTER);
        controls.setPrefHeight(75);
        borderPane.setTop(controls);
        controls.setMaxWidth(990);




        Label scoreLabel = new Label("Player Score is: 0");
        scoreLabel.setFont(new Font(12));
        scoreLabel.setStyle("-fx-background-color: #ceffc6;-fx-padding: 10px;");


        Label cscoreLabel = new Label("Computer Score is: 0");
        cscoreLabel.setFont(new Font(12));
        cscoreLabel.setStyle("-fx-background-color: #ceffc6;-fx-padding: 10px;");

        Label wordCount = new Label("Number of words found: ");
        wordCount.setFont(new Font(12));
        wordCount.setStyle("-fx-background-color: #ceffc6;-fx-padding: 10px;");

        Label pWordlabel = new Label("Computer Words Found: ");
        pWordlabel.setFont(new Font(12));
        pWordlabel.setAlignment(Pos.TOP_CENTER);
        pWordlabel.setStyle("-fx-background-color: #ceffc6;-fx-padding: 10px;");
        pWordlabel.setPrefHeight(Integer.MAX_VALUE); pWordlabel.setPrefWidth(Integer.MAX_VALUE);


        Label cWordlabel = new Label("Computer Words Found: ");
        cWordlabel.setFont(new Font(12));
        cWordlabel.setAlignment(Pos.TOP_CENTER);
        cWordlabel.setStyle("-fx-background-color: #ceffc6;-fx-padding: 10px;");
        cWordlabel.setPrefHeight(Integer.MAX_VALUE); cWordlabel.setPrefWidth(Integer.MAX_VALUE);


        VBox wordBox = new VBox(10, scoreLabel, cscoreLabel, wordCount,pWordlabel, cWordlabel);
        wordBox.setPadding(new Insets(10, 10, 10, 10));
        wordBox.setSpacing(30);
        wordBox.setAlignment(Pos.TOP_CENTER);
        wordBox.setPrefWidth(200);
        borderPane.setLeft(wordBox);

        letters = game.initRound();
        Button enter = new Button("ENTER");
            output = "Enter a word ";
            text.setText(output + "\n");
            text.setFont(new Font(12));
            text.setStyle("-fx-background-color: #ceffc6;-fx-padding: 50px;");
            VBox comms = new VBox(text, input, enter);
            comms.setPrefWidth(300);
            comms.setMaxWidth(300);
            text.maxWidth(300);
            comms.setAlignment(Pos.CENTER);
            borderPane.setRight(comms);
            enter.setOnAction(e -> {
                BoggleGame.MoveResult res = game.humanMoveOnce(input.getText());
                input.clear();

                switch (res) {
                    case EMPTY -> {
                        cscoreLabel.setText("Computer Score is: " + game.getcScore());
                        cWordlabel.setText("Computer Words found:"+game.getComputerWords());
                        cWordlabel.setWrapText(true);

                        text.setText(
                                "Player found " + game.getpwordCount() + " words this round\nComputer found "
                                + game.getcwordcount() + " words this round \nPlayer score is " + game.getpScore() +
                                " this round \nComputer score is " + game.getcScore() + " this round\n\nPlay again? Type 'Y' or 'N' ");
                        text.wrappingWidthProperty();
                        this.game.endRound();
                        enter.setOnAction(f ->{if (input.getText().equals("Y")) {
                            this.letters = game.initRound();
                            createBoard();
                        } else if (input.getText().equals("N")) {
                            text.setText("Good Game! \n\n"+game.getRound()+" rounds were played\nPlayer found average of "+
                                    game.getApwordCount()+" words\nPlayer scored "+game.getpScoreTotal()+
                                    " points\nComputer found " +game.getAcwordcount()+" words\nComputer scored "
                                    +game.getcScoreTotal()+ " points");
                        }
                        });
                    }
                    case BAD_WORD -> {
                        text.setText("Invalid word, or already used, try again!!");
                    }
                    case WORD_FOUND -> {
                        scoreLabel.setText("Player Score is: " + game.getpScore());
                        wordCount.setText("Number of words found: " + game.getpwordCount());
                        pWordlabel.setText("Player words found: " + game.getPlayerWords().toString());
                        pWordlabel.setWrapText(true);

                    }
                }
            });


                createButtons(this.letters);
                createGrid(this.buttons);


            Label ScoreM = createLabel("Score Multiplier: ");
            ScoreM.setStyle("-fx-background-color: #befaff;-fx-padding: 10px;");
            Label Time = createLabel("Time: ");
            Time.setStyle("-fx-background-color: #befaff;-fx-padding: 10px;");

            VBox timeRushBox = new VBox(10, Time, ScoreM);
            timeRushBox.setPadding(new Insets(10, 10, 10, 10));
            timeRushBox.setAlignment(Pos.BOTTOM_LEFT);
            borderPane.setBottom(timeRushBox);

        return borderPane;
    }

    private static Label createLabel(String text) {
        Label label = new Label(text);
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return label;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public void createButtons(String letters){
        this.gridPane.getChildren().clear();
        for (int i = 0; i < buttons.length; i++) {
            this.buttons[i] = new Button(String.valueOf(letters.charAt(i)));
            this.buttons[i].setPrefWidth(Integer.MAX_VALUE);
            this.buttons[i].setPrefHeight(Integer.MAX_VALUE);
            this.buttons[i].setMinWidth(120);

            Button button = buttons[i];
            this.buttons[i].setOnAction(e -> {
                input.setText(input.getText() + button.getText());
            });
        }
    }
    public void createGrid(Button[] buttons){
        gridPane.add(buttons[0], 0, 0, 1, 1);
        gridPane.add(buttons[1], 1, 0, 1, 1);
        gridPane.add(buttons[2], 2, 0, 1, 1);
        gridPane.add(buttons[3], 3, 0, 1, 1);
        gridPane.add(buttons[4], 0, 1, 1, 1);
        gridPane.add(buttons[5], 1, 1, 1, 1);
        gridPane.add(buttons[6], 2, 1, 1, 1);
        gridPane.add(buttons[7], 3, 1, 1, 1);
        gridPane.add(buttons[8], 0, 2, 1, 1);
        gridPane.add(buttons[9], 1, 2, 1, 1);
        gridPane.add(buttons[10], 2, 2, 1, 1);
        gridPane.add(buttons[11], 3, 2, 1, 1);
        gridPane.add(buttons[12], 0, 3, 1, 1);
        gridPane.add(buttons[13], 1, 3, 1, 1);
        gridPane.add(buttons[14], 2, 3, 1, 1);
        gridPane.add(buttons[15], 3, 3, 1, 1);
        gridPane.setAlignment(Pos.CENTER);
        borderPane.setCenter(gridPane);
    }
    public void intro(){
        text.setText("The Boggle board contains a grid of letters that are randomly placed.\n" +
        "We're both going to try to find words in this grid by joining the letters.\n" +
        "You can form a word by connecting adjoining letters on the grid.\n" +
        "Two letters adjoin if they are next to each other horizontally, \n" +
        "vertically, or diagonally. The words you find must be at least 4 letters long,\n" +
        "and you can't use a letter twice in any single word. Your points \n" +
        "will be based on word length: a 4-letter word is worth 1 point, 5-letter\n" +
        "words earn 2 points, and so on. After you find as many words as you can,\n" +
        "I will find all the remaining words.\n" +
        "Click on the letters so select them\n" +
        "Hit enter when your ready");
    }
}