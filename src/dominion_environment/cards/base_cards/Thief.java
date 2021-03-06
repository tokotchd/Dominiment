package dominion_environment.cards.base_cards;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionAttackCard;
import dominion_environment.players.Player;

public class Thief extends Card implements ActionAttackCard{

	public void resolveCard(Player player, ArrayList<Player> otherPlayers) {
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

	public void attackEffect(Player defender, Player attacker, ArrayList<Card> revealedCards) {
		revealedCards.add(defender.reveal());
		revealedCards.add(defender.reveal());
		defender.resolveAttackDefense(this, revealedCards);
		int[] decisions = attacker.resolveAttackOffense(this);
		int cardDecision = decisions[0];
		int trashDecision = decisions[1];
	}

	@Override
	public int getCost() 
	{
		return 4;
	}

	@Override
	public String toString() {
		return "Thief";
	}

}
