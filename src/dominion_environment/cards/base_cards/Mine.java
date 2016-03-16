package dominion_environment.cards.base_cards;

import game_state.MisuseException;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionCard;
import dominion_environment.cards.card_types.TreasureCard;
import dominion_environment.cards.stock_cards.Copper;
import dominion_environment.cards.stock_cards.Gold;
import dominion_environment.cards.stock_cards.Silver;
import dominion_environment.players.Player;

public class Mine extends Card implements ActionCard {
	public void playCard(Player player) {
		player.moveToPlayed(this);
		player.actions--;
	}
	public void resolveCard(Player player, ArrayList<Player> otherPlayers) {
		Card card = player.resolve(this);
		if(!(card instanceof TreasureCard))
		{
			throw new MisuseException();
		}
		player.hand.remove(card);
		if(card instanceof Copper)
		{
			player.gainToHand(new Silver());
		}
		else if(card instanceof Silver)
		{
			player.gainToHand(new Gold());
		}
		else if(card instanceof Gold)
		{
			player.gainToHand(new Gold());
		}
	}
	@Override
	public int getCost() {
		return 5;
	}
	@Override
	public String toString() {
		return "Mine";
	}
}
