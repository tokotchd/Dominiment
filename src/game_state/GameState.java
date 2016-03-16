package game_state;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.cards.base_cards.Gardens;
import dominion_environment.cards.stock_cards.Copper;
import dominion_environment.cards.stock_cards.Curse;
import dominion_environment.cards.stock_cards.Duchy;
import dominion_environment.cards.stock_cards.Estate;
import dominion_environment.cards.stock_cards.Gold;
import dominion_environment.cards.stock_cards.Province;
import dominion_environment.cards.stock_cards.Silver;
import dominion_environment.players.Player;

public class GameState 
{
	public ArrayList<GameStateCardStack> supplyPiles;
	public int gameTurn;
	public int emptiedPiles;
	public int numOfPlayers;
	public ArrayList<Card> trashPile;
	public ArrayList<Player> players;
	public GameState(ArrayList<Card> kingdomCards, int numberOfPlayers, boolean includeProsperity)
	{
		supplyPiles = new ArrayList<GameStateCardStack>();
		players = new ArrayList<Player>();
		trashPile = new ArrayList<Card>();
		//stock cards
		supplyPiles.add(new GameStateCardStack(new Copper(), 60));
		supplyPiles.add(new GameStateCardStack(new Silver(), 40));
		supplyPiles.add(new GameStateCardStack(new Gold(), 30));
		int baseNumber;
		if(numberOfPlayers <= 2)
			baseNumber = 8;
		else
			baseNumber = 12;
		supplyPiles.add(new GameStateCardStack(new Estate(), baseNumber + numberOfPlayers*3));
		supplyPiles.add(new GameStateCardStack(new Duchy(), baseNumber));
		supplyPiles.add(new GameStateCardStack(new Province(), Math.max(baseNumber, numberOfPlayers*3)));
		supplyPiles.add(new GameStateCardStack(new Curse(), (numberOfPlayers - 1)*10));
		//Prosperity?
		if(includeProsperity)
		{
			//add those
		}
		for(int counter = 0; counter < kingdomCards.size(); counter++)
		{
			//how many of each card should be added goes here
			Card card = kingdomCards.get(counter);
			if(card instanceof Gardens)
			{
				supplyPiles.add(new GameStateCardStack(card, baseNumber));
			}
			else
			{
				supplyPiles.add(new GameStateCardStack(card, 10));
			}
		}		
		
		gameTurn = 1;
		emptiedPiles = 0;
		numOfPlayers = numberOfPlayers;
	}
	public ArrayList<Player> getOtherPlayers(Player player)
	{
		ArrayList<Player> otherPlayers = new ArrayList<Player>();
		for(Player thisPlayer : players)
		{
			if(thisPlayer != player)
			{
				otherPlayers.add(thisPlayer);
			}
		}
		return otherPlayers;
	}
	public boolean isGameOver()
	{
		//Provinces game over condition
		if(getNumberRemainingOfCard(new Province()) == 0)
		{
			return true;
		}
		//Pile Exhaust Condition
		else
		{
			if(numOfPlayers > 4 && emptiedPiles == 4)
			{
				return true;
			}
			else if(emptiedPiles == 3)
			{
				return true;
			}
		}
		return false;
	}
	public int getIndexOfCardType(Card card)
	{
		int index = -1;
		for(int counter = 0; counter < supplyPiles.size(); counter++)
		{
			if(supplyPiles.get(counter).thisCard.isA(card))
			{
				index = counter;
				break;
			}
		}
		return index;
	}
	public void takeCard(Card card)
	{
		//throws exception if there were none left to take
		if(getNumberRemainingOfCard(card) == 0)
			throw new EmptyDrawException();
		else
		{
			int index = getIndexOfCardType(card);
			supplyPiles.get(index).numLeft--;
			if(getNumberRemainingOfCard(card) == 0)
			{
				emptiedPiles++;
			}
		}
	}
	public Card getCardOfIndex(int index)
	{
		GameStateCardStack stack = supplyPiles.get(index);
		return stack.thisCard;
	}
	public int getNumberRemainingOfCard(Card card)
	{
		int index = getIndexOfCardType(card);
		return supplyPiles.get(index).numLeft;
	}
	public String toString()
	{		
		String returnString = "";
		for(int counter = 0; counter < supplyPiles.size(); counter++)
		{
			if(counter % 10 == 0)
			{
				returnString += counter + "[" + supplyPiles.get(counter).toString() + ", ";
			}
			else if(counter % 10 == 9 || counter == supplyPiles.size() - 1)
			{
				returnString += supplyPiles.get(counter).toString() + "]" + counter + "\n";
			}
			else
			{
				returnString += supplyPiles.get(counter).toString() + ", ";
			}
		}
		return returnString + 
				"Game Turn: " + gameTurn + ", " +
				"Empty Piles: " + emptiedPiles + ", " +
				"Number of Players: " + numOfPlayers + ", ";
	}
}
