
public class PlayerDealer extends Player
{
    
    public PlayerDealer(int lev){
        level = lev;
    }
    
    @Override public boolean makeChoice(Deck deck){//decision making process for the dealer
        switch(level){
            case 0:
                if(Math.random()>.49){return true;}
                return false;
            case 1: 
                if(handSum()>17){
                    return false;
                } else {
                    return true;
                }
            case 2:
                if(handSum()+deck.checkNextCard()>21) {return false;}
                return true;
        }
        return false;
    }

    @Override public String printCards(boolean isEndGame){//this overwritten method prevents the dealers cards from being shown
        String temp = "";
        if(isEndGame){
            for(int i = 0;i<numberOfCards;i++){
                temp += "\t" + hand[i].name();
            }
        } else {
            for(int i = 0;i<numberOfCards;i++){
                if(i==0){
                    temp += "\t" + hand[i].name();
                } else {
                    temp += "\tUnknown";
                }
            }
        }
        return temp;
    }
}
