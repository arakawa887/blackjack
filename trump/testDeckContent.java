package game.test;


import game.card;
import game.deck;


public class testDeckContent{
	public static void main (String[] args) {
		for(card check:deck.testGetDeck()){
			System.out.println(check.trumpMark() + "の" + check.trumpNumber());
		}
		deck.testDeckcontein();
	}
}