
/**
 * Write a description of class Card here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Card
{
    private final String[] valsChar = {"2","3","4","5","6","7","8","9","10","Jack","Queen","King","Ace"};//The name of the card corresponding to the card value
    private final int[] valsInt = {2,3,4,5,6,7,8,9,10,10,10,10,1};//The value of each card given the card value
    private final String[] valsSuit = {"Hearts","Spades","Diamonds","Clubs"};
    private int value;//the value is not a card's worth but rather the card's identifier, a number 0-12 deciding its face (i.e. 1,7,J,A)
    private int suit;//integer implying card suit
    public Card() {//creates a random card
        value = (int) Math.floor(Math.random() * 13.0);
    }
    public Card(int cardNum, int cardSuit) {//creates a specified card
        value = cardNum;
        suit = cardSuit;
    }
    public String name() {//returns name
        return valsChar[value];
    }
    public int value() {//returns value
        return valsInt[value];
    }
    public int iD() {//returns card identifier
        return value;
    }
    public int suit() {//returns suit
        return suit;
    }
    public String toString() {//prints specific card
        return (valsChar[value] + " of " + valsSuit[suit]);
    }
}
