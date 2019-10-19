import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.EventListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * A class used to display a pop-up window with a restart button attached. An object of the class
 * is created by an input of a two dimensional X*X ImageIcon array(ImageIcon[X][X]) and an array
 * of EventListeners. 
 */

public class DisplaySimpleBoardGraphics{

	// @invariance layout.getRows == icons.length ^ layout == null
	// @invariance layout.getColumns == icons.length ^ layout == null
	// @invariance holder.length == icons.length ^ holder == null
	// @invariance holder[n].length == icons.length ^ holder == null
	// @invariance holder[n].length == holder[n][n].length
	
	JPanel  panel;
	GridLayout layout;
	JLabel holder[][];
	JButton restart;															
	JFrame  frame;
	
	public DisplaySimpleBoardGraphics(ImageIcon[][] icons, EventListener[] listeners) throws IllegalArgumentException {
		
		assert icons.length == icons[0].length;
		assert frame == null;
		
		for (int j = 0; j < icons.length; j++) {
			if(icons.length != icons[j].length){
				throw new IllegalArgumentException ("Invalid size of board! Board must be of size n*n.");
			}
		}
		
		holder = new JLabel[icons.length][icons.length];
		layout =  new GridLayout(icons.length,icons.length);
		frame = new JFrame();
		restart = new JButton("RESTART");
		frame.add(restart, BorderLayout.SOUTH);
		
		setGraphics(icons);
		addListeners(listeners);
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		
		frame.setFocusable(true);
		frame.requestFocusInWindow();
		
		frame.setResizable(false);
	    frame.pack();
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); 
	    
	    assert holder.length == icons.length;
	    assert holder[0].length == icons[0].length;
	    assert frame != null;
	    
	}
	
	public void setGraphics(ImageIcon[][] icons){
		
		panel = new JPanel();
		panel.setLayout(layout);
		for (int i = 0; i < icons.length; i++) {
			for (int j = 0; j < icons.length; j++) {
				holder[i][j] = new JLabel (icons[i][j]);
				panel.add(holder[i][j]);
			}
		}
		frame.add(panel);
		
		assert panel != null;
		
	}
	
	public void updateGraphics(ImageIcon[][] newBoard){
		
		assert newBoard.length == holder.length;
		assert newBoard[0].length == holder[0].length;
		
		for (int i = 0; i < holder.length; i++) {
			for (int j = 0; j < holder.length; j++) {
				panel.remove(holder[i][j]);
				holder[i][j] = new JLabel (newBoard[i][j]);
				panel.add(holder[i][j]);
			}
		}
		panel.revalidate();
		panel.repaint();
		frame.repaint();
		
		assert holder != null;
		
	}
	
	public void addListeners(EventListener[] listeners){
		
		assert listeners.length == 3;
		assert panel.getMouseListeners().length == 0;
		
		for(EventListener l : listeners){
			if(l instanceof MouseListener) panel.addMouseListener((MouseListener) l);
			if(l instanceof ActionListener) restart.addActionListener((ActionListener) l);
			if(l instanceof KeyListener) frame.addKeyListener((KeyListener) l);
		}
		
		assert panel.getMouseListeners()[0] == (MouseListener)listeners[2];
		
	}
	
	public int[] labelPos(JLabel label){
		JLabel n = label;
		int[] pos = new int[2];
		for (int i = 0; i < holder.length; i++) {
			for (int j = 0; j < holder.length; j++) {
				if(n==holder[i][j]){
					pos[0] = i;
					pos[1] = j;
					return pos;
				}
			}
		}
		return pos;
	}	
	
	public void frameRequest() {		
		frame.requestFocus();
	}	
	
}
