package dominion_environment.cards.base_cards;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionCard;
import dominion_environment.cards.card_types.TreasureCard;
import dominion_environment.players.Player;

public class Adventurer extends Card implements ActionCard{

	public void resolveCard(Player player, ArrayList<Player> otherPlayers) {
		ArrayList<Card> treasureCards = new ArrayList<Card>();
		ArrayList<Card> cardsToDiscard = new ArrayList<Card>();
		while(treasureCards.size() < 2)
		{
			Card revealedCard = player.reveal();
			if(revealedCard == null)
			{
				//this should only ever trigger if Adventurer is played and player has <2 treasure cards in deck + discard
				break;
			}
			else if(revealedCard instanceof TreasureCard)
			{
				treasureCards.add(revealedCard);
			}
			else
			{
				cardsToDiscard.add(revealedCard);
			}
		}
		player.hand.addAll(treasureCards);
		player.discard.addAll(cardsToDiscard);
		player.resolve(this);
	}

	public void playCard(Player player) {
		player.moveToPlayed(this);
		player.actions--;
	}

	@Override
	public int getCost() {
		return 6;
	}

	@Override
	public String toString() {
		return "Adventurer";
	}
}
