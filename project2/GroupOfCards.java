/**
 * Created by yzwang on 2017/11/6.
 */
public class GroupOfCards {

    private Card[] cards;
    private int currentSize;

    public GroupOfCards(int number){
        cards = new Card[number];
        currentSize = 0;
    }

    public int getCurrentSize(){
        return currentSize;
    }

    public Card getCard(int i){
        return cards[i];
    }

    public void setCard(int index,Card card){
        cards[index] = card;
    }

    public void addCard(Card card){

//        Card[] newCards = new Card[currentSize+1];
//        for (int i = 0; i < currentSize; i++) {
//            newCards[i] = cards[i];
//        }
//        newCards[currentSize] = card;
//        cards = newCards;

        cards[currentSize] = card;

        currentSize ++;
    }

    public Card remove(int index){
        Card result = cards[index];

        for (int i = index; i < currentSize-1; i++) {
            cards[i] = cards[i+1];
        }
        currentSize--;
        return result;
    }

    public void display(){

        for (int i = 0; i < currentSize; i++) {
            cards[i].display();
        }
        int a =1;
    }
}
