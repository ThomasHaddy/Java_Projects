package ui;
import java.awt.Color;
import java.awt.Dimension;
//import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import hw3.FlowGame;
import hw3.Util;

/**
 * Main class for a GUI for a Flow Free game sets up a 
 * GamePanel instance in a frame.
 * @author smkautz
 */
public class GameMain
{
	/**
	 * Cell size in pixels.
	 */
	public static final int SIZE = 40; 

	/**
	 * Dot size in pixels, must be less than or equal to SIZE.
	 */
	public static final int DOT_SIZE = 20; 

	/**
	 * Line width in pixels.
	 */
	public static final int LINE_SIZE = 6;

	/**
	 * Font size for displaying score.
	 */
	public static final int SCORE_FONT = 24; 

	/**
	 * Background color.
	 */
	public static final Color BACKGROUND_COLOR = Color.BLACK;

	/**
	 * Color for cell outlines.
	 */
	public static final Color GRID_COLOR = Color.DARK_GRAY;

	/**
	 * Helper method for instantiating the components.  This
	 * method should be executed in the context of the Swing
	 * event thread only.
	 * @throws FileNotFoundException 
	 */
	private static void create() throws FileNotFoundException
	{ 
		// EDIT HERE TO CHANGE THE GAME BEING CREATED
		FlowGame game;
		//Text file case -----------------------------------------------------------------
//		ArrayList<FlowGame> flowGames = Util.readFile("flowgames12x12.txt");
//		if (flowGames == null) {
//			System.out.println("Text file contains an error.");
//			return;
//		}
//
//		game = flowGames.get(0);
//		game = flowGames.get(1);
//		game = flowGames.get(2);

		// create a simple game ----------------------------------------------------------
//		Flow[] flows = new Flow[3];
//	    flows[0] = new Flow(new Cell(0, 0, 'G'), new Cell(1, 2, 'G'));
//	    flows[1] = new Flow(new Cell(0, 1, 'R'), new Cell(0, 3, 'R'));
//	    flows[2] = new Flow(new Cell(2, 0, 'B'), new Cell(1, 3, 'B'));
//	    game = new FlowGame(flows, 4, 3);
//	    // check the initial game state
//	    System.out.println(game.getWidth());
//	    System.out.println(game.getHeight());
//	    System.out.println(game.getCurrent());
//	    Flow[] temp = game.getAllFlows();
//	    for (Flow f : temp)
//	    {
//		    System.out.println(f);
//	    }
		
		// a simple game -----------------------------------------------------------------
//	    Flow[] flows = new Flow[3];
//	    flows[0] = new Flow(new Cell(0, 0, 'G'), new Cell(1, 2, 'G'));
//	    flows[1] = new Flow(new Cell(0, 1, 'R'), new Cell(0, 3, 'R'));
//	    flows[2] = new Flow(new Cell(2, 0, 'B'), new Cell(1, 3, 'B'));
//	    game = new FlowGame(flows, 4, 3);

	    //Another simple game -----------------------------------------------------------
//	    Flow[] flows = new Flow[4];
//	    flows[0] = new Flow(new Cell(1, 1, 'O'), new Cell(2, 3, 'O'));
//	    flows[1] = new Flow(new Cell(1, 2, 'R'), new Cell(2, 4, 'R'));
//	    flows[2] = new Flow(new Cell(1, 4, 'G'), new Cell(2, 1, 'G'));
//	    flows[3] = new Flow(new Cell(2, 0, 'B'), new Cell(4, 0, 'B'));
//	    game = new FlowGame(flows, 6, 5);

		// String Descriptor Case --------------------------------------------------------
//		String[] testgrid1 = {
//			"GR-R",
//			"--GB",
//			"B---"
//		};
//		
//		String[] testgrid2 = {
//			"------",
//			"-OR-G-",
//			"BG-OR-",
//			"------",
//			"B-----",
//		};
		
//		String[] testgrid3 = {
//		"R---------Y",
//		"OB--------G",
//		"----------M",
//		"S--------G-",
//		"--VP-O-----",
//		"-P---------",
//		"----CS-B---",
//		"--M----FR--",
//		"----Y------",
//		"---------F-",
//		"VC---------",
//		};

//		game = new FlowGame(testgrid1);
//		game = new FlowGame(testgrid2);
//		game = new FlowGame(testgrid3);
		//Fun version ---------------------------------------------------------------------
		Random rand = new Random();
		ArrayList<FlowGame> flowGames = Util.readFile("flowgames12x12.txt");
		
		if (flowGames == null) {
			System.out.println("Fix the error.");
			return;
		}

		game = flowGames.get(rand.nextInt(flowGames.size()));	
		// create the three UI panels
		ScorePanel scorePanel = new ScorePanel();
		GamePanel panel = new GamePanel(game, scorePanel);
		ChooseButtonPanel choosePanel = new ChooseButtonPanel(panel, scorePanel);

		// arrange the panels vertically
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(choosePanel);
		mainPanel.add(scorePanel);
		mainPanel.add(panel);

		// put main panel in a window
		JFrame frame = new JFrame("Com S 227 Flow Game");
		frame.getContentPane().add(mainPanel);

		// give panels a nonzero size
		Dimension d = new Dimension(game.getWidth() * GameMain.SIZE, game.getHeight() * GameMain.SIZE);   
		panel.setPreferredSize(d);
		d = new Dimension(game.getWidth() * GameMain.SIZE, 3 * GameMain.SIZE);   
		scorePanel.setPreferredSize(d);
		d = new Dimension(game.getWidth() * GameMain.SIZE, GameMain.SIZE);   
		choosePanel.setPreferredSize(d);
		frame.pack();

		// we want to shut down the application if the 
		// "close" button is pressed on the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// rock and roll...
		frame.setVisible(true);
	}

	/**
	 * Entry point.  Main thread passed control immediately
	 * to the Swing event thread.
	 * @param args not used
	 */
	public static void main(String[] args)
	{
		Runnable r = new Runnable()
		{
			public void run()
			{
				try {
					create(); 
				}
				catch(FileNotFoundException e) {
					System.out.println("File was not found.");
				}

			}
		};
		SwingUtilities.invokeLater(r);
	}
}
