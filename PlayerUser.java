import java.util.Scanner;
public class PlayerUser extends Player
{
    private Scanner input = new Scanner(System.in);
    
    @Override public boolean makeChoice(Deck deck) {//this is the script to request that the user makes the decision to hit or stand
        String response = "null";
        boolean hit = false;//hit is the boolean for whether the player will hit or not
        while (!(response.equalsIgnoreCase("hit")||response.equalsIgnoreCase("stand"))) {//this ensures that the response is either 'hit' or 'stand'
            System.out.println("Hit or stand? (type \"hit\" to hit and \"stand\" to stand)");
            response = input.nextLine().trim();
        }
        if (response.equalsIgnoreCase("hit")) {//since hit is default false, this line will change it to true assuming the player wishes to draw
            hit = true;
        }
        return hit;
    }
}
