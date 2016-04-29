import java.awt.event.MouseEvent;

import acm.graphics.GLine;
import acm.program.GraphicsProgram;

public class DrawLines extends GraphicsProgram {

	/*def value*/
	private static final long serialVersionUID = 1L;
	 
	public void run(){
		setSize(400, 500);
		addMouseListeners();
	}
	
	public void mousePressed(MouseEvent e){
		line = new GLine(e.getX(), e.getY(), e.getX(), e.getY());
		add(line);
	}
	
	public void mouseDragged(MouseEvent e){
		line.setEndPoint(e.getX(), e.getY());
	}
	
	private GLine line;

}
