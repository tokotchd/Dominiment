package dominion_environment.cards.base_cards;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionAttackCard;
import dominion_environment.cards.stock_cards.Curse;
import dominion_environment.players.Player;

public class Witch extends Card implements ActionAttackCard{

	public void resolveCard(Player player, ArrayList<Player> otherPlayers) {
		player.draw(2);
		player.resolve(this);
		attackPlayers(player, otherPlayers);
	}

	public void playCard(Player player) {
		player.moveToPlayed(this);
		player.actions--;
	}

	public void attackPlayers(Player player, ArrayList<Player> otherPlayers) {
		for(Player otherPlayer: otherPlayers)
		{
			otherPlayer.checkAndReactToAnAttack(this, player);
		}
	}

	public void attackEffect(Player defender, Player attacker) {
		attacker.resolveAttackOffense(this);
		defender.resolveAttackDefense(this);
		defender.gainIgnoreWarning(new Curse());
	}

	@Override
	public int getCost() {
		return 5;
	}

	@Override
	public String toString() {
		return "Witch";
	}

}
