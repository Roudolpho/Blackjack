
public class Deck
{
    Card[] deck = new Card[52];
    int currentCard = 0;// current card indicates how far through the deck the user is
    public Deck() {//initializes a random deck
        createDeck();
    }

    private void createDeck(){//creates a brand new pack of cards, still in order
        int deckNum = 0;
        for(int suit=0;suit<4;suit++){//creates one of each card for each suit and puts them in an array
            for(int value=0;value<13;value++){
                deck[deckNum] = new Card(value, suit);
                deckNum++;
            }
        }
    }

    public Card drawCard() {//draws the top card
        Card temp = deck[currentCard];
        currentCard++;
        return temp;
    }

    public void listDeck() {//this is like a toString but more for debugging
        for(int i = 0;i<deck.length;i++){
            System.out.println(deck[i].toString());
        }
    }

    public String toString(){ //displays how many cards left in the deck
        return ("There are " + ((int) (52-currentCard)) + " left in the deck");
    }
    
    public void shuffle() {//shuffles the current deck
        Card[] temp = deck;//create a temporary copy of the deck
        for(int i = 0;i<52;i++){//reassign the position of each card
            int chosenCard = (int) Math.floor(Math.random()*temp.length);
            deck[i] = temp[chosenCard];
            if(i<51){//removes the card that has already been assigned from the temporary array.
                Card[] temp2 = temp;
                temp = new Card[temp2.length-1];
                for(int j = 0;j<temp.length;j++){
                    if(j<chosenCard){
                        temp[j]=temp2[j];
                    } else {
                        temp[j]=temp2[j+1];
                    }
                }
            }
        }
        currentCard = 0;
    }
    
    public void checkForDup() {//for dubugging purposes, this function checks to ensure there are no duplicates in the deck
        for(int i = 1;i<deck.length;i++){
            for(int j = 0;j<i;j++){
                if(i == j){
                    System.out.println(deck[i] + " has been duplicated");
                }
            }
        }
    }
}
