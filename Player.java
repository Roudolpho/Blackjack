
public class Player
{
    Card[] hand = new Card[11];
    int numberOfCards = 0;
    public int level = 1;
    int cash = 0;
    public Player() {

    }
    
    public Player(int lev) {
        level = lev;
    }

    public void dealHand(Card card1, Card card2){//sets the player's starting hand
        numberOfCards = 2;
        hand[0] = card1;
        hand[1] = card2;
    }
    
    public void addCash(int c) {
        cash += c;
    }
    
    public void hit(Card nextCard){//lets the player take another card
        hand[numberOfCards] = nextCard;
        numberOfCards++;
    }

    public int handSum(){//sums up the score of the player's hand
        int sum = 0;
        int acesInHand = 0;
        for(int i = 0;i<numberOfCards;i++){
            sum+=hand[i].value();
            if(hand[i].iD()==12){
                acesInHand++;
            }
        }
        for(int i = 0;i<acesInHand;i++){
            if(sum+10<=21){
                sum+=10;
            }
        }
        return sum;
    }

    public int handSumWithoutAces(){//sums up the score 
        int sum = 0;
        for(int i = 0;i<numberOfCards;i++){
            sum+=hand[i].value();
        }
        return sum;
    }

    public boolean bust(){//tells if the player has busted
        if(handSum()<=21){
            return false;
        } else {
            return true;
        }
    }

    public boolean makeChoice(Deck deck){//this method is overwritten by extending classes so it is simply a placeholder for now
        return true;
    }

    public String printCards(){//creates a string listing the player's cards
        String temp = "";
        for(int i = 0;i<numberOfCards;i++){
            temp += "\t" + hand[i].name();
        }
        return temp;
    }

    public String printCards(boolean hideCards){//creates a string listing the player's cards
        String temp = "";
        for(int i = 0;i<numberOfCards;i++){
            temp += "\t" + hand[i].name();
        }
        return temp;
    }
}
