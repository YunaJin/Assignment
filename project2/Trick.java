/**
 * Created by yzwang on 2017/11/6.
 */
public class Trick extends GroupOfCards{

    private int winner;
    private Card winningCard;
    private boolean hearts = false;
    private boolean queen = false;

    public Trick(int players){
        super(2 * players -1);


    }

    public int getWinner() {
        return winner;
    }

    public Card getWinningCard() {
        return winningCard;
    }

    public boolean getHearts() {
        return hearts;
    }

    public boolean getQueen() {
        return queen;
    }

    private boolean isWinner(Card card){

        //flag1 , if the current card is in the suit played
//        boolean flag1 = true;
//        for (int i = 0; i < getCurrentSize(); i++) {
//            Card cc = getCard(i);
//            if(cc.getSuit() != card.getSuit()){
//                flag1 = false;
//                break;
//            }
//        }
        if(winningCard == null )
            return true;

        if(card.getSuit() == winningCard.getSuit() && card.getNum() > winningCard.getNum())
            return true;

        return false;
    }

    public void update(int playNum, Card card){

        if(isWinner(card)){
            winner = playNum;
            winningCard = card;
        }

        if(card.getSuit() == 2)
            hearts = true;

        if(card.getSuit() == 3 && card.getNum() ==12)
            queen = true;
    }
}
