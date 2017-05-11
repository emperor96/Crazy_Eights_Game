import java.util.ArrayList;
import java.util.Collections;


public class Deck {
	
	private ArrayList<Card> cards = new ArrayList<>();
	
	public ArrayList<Card> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}

	public Deck() {
		for(int i = 1; i <= 13; i++) {
			for(int j = 1; j <= 4; j++) {
				Card card = new Card(i, j);
				cards.add(card);
			}
		}
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public Card draw() {
		Card c = cards.get(cards.size() - 1);
		cards.remove(c);
		return c;
	}
}
