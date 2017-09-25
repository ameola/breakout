import Engine.Game;

public class Main {
	public static void main(String[] args) {
		// Create an instance of the Breakout class.
		Breakout instance = new Breakout();
			
		// Create the game object providing it the instance of the controller (Breakout) class.
		Game game = new Game(instance, "Breakout!", Breakout.windowWidth, Breakout.windowHeight);

		// Assign the game object to the instance of the game class and start the game.
		instance.game = game;
		instance.Start();
	}
}