import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * A framework for creating board games of a puzzle type. The framework consists of the classes SimpleBoardGame,
 * DisplaySimpleBoardGraphics and GameSound.
 */

public abstract class SimpleBoardGame{
	
	// @invariance listeners.length == 3 ^ listeners == null
	// @invariance iconClickedPos.length == 2 ^ iconClickedPos == null
	
	private DisplaySimpleBoardGraphics board;
	private EventListener[] listeners;
	private int[] iconClickedPos;
	private GameSound sound;
	
	public SimpleBoardGame(){
		
		iconClickedPos = new int[2];
		listeners = new EventListener[3];
		listeners[2] = mouse;
		listeners[1] = keyBoard;
		listeners[0] = button;
		board = new DisplaySimpleBoardGraphics(startBoard(),listeners);
		
		assert board != null;
		
	}
	
	EventListener mouse = new MouseListener(){
		public void mouseClicked(MouseEvent e){
			JPanel panel = (JPanel)e.getComponent();
			JLabel label = (JLabel)panel.getComponentAt(e.getY(), e.getX());
		 	iconClickedPos = board.labelPos(label);
		 	board.frameRequest();
		 	
		 	madeMove(iconClicked());
		}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){}
		public void mouseReleased(MouseEvent e){}	
		public void mousePressed(MouseEvent e){
		}
	 };
	 
	public int[] getIconClickedPos(){
		
		assert iconClickedPos.length == 2;
		
		return iconClickedPos;			
	}
	 
	EventListener button = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				board.frameRequest();
				restartPressed();
			}
	};
	
	 EventListener keyBoard = new KeyListener(){
		public void keyPressed(KeyEvent e) {
			int id = e.getExtendedKeyCode();
			if(id == 37) {//left
				madeMove(leftKeyPressed());
			}
			else if(id==38){//up
				madeMove(upKeyPressed());
			}
			else if(id==39){//right
				madeMove(rightKeyPressed());
			}
			else if(id==40){//down
				madeMove(downKeyPressed());
			}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	};	
	
	public void restartPressed() {
		board.updateGraphics(startBoard());
	}
	
	
	public void madeMove(ImageIcon[][] newBoard){
		if(newBoard != null){	
			board.updateGraphics(newBoard);
			int winOrLoss = evaluateBoard();
			if(winOrLoss == 1) won();
			else if(winOrLoss == -1) lost();
		}
	}
	
	public void won() {
		JFrame winFrame = new JFrame();
		JLabel winLabel = new JLabel("YOU WIN!", JLabel.CENTER);
		winLabel.setFont(new Font("Serif", Font.BOLD, 30));
		winFrame.add(winLabel);
		winFrame.setSize(300, 150);
		winFrame.setLocationRelativeTo(null);
	    winFrame.setVisible(true);
	    sound = new GameSound(winningSound());
	}
	
	public void lost() {
		JFrame lostFrame = new JFrame();
		JLabel lostLabel = new JLabel("GAME OVER!", JLabel.CENTER);
		lostLabel.setForeground(Color.red);
		lostLabel.setFont(new Font("Serif", Font.BOLD, 30));
		lostFrame.add(lostLabel);
		lostFrame.setSize(300, 150);
		lostFrame.setLocationRelativeTo(null);
	    lostFrame.setVisible(true);
	    sound = new GameSound(lostSound());
	}
	
	/*
	 * Representation of graphical start board of the game. Mandatory method.
	 */
	public abstract ImageIcon[][] startBoard();
	
	/* Input methods that is called when corresponding key or mouse is used. Returns the modified board state made after a move.
	 * When iconClicked is called, getIconClickedPos is guaranteed to return an integer array of size two containing the position
	 * of the icon that was clicked in the board. For example if the icon in the top left corner was pressed the return array would
	 * be {0, 0}.
	 * At least one of the methods must be implemented. Return null if not used.
	 */
	public abstract ImageIcon[][] leftKeyPressed();
	public abstract ImageIcon[][] rightKeyPressed();
	public abstract ImageIcon[][] upKeyPressed();
	public abstract ImageIcon[][] downKeyPressed();
	public abstract ImageIcon[][] iconClicked();
	
	/*
	 * evaluateBoard is meant to check if any win or lose conditions are satisfied by the current board state.
	 * If it is a win, return 1. If it is a loss, return -1. If none are satisfied return 0.
	 * Mandatory method.
	 */
	public abstract int evaluateBoard();
	
	/*
	 * Optional methods for providing sound for winning and losing conditions. Uses ".wav" format files.
	 * Return null if not used.
	 */
	public abstract String winningSound();
	public abstract String lostSound();
	
}
