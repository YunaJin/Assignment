/**
 * Created by yzwang on 2017/11/6.
 */
public class Hand extends GroupOfCards{

    public final int NUM;
    private int shortest =0;

    //flag , if heart has been played
    public boolean flag = false;

    public Hand(int playerNum, int numberOfCards){

        super(numberOfCards);

        NUM = playerNum;
    }

    public void sort(){

        for (int j = 0; j < getCurrentSize(); j++) {

            int index = 0;
            int min = Integer.MAX_VALUE;

            for (int i = 0; i < getCurrentSize() - j; i++) {
                int haha = 13 * getCard(i).getSuit() + getCard(i).getNum();
                if (haha < min) {
                    min = haha;
                    index = i;
                }
            }

//            Card temp = remove(index);
//            addCard(temp);
            Card temp = getCard(getCurrentSize()-1-j);
            setCard(getCurrentSize()-1-j,getCard(index));
            setCard(index,temp);

//            System.out.println("-----------");
//            display();
        }
    }

    public void setShortest(){

        shortest = 0;

        if(findCount(1) <= findCount(0))
            shortest = 1;

        if(findCount(3) <= Math.min(findCount(1),findCount(0)) &&
                find(14,3)==-1 && find(13,3)==-1 && find(12,3)==-1)
            shortest = 3;
    }

    public int getShortest(){

        return shortest;
    }
//
    public Card playACard(Game game, Trick trick){

        int index = -1;

        if(trick.getCurrentSize() == 0 ){

//            setShortest();
            //can not exists shortest is void
            //...
            if(findHighest(getShortest()) == -1)
                index = findLowestAnysuit();
            else
                index =findHighest(getShortest());
        }
//        //has not queen or heart
        else if(trick.getCurrentSize() +1 == game.PLAYERS && !trick.getQueen() && !trick.getHearts() &&
                (index = findLastHigh(trick.getWinningCard().getNum(),trick.getWinningCard().getSuit())) >=0);
//
        else if(trick.getCurrentSize() +1 == game.PLAYERS &&
                (index = findHighestBelow(trick.getWinningCard()))>=0);
//        else if((index = findHighestBelow(trick.getWinningCard())) >= 0)
//            ;
        else if((index = findMiddleHigh(game, trick.getWinningCard().getSuit())) >= 0)//change
            ;
        else if ((index = find(12, 3)) >= 0); // queen of Spades
        else if ((index = find(14, 3)) >= 0); // Ace of Spades
        else if ((index = find(13, 3)) >= 0); // King of Spades
        else if ((index = findHighest(2)) >= 0); // heart
        else
        {
            index = findHighest();
        }
//        //finnal hand
//        else if(trick.getCurrentSize() +1 == game.PLAYERS){
//            //has not
//            if(!trick.getQueen() && !trick.getHearts()){
//                index = findLastHigh(trick.getWinningCard().getNum(),trick.getWinningCard().getSuit());
//            }else {
//                index = findHighestBelow(trick.getWinningCard());
//            }
//        }else if()

        if(index == -1)
            index = findHighest();

        Card result = remove(index);
        trick.addCard(result);
        trick.update(NUM,result);
        game.updateHeartsAndQueen(result);

        return result;
    }
//
    public int findLowest(int suit){

        int result = Integer.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < getCurrentSize(); i++) {
            if(getCard(i).getSuit() == suit){
                if(getCard(i).getNum() < result){
                    result = getCard(i).getNum();
                    index = i;
                }
            }
        }

        if(result == Integer.MAX_VALUE)
            return -1;

        return index;
    }

    //find lowest in any suit
    public int findLowestAnysuit(){
        int result = Integer.MAX_VALUE;
        int index = -1;

        for (int i = 0; i < getCurrentSize(); i++) {
                if(getCard(i).getNum() < result){
                    result = getCard(i).getNum();
                    index = i;
                }
        }

        if(result == Integer.MAX_VALUE)
            return -1;

        return index;
    }
//
    private int findCount(int suit){

        int result = 0;
        for (int i = 0; i < getCurrentSize(); i++) {
            if(getCard(i).getSuit() == suit)
                result ++;
        }

        return result;
    }
//
    private int find(int num, int suit){

        for (int i = 0; i < getCurrentSize(); i++) {
            if(getCard(i).getNum() == num && getCard(i).getSuit() == suit)
                return i;
        }

        return -1;
    }
//
    private int findHighest(int suit){

        int result = Integer.MIN_VALUE;
        int index = -1;

        for (int i = 0; i < getCurrentSize(); i++) {
            if(getCard(i).getSuit() == suit){
                if(getCard(i).getNum() > result){
                    result = getCard(i).getNum();
                    index = i;
                }
            }
        }

        if(result == Integer.MIN_VALUE)
            return -1;

        return index;
    }
//
    public int findLowest(Game game){


        int min = Integer.MAX_VALUE;
        int index = -1 ;

        for (int i = 0; i < getCurrentSize(); i++) {
            if(getCard(i).getNum() < min){

                if(getCard(i).getSuit() == 2 && !flag)
                    continue;

                min = getCard(i).getNum();
                index = i;
            }
        }

        return index;
    }
//
    public int findLastHigh(int num, int suit){

        int high = findHighest(suit);
        if(high == -1)
            return high;

        int index = high;

        if(suit == 3 && getCard(index).getNum() == 12){

            if(num > 12)
                return index;
            else {

                for (int i = 0; i < getCurrentSize(); i++) {
                    if(getCard(i).getSuit() == suit){
                        if(getCard(i).getNum() <12){
                            index = i;
                            break;
                        }
                    }
                }
                return index;
            }

        }

        return index;
    }
//
    private int findHighestBelow(Card winningcard){

//        if(winningcard)
        for (int i = 0; i < getCurrentSize(); i++) {
            Card cc = getCard(i);
            if(cc.getSuit() == winningcard.getSuit() && cc.getNum() < winningcard.getNum()){
//                if((i+1) < getCurrentSize() && getCard(i+1).getSuit() != winningcard.getSuit())
//                    return -1;
//                else
                    return i;

            }

        }

        return  -1;
    }
//
    private int findMiddleHigh(Game game, int suit){

        if(suit == 3 && !game.getQueenOfSpades())
            return findHighestBelow(new Card(12,3));

        int highest = findHighest(suit);
        if(highest == -1)
            return highest;

//
//        int highest = findHighest(suit);
//        if(highest == -1 && find(12,3)==-1 && find(13,3)==-1 && find(12,3)==-1)
//            return findHighest();

//        if(suit == 3 && find(12,3)!=-1){
//            for (int i = 0; i < getCurrentSize(); i++) {
//                if(getCard(i).getSuit() ==3 && getCard(i).getNum() < 12)
//                    return i;
//            }
//        }

        return highest;
    }
//
    // just find the highest
    private int findHighest(){

        int max = Integer.MIN_VALUE;
        int index = -1;

        for (int i = 0; i < getCurrentSize(); i++) {
            if(getCard(i).getNum() > max){
                max = getCard(i).getNum();
                index = i;
            }
        }

        return index;
    }

}
