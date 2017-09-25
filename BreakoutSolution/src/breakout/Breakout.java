import java.util.ArrayList;

import Engine.Game;
import Engine.GameController;
import Engine.Sprite;

// The entry point for the breakout game. Implements game controller so we can get
// notified after each frame is drawn.
public class Breakout implements GameController {

    // The width of the window.
    public static int windowWidth = 512;
	
    // The height of the window.
    public static int windowHeight = 512;
	
    // The game object that controls the running of the game.
    public Game game;
    
    // The ball sprite.
    public Ball ball;
    
    // The score sprite (contains the player's score).
    public Score score;
    
    // The level sprite (contains the current level).
    public Level level;
    
    // The player's paddle sprite.
    public Paddle paddle;
    
    // The live sprite (contains the number of lives remaining).
    public Lives lives;
    
    // A list of blocks in the game.
    public ArrayList<Block> blocks = new ArrayList<Block>();
    
	// Starts the game.
	public void Start(){
		// Create the score sprite and add it to the games sprites.
		score = new Score();
		game.addSprite(score);
		
		// Create the level sprite and add it to the games sprites.
		level = new Level();
		game.addSprite(level);
		
		// Create the lives sprite and add it to the games sprites.
		lives = new Lives();
		game.addSprite(lives);
		
		// Create the paddle sprite and add it to the games sprites.
		paddle = new Paddle();
		game.addSprite(paddle);
		
		// Setup the block width and block height (width is based on the window width).
		int blockWidth = (windowWidth - 10) / 5;
		int blockHeight = 20;

		// Create 25 blocks. Calculate the correct position of the block and add it to the games sprites.
		for (int i = 0; i < 25; i++)
		{
                    Block block;
                    if (i == 13)
                    {
			block = new PowerupBlock(2 + (i%5 * 2) + (i%5 * blockWidth), 
					50 + (i / 5 * 2) + (i / 5 * blockHeight),
					blockWidth,
					blockHeight);
                        
                    }
                    else
                    {
			block = new Block(2 + (i%5 * 2) + (i%5 * blockWidth), 
					50 + (i / 5 * 2) + (i / 5 * blockHeight),
					blockWidth,
					blockHeight);
                    }
		blocks.add(block);
		game.addSprite(block);
		}

		
		// Create the ball sprite and add it to the games sprites.
		ball = new Ball();
		game.addSprite(ball);
		
		// Run the main game loop
		game.runGameLoop();
	}
	
	// Run each frame after the sprites have been updated.
	public void UpdateOcurred() {
		// Check that the ball hit the paddle. If it did not, we lose a life.
		if (ball.yPos + ball.height > paddle.yPos + paddle.height)
		{
			// The ball missed the paddle so decrement the lives and reset the ball and paddle positions.
			lives.lives--;
			paddle.resetPosition();
			ball.resetPosition();

			// Oh no! Out of lives. Pause the game loop to indicate the end of the game.
			if (lives.lives == 0)
			{
				// todo: Could display game over screen here.
				game.PauseGameLoop();
			}
			
			return;
		}
		
		// Check to see if the ball collided with anything.
		Sprite collidedSprite = game.checkCollision(ball);
		
		// If the collided sprite is null, it means there was no collision. If there
		// was a collision, we have some work to do.
		if (collidedSprite != null)
		{
			// Did the sprite hit the paddle?
			if (collidedSprite.getClass() == Paddle.class)
			{
				// Todo: Add better logic to allow varying angles of the ball.				
				// Deflect the ball 180 degrees or at an angle of +- 90 degrees to the paddle
				// depending on where the hit was.
				// First grab the mid point of the ball and the paddle.
				float midCircle = ball.xPos + ball.width / 2;
				float midPaddle = collidedSprite.xPos + collidedSprite.width / 2;
			
				// Second, set the x direction based on where the hit was using the mid points
				// as a reference.
				if (midCircle > midPaddle - 10 && midCircle < midPaddle + 10)
				{
					ball.dx = 0;
				}
				else if (midCircle < midPaddle)
				{
					ball.dx = -1 * Math.abs(ball.dy);
				}
				else
				{
					ball.dx = Math.abs(ball.dy);
				}
				
				// Finally, reverse the ball's y direction.
				ball.dy = -1 * ball.dy;
			}
			
			// Did we hit a powerup block?
			if (collidedSprite.getClass() == PowerupBlock.class)
			{
				// First change the ball's direction.
				ball.dy = ball.dy * -1;
				
				// Second, make the block disappear and increment the score.
				collidedSprite.isVisible = false;
				collidedSprite.canCollide = false;
				score.incrementScore(10 * level.level);

                                // Third, add the poewrup.
                		game.addSprite(new Powerup(game, collidedSprite.xPos, collidedSprite.yPos));
                                
				// Finally, check to see if we've cleared the screen of blocks.
				checkWin();
			}

                        // Did we hit a block?
			if (collidedSprite.getClass() == Block.class)
			{
				// First change the ball's direction.
				ball.dy = ball.dy * -1;
				
				// Second, make the block disappear and increment the score.
				collidedSprite.isVisible = false;
				collidedSprite.canCollide = false;
				score.incrementScore(10 * level.level);
				
				// Finally, check to see if we've cleared the screen of blocks.
				checkWin();
			}
		}
	}
	
	// Checks for a win condition (have we cleared the screen of blocks).
	public void checkWin(){
		// Loop through all the blocks. If one is visible, return because we have not won.
		for (Block b : blocks){
			if (b.isVisible == true){
				return;
			}
		}
		
		// We've dropped through so we've won. First we'll increment the level and
		// increase the speed of the ball.
		level.level += 1;
		ball.dy += 1;
		
		// Then we'll make all the blocks visible again.
		for (Block b : blocks){
			b.isVisible = true;
			b.canCollide = true;
		}
	}
}
