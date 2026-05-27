package game.test;

import game.card;
import game.deck;
import game.blackjack.player;
import java.util.*;

public class test{
	public static void main (String[] args) {
		player playerYou = new player("プレイヤー");
		player playerEnemy = new player("対戦相手");
		Random random = new Random();
		int testdraw = random.nextInt(25) + 3;
		for(int i = 0;i < testdraw;i++){
			playerYou.draw(playerYou.getHands(),playerEnemy.getHands());
		}
		System.out.println("今引いた" +testdraw +"枚を捨て札にします。");
		playerYou.handsReset();
		int randDraw = random.nextInt(deck.deckSize());
		System.out.println( "プレイヤーは" + randDraw + "枚引きます");
		for(int i = 0;i < randDraw;i++){
			playerYou.draw(playerYou.getHands(),playerEnemy.getHands());
		}
		System.out.println( "対戦相手は" + (53 - (randDraw + testdraw)) + "枚引きます");
		for(int i = 0;i < (53 - (randDraw + testdraw));i++){
			playerEnemy.draw(playerYou.getHands(),playerEnemy.getHands());
		}
		randDraw = deck.deckSize();
		int handsCardNumberEnemy = playerEnemy.getHands().size();
		card lastCard = playerEnemy.getHands().get(handsCardNumberEnemy - 1);
		System.out.println();
		
		System.out.println("山札のカード" + deck.deckSize() +"枚");
		for(card check:deck.testGetDeck()){
			System.out.println(check.trumpMark() + "の" + check.trumpNumber());
		}
		System.out.println("プレイヤーのカード" + playerYou.getHands().size() +"枚");
		for(card check:playerYou.getHands()){
			System.out.println(check.trumpMark() + "の" + check.trumpNumber());
		}
		System.out.println("対戦相手のカード" + playerEnemy.getHands().size() +"枚");
		for(card check:playerEnemy.getHands()){
			System.out.println(check.trumpMark() + "の" + check.trumpNumber());
		}
		int totalCard = deck.deckSize() + playerYou.getHands().size() + playerEnemy.getHands().size();
		System.out.println(totalCard);
	}
}