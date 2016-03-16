package dominion_environment.cards.base_cards;

import java.util.ArrayList;

import game_state.MisuseException;
import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionCard;
import dominion_environment.players.Player;

public class Workshop extends Card implements ActionCard
{
	public void playCard(Player player) {
		player.moveToPlayed(this);
		player.actions--;
	}
	public void resolveCard(Player player, ArrayList<Player> otherPlayers) {
		Card card = player.resolve(this);
		if(card.getCost() > 4)
		{
			throw new MisuseException();
		}
		player.gain(card);
	}

	@Override
	public int getCost() {
		return 3;
	}
	@Override
	public String toString() {
		return "Workshop";
	}
}
