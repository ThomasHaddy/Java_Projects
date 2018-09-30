import java.awt.*;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;

/**
 * Lexer that reads in from console and outputs into an array
 * 
 * @author Thomas Haddy 4/23/2018
 */
public class Lexer {

	/**
	 * Display for the calculator
	 */
	private JLabel out;

	/**
	 * Display for the calculator
	 */
	private JLabel in;


	/**
	 * User input
	 */
	private String input;

	/**
	 * Lexer output
	 */
	private String[] output;

	/**
	 * Width of the window
	 */
	private int width;

	/**
	 * Height of the window
	 */
	private int height;

	/**
	 * This constructor creates a window with a width and height
	 * @param width
	 * 		Given width
	 * @param height
	 * 		Given height
	 */
	public Lexer(int width, int height) {

		this.width = width;
		this.height = height;
	}

	public JLabel getDisplay() {
		return out;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getInput() {
		return input;
	}

	public String[] getOutput() {
		return output;
	}

	public void setDisplay(JLabel dis) {
		out = dis;
	}

	public void setWidth(int w) {
		width = w;
	}

	public void setHeight(int h) {
		height = h;
	}

	public void setInput(String in) {
		input = in;
	}

	public void setOutput(String[] out) {
		output = out;
	}

	/**
	 * Creates the window
	 */
	public void launch() {

		JFrame frame = new JFrame();
		JPanel displayPanel = new JPanel();
		JPanel grid = new JPanel();

		//Set up the out panel
		out = new JLabel();
		out.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
		displayPanel.add(out);
		displayPanel.setPreferredSize(new Dimension(width, 75));
		displayPanel.setBackground(Color.WHITE);
		displayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));

		//Set up the in panel
		in = new JLabel();
		in.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
		displayPanel.add(in);
		displayPanel.setPreferredSize(new Dimension(width, 75));
		displayPanel.setBackground(Color.WHITE);
		displayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));

		//Set up layout of grid
		grid.setLayout(new GridLayout(2, 1, 1, 1));
		grid.setBackground(Color.WHITE);
		grid.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		grid.setPreferredSize(new Dimension(width, height));
		grid.add(in);
		grid.add(out);

		//Center the frame
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();      
		int x=(int)((dimension.getWidth() - width)/2);
		int y=(int)((dimension.getHeight() - height)/2);

		//Put everything into the frame
		frame.getContentPane().add(grid, BorderLayout.CENTER);
		frame.pack();
		frame.setLocation(x, y);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setVisible(true);
	}

	/**
	 * Reads in from the console
	 */
	public void readUserInput() {

		Scanner in = new Scanner(System.in);
		System.out.println("Please enter input: ");
		input = in.nextLine();
		in.close();
		launch();
		output();
	}

	/**
	 * Outputs to a simple GUI
	 */
	public void output() {
		in.setText("Input: " + input);
		output = input.split(" ");
		out.setText("Output:" + Arrays.toString(output));
	}

	/**
	 * Main method to run the lexer
	 */
	public static void main(String[] args) {

		Lexer lexer = new Lexer(500, 500);
		lexer.readUserInput();
	}
}
