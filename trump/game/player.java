package game.blackjack;

import game.card;
import game.deck;
import java.util.*;

public class player{
	private int money = 20000;
	private ArrayList<card> handOfCards = new ArrayList<>(); 
	private int point = 0;
	private final card HEART_1 = new card("heart",1);
	private final card DIAMOND_1 = new card("diamond",1);
	private final card SPADE_1 = new card("spade",1);
	private final card CLUB_1 = new card("club",1);
	private String name;
	public player(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void draw(ArrayList<card> handOfYou,ArrayList<card> handOfEnemy){
		card drawedCard = deck.draw(handOfYou,handOfEnemy,name);
		handOfCards.add(drawedCard);
		this.point = calculationPoint();
	}
	public int getMoney(){
		return money;
	}
	
	public int getPoint(){
		return point;
	}
	
	public int calculationPoint(){
		point = 0;
		int numberOfCardsInHand = handOfCards.size();
		for(int i = 0;i < numberOfCardsInHand;i++){
			if(handOfCards.get(i).trumpNumber() > 10){
				point += 10;
			}else{
				point = point + handOfCards.get(i).trumpNumber();
			}
		}
		if((handOfCards.contains(HEART_1) || handOfCards.contains(DIAMOND_1) || handOfCards.contains(SPADE_1) || handOfCards.contains(CLUB_1)) && point <= 11){
			point += 10;
		}
		return point;
	}
	public ArrayList<card> getHands(){
		return handOfCards;
	}
	public void handsReset(){
		handOfCards = new ArrayList<>();
	}	
	public void settlementWin(int betMoney){
		money += betMoney;
	}
	public void settlementLose(int betMoney){
		money -= betMoney;
	}
	
	public void burst(){
		this.point = 0;
		System.out.println("バーストです。");
	}
	public void handsOpen(){
		for(int i = 0;i < handOfCards.size();i++){
			card draewdCard = handOfCards.get(i);
			System.out.print(draewdCard.trumpMark() + "の" + draewdCard.trumpNumber() + "で");
		}
		if(point > 21){
			burst();
		}
		System.out.println(name + "のポイントは" + point + "です。");
	}
	
	public void playerActionNext(ArrayList<card> handOfYou,ArrayList<card> handOfEnemy,Scanner sc){
		System.out.println("行動を教えてください。ヒットならH、スタンドならSを入力してください。");
		String action = sc.next();
		nextAction:
		while(true){
			if(action.matches("[a-zA-Z]")){
				if(action.equals("H")){
					draw(handOfYou,handOfEnemy);
					System.out.print(name + "の手札は");
					handsOpen();
					if(point == 0){
						break nextAction;
					}
					action = sc.next();
				}else if(action.equals("S")){
					break nextAction;
				}else{
					System.out.println("ヒットならH、スタンドならSを入力してください。");
					action = sc.next();
				}
			}else{
				System.out.println("ヒットならH、スタンドならSを入力してください。");
				action = sc.next();
			}
		}
	}
	
	public int playerActionFirst(int betmoney,ArrayList<card> handOfYou,ArrayList<card> handOfEnemy,Scanner sc){
		int rate = betmoney;
		String action = sc.next();
		firstAction:
		while(true){
			if(action.matches("[a-zA-Z]")){
				if(action.equals("H")){
					draw(handOfYou,handOfEnemy);
					System.out.println(name + "の手札は");
					handsOpen();
					if(point == 0){
						break firstAction;
					}
					playerActionNext(handOfYou,handOfEnemy,sc);
					break firstAction;
				}else if(action.equals("S")){
					break firstAction;
				}else if(action.equals("D")){
					draw(handOfYou,handOfEnemy);
					System.out.println(name + "の手札は");
					handsOpen();
					rate *= 2;
					break firstAction;
				}else{
					System.out.println("ヒットならH、スタンドならS、ダブルダウンならDを入力してください。");
					action = sc.next();
				}		
			}else{
				System.out.println("ヒットならH、スタンドならS、ダブルダウンならDを入力してください。");
				action = sc.next();
			}
		}
		return rate;
	}
}