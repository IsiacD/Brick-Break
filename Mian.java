package brick_break;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Mian {
	
	public static void main(String[] args) {
		
		JFrame obj = new JFrame();
		obj.setBounds(15, 15, 1250, 700);
		Gameplay gamePlay = new Gameplay();
		obj.setTitle("BreakOut Balls");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gamePlay);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		obj.setLocation(dim.width/2-obj.getSize().width/2, dim.height/2-obj.getSize().height/2);
	}
	

}
