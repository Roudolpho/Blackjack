
import java.util.Scanner;
public class BlackJack
{
    private static Scanner input = new Scanner(System.in);
    private static Player player = new PlayerUser();
    private static Player dealer = new PlayerDealer(1);
    private static boolean gameRunning = true;
    private static boolean stillDealing = true;
    private static int bet = 0;
    private static Deck deck = new Deck();
    public static void main(String args[]) {
        System.out.println("Please choose a level for the dealer\neasy, medium or hard");
        String temp = input.nextLine();
        while(!(temp.equalsIgnoreCase("easy")||temp.equalsIgnoreCase("medium")||temp.equalsIgnoreCase("hard"))){
            System.out.println("easy, medium or hard");
            temp = input.nextLine();
        }
        switch(temp.toLowerCase()){
            case "easy":
            dealer = new PlayerDealer(0);
            case "medium":
            dealer = new PlayerDealer(1);
            case "hard":
            dealer = new PlayerDealer(2);
        }

        player.addCash(ask("How much money would you like to start with?"));
        while(gameRunning){
            playRound();//deals and plays a round of BlackJack
            playAgain();//prompts the player to play again
        }
        System.out.println("End Program");

    }

    public static int ask(String s) {
        System.out.println(s);
        String temp2 = input.nextLine();
        while(!isInteger(temp2)) {
            System.out.println(s);
            temp2 = input.nextLine();
        }
        return Integer.parseInt(temp2);
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    public static void playAgain(){//prompts the user if they would like to play again
        if(player.cash > 0){
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
        } else {
            System.out.println("You are out of cash");
            gameRunning = false;
        }
    }
    
    public void takeBet(){
        
    }
    
    public static void playRound(){//plays a single round of Blackjack
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
            if(player.makeChoice(deck)){
                player.hit(deck.drawCard());
                if(player.bust()){
                    victor = 2;
                } else {
                    if(dealer.makeChoice(deck)){
                        dealer.hit(deck.drawCard());
                    }
                }
            } else {
                if(dealer.makeChoice(deck)){
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
        if(victor == 1){}else if(victor == 2){bet *= -1;} else {bet = 0;}
        player.addCash(bet);
        bet = 0;
    }

    public static void dealHands(){//deals the players' starting hands
        Card[] dealing = {deck.drawCard(),deck.drawCard(),deck.drawCard(),deck.drawCard()};
        player.dealHand(dealing[0],dealing[2]);
        dealer.dealHand(dealing[1],dealing[3]);
    }

    public static void endGameDisplay(boolean blackjack, int victor){//displays the end of game text
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

    public static String endScore(Player person){//this method helps arrange the end of game text
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

    public static void displayGame(boolean isEndGame){//displays the game text
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("\t\t   Dealer Cards");
        System.out.println(dealer.printCards(isEndGame));
        System.out.println("\n\t\t   Player Cards");
        System.out.println(player.printCards());
        System.out.println("-------------------------------------------------------------------------------");
    }

    public static int analyzeGame(){//checks who the victor is
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
