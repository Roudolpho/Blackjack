
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
