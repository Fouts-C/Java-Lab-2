package deck;

import java.util.ArrayList;
import java.util.Random;


public class Deck{


   public static final int NUM_CARDS = 52;
   private Random generator;
   private ArrayList<PlayingCard> deck;


   public Deck() {
    generator = new Random();
    initialize(); 
   }

   public Deck(int seed) {
    generator = new Random(seed);
    initialize(); 
   }


   public void initialize(){
       deck = new ArrayList<PlayingCard>();


       for (Rank r : Rank.values()){
           for (Suit s : Suit.values()){
               deck.add(new PlayingCard(r, s));
           }
       }
   }


   public void shuffleDeck() {

    for (int i = 51; i > 0; i--) {
        int rand = generator.nextInt(i + 1);

        PlayingCard cardA = deck.get(i);
        PlayingCard cardB = deck.get(rand);

        // Swap cardA and cardB
        deck.set(i, cardB);
        deck.set(rand, cardA);
    }
  }

   public PlayingCard getCard(int index){
       return deck.get(index);
   }

   public String toString(){
       
    String finalDeck =  "";

    for (PlayingCard ind : deck){
        finalDeck += ind.toString() + "\n";
    }

    return finalDeck.trim();

   }


}