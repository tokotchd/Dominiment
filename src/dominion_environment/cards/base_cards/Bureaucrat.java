package dominion_environment.cards.base_cards;

import game_state.MisuseException;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionAttackCard;
import dominion_environment.cards.card_types.VictoryCard;
import dominion_environment.cards.stock_cards.Silver;
import dominion_environment.players.Player;

public class Bureaucrat extends Card implements ActionAttackCard{

	public void resolveCard(Player player, ArrayList<Player> otherPlayers) {
		player.gainToTopOfDeck(new Silver());
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
			boolean shouldAttack = false;
			for(Card card: otherPlayer.hand)
			{
				if(card instanceof VictoryCard)
				{
					shouldAttack = true;
					break;
				}
			}
			if(shouldAttack)
			{
				otherPlayer.checkAndReactToAnAttack(this, player);
			}
		}
	}

	public void attackEffect(Player defender, Player attacker) {
		attacker.resolveAttackOffense(this);
		Card cardToPutOnTopOfDeck = defender.resolveAttackDefense(this);
		if(!(cardToPutOnTopOfDeck instanceof VictoryCard))
		{
			throw new MisuseException();
		}
		else
		{
			defender.putCardOnDeck(cardToPutOnTopOfDeck);
		}	
	}

	@Override
	public int getCost() {
		return 4;
	}

	@Override
	public String toString() {
		return "Bureaucrat";
	}

}
