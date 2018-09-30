import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Simple decimal calculator that can add, subtract, multiply, and divide
 * 
 * @author Thomas Haddy 4/23/2018
 */
public class DecimalCalculator {

	/**
	 * This private class creates and handles the behaviors of each button
	 */
	private class ButtonListener implements ActionListener {

		/**
		 * Defines what happens when a button is pressed
		 */
		public void actionPerformed(ActionEvent e) {

			//Display numbers or symbols on the buttons
			JButton buttonClicked = (JButton) e.getSource();
			buttonClicked.setEnabled(true);
			buttonClicked.setFocusable(false);
			handleButtonClickedBehavior(buttonClicked);
			
			//Event-Driven Part
			updateDisplay();
		}

		/**
		 * Handles the behavior of a button being clicked
		 * @param clickedOn
		 * 			Current button clicked on
		 */
		private void handleButtonClickedBehavior(JButton clickedOn) {

			if (!answer.equals("")) {
				prevAnswer = answer;
				clearCalculation();
			}

			switch(clickedOn.getText()) {
			case "7":
				addToOperand("7");
				break;
			case "8":
				addToOperand("8");
				break;
			case "9":
				addToOperand("9");
				break;
			case "+":
				setOperator(clickedOn, '+');
				break;
			case "-":
				//Subtract from previous answer
				if (!prevAnswer.equals("")) {
					setOperator(clickedOn, '-');
				}
				//Make first number negative
				else if (operand1.equals("")) {
					addToOperand("-");
				}
				//Make second number negative
				else if (!operand1.equals("") && operator != '\0' && operand2.equals("")) {
					addToOperand("-");
				}
				//Set operator to -
				else {
					setOperator(clickedOn, '-');
				}
				break;
			case "4":
				addToOperand("4");
				break;
			case "5":
				addToOperand("5");
				break;
			case "6":
				addToOperand("6");
				break;
			case "*":
				setOperator(clickedOn, '*');
				break;
			case "/":
				setOperator(clickedOn, '/');
				break;
			case "1":
				addToOperand("1");
				break;
			case "2":
				addToOperand("2");
				break;
			case "3":
				addToOperand("3");
				break;
			case "0":
				addToOperand("0");
				break;	
			case ".":
				addToOperand(".");
				break;
			case "=":
				calculate();
				break;
			case "C":
				prevAnswer = "";
				clearCalculation();
				for (int i = 0; i < buttons.length; i++) {
					buttons[i].setForeground(Color.BLACK);
				}
				break;
			case "MR":
				answer = memory;
				break;
			case "M-":
				if (!prevAnswer.equals("")) {
					memory = Double.toString(Double.parseDouble(prevAnswer) 
							 - Double.parseDouble(memory));
					prevAnswer = "";
					clearCalculation();
				}
				break;
			case "M+":
				if (!prevAnswer.equals("")) {
					memory = Double.toString(Double.parseDouble(prevAnswer) 
							 + Double.parseDouble(memory));
					prevAnswer = "";
					clearCalculation();
				}
				break;
			case "MC":
				memory = "0.0";
				break;
			}
		}

	} //End ButtonListener

	/**
	 * The buttons for the calculator
	 */
	private JButton buttons[];

	/**
	 * First number
	 */
	private String operand1;

	/**
	 * Operator i.e. +, -, *, /
	 */
	private char operator;

	/**
	 * Second number
	 */
	private String operand2;

	/**
	 * Answer to calculation
	 */
	private String answer;

	/**
	 * Previous answer
	 */
	private String prevAnswer;

	/**
	 * Number stored in memory from M+ or M-
	 */
	private String memory;

	/**
	 * Display for the calculator
	 */
	private JLabel display;

	/**
	 * Width of the window
	 */
	private int width;

	/**
	 * Height of the window
	 */
	private int height;

	/**
	 * This constructor creates a calculator with a width and height for the window dimensions
	 * @param width
	 * 		Given width
	 * @param height
	 * 		Given height
	 */
	public DecimalCalculator(int width, int height) {
		
		this.width = width;
		this.height = height;
		buttons = new JButton[21];
		operand1 = "";
		operand2 = "";
		answer = "";
		prevAnswer = "";
		memory = "0.0";
	}
	
	public JButton[] getButtons() {
		return buttons;
	}

	public String getOperand1() {
		return operand1;
	}
	
	public String getOperand2() {
		return operand2;
	}

	public char getOperator() {
		return operator;
	}
	
	public JLabel getDisplay() {
		return display;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public String getAnswer() {
		return answer;
	}

	public String getPrevAnswer() {
		return prevAnswer;
	}
	
	public String getMemory() {
		return memory;
	}
	
	public void setButtons(JButton[] b) {
		buttons = b;
	}

	public void setOperand1(String op1) {
		operand1 = op1;
	}
	
	public void setOperand2(String op2) {
		operand2 = op2;
	}

	public void setOperator(char ch) {
		operator = ch;
	}
	
	public void setDisplay(JLabel dis) {
		display = dis;
	}
	
	public void setWidth(int w) {
		width = w;
	}

	public void setHeight(int h) {
		height = h;
	}
	
	public void setAnswer(String ans) {
		answer = ans;
	}

	public void setPrevAnswer(String prevAns) {
		prevAnswer = prevAns;
	}
	
	public void setMemory(String mem) {
		memory = mem;
	}

	/**
	 * Gets the button text from its position on the grid
	 * 
	 * Grid positions:
	 * 0  1  2  3  4
	 * 6  7  8  9  10
	 * 11 12 13 14 15
	 * 16 17 18 19 20
	 * 21
	 * 
	 * @param gridPos
	 * 			Current grid position
	 * @return
	 * 			Returns the button's text
	 */
	public String getButtonText(int gridPos) {

		String result = "";
		switch(gridPos) {
		case 0:
			result = "7";
			break;
		case 1:
			result = "8";
			break;
		case 2:
			result = "9";
			break;
		case 3:
			result = "+";
			break;
		case 4:
			result = "-";
			break;
		case 5:
			result = "4";
			break;
		case 6:
			result = "5";
			break;
		case 7:
			result = "6";
			break;
		case 8:
			result = "*";
			break;
		case 9:
			result = "/";
			break;
		case 10:
			result = "1";
			break;
		case 11:
			result = "2";
			break;
		case 12:
			result = "3";
			break;
		case 13:
			result = "MC";
			break;
		case 14:
			result = "MR";
			break;
		case 15:
			result = "0";
			break;
		case 16:
			result = ".";
			break;
		case 17:
			result = "=";
			break;
		case 18:
			result = "M+";
			break;
		case 19:
			result = "M-";
			break;
		case 20:
			result = "C";
			break;

		}
		return result;
	}

	/**
	 * Adds a number, decimal point, or negative sign to operand1 or operand 2
	 * @param num
	 * 			A number, decimal, or negative
	 */
	public void addToOperand(String num) {

		if (operator == '\0') {
			//Don't add another decimal or negative if one already exists
			if (num.equals(".")  && operand1.contains(".") || num.equals("-")  && !operand1.equals("")) {
				return;
			}
			//Otherwise, add the number or decimal and make a new calculation
			operand1 += num;
		}
		else {
			//Don't add another decimal or negative if one already exists
			if (num.equals(".")  && operand2.contains(".") || num.equals("-")  && !operand2.equals("")) {
				return;
			}
			//Otherwise, add the number or decimal
			operand2 += num;
		}
	}

	/**
	 * Sets the operator to either a +, -, *, or /
	 * @param clickedOn
	 * 			Current button being clicked
	 * @param oper
	 * 			Current button's text symbol
	 */
	public void setOperator(JButton clickedOn, char oper) {

		//Makes sure its a valid operator
		if (oper != '+' && oper != '-' && oper != '*' && oper != '/') {
			return;
		}
		
		//Don't allow user to press operator before the first operand
		if (operand1.equals("") && prevAnswer.equals("")) {
			clickedOn.setForeground(Color.BLACK);
			return;
		}
		
		//Operator has already been pressed
		if (operator != '\0') {
			clickedOn.setForeground(Color.BLACK);
			return;
		}
		else if (oper == '+') {
			operator = '+';
		}
		else if (oper == '-') {
			operator = '-';
		}
		else if (oper == '*') {
			operator = '*';
		}
		else {
			operator = '/';
		}
		clickedOn.setForeground(Color.RED);
	}

	/**
	 * Calculates either "operand1 operator operand2" or "prevAnswer operator operand2"
	 * and puts the value in variable answer
	 */
	public void calculate() {

		//Not valid: nothing to calculate
		if (operand1.equals("") && operand2.equals("") && prevAnswer.equals("")) {
			return;
		}
		//No operator: answer is first operand
		else if (!operand1.equals("") && operand2.equals("")) {
			answer = operand1;
			return;
		}
		//Show answer if user keeps pressing equal sign after answer is shown
		else if (operand2.equals("")) {
			answer = prevAnswer;
			return;
		}

		//We must have a valid calculation
		if (operator == '=') {
			answer = prevAnswer;
			return;
		}
		if (operator == '+') {
			if (prevAnswer.equals("")) {
				answer = Double.toString(Double.parseDouble(operand1) + Double.parseDouble(operand2));
			}
			else if (!prevAnswer.equals("") && !operand1.equals("")) {
				answer = Double.toString(Double.parseDouble(operand1) + Double.parseDouble(operand2));
			}
			else {
				answer = Double.toString(Double.parseDouble(prevAnswer) + Double.parseDouble(operand2));
			}
		}
		else if (operator == '-') {
			if (prevAnswer.equals("")) {
				answer = Double.toString(Double.parseDouble(operand1) - Double.parseDouble(operand2));
			}
			else if (!prevAnswer.equals("") && !operand1.equals("")) {
				answer = Double.toString(Double.parseDouble(operand1) - Double.parseDouble(operand2));
			}
			else {
				answer = Double.toString(Double.parseDouble(prevAnswer) - Double.parseDouble(operand2));
			}
		}
		else if (operator == '*') {
			if (prevAnswer.equals("")) {
				answer = Double.toString(Double.parseDouble(operand1) * Double.parseDouble(operand2));
			}
			else if (!prevAnswer.equals("") && !operand1.equals("")) {
				answer = Double.toString(Double.parseDouble(operand1) * Double.parseDouble(operand2));
			}
			else {
				answer = Double.toString(Double.parseDouble(prevAnswer) * Double.parseDouble(operand2));
			}

		}
		else if (operator == '/') {
			if (prevAnswer.equals("")) {
				answer = Double.toString(Double.parseDouble(operand1) / Double.parseDouble(operand2));
			}
			else if (!prevAnswer.equals("") && !operand1.equals("")) {
				answer = Double.toString(Double.parseDouble(operand1) / Double.parseDouble(operand2));
			}
			else {
				answer = Double.toString(Double.parseDouble(prevAnswer) / Double.parseDouble(operand2));
			}
		}

		//Make the buttons all black again
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setForeground(Color.BLACK);
		}
	}

	/**
	 * Clears the calculation
	 */
	public void clearCalculation() {
		
		operand1 = "";
		operator = '\0';
		operand2 = "";
		answer = "";
	}
	
	/**
	 * Updates the display to show what the user pressed
	 */
	public void updateDisplay() {
		
		//Display function
		if (answer.equals("")) {
			display.setText(operand1 + operator + operand2);
		}
		//Display error. Pressed equal with operator and no operand2
		else if (!operand1.equals("") && operator != '\0' && operand2.equals("")) {
			display.setText("Syntax Error:" + operand1 + operator);
		}
		//Display error. Pressed equal with operator and no numbers
		else if (operator != '\0' && operand2.equals("")) {
			display.setText("Syntax Error:" + operator);
		}
		//Display answer
		else {
			display.setText(answer);
		}
	}

	/**
	 * Creates all of the necessary frames, panels, and buttons for a calculator
	 */
	public void launch() {

		JFrame frame = new JFrame();
		JPanel grid = new JPanel();
		JPanel displayPanel = new JPanel();

		//Set up buttons
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setText(getButtonText(i));
			buttons[i].setBackground(Color.WHITE);
			buttons[i].setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 20));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(new ButtonListener());
			grid.add(buttons[i]);
		}

		//Set up layout of grid
		grid.setLayout(new GridLayout(5, 5, 2, 2));
		grid.setBackground(Color.DARK_GRAY);
		grid.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		grid.setPreferredSize(new Dimension(width / 3, height - 100));

		//Set up the display panel
		display = new JLabel();
		display.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 35));
		displayPanel.add(display);
		displayPanel.setPreferredSize(new Dimension(width, 75));
		displayPanel.setBackground(Color.WHITE);
		displayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));

		//Center the frame
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();      
		int x=(int)((dimension.getWidth() - width)/2);
		int y=(int)((dimension.getHeight() - height)/2);

		//Put everything into the frame
		frame.getContentPane().add(displayPanel, BorderLayout.NORTH);
		frame.getContentPane().add(grid, BorderLayout.SOUTH);
		frame.pack();
		frame.setLocation(x, y);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.setVisible(true);
	}

	/**
	 * Main method to run the calculator
	 */
	public static void main(String[] args) {
		
		DecimalCalculator game = new DecimalCalculator(500, 500);
		game.launch();
	}
}
