package brick_break;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	private static final long serialVersionUID = 1L;
	private boolean play = false;
	private int score = 0;
	private int totalBricks = 45;
	private Timer timer;
	private int delay = 8;
	
	private int playerX =310;
	
	private int ballposX = 876;
	private int ballposY = 592;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	private Bricks map;
	
	public Gameplay() {
		map = new Bricks(5, 9);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		//background
		g.setColor(Color.blue);
		g.fillRect(1, 1, 1292, 742);
		
		// map
		map.draw((Graphics2D)g);
		
		//border
		g.setColor(Color.green);
		g.fillRect(0, 0, 1, 750);
		g.fillRect(0, 0, 1300, 3);
		g.fillRect(1300, 0, 1, 750);
		
		//scores
		g.setColor(Color.BLACK);
		g.setFont(new Font("serif", Font.BOLD, 40));
		g.drawString(""+score, 1160, 40);
		
		// paddle
		g.setColor(Color.BLACK);
		g.fillRect(playerX, 600, 100, 8);
		
		//ball
		g.setColor(Color.RED);
		g.fillOval(ballposX, ballposY, 25, 25);
		
		if(totalBricks <= 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.black);
			g.setFont(new Font("serif", Font.BOLD, 40));
			g.drawString("YOU WON!! YOUR SCORE: "+score, 300, 350);
			
			g.setFont(new Font("serif", Font.BOLD, 40));
			g.drawString("Press Space to Continue", 420, 400);
		}
		if(ballposY > 640) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.black);
			g.setFont(new Font("serif", Font.BOLD, 40));
			g.drawString("GAME OVER. YOUR SCORE: "+score, 300, 350);
			
			g.setFont(new Font("serif", Font.BOLD, 40));
			g.drawString("Press Space to Retry", 420, 400);
		}
			g.dispose();
	}
		
		
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			if(new Rectangle(ballposX, ballposY, 25, 25).intersects(new Rectangle(playerX, 600, 100, 8))) {
				ballYdir = -ballYdir;
			}
			
			A: for(int i = 0; i < map.map.length; i++) {
				for(int j = 0; j < map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 30, 30);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score += 5;
							
							if(ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							}else {
								ballYdir = -ballYdir;
							}
							break A;
						}
					}
				}
			}
			
			ballposX += ballXdir * 1.5;
			ballposY += ballYdir * 1.5;
			if(ballposX < 0) {
				ballXdir = -ballXdir;
			}
			if(ballposY < 0) {
				ballYdir = -ballYdir;
			}
			if(ballposX > 1214) {
				ballXdir = -ballXdir; 
			}
			if(ballposY > 700) {
				ballYdir = -ballYdir;
			}
			
		}
		
		
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 1140) {
				playerX = 1140;
			}else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX <= 0) {
				playerX = 0;
			}else {
				moveLeft();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(!play) {
				play = true;
				ballposX = 643;
				ballposY = 638;
				ballXdir = 1;
				ballYdir = -2;
				playerX = 527;
				score = 0;
				totalBricks = 45;
				map = new Bricks(5, 9);
				
				repaint();
				
			}
		}

		
	}
	public void moveRight() {
		play = true;
		playerX+=20;
	}
	public void moveLeft() {
		play = true;
		playerX-=20;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}
	
}
