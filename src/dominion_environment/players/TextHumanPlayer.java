package dominion_environment.players;

import game_state.TestDriver;

import java.util.ArrayList;
import java.util.Scanner;

import dominion_environment.cards.Card;
import dominion_environment.cards.base_cards.Adventurer;
import dominion_environment.cards.base_cards.Bureaucrat;
import dominion_environment.cards.base_cards.Cellar;
import dominion_environment.cards.base_cards.Chancellor;
import dominion_environment.cards.base_cards.Chapel;
import dominion_environment.cards.base_cards.CouncilRoom;
import dominion_environment.cards.base_cards.Feast;
import dominion_environment.cards.base_cards.Festival;
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
import dominion_environment.cards.card_types.ActionAttackCard;
import dominion_environment.cards.card_types.ActionCard;
import dominion_environment.cards.card_types.ReactionAttackCard;
import dominion_environment.cards.card_types.TreasureCard;
import dominion_environment.cards.card_types.VictoryCard;
import dominion_environment.cards.stock_cards.Copper;
import dominion_environment.cards.stock_cards.Gold;
import dominion_environment.cards.stock_cards.Silver;

public class TextHumanPlayer extends Player
{
	private Scanner scan;
	public TextHumanPlayer(String name, int position)
	{
		super(name, position);
		scan = new Scanner(System.in);
	}
	@Override
	public void actionPhase() 
	{
		int input = 0;
		while(input != -1 && actions > 0)
		{
			System.out.println("It is " + this.name + "'s action phase!--------------------------------------------------------------------------------------------------------------------------------");
			printGameAndPlayerState();
			System.out.print("Enter index of card you wish to play (-1 to end action phase): ");
			input = scan.nextInt();
			if(input == -1)
			{
				break;
			}	
			else if(input >= hand.size() || input < 0)
			{
				System.out.println("You don't have a card there!");
			}
			else
			{
				Card thisCard = this.hand.get(input);
				if(thisCard instanceof ActionCard)
				{
					ActionCard tCard = (ActionCard)thisCard;
					tCard.playCard(this);
					tCard.resolveCard(this, TestDriver.gameState.getOtherPlayers(this));
				}
				else
				{
					System.out.println("That is not an action card!");
				}
			}
		}
	}

	@Override
	public void buyPhase() 
	{
		int input = 0;
		while(input != -1 && buys > 0)
		{
			System.out.println("It is " + this.name + "'s buy phase!--------------------------------------------------------------------------------------------------------------------------------");
			printGameAndPlayerState();
			System.out.print("Enter index of card you wish to buy (-1 to end buy phase): ");
			input = scan.nextInt();
			if(input == -1)
			{
				break;
			}	
			else if(input >= TestDriver.gameState.supplyPiles.size() || input < 0)
			{
				System.out.println("That index isn't a card you can buy!");
			}
			else
			{
				this.buy(input);
			}
		}
	}
	public void printGameAndPlayerState()
	{
		System.out.print(TestDriver.gameState.toString());
		System.out.print(this.toString());
	}
	public int pickCardFromSupply(String message)
	{
		int input;
		while(true)
		{
			System.out.print(message);
			System.out.println("Chose index of card from supply: ");
			System.out.println(TestDriver.gameState.toString());
			input = scan.nextInt();
			if(input >= TestDriver.gameState.supplyPiles.size() || input < 0)
			{
				System.out.println("Index out of bounds");
			}
			else
			{
				break;
			}
		}
		return input;
	}
	public boolean pickYesNo(String message)
	{
		System.out.println(message);
		System.out.println("Choose yes(1) or no(0)");
		System.out.print(printDeck(hand));
		int input = scan.nextInt();
		if(input != 0)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	public int pickCardFromHand(String message)
	{
		int input;
		while(true)
		{
			System.out.print(message);
			System.out.println("Chose card.");
			System.out.print(printDeck(hand));
			input = scan.nextInt();
			if(input < -1 || input > hand.size())
			{
				System.out.println("Error Parsing Input");
			}
			else
			{
				break;
			}
		}
		return input;
	}
	public int[] pickCardsFromHand(String message)
	{
		int[] inputs;
		while(true)
		{
			System.out.print(message);
			System.out.println("Chose cards. Comma delimited indicies.");
			System.out.print(printDeck(hand));
			String input = scan.next();
			String[] splits = input.split(",");
			inputs = new int[splits.length];
			boolean noErrors = true;
			for(int counter = 0; counter < inputs.length; counter++)
			{
				inputs[counter] = Integer.parseInt(splits[counter]);
				if(inputs[counter] < 0 || inputs[counter] >= hand.size())
				{
					System.out.println("Error Parsing inputs");
					noErrors = false;
					break;
				}
			}
			if(noErrors)
			{
				break;
			}
		}
		return inputs;
	}
	//RESOLVE STATES---------------------------------------------------------------------------------------------------------------------
	public void resolve(Adventurer adventurer)
	{
		System.out.println("Adventurer: Drew until 2 treasure cards");
	}
	public void resolve(Bureaucrat bureaucrat) {
		System.out.println("Bureaucrat: Gain Silver to top of deck");
	}
	public void resolveAttackOffense(Bureaucrat bureaucrat)
	{
		System.out.println("You attacked with Bureaucrat!");
	}
	public Card resolveAttackDefense(Bureaucrat bureaucrat) {
		while(true)
		{
			int returned = pickCardFromHand("Bureaucrat: Choose Victory Card to put on deck. ");
			Card card = hand.get(returned);
			if(card instanceof VictoryCard)
			{
				return card;
			}
			else
			{
				System.out.println("That is not a victory card");
			}
		}		
	}
	public ArrayList<Card> resolve(Cellar cellar) 
	{
		int inputs[] = pickCardsFromHand("You're playing Cellar: ");
		ArrayList<Card> cardsToDiscard = new ArrayList<Card>();
		for(int counter = 0; counter < inputs.length; counter++)
		{
			Card card = hand.get(inputs[counter]);
			cardsToDiscard.add(card);
		}
		return cardsToDiscard;
	}
	public boolean resolve(Chancellor chancellor) 
	{
		boolean input = pickYesNo("Chancellor: Discard your Draw Deck?");
		return input;
	}
	public ArrayList<Card> resolve(Chapel chapel) {
		int inputs[];
		while(true)
		{
			inputs = pickCardsFromHand("Chapel: Trash up to 4 cards.");
			if(inputs.length > 4)
			{
				System.out.print("You chose more than 4 cards.");
			}
			else
			{
				break;
			}
		}
		ArrayList<Card> cardsToTrash = new ArrayList<Card>();
		for(int counter = 0; counter < inputs.length; counter++)
		{
			Card card = hand.get(inputs[counter]);
			cardsToTrash.add(card);
		}
		return cardsToTrash;
	}
	public void resolve(CouncilRoom councilRoom) {
		System.out.println("CouncilRoom: +4 cards, +1 buy");
	}
	public Card resolve(Feast feast) 
	{
		int input;
		Card card;
		while(true)
		{
			input = pickCardFromSupply("Feast: Gain a card costing up to 5. ");
			card = TestDriver.gameState.getCardOfIndex(input);
			if(card.getCost() > 5)
			{
				System.out.println("That costs more than 5");
			}
			else
			{
				break;
			}
		}
		return card;
	}
	public void resolve(Festival festival) {
		System.out.println("+2 actions, +1 buy, +2 money");
	}
	public void resolve(Laboratory laboratory) {
		System.out.println("Laboratory: +2 Cards, +1 Action");
	}
	public void resolve(Market market) 
	{
		System.out.println("Market: +1 money, +1 buy, +1 action, +1 card");
	}
	public void resolve(Militia militia) {
		System.out.println("Militia: +2 money");
	}
	public void resolveAttackOffense(Militia militia)
	{
		System.out.println("You attacked with Mlitia!");
	}
	public ArrayList<Card> resolveAttackDefense(Militia militia) {
		ArrayList<Card> cardsToDiscard = new ArrayList<Card>();
		int[] returned = pickCardsFromHand("Militia: Choose cards to discard down to 3. ");
		for(int counter = 0; counter < returned.length; counter++)
		{
			cardsToDiscard.add(hand.get(returned[counter]));
		}
		return cardsToDiscard;
	}
	public Card resolve(Mine mine)
	{
		while(true)
		{
			int input = pickCardFromHand("Mine: Choose a treasure card to upgrade");
			Card card = this.hand.get(input);
			if(card instanceof TreasureCard)
			{
				return card;
			}
			else
			{
				System.out.println("You didn't pick a treasure card");
			}
		}
	}
	public void resolve(Moat moat) {
		System.out.println("Moat: +2 cards");
	}
	public void resolve(Moneylender moneylender)
	{
		System.out.println("Moneylender: trashing copper, +3 money");
	}
	public Card[] resolve(Remodel remodel) {
		int[] chosen1;
		while(true)
		{
			chosen1 = pickCardsFromHand("Remodel: chose a card from your hand to remodel.");
			if(chosen1.length > 1)
			{
				System.out.println("You chose more than one");
			}
			else if(chosen1.length == 1)
			{
				break;
			}
		}
		Card card1 = hand.get(chosen1[0]);
		int chosen2;
		Card card2;
		while(true)
		{
			chosen2 = pickCardFromSupply("Remodel: chose a card from supply to gain.");
			card2 = TestDriver.gameState.getCardOfIndex(chosen2);
			if (card1.getCost() + 2 < card2.getCost())
			{
				System.out.println("You chose too expensive a card!");
			}
			else
			{
				break;
			}
		}
		Card[] cards = new Card[2];
		cards[0] = card1;
		cards[1] = card2;
		return cards;		
	}
	public void resolve(Smithy smithy) 
	{
		System.out.println("Smithy: +3 cards");
	}
	public void resolve(Spy spy)
	{
		System.out.println("Spy: +1 card and +1 action.");

	}
	public void resolveAttackDefense(Spy spy)
	{
		System.out.println("Your top card was spied!");
	}
	public boolean resolveAttackOffense(Spy spy, Card revealed)
	{
		System.out.println("Player revealed: " + revealed.toString());
		boolean shouldDiscard = this.pickYesNo("Do you wish them to discard it?");
		return shouldDiscard;
	}
	public Card resolve(ThroneRoom throneRoom) 
	{
		while(true)
		{
			int input = pickCardFromHand("Throne Room: Choose an action card to play twice:");
			Card card = this.hand.get(input);
			if(card instanceof ActionCard)
			{
				return card;
			}
			else
			{
				System.out.println("You didn't pick an action card");
			}
		}
	}
	public void resolve(Village village) {
		System.out.println("Village: +2 actions, +1 card");	
	}
	public void resolve(Witch witch)
	{
		System.out.println("Witch: +2 cards");
	}
	public void resolveAttackOffense(Witch witch)
	{
		System.out.println("You attacked with Witch!");

	}
	public void resolveAttackDefense(Witch witch)
	{
		System.out.println("Witch: gain a curse card.");
	}
	public void resolve(Woodcutter woodcutter) {
		System.out.println("Woodcutter: +2 money, +1 buy");
	}	
	public Card resolve(Workshop workshop) 
	{
		int input;
		Card card;
		while(true)
		{
			input = pickCardFromSupply("Workshop: choose a card costing less than 4 to acquire: ");
			card = TestDriver.gameState.getCardOfIndex(input);
			if(card.getCost() > 4)
			{
				System.out.println("That costs more than 4");
			}
			else
			{
				break;
			}
		}
		return card;
	}
	@Override
	public Card choseReactionCardForAnAttack(ActionAttackCard actionAttackCard) {
		while(true)
		{
			System.out.print(actionAttackCard.toString() + ": ");
			int cardIndex = pickCardFromHand("You are being attacked: chose reaction card to play, -1 to terminate. ");
			if(cardIndex == -1)
			{
				return null;
			}
			else
			{
				Card card = this.hand.get(cardIndex);
				if(card instanceof ReactionAttackCard)
				{
					return card;
				}
				else
				{
					System.out.println("That's not an applicable reaction card.");
				}
			}
		}
	}
	
}
