package game;

import game.card;
import java.util.*;

public class deck{
 private static ArrayList<card> deck = new ArrayList<>();
 private static int numberOfCardsInTheDeck;
 private static Random random = new Random();
 private static card addCard;
 static {
  for(int i = 1;i < 14;i++){
   for(int j = 0;j < 4;j++){
    String trumpMark = switch(j){
     case 0 -> "heart";
     case 1 -> "diamond";
     case 2 -> "spade";
     default -> "club";
    };
   	addCard = new card(trumpMark,i);
    deck.add(addCard);
   }
  }
 }
 public static card draw(ArrayList<card> handsOfYou,ArrayList<card> handsOfEnemy,String name){
	card drawedCard;
 	numberOfCardsInTheDeck = deck.size();
	drawedCard = deck.remove(random.nextInt(numberOfCardsInTheDeck));
 	System.out.println(name + "が引いたのは" + drawedCard.trumpMark() + "の" + drawedCard.trumpNumber() + "です。");
 	shuffle(handsOfYou,handsOfEnemy);
 	return drawedCard;
 }
 public static void shuffle(ArrayList<card> handsOfYou,ArrayList<card> handsOfEnemy){
	numberOfCardsInTheDeck = deck.size();
 	if(numberOfCardsInTheDeck == 0){
 		for(int i = 1;i < 14;i++){
   for(int j = 0;j < 4;j++){
    String trumpMark = switch(j){
     case 0 -> "heart";
     case 1 -> "diamond";
     case 2 -> "spade";
     default -> "club";
    };
   	addCard = new card(trumpMark,i);
   	if(!(handsOfYou.contains(addCard) || handsOfEnemy.contains(addCard))){
    	deck.add(addCard);
    }
   }
  }
 }
 }
}