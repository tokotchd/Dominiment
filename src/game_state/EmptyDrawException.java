package game_state;

public class EmptyDrawException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -263747285113965503L;

	public EmptyDrawException()
	{
		super("You drew from an empty pile.  The player is responsible for checking pile sizes before drawing.");
	}

}
