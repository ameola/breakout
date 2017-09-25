import java.awt.Color;
import java.awt.Graphics;

import Engine.Sprite;

// Represents the level we're currently on.
public class Level extends Sprite {

	// The level we're currently on. Defaults to 1.
	public int level = 1;
	
	// Setup the position of the level text drawn in the window.
	public Level(){
		xPos = 20;
		yPos = 20;
	}
		
	// Draw the level text at the position required.
	public void Paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Level: " + level, (int)xPos, (int)yPos);
	}

	public void Update(boolean[] keysPressed) {
		// No implementation.
	}
}
