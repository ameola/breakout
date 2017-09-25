package Engine;
import java.awt.Graphics;
import javax.swing.JPanel;

// Internal game class. Should not be used directly.
public class InternalPanel extends JPanel {
	/**
	 * Required for extending from JPanel
	 */
	private static final long serialVersionUID = 4836735869326587280L;

	// Reference back to the game class to access the sprite.
	private Game game;
	
	// Constructor that takes an instance of the game.
	public InternalPanel(Game gameReference){
		game = gameReference;
	}
	
	// Redraw the game.
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Erase any existing content
		g.setColor(game.backgroundColor);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		// Loop through each sprite calling pain on itself.
		for (Sprite s : game.getSprites()){
			if (s.isVisible){
				s.Paint(g);
			}
		}
	}
}
