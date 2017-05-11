
public class Card {
	private static final String [] FACE_VALUE_STRS =  { "A","2","3","4","5","6","7","8","9","10","J","Q","K" };
	public static final String [] SUIT_STRS =  { "H", "D", "S", "C"};
	
	private int faceValue;
	private int suit;

	public int getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}

	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}

	public Card (int faceValue, int suit) {
		this.faceValue = faceValue;
		this.suit = suit;
	}
	
	public String toString() {
		return FACE_VALUE_STRS[faceValue - 1] + SUIT_STRS[suit - 1];
	}
}
