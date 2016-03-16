package dominion_environment.players;

import java.util.ArrayList;

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
import dominion_environment.cards.base_cards.Thief;
import dominion_environment.cards.base_cards.ThroneRoom;
import dominion_environment.cards.base_cards.Village;
import dominion_environment.cards.base_cards.Witch;
import dominion_environment.cards.base_cards.Woodcutter;
import dominion_environment.cards.base_cards.Workshop;

public interface PlayerHandlingInterface 
{
	public abstract void resolve(Adventurer adventurer);
	public abstract void resolve(Bureaucrat bureaucrat);
	public abstract void resolveAttackOffense(Bureaucrat bureaucrat);
	public abstract Card resolveAttackDefense(Bureaucrat bureaucrat);
	public abstract ArrayList<Card> resolve(Cellar cellar);
	public abstract boolean resolve(Chancellor chancellor);
	public abstract ArrayList<Card> resolve(Chapel chapel);
	public abstract void resolve(CouncilRoom councilRoom);
	public abstract Card resolve(Feast feast);
	public abstract void resolve(Festival festival);
	public abstract void resolve(Laboratory laboratory);
	public abstract void resolve(Market market);
	public abstract void resolve(Militia militia);
	public abstract ArrayList<Card> resolveAttackDefense(Militia militia);
	public abstract void resolveAttackOffense(Militia militia);
	public abstract Card resolve(Mine mine);
	public abstract void resolve(Moat moat);
	public abstract void resolve(Moneylender moneylender);
	public abstract Card[] resolve(Remodel remodel);
	public abstract void resolve(Smithy smithy);
	public abstract void resolve(Spy spy);
	public abstract boolean resolveAttackOffense(Spy spy, Card revealed);
	public abstract void resolveAttackDefense(Spy spy);
	public abstract void resolve(Thief thief);
	public abstract int[] resolveAttackOffense(Thief thief);
	public abstract void resolveAttackDefense(Thief thief, ArrayList<Card> revealedCards);
	public abstract Card resolve(ThroneRoom throneRoom);
	public abstract void resolve(Village village);
	public abstract void resolve(Witch witch);
	public abstract void resolveAttackDefense(Witch witch);
	public abstract void resolveAttackOffense(Witch witch);
	public abstract void resolve(Woodcutter woodcutter);
	public abstract Card resolve(Workshop workshop);
	
	
}
