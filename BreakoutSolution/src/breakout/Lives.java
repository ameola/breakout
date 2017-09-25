import java.awt.Color;
import java.awt.Graphics;

import Engine.Sprite;

// Represents the number of lives the player has.
public class Lives extends Sprite {

	// The number of lives the player has. Defaults to 3.
	public int lives = 3;
	
	// Setup the position to draw the lives status at.
	public Lives(){
		xPos = Breakout.windowWidth / 2 - 25;
		yPos = 20;
	}
	
	// Draw the number of lives available at the appropriate screen position.
	public void Paint(Graphics g) {
		if (this.isVisible)
		{
			g.setColor(Color.WHITE);
			g.drawString("Lives: " + lives, (int)xPos, (int)yPos);
		}
	}

	public void Update(boolean[] keysPressed) {
	}
}
