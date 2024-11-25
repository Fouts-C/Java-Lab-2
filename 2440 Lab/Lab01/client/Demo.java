package client;

import deck.Deck;

/**
 * Demo used to manually test the deck and cards.
 * 
 * @author Willow Sapphire
 * @version 04/06/2023
 */
public class Demo
{
	/**
     * Runs the demo.
     * By default, creates a sorted deck, prints.
     * then cshuffles the deck and prints again.
     * 
     * @param args command line arguments, unused
     */
    public static void main(String[] args) 
    {
        Deck deck = new Deck();
        System.out.println(deck);
        deck.shuffleDeck();
        System.out.println(deck);
    }
}
