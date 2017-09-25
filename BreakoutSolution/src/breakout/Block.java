import java.awt.Color;
import java.awt.Graphics;

import Engine.Sprite;


public class Block extends Sprite {

        public Color blockColor;
        
	// Constructs a block with an X Position, Y Position, Width and Height.
	public Block(int xPosition, int yPosition, int blockWidth, int blockHeight)
	{
            blockColor = Color.GREEN;
            
		xPos = xPosition;
		yPos = yPosition;
		width = blockWidth;
		height = blockHeight;
	}
	
	public void Paint(Graphics g) {
		// Draw a rectangle at the provided position.
		if (this.isVisible)
		{
			g.setColor(blockColor);
			g.fillRect((int)xPos, (int)yPos, (int)width, (int)height);
		}
	}

	public void Update(boolean[] keysPressed) {
		// No implementation needed. Blocks don't do anything.
	}
}
