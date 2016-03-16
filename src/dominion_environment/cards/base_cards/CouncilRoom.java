package dominion_environment.cards.base_cards;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.cards.card_types.ActionCard;
import dominion_environment.players.Player;

public class CouncilRoom extends Card implements ActionCard{
	public void playCard(Player player) {
		player.moveToPlayed(this);
		player.actions--;
	}
	public void resolveCard(Player player, ArrayList<Player> otherPlayers) {
		player.resolve(this);
		player.buys++;
		player.draw(4);
		for(Player otherPlayer : otherPlayers)
		{
			otherPlayer.draw(1);
		}
	}
	@Override
	public int getCost() {
		// TODO Auto-generated method stub
		return 5;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Council Room";
	}
}
