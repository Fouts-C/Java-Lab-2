package deck;

public class PlayingCard {

   private Suit suit;
   private Rank rank;


   public PlayingCard(Rank rank, Suit suit){
       this.rank = rank;
       this.suit = suit;
   }


   public Rank getRank(){
       return rank;
   }


   public Suit getSuit(){
       return suit;
   }


   public void setRank(Rank rank){
       this.rank = rank;
   }


   public void setSuit(Suit suit){
       this.suit = suit;
   }


   public String toString(){
       return rank + " of " + suit;
   }
}
