package Engine;
import java.awt.Graphics;

// Base class for a sprite (any object drawn to screen).
public abstract class Sprite {

	// The x position of the sprite. Default is 0.
	public float xPos = 0;

	// The y position of the sprite. Default is 0.
	public float yPos = 0;
	
	// The width of the sprite. Default is 0.
	public float width = 0;
	
	// The height of the sprite. Default is 0.
	public float height = 0;
	
	// Can objects in the game collide with this sprite?
	public boolean canCollide = true;
	
	// Is this sprite visible? Should we draw it?
	public boolean isVisible = true;

	// Paint method to implement in each sprite. Called when the window is updated.
	public abstract void Paint(Graphics g);
	
	// Update method to implement in each sprite. Called before each frame is painted so the
	// sprite can be updated (moved, etc).
	public abstract void Update(boolean[] keysPressed);
}
