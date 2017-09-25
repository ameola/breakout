package Engine;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Timer;
import javax.swing.JFrame;

// Implements action listener for the time and key listener for keyboard input
public class Game implements ActionListener, KeyListener {	

	// Internal Panel class to hide the Jpanel implementation.
	private InternalPanel internalGame;
	
	// Window background color. Default is black.
	public Color backgroundColor = Color.BLACK;
	
	// Game name (window name)
	public String gameName;
	
	// Width and height of the window
	private int windowWidth, windowHeight;
	
	// Speed at which the game is running. Default is 60fps.
	private int fps = 60;
	
	// Timer to control when to draw frames (note there is no logic to ensure the frame rate is met
	// the actual frame rate will be 60fps minus the amount of time spent in game logic).
	private Timer gameTimer;
	
	// A list of sprites to draw.
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();

        // A list of sprites to add.
	private ArrayList<Sprite> spritesToAdd = new ArrayList<Sprite>();

        // A list of sprites to remove.
	private ArrayList<Sprite> spritesToRemove = new ArrayList<Sprite>();

	// A list of all keys currently pressed. Constants are stored in the java.awt.event.KeyEvent class
	private boolean[] keysPressed = new boolean[255];
	
	// The main game controller to receive update events.
	private GameController gameController;
	
	// Game constructor taking in the game controller, game name, width and height of the window.
	public Game(GameController controller, String name, int width, int height){
		gameController = controller;
		gameName = name;
		windowWidth = width;
		windowHeight = height;
		
		initializeGame();
	}
	
	// Adds a sprite to the sprites we are drawing.
	public void addSprite(Sprite sprite){
		spritesToAdd.add(sprite);		
	}
	
	// Remove a sprite from the sprites we are drawing.
	public void removeSprite(Sprite sprite){
		spritesToRemove.remove(sprite);
	}
	
	// Gets all the sprites.
	public Sprite[] getSprites(){
		// Some pretty crazy Java syntax here.
		Sprite[] returnArray = new Sprite[sprites.size()];
		return sprites.toArray(returnArray);
	}

	// Is the provided sprite loaded in the game?
	public boolean isSpriteLoaded(Sprite sprite){
		return sprites.contains(sprite);
	}
	
	// Initializes the window for the game.
	private void initializeGame(){
		internalGame = new InternalPanel(this);
		
		JFrame frame = new JFrame(gameName);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setResizable(false);		
		// Window width and height are adjusted for the menu bar.
		frame.setSize(windowWidth + 6, windowHeight + 30);
		frame.getContentPane().add(internalGame, BorderLayout.CENTER);
		frame.addKeyListener(this);
	}
	
	// Starts the game timer or resumes it if already running.
	public void runGameLoop(){
		if (gameTimer == null) {
		    gameTimer = new Timer(1000 / fps, this);
		}
		
		gameTimer.start();
	}

	// Pauses the game loop.
	public void PauseGameLoop() {
		gameTimer.stop();
	}
	
	// Sets the speed of the game loop.
	public void setFps(int framesPerSecond) {
		fps = framesPerSecond;
		
		if (gameTimer != null) {
			gameTimer.setDelay(1000 / fps);
		}
	}
	
	// Checks to see if the provided sprite collided with any other sprite. Returns null if
	// no collision was detected.
	public Sprite checkCollision(Sprite source){
		// Loop through all sprites returning the first sprite we collided with.
		for (Sprite s : sprites){
			if (s != source && s.canCollide){
				if (checkCollision(source, s) != null){
					return s;
				}
			}
		}		
		
		// Returns null if no collision was detected.
		return null;
	}
	
	// Checks if the provided sprites collided with each other
	public Sprite checkCollision(Sprite source, Sprite destination)
	{		  
		// Get the left, right, top and bottom positions for simplicity
		float left1, left2;
		float right1, right2;
		float top1, top2;
		float bottom1, bottom2;

		left1 = source.xPos;
		left2 = destination.xPos;
		right1 = source.xPos + source.width;
		right2 = destination.xPos + destination.width;
		top1 = source.yPos;
		top2 = destination.yPos;
		bottom1 = source.yPos + source.height;
		bottom2 = destination.yPos + destination.height;

		// return null if no collision occurred
		if (bottom1 < top2) return null;
		if (top1 > bottom2) return null;

		if (right1 < left2) return null;
		if (left1 > right2) return null;

		// return the sprite we collided with if a collision occurred
		return destination;
	}
	
	// Event handler that occurs when the timer reaches 0.
	public void actionPerformed(ActionEvent e) {
		// Update any keys that were pressed first.
		for (Sprite s : sprites){
			s.Update(keysPressed);
		}

		// Inform the game controller an update has occurred.
		if (gameController != null)
		{
			gameController.UpdateOcurred();
		}

                for (Sprite s : spritesToRemove){
                    sprites.remove(s);
                }
                
                spritesToRemove.clear();
                
                for (Sprite s : spritesToAdd){
                    sprites.add(s);
                }
                
                spritesToAdd.clear();
		// Redraw the game.
		internalGame.invalidate();
		internalGame.repaint();
	}
	
	// Event handler that fires when a key is pressed.
	public void keyPressed(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		
		// Indicate the specified key was pressed only if it's code is less than 256.
		if (code < 256) {
			keysPressed[code] = true;
		}
	}

	// Event handler that fires when a key is released.
	public void keyReleased(KeyEvent arg0) {
		int code = arg0.getKeyCode();

		// Indicate the specified key was released only if it's code is less than 256.
		if (code < 256) {
			keysPressed[code] = false;
		}
	}

	public void keyTyped(KeyEvent arg0) {
		// Required for KeyListener interface but we don't need it.		
	}	
}
