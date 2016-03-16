package dominion_environment.cards.base_cards;

import java.util.ArrayList;

import game_state.MisuseException;
import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionCard;
import dominion_environment.players.Player;

public class Remodel extends Card implements ActionCard{
	public void playCard(Player player) {
		player.moveToPlayed(this);
		player.actions--;
	}
	public void resolveCard(Player player, ArrayList<Player> otherPlayers) {
		Card cardToTrash, cardToGain;
		Card[] cards = player.resolve(this);
		if(cards.length != 2)
		{
			throw new MisuseException();
		}
		cardToTrash = cards[0];
		cardToGain = cards[1];
		if(cardToTrash.getCost() + 2 < cardToGain.getCost())
		{
			throw new MisuseException();
		}
		player.trash(cardToTrash);
		player.gain(cardToGain);
	}
	@Override
	public int getCost() {
		return 4;
	}
	@Override
	public String toString() {
		return "Remodel";
	}
}
