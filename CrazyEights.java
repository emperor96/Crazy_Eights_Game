import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class CrazyEights {
	
	private Deck deck;
	private DiscardPile discardPile;
	private Player player;
	private Player computer;
	private Scanner input;
	private Random random;
	
	public CrazyEights() {
		input = new Scanner(System.in);
		random = new Random();
		deck = new Deck();
		discardPile = new DiscardPile();
		player = new Player();
		computer = new Player();
		// shuffle the deck
		deck.shuffle();
		// deal 8 cards to both players
		for(int i = 0; i < 8; i++) {
			player.getHand().add(deck.draw());
			computer.getHand().add(deck.draw());
		}
		// first card to play on
		discardPile.add(deck.draw());
	}
	
	private ArrayList<Card> getPlayableCards(Player player) {
		ArrayList<Card> cards = new ArrayList<>();
		for(Card c : player.getHand()) {
			if(c.getFaceValue() == discardPile.getTopCard().getFaceValue() // same face value
					|| c.getSuit() == discardPile.getTopCard().getSuit() // same suit
					|| c.getFaceValue() == 8) { // face value 8
				cards.add(c);
			}
		}
		return cards;
	}
	
	private void pickUpCards(Player player) {
		
		if(discardPile.getTopCard().getFaceValue() == 2) {
			if(player == this.player) {
				System.out.println("You have to pick up 2 cards");
			} else {
				System.out.println("Computer has to pick up 2 cards");
			}
			player.getHand().add(deck.draw());
			player.getHand().add(deck.draw());
		} else if(discardPile.getTopCard().getFaceValue() == 12 && discardPile.getTopCard().getSuit() == 3) {
			if(player == this.player) {
				System.out.println("You have to pick up 5 cards");
			} else {
				System.out.println("Computer has to pick up 5 cards");
			}
			player.getHand().add(deck.draw());
			player.getHand().add(deck.draw());
			player.getHand().add(deck.draw());
			player.getHand().add(deck.draw());
			player.getHand().add(deck.draw());
		}
	}
	
	private boolean userTurn() {
		boolean userTurn = false;
		System.out.println("Computer is holding " + computer.getHand().size() + " cards");
		System.out.println(discardPile);
		pickUpCards(player);
		System.out.println(player);	
		ArrayList<Card> playableCards = getPlayableCards(player);
		if(playableCards.size() > 0) {
			System.out.print("Which card do you want to discard (");
			for(int i = 0; i < playableCards.size(); i++) {
				System.out.print(i + "=" + playableCards.get(i));
				if(i < playableCards.size() - 1) {
					System.out.print(", ");
				}
			}
			System.out.print(")? ");
			int option = input.nextInt();
			input.nextLine();
			Card cardToDiscard = playableCards.get(option);
			System.out.println("You discard " + cardToDiscard);
			player.getHand().remove(cardToDiscard);
			if(cardToDiscard.getFaceValue() == 8) {
				System.out.print("Which suit would like (");
				for(int i = 0; i < Card.SUIT_STRS.length; i++) {
					System.out.print(i + "=" + Card.SUIT_STRS[i]);
					if(i < Card.SUIT_STRS.length - 1) {
						System.out.print(", ");
					}
				}
				System.out.print(")? ");
				int suit = input.nextInt();
				input.nextLine();
				cardToDiscard.setSuit(suit + 1);
			} else if(cardToDiscard.getFaceValue() == 11) {
				// skip computer's turn
				System.out.println("You skipped the computer's turn");
				userTurn = true;
			}
			discardPile.add(cardToDiscard);
		} else {
			Card cardToDraw = deck.draw();
			player.getHand().add(cardToDraw);
			System.out.println("You are unable to play");
			System.out.println("You draw " + cardToDraw);
			System.out.println(player);	
			playableCards = getPlayableCards(player);
			if(playableCards.size() > 0) {
				System.out.print("Which card do you want to discard (");
				for(int i = 0; i < playableCards.size(); i++) {
					System.out.print(i + "=" + playableCards.get(i));
					if(i < playableCards.size() - 1) {
						System.out.print(", ");
					}
				}
				System.out.print(")? ");
				int option = input.nextInt();
				input.nextLine();
				Card cardToDiscard = playableCards.get(option);
				System.out.println("You discard " + cardToDiscard);
				player.getHand().remove(cardToDiscard);
				if(cardToDiscard.getFaceValue() == 8) {
					System.out.print("Which suit would like (");
					for(int i = 0; i < Card.SUIT_STRS.length; i++) {
						System.out.print(i + "=" + Card.SUIT_STRS[i]);
						if(i < Card.SUIT_STRS.length - 1) {
							System.out.print(", ");
						}
					}
					System.out.print(")? ");
					int suit = input.nextInt();
					input.nextLine();
					cardToDiscard.setSuit(suit + 1);
				} else if(cardToDiscard.getFaceValue() == 11) {
					// skip computer's turn
					System.out.println("You skipped the computer's turn");
					userTurn = true;
				}
				discardPile.add(cardToDiscard);
			} else {
				System.out.println("You are still unable to play");
			}
			
		}
		return userTurn;
	}
	
	private boolean computerTurn() {
		boolean userTurn = true;
		System.out.println(discardPile);
		pickUpCards(computer);
		ArrayList<Card> playableCards = getPlayableCards(computer);
		if(playableCards.size() > 0) {
			int option = random.nextInt(playableCards.size());
			Card cardToDiscard = playableCards.get(option);
			System.out.println("Computer discards " + cardToDiscard);
			computer.getHand().remove(cardToDiscard);
			if(cardToDiscard.getFaceValue() == 8) {
				int suit = random.nextInt(Card.SUIT_STRS.length);
				System.out.println("Computer changes suit to " + Card.SUIT_STRS[suit]);
				cardToDiscard.setSuit(suit + 1);
			} else if(cardToDiscard.getFaceValue() == 11) {
				System.out.println("Your turn is skipped");
				userTurn = false;
			}
			discardPile.add(cardToDiscard);
		} else {
			Card cardToDraw = deck.draw();
			computer.getHand().add(cardToDraw);
			
			System.out.println("Computer is unable to play");
			System.out.println("Computer draws a card");
			playableCards = getPlayableCards(computer);
			if(playableCards.size() > 0) {
				int option = random.nextInt(playableCards.size());
				Card cardToDiscard = playableCards.get(option);
				System.out.println("Computer discards " + cardToDiscard);
				computer.getHand().remove(cardToDiscard);
				if(cardToDiscard.getFaceValue() == 8) {
					int suit = random.nextInt(Card.SUIT_STRS.length);
					System.out.println("Computer changes suit to " + Card.SUIT_STRS[suit]);
					cardToDiscard.setSuit(suit + 1);
				} else if(cardToDiscard.getFaceValue() == 11) {
					System.out.println("Your turn is skipped");
					userTurn = false;
				}
				discardPile.add(cardToDiscard);
			} else {
				
				System.out.println("Computer is still unable to play");
			}
		}
		return userTurn;
	}
	
	private boolean isGameEnd() {
		if(player.getHand().size() == 0) {
			// player wins
			System.out.println("You win!");
			return true;
		} else if(computer.getHand().size() == 0) {
			System.out.println("Computer wins.");
			return true;
		} else if(deck.getCards().size() == 0) {
			System.out.println("It's a draw.");
			return true;
		} else {
			return false;
		}
	}
	
	public void play() {
		boolean playerTurn = true;
		boolean gameEnd = false;
		while(gameEnd == false) {
			if(playerTurn == true) {
				playerTurn = userTurn();
			} else {
				playerTurn = computerTurn();
			}
			gameEnd = isGameEnd();
			System.out.println();
		}
	}

	public static void main(String[] args) {
		CrazyEights ce = new CrazyEights();
		ce.play();
	}

}
