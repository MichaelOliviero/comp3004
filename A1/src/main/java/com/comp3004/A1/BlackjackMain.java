package com.comp3004.A1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
	Hand dealer;
	Hand player;
	Deck deck;
	
	private SimpleBooleanProperty playable = new SimpleBooleanProperty(false);
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void endGame() {
		if (playable.get()) {
			String winner = null;
			int dealerScore = this.dealer.getScore();
			int playerScore = this.player.getScore();
			
	        if (dealerScore == 21 || playerScore > 21 || dealerScore == playerScore || (dealerScore < 21 && dealerScore > playerScore)) {
	            winner = "Dealer ";
	        } else if (playerScore == 21 || dealerScore > 21 || playerScore > dealerScore) {
	        	winner = "Player ";
	        }
	        
	        playable.set(false);
	        print();
	        System.out.println("-------------------------");
	        System.out.println(winner + "wins!");
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
		if (playable.get()) {
			System.out.println("\t Card: -============-");
			for (int i = 1; i < dealer.getCardCount(); i++) {
				System.out.println(dealer.getCard(i).cardText());
			}System.out.println("Dealer's Score: ?");
		} else {
			for (int i = 0; i < dealer.getCardCount(); i++) {
				System.out.println(dealer.getCard(i).cardText());
			}System.out.println("Dealer's Score: " + dealer.getScore());
		}
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
		
		dealer = new Hand();
		player = new Hand();
		deck = new Deck();
		
		deck.shuffleDeck();
		
		player.Hit(deck.nextCard());
		player.Hit(deck.nextCard());
		dealer.Hit(deck.nextCard());
		dealer.Hit(deck.nextCard());
		
		gameHandler();
	}
	
	public void continueGame(Hand dealer, Hand player) {
		playable.set(true);
		
		this.player = player;
		this.dealer = dealer;
		deck = new Deck();
		
		deck.shuffleDeck();
		
		endGame();
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
			System.out.println("\n\n\n\n");
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
			//fileChooser.setInitialDirectory(new File ("C:\\Users\\Micha\\git\\comp3004\\A1"));
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
            	try {
            		System.out.println("The Dealer is making a decision...");
					TimeUnit.SECONDS.sleep(4);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
            	dealer.Hit(deck.nextCard());
            	gameHandler();
            }
            if (!(dealer.dealersChoice())) {
            	endGame();
            }
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
