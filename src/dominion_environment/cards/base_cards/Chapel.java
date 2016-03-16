package dominion_environment.cards.base_cards;

import game_state.MisuseException;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionCard;
import dominion_environment.players.Player;

public class Chapel extends Card implements ActionCard{
	public void playCard(Player player) {
		player.moveToPlayed(this);
		player.actions--;
	}
	public void resolveCard(Player player, ArrayList<Player> otherPlayers) {
		ArrayList<Card> cardsToTrash = player.resolve(this);
		if(cardsToTrash.size() > 4)
		{
			throw new MisuseException();
		}
		for(Card card : cardsToTrash)
		{
			player.trash(card);
		}
	}
	@Override
	public int getCost() {
		return 2;
	}
	@Override
	public String toString() {
		return "Chapel";
	}
}
