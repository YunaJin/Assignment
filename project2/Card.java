/**
 * Created by yzwang on 2017/11/6.
 */
public class Card {
    /**
     * from 2 to 14,jack,queen,king,ace,11,12,13,14
     */
    private int num;

    /**
     * 0: clubs,1: diamonds, 2: hearts, 3: spades
     */
    private int suit;

    public Card(int num,int suit){
        this.num = num;
        this.suit = suit;
    }

    public int getNum(){
        return num;
    }

    public int getSuit(){
        return suit;
    }

    public void display(){

//        System.out.println();

        String what = "";
        switch (suit){
            case 0:
                what = "clubs";break;
            case 1:
                what = "diamonds";break;
            case 2:
                what = "hearts";break;
            case 3:
                what = "spades";break;
        }
        if(num>10){
            String which = "";
            switch (num){
                case 11:
                    which = "Jack";break;
                case 12:
                    which = "Queen";break;
                case 13:
                    which = "King";break;
                case 14:
                    which = "Ace";break;
            }
            System.out.println(which+" of "+what);
        }else
            System.out.println(num+" of "+what);
    }


}
