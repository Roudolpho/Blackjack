
/**
 * Write a description of class Card here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Card
{
    public static String[] valsChar = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    public static int[] valsInt = {2,3,4,5,6,7,8,9,10,10,10,10,1};
    private int value;
    public Card() {
        value = (int) Math.floor(Math.random() * 13.0);
    }
    public Card(int cardNum) {
        value = cardNum;
    }
    public Card(String cardName) {
        
    }
    public String Name() {
        
    }
    public int Value() {
        
    }
    public int ID() {
        return value;
    }
}
