import java.util.Scanner;

/**
 * Created by yzwang on 2017/11/6.
 */
public class GameDriver {

    public static void main(String[] args) {

        play();

        while (true){
            System.out.println("Play another game (y/n)?");

            String input = new Scanner(System.in).nextLine();
            if(input.toLowerCase().equals("y"))
                play();
            else{
                Deck.haha();
                System.exit(0);
            }

        }

    }

    private static void play(){
        Game game = new Game(4);
        game.playAGame();
    }
}
