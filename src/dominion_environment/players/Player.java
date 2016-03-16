package dominion_environment.players;

import game_state.EmptyDrawException;
import game_state.MisuseException;
import game_state.TestDriver;

import java.util.ArrayList;

import dominion_environment.cards.Card;
import dominion_environment.cards.base_cards.Adventurer;
import dominion_environment.cards.base_cards.Bureaucrat;
import dominion_environment.cards.base_cards.Cellar;
import dominion_environment.cards.base_cards.Chapel;
import dominion_environment.cards.base_cards.Feast;
import dominion_environment.cards.base_cards.Festival;
import dominion_environment.cards.base_cards.Laboratory;
import dominion_environment.cards.base_cards.Militia;
import dominion_environment.cards.base_cards.Moat;
import dominion_environment.cards.base_cards.Moneylender;
import dominion_environment.cards.base_cards.Smithy;
import dominion_environment.cards.base_cards.Spy;
import dominion_environment.cards.base_cards.ThroneRoom;
import dominion_environment.cards.base_cards.Witch;
import dominion_environment.cards.card_types.ActionAttackCard;
import dominion_environment.cards.card_types.ReactionAttackCard;
import dominion_environment.cards.card_types.TreasureCard;
import dominion_environment.cards.card_types.VictoryCard;
import dominion_environment.cards.stock_cards.Copper;
import dominion_environment.cards.stock_cards.Curse;
import dominion_environment.cards.stock_cards.Duchy;
import dominion_environment.cards.stock_cards.Estate;
import dominion_environment.cards.stock_cards.Gold;
import dominion_environment.cards.stock_cards.Province;
import dominion_environment.cards.stock_cards.Silver;

public abstract class Player implements PlayerHandlingInterface
{
	public boolean hasMoatSafety;
	public String name;
	public int actions;
	public int buys;
	public int money;
	public ArrayList<Card> drawDeck;
	public ArrayList<Card> hand;
	public ArrayList<Card> discard;
	public ArrayList<Card> played;
	public int position;
	public Player(String name, int position)
	{
		hasMoatSafety = false;
		this.name = name;
		actions = 1;
		buys = 1;
		money = 0;
		drawDeck = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		discard = new ArrayList<Card>();
		played = new ArrayList<Card>();
		this.position = position;
	}
	public String toString()
	{
		String returnString = "";
		returnString += "Actions: " + actions;
		returnString += ", Buys: " + buys;
		returnString += ", Money: " + money;
		returnString += "\nHand: " + printDeck(hand);
		returnString += "Draw Deck: " + printDeck(drawDeck);
		returnString += "Played: " + printDeck(played);
		returnString += "Discard: " + printDeck(discard);
		return returnString;
	}
	public void newGame()
	{
		actions = 1;
		buys = 1;
		money = 0;
		drawDeck = new ArrayList<Card>();
		hand = new ArrayList<Card>();
		discard = new ArrayList<Card>();
		played = new ArrayList<Card>();
		this.gain(new Spy());
		this.gain(new Moat());
		this.gain(new Copper());
		this.gain(new Estate());
		this.gain(new Copper());
		this.gain(new Estate());
		this.gain(new Copper());
		this.gain(new Estate());
		this.gain(new Copper());
		this.gain(new Estate());
		this.gain(new Copper());
		
		/*
		for(int counter = 0; counter < 3; counter++)
		{
			gain(new Estate());
		}
		for(int counter = 0; counter < 7; counter++)
		{
			gain(new Copper());
		}
		*/
		
		draw(5);
	}
//----------------------------------------------------------Phase Methods-------------------------------------------------------------------------
	public void takeTurn()
	{
		resetPhase();
		actionPhase();
		tallyPhase();
		buyPhase();
		discardPhase();
		drawPhase();
	}
	public void resetPhase()
	{
		buys = 1;
		actions = 1;
		money = 0;
	}
	public abstract void actionPhase();
	public void tallyPhase()
	{
		//money is counted up based on treasure in hand and plus money from actions in play
		//however, actions are responsible for implementing their own effects.
		for(int counter = 0; counter < hand.size(); counter++)
		{
			Card card = hand.get(counter);
			if (card instanceof TreasureCard)
			{
				money += ((TreasureCard)card).getWorth();
			}
		}
	}
	public abstract void buyPhase();
	public void discardPhase()
	{
		//hand to discard, play to discard
		discard.addAll(hand);
		hand.clear();
		discard.addAll(played);
		played.clear();
	}
	public void drawPhase()
	{
		draw(5);
	}
//----------------------------------------------------------Public Methods------------------------------------------------------------------------	
	public int countVictoryPoints()
	{
		int vp = 0;
		Card card;
		for(int counter = 0; counter < discard.size(); counter++)
		{
			card = discard.get(counter);
			if(card instanceof VictoryCard || card instanceof Curse)
			{
				vp += ((VictoryCard)card).getVictoryPoints(this);
			}
		}
		for(int counter = 0; counter < hand.size(); counter++)
		{
			card = hand.get(counter);
			if(card instanceof VictoryCard || card instanceof Curse)
			{
				vp += ((VictoryCard)card).getVictoryPoints(this);
			}
		}
		for(int counter = 0; counter < drawDeck.size(); counter++)
		{
			card = drawDeck.get(counter);
			if(card instanceof VictoryCard || card instanceof Curse)
			{
				vp += ((VictoryCard)card).getVictoryPoints(this);
			}
		}
		for(int counter = 0; counter < played.size(); counter++)
		{
			card = played.get(counter);
			if(card instanceof VictoryCard || card instanceof Curse)
			{
				vp += ((VictoryCard)card).getVictoryPoints(this);
			}
		}
		return vp;
	}
	public void draw(int numToDraw)
	{
		for(int counter = 0; counter < numToDraw; counter++)
		{
			if(drawDeck.size() == 0)
			{
				if(discard.size() != 0)
					shuffle();
				else
					return;
			}
			Card cardDrawn = drawDeck.get(0);
			drawDeck.remove(0);
			hand.add(cardDrawn);
		}
	}
	public Card reveal()
	{
		if(drawDeck.size() == 0)
		{
			if(discard.size() != 0)
			{
				shuffle();
			}
			else
			{
				//if there are no cards left to reveal (no draw or discard), return null
				return null;
			}
		}
		Card revealedCard = drawDeck.get(0);
		drawDeck.remove(0);
		return revealedCard;
		
	}
	public void shuffle()
	{
		drawDeck.addAll(discard);
		discard.clear();
		//Fisher-Yates shuffle
		for(int counter = 0; counter < drawDeck.size()-1; counter++)
		{
			Card card1 = drawDeck.get(counter);
			int random = (int)(Math.random() * drawDeck.size());
			Card card2 = drawDeck.get(random);
			drawDeck.remove(counter);
			drawDeck.add(counter, card2);
			drawDeck.remove(random);
			drawDeck.add(random, card1);
		}
	}
	public void buy(Card card)
	{
		if(money >= card.getCost() && buys > 0)
		{
			gain(card);
			money-=card.getCost();
			buys--;
		}
	}
	public void buy(int index)
	{
		Card card = TestDriver.gameState.getCardOfIndex(index);
		buy(card);
	}
	public void checkAndReactToAnAttack(ActionAttackCard actionAttackCard, Player attacker)
	{
		//we only bother presenting the prompt to the player if they have an attack reaction in hand
		boolean shouldPromptPlayer = false;
		for(Card card: this.hand)
		{
			if(card instanceof ReactionAttackCard)
			{
				shouldPromptPlayer = true;
			}
		}
		//handle prompting player for which, if any, reaction cards to play
		//put it in a loop to allow for pluripotent and multi use reaction cards
		if(shouldPromptPlayer)
		{
			while(true)
			{
				Card reactionCardToPlay = choseReactionCardForAnAttack(actionAttackCard);
				if(reactionCardToPlay == null)
				{
					break;
				}
				if(!(reactionCardToPlay instanceof ReactionAttackCard))
				{
					throw new MisuseException();
				}
				else
				{
					ReactionAttackCard rac = (ReactionAttackCard)reactionCardToPlay;
					rac.reactionEffect(this);
				}
			}
		}
		//checks for moat safety, if not, then plays the attackEffect of the attack card.
		//if the player has moat safety, it needs to "expire" after this attack so flip the flag
		if(!this.hasMoatSafety)
		{
			actionAttackCard.attackEffect(this, attacker);
		}
		else
		{
			this.hasMoatSafety = false;
		}
	}
	public abstract Card choseReactionCardForAnAttack(ActionAttackCard actionAttackCard);
	//is nested in an infinite loop until it returns a null card
	//return a null card in order to say that the user is "done" with their reaction turn
	public void trash(Card card)
	{
		this.hand.remove(card);
		TestDriver.gameState.trashPile.add(card);
	}
	public void putCardOnDeck(Card card)
	{
		this.hand.remove(card);
		this.drawDeck.add(0, card);
	}
	public void discard(Card card)
	{
		this.hand.remove(card);
		this.discard.add(card);
	}
	public void moveToPlayed(Card card)
	{
		hand.remove(card);
		played.add(card);
	}
	public void gain(Card card)
	{
		TestDriver.gameState.takeCard(card);
		discard.add(card);
	}
	public void gainIgnoreWarning(Card card)
	{
		//for card (not player) use only
		//for attack cards drawing curses automatically, there isn't need for 
		//checking if there's curses left for every forced draw
		try{
			gain(card);
		}
		catch(Exception EmptyDrawException)
		{
			//ignore the fact that there's no curses left to draw.
			//eat the exception because gain(card) prevents drawing of 
			//cards that don't exist.  We just won't get a warning about it.
		}
	}
	public void gainToHand(Card card)
	{
		TestDriver.gameState.takeCard(card);
		hand.add(card);
	}
	public void gainToTopOfDeck(Card card)
	{
		TestDriver.gameState.takeCard(card);
		drawDeck.add(0, card);
	}
	public String printDeck(ArrayList<Card> deck)
	{
		String returnString = "";
		if(deck.size() == 0)
		{
			returnString += "0[]0\n";
		}
		else if(deck.size() == 1)
		{
			returnString += "0[" + deck.get(0).toString() + "]0\n";
		}
		else
		{
			for(int counter = 0; counter < deck.size(); counter++)
			{
				if(counter % 10 == 0)
				{
					returnString += counter + "[" + deck.get(counter).toString() + ", ";
				}
				else if(counter % 10 == 9 || counter == deck.size() - 1)
				{
					returnString += deck.get(counter).toString() + "]" + counter + "\n";
				}
				else
				{
					returnString += deck.get(counter).toString() + ", ";
				}
			}
		}
		return returnString;
	}
}
