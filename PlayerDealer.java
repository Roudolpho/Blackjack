
public class PlayerDealer extends Player
{
    @Override public boolean makeChoice(){//decision making process for the dealer
        if(handSum()>17){
            return false;
        } else {
            return true;
        }
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
