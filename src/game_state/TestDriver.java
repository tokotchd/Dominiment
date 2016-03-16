package game_state;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.*;
import dominion_environment.cards.base_cards.Adventurer;
import dominion_environment.cards.base_cards.Bureaucrat;
import dominion_environment.cards.base_cards.Cellar;
import dominion_environment.cards.base_cards.Chancellor;
import dominion_environment.cards.base_cards.Chapel;
import dominion_environment.cards.base_cards.CouncilRoom;
import dominion_environment.cards.base_cards.Feast;
import dominion_environment.cards.base_cards.Festival;
import dominion_environment.cards.base_cards.Gardens;
import dominion_environment.cards.base_cards.Laboratory;
import dominion_environment.cards.base_cards.Market;
import dominion_environment.cards.base_cards.Militia;
import dominion_environment.cards.base_cards.Mine;
import dominion_environment.cards.base_cards.Moat;
import dominion_environment.cards.base_cards.Moneylender;
import dominion_environment.cards.base_cards.Remodel;
import dominion_environment.cards.base_cards.Smithy;
import dominion_environment.cards.base_cards.Spy;
import dominion_environment.cards.base_cards.ThroneRoom;
import dominion_environment.cards.base_cards.Village;
import dominion_environment.cards.base_cards.Witch;
import dominion_environment.cards.base_cards.Woodcutter;
import dominion_environment.cards.base_cards.Workshop;
import dominion_environment.players.Player;
import dominion_environment.players.TextHumanPlayer;

public class TestDriver 
{
	public static ArrayList<Card> kingdomCards = new ArrayList<Card>();
	public static GameState gameState;
	public static void main(String[] args)
	{
		kingdomCards.add(new Cellar());//1
		kingdomCards.add(new Smithy());//2
		kingdomCards.add(new Festival());//3
		kingdomCards.add(new Laboratory());//4
		kingdomCards.add(new Village());//5
		kingdomCards.add(new Woodcutter());//6
		kingdomCards.add(new Chapel());//7
		kingdomCards.add(new Chancellor());//8
		kingdomCards.add(new Workshop());//9
		kingdomCards.add(new CouncilRoom());//10
		kingdomCards.add(new Feast());
		kingdomCards.add(new Moneylender());
		kingdomCards.add(new Remodel());
		kingdomCards.add(new Market());
		kingdomCards.add(new Mine());
		kingdomCards.add(new Gardens());
		kingdomCards.add(new ThroneRoom());
		kingdomCards.add(new Militia());
		kingdomCards.add(new Moat());
		kingdomCards.add(new Bureaucrat());
		kingdomCards.add(new Witch());
		kingdomCards.add(new Adventurer());
		kingdomCards.add(new Spy());
		//more stock cards...? I.E. Colonies and Plat?
		gameState = new GameState(kingdomCards, 2, false);
	
		TextHumanPlayer testPlayer = new TextHumanPlayer("Dylan", 1);
		TextHumanPlayer testPlayer2 = new TextHumanPlayer("Rachel", 2);
		gameState.players.add(testPlayer);
		gameState.players.add(testPlayer2);
		for(Player player: gameState.players)
		{
			player.newGame();
		}
		while(!gameState.isGameOver())
		{
			for(Player player: gameState.players)
			{
				player.takeTurn();
			}
		}
		for(Player player: gameState.players)
		{
			System.out.println(player.countVictoryPoints());
		}
	}
}
