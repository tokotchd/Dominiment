package dominion_environment.cards.card_types;

import java.util.ArrayList;

import dominion_environment.players.Player;

public interface ActionAttackCard extends ActionCard{
	public abstract void attackPlayers(Player player, ArrayList<Player> otherPlayers);
	public abstract void attackEffect(Player defender, Player attacker);
}
