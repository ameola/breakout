
import javafx.scene.paint.Color;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aaron Meola
 */
public class PowerupBlock extends Block{
    
    	// Constructs a block with an X Position, Y Position, Width and Height.
	public PowerupBlock(int xPosition, int yPosition, int blockWidth, int blockHeight)
	{
            super(xPosition, yPosition, blockWidth, blockHeight);
            super.blockColor = java.awt.Color.yellow;
	}
}
