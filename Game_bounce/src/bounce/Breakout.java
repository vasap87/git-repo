package bounce;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

//import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/*default*/
	private static final long serialVersionUID = 1L;
/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 500;
	public static final int APPLICATION_HEIGHT = 800;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 15;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
/** Start velocity in Y - coordinate*/
	private static final double VY = 3.0;
	
/** Delay*/
	private static final int DELAY = 20;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		
		/*Set up window size*/
		setSize(APPLICATION_WIDTH+BRICK_SEP, APPLICATION_HEIGHT);
		
		/*Setup program*/
		setup();
		
		/*Add mouse listeners*/
		addMouseListeners();
		
		/*Add play method*/
		play();
	}
	
	
	/*this method setup the program in first time*/
	private void setup(){
		
		turn = NTURNS;
		bricks=NBRICK_ROWS*NBRICKS_PER_ROW;
		
		/*Add labels about score and lives*/
		lives = new GLabel("Lives: "+ turn);
		add(lives,10,HEIGHT-lives.getAscent());
		score = new GLabel("Score: "+ (NBRICK_ROWS*NBRICKS_PER_ROW-bricks));
		add(score,lives.getX()+lives.getWidth()+10,HEIGHT-lives.getAscent());
		
		/*Add NBRICK_ROWS with NBRICKS_PER_ROW of bricks*/
		int startY=BRICK_Y_OFFSET;
		for(int i=1;i<=NBRICK_ROWS;i++){
			int startX=BRICK_SEP;
			for (int j=1;j<=NBRICKS_PER_ROW;j++){
				GRect rect = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
				rect.setFilled(true);
				if(i==1||i==2){
					Color c = Color.RED;
					rect.setColor(c);
					rect.setFillColor(c);
				}else if (i==3||i==4){
					Color c = Color.ORANGE;
					rect.setColor(c);
					rect.setFillColor(c);
				}else if (i==5||i==6){
					Color c = Color.YELLOW;
					rect.setColor(c);
					rect.setFillColor(c);
				}else if (i==7||i==8){
					Color c = Color.GREEN;
					rect.setColor(c);
					rect.setFillColor(c);
				}else if (i==9||i==10){
					Color c = Color.CYAN;
					rect.setColor(c);
					rect.setFillColor(c);
				}
				add(rect,startX,startY);
				startX = startX+BRICK_WIDTH+BRICK_SEP;
			}
			startY=startY+BRICK_SEP+BRICK_HEIGHT;
		}
		
		/*Add paddle*/
		paddleY = APPLICATION_HEIGHT-PADDLE_Y_OFFSET-PADDLE_HEIGHT;
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle,(APPLICATION_WIDTH+BRICK_SEP)/2-PADDLE_WIDTH/2,paddleY);
		
		/*Add ball in start position*/
		addBall();
	}
	
	/*add ball method*/
	private void addBall(){
		ball = new GOval(BALL_RADIUS*2, BALL_RADIUS*2);
		ball.setFilled(true);
		add(ball,(APPLICATION_WIDTH+BRICK_SEP)/2-BALL_RADIUS,APPLICATION_HEIGHT/2-BALL_RADIUS);
	}
	
	/*Methods move paddle*/
	public void mouseMoved(MouseEvent e){
		paddle.move(e.getX()-(paddle.getX()+paddle.getWidth()/2), 0);
	
	}
	
	/*Add the play method*/
	private void play(){
		startGame();
		while(bricks>0&&turn>0){
			if(bricks<80&&bricks>60)delay=15;
			if(bricks<60&&bricks>40)delay=10;
			if(bricks<40&&bricks>0)delay=5;
			while (ball.getY()<HEIGHT+1){
				move();
				checkBrick();
				checkWall();
				pause(delay);
			}
			
		}
		add(new GLabel("You WIN!!"),WIDTH/2,HEIGHT/2);
			
	}
	
	private void startGame(){
		delay=DELAY;
		vy=VY;
		vx=rand.nextDouble(1.0, 3.0);
		if (rand.nextBoolean(0.5)) vx=-vx;
	}
	
	/*move ball*/
	private void move(){
		ball.move(vx, vy);
	}
	
	/*check and change directory on the wall*/
	private void checkWall(){
		if(ball.getX()<0)vx=-vx;
		else if(ball.getX()+ball.getWidth()>WIDTH)vx=-vx;
		else if(ball.getY()<0)vy=-vy;
		else if(ball.getY()+ball.getHeight()>HEIGHT){
			turn--;
			remove(ball);
			updateLabels(NBRICK_ROWS*NBRICKS_PER_ROW-bricks,turn);
			if(turn>0){
				addBall();
				play();
			}
			else{
				add(new GRect(0, 0, APPLICATION_WIDTH+BRICK_SEP, APPLICATION_HEIGHT));
				add(new GLabel("G@ME OVER"),WIDTH/2,HEIGHT/2);
			}
				
		}
			
	}
	
	/*Check the break*/
	private void checkBrick(){
		gobj = getElementAt(ball.getX()+BALL_RADIUS,  ball.getY()-1); //up side
		if(gobj!=null){
			if (gobj==paddle)vy=-vy;
			else if(gobj!=score&&gobj!=lives){
				vy = removeBreak(gobj, vy);
			}
		}
		gobj = getElementAt(ball.getX()+BALL_RADIUS, ball.getY()+BALL_RADIUS*2+1); //down side
		if(gobj!=null){
			if (gobj==paddle)vy=-vy;
			else if(gobj!=score&&gobj!=lives){
				vy = removeBreak(gobj, vy);
			}
		}
		gobj = getElementAt(ball.getX()-1, ball.getY()+BALL_RADIUS); //left side
		if(gobj!=null){
			if (gobj==paddle)vx=-vx;
			else if(gobj!=score&&gobj!=lives){
				vx = removeBreak(gobj, vx);
			}
		}
		gobj = getElementAt(ball.getX()+BALL_RADIUS*2+1, ball.getY()+BALL_RADIUS); //right side
		if(gobj!=null){
			if (gobj==paddle)vx=-vx;
			else if(gobj!=score&&gobj!=lives){
				vx = removeBreak(gobj, vx);
			}
		}
	}
	/**Method add or update lives and score in left bottom edge
	 * @param bricks - how many bricks done
	 * @param turn - how many turn player have
	 * */
	private void updateLabels(int bricks, int turn){
		if (turn>0){
			lives.setLabel("Lives: "+ turn);
			score.setLabel("Score: "+ bricks);
		}	
	}
	
	private double removeBreak(GObject obj, double vel){
		addBonus(obj.getX()+BRICK_WIDTH/2, obj.getY()+BRICK_HEIGHT); //add bonus 
		remove(obj);
		updateLabels(NBRICK_ROWS*NBRICKS_PER_ROW-bricks,turn);
		bricks--;
		return vel=-vel;
	}
	
	/**
	 * This method add something bonus to user, from the break
	 * @param x - X-coordinates of break
	 * @param y - Y-coordinates of break*/
	private void addBonus(double x, double y){
	 //write here method to add bonus
		
	}
	
	
	private GObject gobj;
	private GRect paddle;
	private GOval ball;
	private GLabel lives;
	private GLabel score;
	private int turn;
	private int paddleY;
	private int delay;
	private int bricks;
	private double vx, vy; //velocity of ball
	private RandomGenerator rand = new RandomGenerator();
	//private AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
	//private AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");

}
