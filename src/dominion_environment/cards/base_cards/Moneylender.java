package dominion_environment.cards.base_cards;

import java.util.ArrayList;

import game_state.TestDriver;
import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionCard;
import dominion_environment.cards.stock_cards.Copper;
import dominion_environment.players.Player;

public class Moneylender extends Card implements ActionCard{
	public void playCard(Player player) {
		player.moveToPlayed(this);
		player.actions--;
	}
	public void resolveCard(Player player, ArrayList<Player> otherPlayers) {
		int result = findCardTypeInArray(new Copper(), player.hand);
		if(result != -1)
		{
			Card card = player.hand.get(result);
			player.hand.remove(card);
			TestDriver.gameState.trashPile.add(card);
			player.money+=3;			
		}
		player.resolve(this);
	}
	@Override
	public int getCost() {
		return 4;
	}
	@Override
	public String toString() {
		return "Moneylender";
	}
}
