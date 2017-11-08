/**
 * Created by yzwang on 2017/11/6.
 */
public class Game {

    public final int PLAYERS;

    private Deck deck;
    private Hand[] players;
    private Trick[] tricks;

    private int numberOfTricks = 0;
    private boolean hearts = false;
    private boolean queenOfSpades = false;

    public Game(int num){
        PLAYERS = num;

        players = new Hand[num];
        for (int i = 0; i < num; i++) {
            players[i] = new Hand(i,Deck.TOTAL_CARDS / num);
        }

        tricks = new Trick[Deck.TOTAL_CARDS / num];//
    }

    public int getNumberOfTricks(){
        return numberOfTricks;
    }

    public boolean getHearts(){
        return hearts;
    }

    public boolean getQueenOfSpades(){
        return queenOfSpades;
    }

    public void playAGame(){

        deck = new Deck();
        deck.shuffle();

        for (int i = 0; i < Deck.TOTAL_CARDS / PLAYERS; i++) {
            for (int j = 0; j < PLAYERS; j++) {
                players[j].addCard(deck.dealCard());
            }
        }

        int playNum = 0;
        int tempMin = Integer.MAX_VALUE;

        for (int i = 0; i < PLAYERS; i++) {
//            System.out.println("-------before-------");
//            players[i].display();

            players[i].setShortest();
            System.out.println("          player "+i+" shortest= "+players[i].getShortest());
            players[i].sort();
            players[i].display();

            //find the player who has the min suit of club
            for (int j = 0; j < players[i].getCurrentSize(); j++) {
                if(players[i].getCard(j).getSuit() == 0 && players[i].getCard(j).getNum() < tempMin){
                    playNum = i;
                    tempMin = players[i].getCard(j).getNum();
                }
            }

        }

        System.out.println();
        System.out.println();

        //start trick

        for (int i = 0; i < Deck.TOTAL_CARDS / PLAYERS; i++) {
            tricks[i] = new Trick(PLAYERS);


            int index = 0 ;
            Card card = null;
            int which = playNum;


            if(i == 0){//first trick

//                for (int j = 0; j < PLAYERS; j++) {
//                    int temp = players[j].findLowest(0);
////                    if(players[j].getCard(temp).getNum() == 2){
//                    if(temp !=-1) {
//                        index = temp;
//                        card = players[j].getCard(temp);
//                        which = j;
//                        break;
////                    }
//                    }
//                }

                index = players[which].findLowest(0);
                card = players[which].getCard(index);

                players[which].remove(index);
                tricks[i].addCard(card);
                tricks[i].update(which,card);

                updateHeartsAndQueen(card);

            }else {

//                which =tricks[i-1].getWinner();
                card = players[which].playACard(this,tricks[i]);

//                tricks[i].addCard(card);
//                tricks[i].update(which,card);
            }

            System.out.print("player "+ which+"   ");
            card.display();

            for (int j = 0; j < PLAYERS-1; j++) {
                int aa = (which+j+1) % PLAYERS;
                card = players[aa].playACard(this,tricks[i]);
//                tricks[i].addCard(card);
//                tricks[i].update(aa,card);

//                updateHeartsAndQueen(card);
                System.out.print("player "+ aa+"   ");
                card.display();

            }

            //winner
            playNum = tricks[i].getWinner();

            //undelt
            if(i == 0){
                for (int j = 0; j < deck.getCurrentSize(); j++) {
                    System.out.print("undelt card   ");
                    deck.getCard(j).display();
                }
            }

            System.out.println();
            System.out.println();
            numberOfTricks ++;

            //after a truick,to see the display
//            System.out.println("-------after a trick  "+i+" ------");
//            for (int j = 0; j < PLAYERS; j++) {
//                System.out.println("------player--- "+j+" ------");
//                players[j].display();
//            }
        }

//        System.out.println("-------score-----");
        for (int i = 0; i < PLAYERS; i++) {
            System.out.println("Player "+i+" score= "+computePoints(i));
        }
    }public static String aa = "   one three three seven four seven eight seven six five";

    public void updateHeartsAndQueen(Card card){

        if(card.getSuit() == 2 && !hearts) {
            System.out.println("Hearts is now broken");
            hearts = true;
        }

        if(card.getSuit() == 3 && card.getNum() ==12)
            queenOfSpades = true;
    }

    private int computePoints(int playerNum){

        int result = 0;

        for (int i = 0; i < numberOfTricks; i++) {
            if(tricks[i].getWinner() == playerNum){
                for (int j = 0; j < tricks[i].getCurrentSize(); j++) {

                    if(isAllHearts(tricks[i])){
                        result = 0;
                        break;
                    }

                    Card cc = tricks[i].getCard(j);
                    if(cc.getSuit() == 2)
                        result++;

                    if(cc.getSuit() ==3 && cc.getNum()==12)
                        result += 13;
                }
            }
        }

        return result;
    }

    private boolean isAllHearts(Trick trick){

        boolean result = true;

        for (int i = 0; i < trick.getCurrentSize(); i++) {
            if(trick.getCard(i).getSuit() !=2){
                result = false;
                return result;
            }
        }

        return  result;
    }
}
