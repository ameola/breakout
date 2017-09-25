import Engine.Game;
import Engine.GameController;

// The entry point for the breakout game. Implements game controller so we can get
// notified after each frame is drawn.
public class Breakout implements GameController {

	// The width of the window.
	public static int windowWidth = 512;
	
	// The height of the window.
	public static int windowHeight = 512;
	
	// The game object that controls the running of the game.
    public Game game;
    
    // The ball sprite.
    public Ball ball;

    // Starts the game.
	public void Start(){		
		// Create the ball sprite and add it to the games sprites.
		ball = new Ball();
		game.addSprite(ball);
		
		// Run the main game loop
		game.runGameLoop();
	}
	
	// Run each frame after the sprites have been updated.
	public void UpdateOcurred() {
		// Nothing needed here. You will need to add logic to check the state of the game here.
	}
}
