package dominion_environment.cards.base_cards;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.VictoryCard;
import dominion_environment.players.Player;

public class Gardens extends Card implements VictoryCard{
	public int getVictoryPoints(Player player) {
		int totalNumberOfCards = 0;
		totalNumberOfCards += player.hand.size();
		totalNumberOfCards += player.played.size();
		totalNumberOfCards += player.discard.size();
		totalNumberOfCards += player.drawDeck.size();
		return totalNumberOfCards/10;
	}
	@Override
	public int getCost() {
		return 4;
	}
	@Override
	public String toString() {
		return "Gardens";
	}
}
