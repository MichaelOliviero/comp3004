package com.comp3004.A1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class BlackjackMain extends Application {
	
	Stage window;
	StringBuilder sb;
	Dealer dealer;
	Player player;
	Deck deck;
	
	private SimpleBooleanProperty playable = new SimpleBooleanProperty(false);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void endGame() {
		playable.set(false);
		
		int dealerScore = dealer.getScore();
		int playerScore = player.getScore();
		
        if (dealerScore == 21 || playerScore > 21 || dealerScore == playerScore || (dealerScore < 21 && dealerScore > playerScore)) {
            System.out.println("Winner: Dealer");
            return;
        } else if (playerScore == 21 || dealerScore > 21 || playerScore > dealerScore) {
            System.out.println("Winner: Player");
            return;
        }
	}
	
	public void print() {
		System.out.println("-------------------------");
		System.out.println("Cards in player's hand:");
		for (int i = 0; i < player.getCardCount(); i++) {
			System.out.println(player.getCard(i).cardText());
		}System.out.println("Player's Score: " + player.getScore());
		System.out.println("-------------------------");
		System.out.println("Cards in dealer's hand:");
		for (int i = 0; i < dealer.getCardCount(); i++) {
			System.out.println(dealer.getCard(i).cardText());
		}System.out.println("Dealer's Score: " + dealer.getScore());
		System.out.println("-------------------------");
	}
	
	public void gameHandler() {
		print();
		
		if (dealer.hasBlackjack()) {
			endGame();
		} else if (player.hasBlackjack() && !(dealer.hasBlackjack())) {
			endGame();
		} else if (dealer.isBusted() || player.isBusted()) {
			endGame();
		}
		
		player.scoreProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            	if (newValue.intValue() >= 21) {
                    endGame();
                }
            }
        });
		
		dealer.scoreProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            	if (newValue.intValue() >= 21) {
                    endGame();
                }
            }
        });
		
	}
	
	public void startNewGame() {
		playable.set(true);
		
		dealer = new Dealer();
		player = new Player();
		deck = new Deck();
		
		deck.shuffleDeck();
		
		player.Hit(deck.nextCard());
		player.Hit(deck.nextCard());
		dealer.Hit(deck.nextCard());
		dealer.Hit(deck.nextCard());
		
		gameHandler();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Blackjack");
		
		Pane root = new Pane();
		BorderPane menuBarLayout = new BorderPane();
		MenuBar menuBar = new MenuBar();
		VBox wholeLayout = new VBox(20);
		HBox buttonLayout = new HBox(20);
		Region background = new Region();
		Rectangle board = new Rectangle(750, 490);
		Button btnHit = new Button("Hit");
        Button btnStand = new Button("Stand");
        
		root.setPrefSize(800, 600);
        background.setPrefSize(800, 600);
        background.setStyle("-fx-background-color: rgba(0, 0, 0, 1)");
        board.setArcWidth(50);
        board.setArcHeight(50);
        board.setFill(Color.GREEN);
        btnHit.setDisable(true);
	    btnStand.setDisable(true);
		
		// Input file menu
		Menu fileMenu = new Menu ("_File");
		
		// New Game Menu Item
		MenuItem newGame = new MenuItem("_New Game...");
		newGame.setOnAction(e -> {
			System.out.println("\n\n");
			System.out.println("-------------------------");
			System.out.println("Creating new game...");
			startNewGame();
		});
		fileMenu.getItems().add(newGame);
		fileMenu.getItems().add(new SeparatorMenuItem());
		
		// Input File Menu Item
		MenuItem inputFile = new MenuItem("_Input File...");
		inputFile.setOnAction(e -> {
			System.out.println("Inputting file...");
			
			FileChooser fileChooser = new FileChooser();
			// REMOVE LINE BELOW
			fileChooser.setInitialDirectory(new File ("C:\\Users\\Micha\\git\\comp3004\\A1"));
			// REMOVE LINE ABOVE
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter(".txt File", "*.txt"));
			File file = fileChooser.showOpenDialog(null);
			
			if (file != null) {
				try {
					sb = new StringBuilder();
					Scanner input = new Scanner(file);
					
					while(input.hasNext()) {
						sb.append(input.nextLine());
						sb.append("\n");
					}
					
					System.out.println(sb);
					
					input.close();
					
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			} else {
				System.out.println("Invalid file.");
			}
			
		});
		fileMenu.getItems().add(inputFile);
		fileMenu.getItems().add(new SeparatorMenuItem());
		
		// Exit Menu Item
		MenuItem exitWindow = new MenuItem("_Exit");
		exitWindow.setOnAction(e -> { window.close(); });
		fileMenu.getItems().add(exitWindow);
		
		// Main menu bar
		menuBar.getMenus().addAll(fileMenu);
		menuBarLayout.setTop(menuBar);
		menuBarLayout.setPrefWidth(800);
		buttonLayout.setPrefWidth(800);
		buttonLayout.setAlignment(Pos.CENTER);
		
		btnHit.disableProperty().bind(playable.not());
        btnStand.disableProperty().bind(playable.not());
		
		btnHit.setOnAction(e -> {
			player.Hit(deck.nextCard());
			gameHandler();
        });

        btnStand.setOnAction(e -> {
            while (dealer.dealersChoice()) {
            	dealer.Hit(deck.nextCard());
            }
            gameHandler();
        });
		
        // Adding children
        buttonLayout.getChildren().addAll(btnHit, btnStand);
        wholeLayout.getChildren().addAll(menuBarLayout, new StackPane(board), buttonLayout);
        root.getChildren().addAll(background, wholeLayout);
		
		Scene scene = new Scene (root, 800, 600);
		window.setScene(scene);
		window.show();
	}
}
