package game.blackjack;

import game.deck;
import game.blackjack.player;
import java.util.*;

public class blackjack{
	public static void main (String[] args) {
			deck deckBlackjack = new deck();
			player playerYou = new player("プレイヤー");
			player playerEnemy = new player("対戦相手");
			int betMoney;
			Scanner sc = new Scanner(System.in);
			blackjack:
			while(playerEnemy.getMoney() > 0 && playerYou.getMoney() > 0){
			System.out.println("あなたの所持金は" + playerYou.getMoney() + "です。賭け金を入力してください(整数)");
			//Scanner sc = new Scanner(System.in);
				try{
					while(true){
						if(sc.hasNextInt()){
							betMoney = sc.nextInt();
							if(betMoney <= playerYou.getMoney()){
								System.out.println(betMoney);
								break;
							}else{
								System.out.println("賭け金が所持金を超えています。所持金の範囲で賭け金を入力してください(整数)");
								sc.next();
							}
						}else if (sc.hasNext()){
							System.out.println("整数で賭け金を入力してください");
							sc.next();
						}else{
							System.out.println("入力されませんでした。ゲームを終了します。");
							throw new NoSuchElementException();
						}
					}
				} catch(NoSuchElementException e){
					return;
				}
				for(int i = 0;i < 2;i++){
					playerYou.draw(playerYou.getHands(),playerEnemy.getHands());
				}
				System.out.print("あなたの手札は");
				playerYou.handsOpen();
				System.out.println("行動を教えてください。ヒットならH、スタンドならS、ダブルダウンならDを入力してください。");
				try{
				betMoney = playerYou.playerActionFirst(betMoney,playerYou.getHands(),playerEnemy.getHands(),sc);
				} catch(NoSuchElementException e){
					System.out.println("入力されませんでした。ゲームを終了します。");
					return;
				}
			for(int i = 0;i < 2;i++){
					playerEnemy.draw(playerYou.getHands(),playerEnemy.getHands());
			}
			System.out.println(playerEnemy.getName() + "の手札は");
			playerEnemy.handsOpen();
			while(playerEnemy.getPoint() < 17){
				System.out.println("ヒットします。");
				playerEnemy.draw(playerYou.getHands(),playerEnemy.getHands());
				if(playerEnemy.getPoint() > 21){
					playerEnemy.burst();
					playerEnemy.handsOpen();
					break;
				}
				playerEnemy.handsOpen();
			}
			System.out.println("スタンドします。");
			System.out.println("対戦相手の手番終了");
			System.out.println(playerEnemy.getName() + "の手札は");
			playerEnemy.handsOpen();
			System.out.println("あなたのポイントは" + playerYou.getPoint() + "です。" + "対戦相手のポイントは" + playerEnemy.getPoint() +"です。");
			if(playerYou.getPoint() > playerEnemy.getPoint()){
				playerYou.settlementWin(betMoney);
				playerEnemy.settlementLose(betMoney);
				System.out.print("あなたの勝ちです。あなたの所持金は" + playerYou.getMoney() + "で、相手の所持金は" + playerEnemy.getMoney() + "です。");
				if(playerEnemy.getMoney() <= 0){
					System.out.print("相手の所持金が0になったためあなたの勝ちです。");
					break;
				}
			}else if(playerYou.getPoint() < playerEnemy.getPoint()){
				playerYou.settlementLose(betMoney);
				playerEnemy.settlementWin(betMoney);
				System.out.print("あなたの負けです。あなたの所持金は" + playerYou.getMoney() + "で、相手の所持金は" + playerEnemy.getMoney() + "です。");
				if(playerYou.getMoney() <= 0){
					System.out.print("あなたの所持金が0になったためあなたの負けです。");
					break;
				}
			}else if(playerYou.getPoint() == playerEnemy.getPoint()){
				System.out.print("引き分けです。あなたの所持金は" + playerYou.getMoney() + "で、相手の所持金は" + playerEnemy.getMoney() + "です。");
			}
			String nextAction;
			System.out.println("続けるかどうかを選択してください。続けるならC、終了するならFを入力してください");
			try{
				nextAction = sc.next();
				while(true){
					if(nextAction.matches("[a-zA-Z]")){
						if(nextAction.equals("F")){
							if(playerYou.getMoney() > playerEnemy.getMoney()){
								System.out.print("あなたの勝ちです。あなたの所持金は" + playerYou.getMoney() + "で、相手の所持金は" + playerEnemy.getMoney() + "です。");
								break blackjack;
							}else if (playerYou.getMoney() < playerEnemy.getMoney()){
								System.out.print("あなたの負けです。あなたの所持金は" + playerYou.getMoney() + "で、相手の所持金は" + playerEnemy.getMoney() + "です。");
								break blackjack;
							}else{
								System.out.print("引き分けです。あなたの所持金は" + playerYou.getMoney() + "で、相手の所持金は" + playerEnemy.getMoney() + "です。");
								break blackjack;
							}
						}else if(nextAction.equals("C")){
							System.out.print("次のゲームに移ります。");
							playerYou.handsReset();
							playerEnemy.handsReset();
							break;
						}else{
							System.out.print("続けるならC、終了するならFを入力してください");
							sc.next();
						}
					}else{
						System.out.print("続けるならC、終了するならFを入力してください");
						sc.next();
					}
				}
			}catch(NoSuchElementException e){
				System.out.println("入力されませんでした。ゲームを終了します。");
				return;
			}
		}
	sc.close();
	}
}