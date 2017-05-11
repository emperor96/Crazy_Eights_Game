import java.util.ArrayList;


public class Player {
	private ArrayList<Card> hand = new ArrayList<>();

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}
	
	public String toString() {
		String str = "Your hand:";
		for(Card c : hand) {
			str += " " + c;
		}
		return str;
	}
}
