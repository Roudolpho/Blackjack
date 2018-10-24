
public class Player
{
    Card[] hand = new Card[11];
    int numberOfCards = 0;

    public void dealHand(Card card1, Card card2) {//sets the player's starting hand
        numberOfCards = 2;
        hand[0] = card1;
        hand[1] = card2;
    }
    
    public void hit(Card nextCard) {//lets the player take another card
        hand[numberOfCards] = nextCard;
        numberOfCards++;
    }

    public int handSum() {//sums up the score of the player's hand
        int sum = 0;
        boolean aceInHand = false;
        for (int i = 0;i<numberOfCards;i++) {//this for loop sums all the cards in a players hand and assumes all aces are 1s
            sum+=hand[i].value();
            if (hand[i].iD()==12) {//This boolean identifies whether there is an ace or not
                aceInHand = true;
            }
        }
        if (aceInHand && sum+10<=21) {//if there is an ace in the player's hand, this checks whether using it as an 11 instead of a one is beneficial for the player or not and acts accordingly
            sum += 10;
        }
        return sum;
    }

    public boolean bust() {//tells if the player has busted
        if (handSum()<=21) {
            return false;
        } else {
            return true;
        }
    }

    public boolean makeChoice(Deck deck) {//this method is overwritten by extending classes so it is simply a placeholder for now
        return true;
    }

    public String printCards() {//creates a string listing the player's cards
        String temp = "";
        for (int i = 0;i<numberOfCards;i++) {
            temp += "\t" + hand[i].name();
        }
        return temp;
    }

    public String printCards(boolean hideCards) {//creates a string listing the player's cards
        String temp = "";
        for (int i = 0;i<numberOfCards;i++) {
            temp += "\t" + hand[i].name();
        }
        return temp;
    }
}
