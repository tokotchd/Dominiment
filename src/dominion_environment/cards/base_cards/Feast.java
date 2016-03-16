package dominion_environment.cards.base_cards;

import java.util.ArrayList;

import game_state.MisuseException;
import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionCard;
import dominion_environment.players.Player;

public class Feast extends Card implements ActionCard{
	public void playCard(Player player) {
		player.trash(this);
		player.actions--;
	}
	public void resolveCard(Player player, ArrayList<Player> otherPlayers) 
	{
		Card card = player.resolve(this);
		if(card.getCost() > 5)
		{
			throw new MisuseException();
		}
		player.gain(card);
	}
	@Override
	public int getCost() 
	{
		return 4;
	}
	@Override
	public String toString()
	{
		return "Feast";
	}
}
