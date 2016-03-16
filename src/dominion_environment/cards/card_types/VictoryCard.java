package dominion_environment.cards.card_types;

import dominion_environment.players.Player;

public interface VictoryCard 
{
	public abstract int getVictoryPoints(Player player);
}
