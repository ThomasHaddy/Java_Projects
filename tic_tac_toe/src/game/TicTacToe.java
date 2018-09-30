package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This is a simple Tic-Tac-Toe game where two players can play against each other locally
 * 
 * @author Thomas Haddy 2/4/2018
 */
public class TicTacToe {

	/**
	 * The buttons for the grid displaying Tic-Tac-Toe
	 */
	private JButton buttons[];

	/**
	 * Number of turns in game
	 */
	private int numTurns;

	/**
	 * Display of whose turn it is
	 */
	private JLabel goLabel;

	/**
	 * Width of the window
	 */
	private int width;

	/**
	 * Height of the window
	 */
	private int height;

	/**
	 * This constructor creates a Tic-Tac-Toe game with a width and height for the window dimensions
	 * @param width
	 * 		Given width
	 * @param height
	 * 		Given height
	 */
	public TicTacToe(int width, int height) {
		this.width = width;
		this.height = height;
	}

	/**
	 * Main method to run the application
	 */
	public static void main(String[] args) {
		TicTacToe game = new TicTacToe(700, 700);
		game.launchGame();
	}
	
	/**
	 * Gets the width
	 * @return
	 * 		Returns the width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the height
	 * @return
	 * 		Returns the height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Gets the number of turns
	 * @return
	 * 		Returns numTurns
	 */
	public int getNumTurns() {
		return numTurns;
	}
	
	/**
	 * Creates all of the necessary frames, panels, and buttons to create a Tic-Tac-Toe game
	 */
	public void launchGame() {

		JFrame frame = new JFrame();
		JPanel board = new JPanel();
		JPanel turnsPanel = new JPanel();
		buttons = new JButton[9];

		//Set up buttons
		for (int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			buttons[i].setText("");
			buttons[i].setBackground(Color.WHITE);
			buttons[i].addActionListener(new buttonListener());
			buttons[i].setForeground(Color.WHITE);
			board.add(buttons[i]);
		}

		//Set up layout of grid
		board.setLayout(new GridLayout(3, 3, 10, 10));
		board.setBackground(Color.DARK_GRAY);
		board.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
		board.setPreferredSize(new Dimension(width / 3, height-75));

		//Set up the turns dialog panel
		goLabel = new JLabel("Your turn:  X");
		goLabel.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
		turnsPanel.setBackground(Color.WHITE);
		turnsPanel.add(goLabel);

		//Center the frame
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();      
		int x=(int)((dimension.getWidth() - width)/2);
		int y=(int)((dimension.getHeight() - height)/2);
		
		
		//Put everything into the frame
		frame.getContentPane().add(board, BorderLayout.NORTH);
		frame.getContentPane().add(turnsPanel, null);
		frame.pack();
		frame.setLocation(x, y);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setVisible(true);
	}

	/**
	 * Checks to see if there are any wins on the board
	 * @return
	 * 		Returns true if there is a win, false otherwise
	 */
	public boolean didWin(JButton[] b) {

		//8 rows and 3 columns
		int[][] winCombos = new int[][] {
			//horizontal wins
			{0, 1, 2}, 
			{3, 4, 5}, 
			{6, 7, 8},
			//vertical wins
			{0, 3, 6}, 
			{1, 4, 7}, 
			{2, 5, 8},
			//diagonal wins
			{0, 4, 8}, 
			{2, 4, 6}  
		};

		for(int i = 0; i < 8; i++){ //Go through each row of winCombos and check the grid for wins
			if(	b[winCombos[i][0]].getText() != "" &&
					b[winCombos[i][0]].getText().equals(b[winCombos[i][1]].getText()) && 
					b[winCombos[i][1]].getText().equals(b[winCombos[i][2]].getText())) 
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Resets the Tic-Tac-Toe game by clearing the buttons and setting the turns back to 0
	 */
	public void resetButtons(JButton b[]) {
		for (int i = 0; i < 9; i++) {
			b[i].setText("");
			b[i].setIcon(null);
			b[i].setEnabled(true);
		}
		numTurns = 0;
	}

	/**
	 * This inner-class better defines the behaviors of the buttons on the Tic-Tac-Toe board
	 *
	 */
	private class buttonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			//Image X
			Image picX = new ImageIcon(this.getClass().getResource("/images/x.jpg")).getImage().getScaledInstance( (int)(width/2.75), (int)(height/2.75), java.awt.Image.SCALE_SMOOTH);
			ImageIcon X = new ImageIcon(picX);

			//Image O
			Image picO = new ImageIcon(this.getClass().getResource("/images/o.jpg")).getImage().getScaledInstance((int)(width/3.125), (int)(height/3.125), java.awt.Image.SCALE_SMOOTH);
			ImageIcon O = new ImageIcon(picO);

			//Put images on the clicked buttons
			JButton buttonClicked = (JButton) e.getSource();
			buttonClicked.setEnabled(false);
			if (numTurns % 2 == 0) {
				buttonClicked.setIcon(X);
				buttonClicked.setText("X");
				buttonClicked.setDisabledIcon(buttonClicked.getIcon());
			} else {
				buttonClicked.setIcon(O);
				buttonClicked.setText("O");
				buttonClicked.setDisabledIcon(buttonClicked.getIcon());
			}		
			numTurns++;

			//Change turns
			String yourTurn = "";
			if (numTurns % 2 == 0)
				yourTurn = "X";
			else 
				yourTurn = "O";

			//Check for a draw
			if (numTurns % 9 == 0 && !didWin(buttons)) {
				int n = JOptionPane.showOptionDialog(null,
						"Draw!  Play again?", "Game Over",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, null, null);
				if (n == 0)
					resetButtons(buttons);
				else
					System.exit(0);
			}

			//Check for a winner
			if (didWin(buttons)) {
				if (yourTurn.equals("O"))
					yourTurn = "X";
				else
					yourTurn = "O";
				int n = JOptionPane.showOptionDialog(null,
						"Congratulations, " + yourTurn + " wins!  Play again?",
						"Game Over", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				//Yes, play again
				if (n == 0)
					resetButtons(buttons);
				//No, exit
				else
					System.exit(0);
			}

			//Reset the turns
			if (numTurns == 0)
				yourTurn = "X";
			goLabel.setText("Your turn:  "+yourTurn);
		}
	}	
}
