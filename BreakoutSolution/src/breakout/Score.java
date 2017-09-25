import java.awt.Color;
import java.awt.Graphics;

import Engine.Sprite;

// Represents the player's score.
public class Score extends Sprite {

	// The player's score. Defaults to 0.
	private int score = 0;
	
	// Sets up the x and y position of the score.
	public Score(){
		xPos = Breakout.windowWidth-100;
		yPos = 20;
	}
	
	// Increment the score by the specified number.
	public void incrementScore(int number)
	{
		score+=number;
	}
	
	// Draw the player's score at the specified position on screen.
	public void Paint(Graphics g) {
		if (this.isVisible)
		{
			g.setColor(Color.WHITE);
			g.drawString("Score: " + score, (int)xPos, (int)yPos);
		}
	}

	public void Update(boolean[] keysPressed) {
	}

}
