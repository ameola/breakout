import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Engine.Sprite;

// Represents the players paddle on the screen.
public class Paddle extends Sprite {
	// The default (starting) x position on the screen of the paddle.
	public final float defaultXPos = (Breakout.windowWidth - this.width) / 2;

	// The default (starting) y position on the screen of the paddle.
	public final float defaultYPos = Breakout.windowHeight - 30 - this.height;

	// The paddle speed.
	public final float paddleSpeed = 6;
	
	//Set the paddle's initial width, height and position.
	public Paddle()
	{
		this.height = 20;
		this.width = 100;

		resetPosition();
	}
	
	// Resets the paddle's position back to default.
	public void resetPosition()
	{
		yPos = defaultYPos;
		xPos = defaultXPos;
	}
	
	// Draw the paddle at the required location.
	public void Paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int)xPos, (int)yPos, (int)width, (int)height);
	}

	public void Update(boolean[] keysPressed) {
		// If the player is holding the left or right keys down, move the paddle that direction.
		if (keysPressed[KeyEvent.VK_LEFT]){
			xPos-=paddleSpeed;
		}
		if (keysPressed[KeyEvent.VK_RIGHT]){
			xPos+=paddleSpeed;
		}
		
		// If the paddle is off the screen, move it back onto the screen.
		if (this.xPos < 0){
			xPos+=paddleSpeed;
		}
		
		if (this.xPos + this.width > Breakout.windowWidth){
			xPos-=paddleSpeed;
		}
	}	
}
