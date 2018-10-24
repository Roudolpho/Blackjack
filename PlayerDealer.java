public class PlayerDealer extends Player
{

    @Override public boolean makeChoice(Deck deck) {//decision making process for the dealer
        return handSum()<17;//this is the simple choice the dealer makes
    }

    @Override public String printCards(boolean isEndGame) {//this overwritten method prevents the dealers cards from being shown during the game
        String temp = "";
        if (isEndGame) {//this determines how to print the cards
            for (int i = 0;i<numberOfCards;i++) {//In this case the cards are printed normally
                temp += "\t" + hand[i].name();
            }
        } else {
            for (int i = 0;i<numberOfCards;i++) {//While the game is in progress all but the first card are kept hidden
                if (i==0) {
                    temp += "\t" + hand[i].name();
                } else {
                    temp += "\tUnknown";
                }
            }
        }
        return temp;
    }
}
