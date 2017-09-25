import java.awt.Color;
import java.awt.Graphics;

import Engine.Sprite;

public class Ball extends Sprite {
	// default position of the ball.
	public final float defaultXPos = (Breakout.windowWidth - width) / 2;
	public final float defaultYPos = (Breakout.windowHeight - height) / 2;
	
	// Speed of the ball in the x direction. Default is 0.
	public float dx = 0;
	
	// Speed of the ball in the y direction. Default is 3.
	public float dy = 3;
	
	public Ball()
	{
		width = 30;
		height = 30;
		resetPosition();
	}

	// Reset the ball's position.
	public void resetPosition()
	{
		xPos = defaultXPos;
		yPos = defaultYPos;		
		dx = 0;
	}
	
	public void Paint(Graphics g) {
		// Draw a red circle.
		g.setColor(Color.RED);
		g.fillOval((int)xPos, (int)yPos, (int)width, (int)height);
	}

	public void Update(boolean[] keysPressed) {
		// Move the ball at the current speed and direction it's traveling in.
		xPos = xPos + dx;
		yPos = yPos + dy;
		
		// Bounce off the walls if we hit one.
		if (xPos < 0 || xPos + this.width > Breakout.windowWidth)
		{
			xPos = xPos - dx;
			dx = dx * -1;
		}
		
		// Bounce off the roof if we hit it.
		if (yPos < 0 || yPos + this.height > Breakout.windowHeight)
		{
			yPos = yPos - dy;
			dy = dy * -1;
		}
	}
}
