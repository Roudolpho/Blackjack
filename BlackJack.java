import java.util.Scanner;
public class BlackJack
{
    private static Scanner input = new Scanner(System.in);
    private static Player player = new PlayerUser();
    private static Player dealer = new PlayerDealer();
    private static boolean gameRunning = true;
    private static boolean stillDealing = true;
    private static Deck deck = new Deck();

    public static void main(String args[]) {
        System.out.println("You are now playing blackjack");
        while (gameRunning) {//this continues to run the game until the player decides to stop playing
            playRound();//deals and plays a round of BlackJack
            playAgain();//prompts the player to play again
        }
        System.out.println("End Program");
    }

    public static void playAgain(){//prompts the user if they would like to play again
        String response = "null";
        while (!(response.equalsIgnoreCase("yes")||response.equalsIgnoreCase("no"))) {
            System.out.println("Would you like to play again? (yes or no)");
            response = input.nextLine().trim();
        }
        if (response.equalsIgnoreCase("yes")) {
            gameRunning = true;
        } else {
            gameRunning = false;
        }
    }

    public static void playRound(){//plays a single round of Blackjack
        deck.shuffle();
        dealHands();
        int victor = 0;//0 is no winner yet, player victory is 1 and dealer victory is 2, tie is 3, -1 tells the game to move from the player to the dealer
        boolean blackjack = false;
        if (player.handSum()==dealer.handSum()&&dealer.handSum()==21) {//if blackjack tie
            victor = 3;
            blackjack = true;
        } else if (player.handSum()==21) {//if dealer gets blackjack
            victor = 1;
            blackjack = true;
        } else if (21==dealer.handSum()) {//if player gets blackjack
            victor = 2;
            blackjack = true;
        }
        if (!blackjack) {
            displayGame(false);
        }
        while (victor == 0) {//while the player has not busted nor hit 21 points, give them the option to hit
            if (player.makeChoice(deck)) {
                player.hit(deck.drawCard());//if they hit they draw a card
                if (player.bust()) {//checks to see if the player has busted
                    victor = 2;
                }
                if (player.handSum() == 21) {//checks to see if the player has blackjack
                    victor = -1;
                }
                if (victor==0) {//if the player has not busted or hit 21, the hands will be displayed again
                    displayGame(false);
                }
            } else {
                victor = -1;//assuming they don't hit, play moves to the dealer
            }
        }
        while (dealer.makeChoice(deck) && victor == -1) {//This is the dealers turn
            dealer.hit(deck.drawCard());
        }
        victor = analyzeGame();//analyzes to see who won the game
        endGameDisplay(blackjack, victor);//displays the results
    }

    public static void dealHands() {//deals the players' starting hands
        //Card[] dealing = {deck.drawCard(),deck.drawCard(),deck.drawCard(),deck.drawCard()};
        player.dealHand(deck.drawCard(),deck.drawCard());
        dealer.dealHand(deck.drawCard(),deck.drawCard());
    }

    public static void endGameDisplay(boolean blackjack, int victor) {//displays the end of game text
        displayGame(true);
        if (blackjack) {
            System.out.println("Blackjack!");
        }
        if (victor == 3) {
            System.out.println("You have tied with the dealer scoring " + player.handSum() + " points");
        } else if (victor == 1) {
            System.out.println("You have " + endScore(player) + ", beating the dealer who " + endScore(dealer));
        } else if (victor ==2) {
            System.out.println("You have " + endScore(player) + ", losing to the dealer who " + endScore(dealer));
        }
    }

    public static String endScore(Player person){//this method helps arrange the end of game text
        String temp;
        if (person.bust()) {
            temp = "busted";
        } else if (person.handSum()!=21) {
            temp = "scored " + person.handSum() + " points";
        } else {
            temp = "got blackjack";
        }
        return temp;
    }

    public static void displayGame(boolean isEndGame){//displays the game text
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("\t\t   Dealer Cards");
        System.out.println(dealer.printCards(isEndGame));
        System.out.println("\n\t\t   Player Cards");
        System.out.println(player.printCards());
        System.out.println("-------------------------------------------------------------------------------");
    }

    public static int analyzeGame(){//checks who the victor is
        if ((dealer.bust()&&player.bust())||(dealer.handSum()==player.handSum())) {
            return 3;
        } else if (player.bust()||(!dealer.bust()&&dealer.handSum()>player.handSum())) {
            return 2;
        } else if (dealer.bust()||(!player.bust()&&dealer.handSum()<player.handSum())) {
            return 1;
        }
        return 0;
    }
}
