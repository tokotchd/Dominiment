package game_state;

public class MisuseException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 569281428624876722L;

	public MisuseException()
	{
		super("You misused an action card.  The player is responsible for checking conditions for resolving Action Cards.");
	}

}
