package Engine;

// The main game controller should implement this class to get notified when updates to the game state occur.
public interface GameController {
	
	// Fired when an update occurs in the game after all sprites have been updated.
	public void UpdateOcurred();
}
