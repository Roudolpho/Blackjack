
public class PlayerDealer extends Player
{
    @Override public boolean makeChoice(){
        if(handSum()>17){
            return false;
        } else {
            return true;
        }
    }

    @Override public String printCards(boolean isEndGame){
        String temp = "";
        if(isEndGame){
            for(int i = 0;i<numberOfCards;i++){
                temp += hand[i].name() +"\t";
            }
        } else {
            for(int i = 0;i<numberOfCards;i++){
                if(i==0){
                    temp += hand[i].name() +"\t";
                } else {
                    temp += "Unknown" +"\t";
                }
            }
        }
        return temp;
    }
}
