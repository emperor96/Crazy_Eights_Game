import java.util.ArrayList;


public class DiscardPile {
	private ArrayList<Card> cards = new ArrayList<>();
	
	public String toString() {
		return "Discard pile: " + getTopCard();
	}
	
	public Card getTopCard() {
		return cards.get(cards.size() - 1);
	}
	
	public void add(Card c) {
		cards.add(c);
	}
}
