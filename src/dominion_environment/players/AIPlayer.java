package dominion_environment.players;

import dominion_environment.cards.base_cards.Cellar;
import dominion_environment.cards.base_cards.Smithy;
import dominion_environment.cards.stock_cards.Copper;

public class AIPlayer extends Player implements PlayerHandlingInterface
{
	@Override
	public void actionPhase() 
	{
		int index = findCardTypeInArray(new Smithy(), hand);
		if(index != -1)
		{
			((Smithy)hand.get(index)).playEffect(this);
		}
	}
	@Override
	public void buyPhase() 
	{
		buy(new Copper());
	}
	public void handle(Cellar cellar) 
	{
		
	}
}
