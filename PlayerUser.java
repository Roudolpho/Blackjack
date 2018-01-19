import java.util.Scanner;
public class PlayerUser extends Player
{
    private Scanner input = new Scanner(System.in);
    @Override public boolean makeChoice(){
        String response = "null";
        boolean hit = false;
        while(!(response.equalsIgnoreCase("hit")||response.equalsIgnoreCase("stand"))){
            System.out.println("Hit or stand? (type \"hit\" to hit and \"stand\" to stand)");
            response = input.nextLine().trim();
        }
        if(response.equalsIgnoreCase("hit"))
            hit = true;
        return hit;
    }
}
