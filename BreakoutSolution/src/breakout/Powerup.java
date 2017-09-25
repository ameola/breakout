
import java.awt.Color;
import java.awt.Graphics;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aaron Meola
 */

import java.awt.Color;
import java.awt.Graphics;

import Engine.Sprite;
import Engine.Game;

public class Powerup extends Sprite {
    
    
    private Game game;
    private boolean hitPaddle = false;
    private int frameCount = 0;
    private Paddle affectedPaddle;
    
    private static final int FramesToAffect=500;
    
    public Powerup(Game g, float x, float y)
    {
        xPos = x;
        yPos = y;
        width = 20;
        height = 10;
        
        this.game = g;
    }
    
    public void Paint(Graphics g) {
	// Draw a red circle.
	g.setColor(Color.PINK);
	g.fillOval((int)xPos, (int)yPos, (int)width, (int)height);
    }

    public void Update(boolean[] keysPressed) {
        if(!hitPaddle)
        {
            yPos = yPos + 2;
                
            if (yPos > Breakout.windowHeight)
            {
                this.isVisible = false;
                this.canCollide = false;
                game.removeSprite(this);
            }
            
            Sprite hit = game.checkCollision(this);
            if (hit != null && hit.getClass() == Paddle.class)
            {
                this.isVisible = false;
                this.canCollide = false;
                Paddle paddle = (Paddle)hit;
                paddle.width += 20;
                hitPaddle = true;
                affectedPaddle = paddle;
            }
        }
        else
        {
            frameCount++;
            
            if (frameCount == FramesToAffect)
            {
                affectedPaddle.width -= 20;
                game.removeSprite(this);
            }
        }
    }
}
