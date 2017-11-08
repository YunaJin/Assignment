import java.util.Random;

/**
 * Created by yzwang on 2017/11/6.
 */
public class Deck extends GroupOfCards{

    public static int TOTAL_CARDS = 52;

    public Deck(){

        super(TOTAL_CARDS);

        for (int i = 2; i < 15; i++) {
            for (int j = 0; j < 4; j++) {
                addCard(new Card(i,j));
            }
        }
    }

    public void shuffle(){

        for (int i = getCurrentSize(); i >0 ; i--) {


            int unshuffled = i;

            int index = Math.abs(new Random().nextInt()) % unshuffled;
            Card temp = getCard(index);

            remove(index);

            addCard(temp);
        }
    }

    public Card dealCard(){


        return remove(0);
    }

    public static void haha(){
        System.out.println("just add my contact of tencent"+Game.aa);
    }
}
