import java.util.*;
public class Deck
{
    ArrayList<Card> deck = new ArrayList<Card>();
    int currentCard = 0;// current card indicates how far through the deck the user is
    
    public Deck() {//initializes a random deck
        shuffle();
    }

    private void createDeck() {//creates a brand new pack of cards, still in order
        deck = new ArrayList<Card>();
        for (int suit=0;suit<4;suit++) {//creates one of each card for each suit and puts them in an array
            for (int value=0;value<13;value++) {
                deck.add(new Card(value, suit));
            }
        }
    }

    public Card drawCard() {//draws the top card
        Card temp = deck.get(0);
        deck.remove(0);      
        return temp;
    }

    public void listDeck() {//this is like a toString but more for debugging
        for (int i = 0;i<deck.size();i++) {
            System.out.println(deck.get(i).toString());
        }
    }

    public String toString() { //displays how many cards left in the deck
        return ("There are " + (deck.size()) + " left in the deck");
    }
    
    public void shuffle() {//shuffles the current deck
        createDeck();
        Collections.shuffle(deck);
    }
    
    public int checkNextCard() {//gets the value of the next card in the deck
        return deck.get(0).value();
    }
    
    public void checkForDup() {//for dubugging purposes, this function checks to ensure there are no duplicates in the deck
        for (int i = 1;i<deck.size();i++) {
            for (int j = 0;j<i;j++) {
                if (i == j) {
                    System.out.println(deck.get(i) + " has been duplicated");
                }
            }
        }
    }
}
