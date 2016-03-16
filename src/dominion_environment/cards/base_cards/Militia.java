package dominion_environment.cards.base_cards;

import game_state.MisuseException;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionAttackCard;
import dominion_environment.players.Player;

public class Militia extends Card implements ActionAttackCard{
	public void playCard(Player player) {
		player.moveToPlayed(this);
		player.actions--;
	}
	public void resolveCard(Player player, ArrayList<Player> otherPlayers) {
		player.money+=2;
		player.resolve(this);
		attackPlayers(player, otherPlayers);
	}
	public void attackPlayers(Player player, ArrayList<Player> otherPlayers) {
		for(Player otherPlayer: otherPlayers)
		{
			otherPlayer.checkAndReactToAnAttack(this, player);
		}
	}
	public void attackEffect(Player defender, Player attacker)
	{
		attacker.resolveAttackOffense(this);
		ArrayList<Card> cardsToDiscard = defender.resolveAttackDefense(this);
		for(Card card: cardsToDiscard)
		{
			defender.discard(card);
		}
		if(defender.hand.size() != 3)
		{
			throw new MisuseException();
		}
	}
	@Override
	public int getCost() {
		return 4;
	}
	@Override
	public String toString() {
		return "Militia";
	}
}
