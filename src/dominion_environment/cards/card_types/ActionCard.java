package dominion_environment.cards.card_types;

import java.util.ArrayList;

import dominion_environment.players.Player;

public interface ActionCard 
{
	public abstract void resolveCard(Player player, ArrayList<Player> otherPlayers);
	public abstract void playCard(Player player);
}
