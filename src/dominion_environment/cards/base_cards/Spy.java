package dominion_environment.cards.base_cards;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionAttackCard;
import dominion_environment.players.Player;

public class Spy extends Card implements ActionAttackCard{

	public void resolveCard(Player player, ArrayList<Player> otherPlayers) {
		player.draw(1);
		player.actions++;
		player.resolve(this);
		attackEffect(player, player);
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
		defender.resolveAttackDefense(this);
		Card revealed = defender.reveal();
		//pass the defender player's name or some ID with the spy for advanced AI?
		boolean shouldDiscard = attacker.resolveAttackOffense(this, revealed);
		if(shouldDiscard)
		{
			defender.discard.add(revealed);
		}
		else
		{
			defender.drawDeck.add(0, revealed);
		}
	}

	@Override
	public int getCost() {
		return 4;
	}

	@Override
	public String toString() {
		return "Spy";
	}

}
