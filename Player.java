
public class Player
{
    Card[] hand = new Card[11];
    int numberOfCards = 0;
    public Player() {

    }

    public void dealHand(Card card1, Card card2){
        numberOfCards = 2;
        hand[0] = card1;
        hand[1] = card2;
    }

    public void hit(Card nextCard){
        hand[numberOfCards] = nextCard;
        numberOfCards++;
    }

    public int handSum(){
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

    public int handSumWithoutAces(){
        int sum = 0;
        for(int i = 0;i<numberOfCards;i++){
            sum+=hand[i].value();
        }
        return sum;
    }

    public boolean bust(){
        if(handSum()<=21){
            return false;
        } else {
            return true;
        }
    }
    public boolean makeChoice(){
        return true;
    }
    
    public String printCards(){
        String temp = "";
        for(int i = 0;i<numberOfCards;i++){
            temp += hand[i].name() +"\t";
        }
        return temp;
    }
    
    public String printCards(boolean hideCards){
        String temp = "";
        for(int i = 0;i<numberOfCards;i++){
            temp += hand[i].name() +"\t";
        }
        return temp;
    }
}
