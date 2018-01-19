/**
 * This is a game of Blackjack where the player will play against one dealer
 *
 * @author Ryan Druffel
 * @version 0.0.1
 * 
 * 
 */
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
        while(gameRunning){
            playRound();//deals and plays a round of BlackJack
            playAgain();//prompts the player to play again
        }
        System.out.println("End Program");

    }

    public static void playAgain(){
        String response = "null";
        while(!(response.equalsIgnoreCase("yes")||response.equalsIgnoreCase("no"))){
            System.out.println("Would you like to play again? (yes or no)");
            response = input.nextLine().trim();
        }
        if(response.equalsIgnoreCase("yes")){
            gameRunning = true;
        } else {
            gameRunning = false;
        }
    }

    public static void playRound(){
        deck.shuffle();
        dealHands();
        int victor = 0;//0 is no winner yet, player victory is 1 and dealer victory is 2, tie is 3
        boolean blackjack = false;
        if(player.handSum()==dealer.handSum()&&dealer.handSum()==21){//if blackjack tie
            victor = 3;
            blackjack = true;
        } else if(player.handSum()==21) {//if dealer gets blackjack
            victor = 1;
            blackjack = true;
        } else if(21==dealer.handSum()) {//if player gets blackjack
            victor = 2;
            blackjack = true;
        }
        if(!blackjack)
            displayGame(false);
        while(victor==0){
            if(player.makeChoice()){
                player.hit(deck.drawCard());
                if(player.bust()){
                    victor = 2;
                } else {
                    if(dealer.makeChoice()){
                        dealer.hit(deck.drawCard());
                    }
                }
            } else {
                if(dealer.makeChoice()){
                    dealer.hit(deck.drawCard());
                } else {
                    victor = analyzeGame();
                }
            }
            if(victor==0){
                displayGame(false);
            }
        }
        endGameDisplay(blackjack, victor);
    }

    public static void dealHands(){
        Card[] dealing = {deck.drawCard(),deck.drawCard(),deck.drawCard(),deck.drawCard()};
        player.dealHand(dealing[0],dealing[2]);
        dealer.dealHand(dealing[1],dealing[3]);
    }

    public static int checkVictor(){
        return 0;
    }

    public static void endGameDisplay(boolean blackjack, int victor){
        displayGame(true);
        if(blackjack){
            System.out.println("Blackjack!");
        }
        if(victor == 3){
            System.out.println("You have tied with the dealer scoring " + player.handSum() + " points");
        } else if(victor == 1){
            System.out.println("You have " + endScore(player) + ", beating the dealer who " + endScore(dealer));
        } else if(victor ==2){
            System.out.println("You have " + endScore(player) + ", losing to the dealer who " + endScore(dealer));
        }
    }

    public static String endScore(Player person){
        String temp;
        if(person.bust()){
            temp = "busted";
        } else if(person.handSum()!=21) {
            temp = "scored " + person.handSum() + " points";
        } else {
            temp = "got blackjack";
        }
        return temp;
    }
    
    public static void displayGame(boolean isEndGame){
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("\t\t   Dealer Cards");
        System.out.println(dealer.printCards(isEndGame));
        System.out.println("\n\t\t   Player Cards");
        System.out.println(player.printCards());
        System.out.println("-------------------------------------------------------------------------------");
    }

    public static int analyzeGame(){
        if((dealer.bust()&&player.bust())||(dealer.handSum()==player.handSum())){
            return 3;
        } else if(player.bust()||(!dealer.bust()&&dealer.handSum()>player.handSum())){
            return 2;
        } else if(dealer.bust()||(!player.bust()&&dealer.handSum()<player.handSum())){
            return 1;
        }
        return 0;
    }
}
