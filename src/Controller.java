import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Controller{
	private View view;
	private Model model;
	
	public Controller() {
		view = new View();
		model = new Model(view.frameStartSize, view.picSize);
	}
	
	public void run() {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Timer t = new Timer(view.drawDelay, view.drawAction);
				t.start();
			}
		});
	}
	
	
}
