import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Simple decimal calculator that can add, multiply, divide, modulus, 
 * bitwise shift, AND, OR, and invert. Calculator only works with positive binary.
 * 
 * @author Thomas Haddy 4/21/2018
 */
public class BinaryCalculator {

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

			if (operator.equals("<<") || operator.equals(">>") || operator.equals("~")) {
				prevAnswer = answer;
				clearCalculation();
			}
			else if (!answer.equals("")) {
				prevAnswer = answer;
				clearCalculation();
			}

			switch(clickedOn.getText()) {
			case "0":
				addToOperand("0");
				break;
			case "1":
				addToOperand("1");
				break;
			case "+":
				setOperator(clickedOn, "+");
				break;
			case "*":
				setOperator(clickedOn, "*");
				break;
			case "/":
				setOperator(clickedOn, "/");
				break;
			case "%":
				setOperator(clickedOn, "%");
				break;
			case "<<":
				setOperator(clickedOn, "<<");
				calculate();
				break;
			case ">>":
				setOperator(clickedOn, ">>");
				calculate();
				break;
			case "&":
				setOperator(clickedOn, "&");
				break;
			case "|":
				setOperator(clickedOn, "|");
				break;
			case "~":
				setOperator(clickedOn, "~");
				calculate();
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
					memory = Integer.toString(Integer.parseInt(prevAnswer, 2) 
							- Integer.parseInt(memory, 2), 2);
					prevAnswer = "";
					clearCalculation();
				}
				break;
			case "M+":
				if (!prevAnswer.equals("")) {
					memory = Integer.toString(Integer.parseInt(prevAnswer, 2) 
							+ Integer.parseInt(memory, 2), 2);
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
	 * Operator i.e. +, *, /, ...
	 */
	private String operator;

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
	 * This constructor creates a calculator with a width and height 
	 * for the window dimensions
	 * @param width
	 * 		Given width
	 * @param height
	 * 		Given height
	 */
	public BinaryCalculator(int width, int height) {
		this.width = width;
		this.height = height;
		buttons = new JButton[17];
		operand1 = "";
		operator = "";
		operand2 = "";
		answer = "";
		prevAnswer = "";
		memory = "0";
	}

	public JButton[] getButtons() {
		return buttons;
	}

	public String getOperand1() {
		return operand1;
	}

	public String getOperator() {
		return operator;
	}

	public String getOperand2() {
		return operand2;
	}

	public String getAnswer() {
		return answer;
	}

	public String getPrevAnswer() {
		return prevAnswer;
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

	public String getMemory() {
		return memory;
	}

	public void setButtons(JButton[] b) {
		buttons = b;
	}

	public void setOperand1(String op1) {
		operand1 = op1;
	}

	public void setOperator(String oper) {
		operator = oper;
	}

	public void setOperand2(String op2) {
		operand2 = op2;
	}

	public void setAnswer(String ans) {
		answer = ans;
	}

	public void setPrevAnswer(String prevAns) {
		prevAnswer = prevAns;
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

	public void setMemory(String mem) {
		memory = mem;
	}

	/**
	 * Gets the button text from its position on the grid
	 * 
	 * Grid positions:
	 * 0  1  2
	 * 3  4  5
	 * 6  7  8
	 * 9  10 11
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
			result = "0";
			break;
		case 1:
			result = "1";
			break;
		case 2:
			result = "+";
			break;
		case 3:
			result = "&";
			break;
		case 4:
			result = "|";
			break;
		case 5:
			result = "*";
			break;
		case 6:
			result = "/";
			break;
		case 7:
			result = "%";
			break;
		case 8:
			result = "MC";
			break;
		case 9:
			result = "MR";
			break;
		case 10:
			result = "~";
			break;
		case 11:
			result = "<<";
			break;
		case 12:
			result = ">>";
			break;
		case 13:
			result = "M+";
			break;
		case 14:
			result = "M-";
			break;
		case 15:
			result = "C";
			break;
		case 16:
			result = "=";
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

		if (operator.equals("")) {
			operand1 += num;
		}
		else {
			operand2 += num;
		}
	}

	/**
	 * Sets the operator to either a +, *, /, %, &, |, <<, >>, ~
	 * @param clickedOn
	 * 			Current button being clicked
	 * @param oper
	 * 			Current button's text symbol
	 */
	public void setOperator(JButton clickedOn, String oper) {

		//Makes sure its a valid operator
		if (!oper.equals("+") && !oper.equals("*") && !oper.equals("/") &&
				!oper.equals("%") && !oper.equals("&") && !oper.equals("|") &&
				!oper.equals("<<") && !oper.equals(">>") && !oper.equals("~")) {
			return;
		}

		//Don't allow user to press operator before the first operand
		if (operand1.equals("") && prevAnswer.equals("") && !oper.equals("<<") && 
				!oper.equals(">>") && !oper.equals("~")) {
			clickedOn.setForeground(Color.BLACK);
			return;
		}

		//Operator has already been pressed
		if (!operator.equals("")) {
			clickedOn.setForeground(Color.BLACK);
			return;
		}
		switch(oper) {
		case "+":
			operator = "+";
			break;
		case "*":
			operator = "*";
			break;
		case "/":
			operator = "/";
			break;
		case "%":
			operator = "%";
			break;
		case "<<":
			operator = "<<";
			break;
		case ">>":
			operator = ">>";
			break;
		case "&":
			operator = "&";
			break;
		case "|":
			operator = "|";
			break;
		case "~":
			operator = "~";
			break;
		}

		clickedOn.setForeground(Color.RED);
	}

	/**
	 * Calculates either "operand1 operator operand2" or "prevAnswer operator operand2"
	 * and puts the value in variable answer
	 */
	public void calculate() {

		//Invalid calculation: Nothing to calculate
		if (operand1.equals("") && operand2.equals("") && 
				!operator.equals("<<") && !operator.equals(">>") && !operator.equals("~")) {
			return;
		}
		//If user typed only one operand and pressed equal, answer is first operand
		else if (operand2.equals("") && !operator.equals("<<") && 
				!operator.equals(">>") && !operator.equals("~")) {
			answer = operand1;
			return;
		}

		//We must have a valid calculation
		if (operator.equals("+")) {
			if (prevAnswer.equals("")) {
				answer = Integer.toString(Integer.parseInt(operand1, 2) + 
						Integer.parseInt(operand2, 2), 2);
			}
			else if (!prevAnswer.equals("") && !operand1.equals("")) {
				answer = Integer.toString(Integer.parseInt(operand1, 2) + 
						Integer.parseInt(operand2, 2), 2);
			}
			else {
				answer = Integer.toString(Integer.parseInt(prevAnswer, 2) + 
						Integer.parseInt(operand2, 2), 2);
			}
		}
		else if (operator.equals("*")) {
			if (prevAnswer.equals("")) {
				answer = Integer.toString(Integer.parseInt(operand1, 2) * 
						Integer.parseInt(operand2, 2), 2);
			}
			else if (!prevAnswer.equals("") && !operand1.equals("")) {
				answer = Integer.toString(Integer.parseInt(operand1, 2) * 
						Integer.parseInt(operand2, 2), 2);
			}
			else {
				answer = Integer.toString(Integer.parseInt(prevAnswer, 2) * 
						Integer.parseInt(operand2, 2), 2);
			}

		}
		else if (operator.equals("/")) {
			if (prevAnswer.equals("")) {
				answer = Integer.toString(Integer.parseInt(operand1, 2) / 
						Integer.parseInt(operand2, 2), 2);
			}
			else if (!prevAnswer.equals("") && !operand1.equals("")) {
				answer = Integer.toString(Integer.parseInt(operand1, 2) / 
						Integer.parseInt(operand2, 2), 2);
			}
			else {
				answer = Integer.toString(Integer.parseInt(prevAnswer, 2) / 
						Integer.parseInt(operand2, 2), 2);
			}
		}
		else if (operator.equals("%")) {
			if (prevAnswer.equals("")) {
				answer = Integer.toString(Integer.parseInt(operand1, 2) % 
						Integer.parseInt(operand2, 2), 2);
			}
			else if (!prevAnswer.equals("") && !operand1.equals("")) {
				answer = Integer.toString(Integer.parseInt(operand1, 2) % 
						Integer.parseInt(operand2, 2), 2);
			}
			else {
				answer = Integer.toString(Integer.parseInt(prevAnswer, 2) % 
						Integer.parseInt(operand2, 2), 2);
			}
		}
		else if (operator.equals("&")) {
			if (prevAnswer.equals("")) {
				answer = Integer.toString(Integer.parseInt(operand1, 2) & 
						Integer.parseInt(operand2, 2), 2);
			}
			else if (!prevAnswer.equals("") && !operand1.equals("")) {
				answer = Integer.toString(Integer.parseInt(operand1, 2) & 
						Integer.parseInt(operand2, 2), 2);
			}
			else {
				answer = Integer.toString(Integer.parseInt(prevAnswer, 2) & 
						Integer.parseInt(operand2, 2), 2);
			}
		}
		else if (operator.equals("|")) {
			if (prevAnswer.equals("")) {
				answer = Integer.toString(Integer.parseInt(operand1, 2) | 
						Integer.parseInt(operand2, 2), 2);
			}
			else if (!prevAnswer.equals("") && !operand1.equals("")) {
				answer = Integer.toString(Integer.parseInt(operand1, 2) | 
						Integer.parseInt(operand2, 2), 2);
			}
			else {
				answer = Integer.toString(Integer.parseInt(prevAnswer, 2) | 
						Integer.parseInt(operand2, 2), 2);
			}
		}
		else if (operator.equals("<<")) {
			if (prevAnswer.equals("")) {
				answer = Integer.toString(Integer.parseInt(operand1, 2) << 1, 2);
			}
			else {
				answer = Integer.toString(Integer.parseInt(prevAnswer, 2) << 1, 2);
			}
		}
		else if (operator.equals(">>")) {
			if (prevAnswer.equals("")) {
				answer = Integer.toString(Integer.parseInt(operand1, 2) >> 1, 2);
			}
			else {
				answer = Integer.toString(Integer.parseInt(prevAnswer, 2) >> 1, 2);
			}
		}
		else if (operator.equals("~")) {
			if (prevAnswer.equals("")) {
				answer = Integer.toString(Integer.parseInt(operand1, 2), 2)
						.replaceAll("0", "x").replaceAll("1", "0").replaceAll("x", "1");
				//Remove leading zeros
				while (answer.startsWith("0") && answer.length() != 1) {
					answer = answer.substring(1);
				}
			}
			else {
				answer = Integer.toString(Integer.parseInt(prevAnswer, 2), 2)
						.replaceAll("0", "x").replaceAll("1", "0").replaceAll("x", "1");
				//Remove leading zeros
				while (answer.startsWith("0") && answer.length() != 1) {
					answer = answer.substring(1);
				}
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
		operator = "";
		operand2 = "";
		answer = "";
	}

	/**
	 * Updates the display to show what the user pressed
	 */
	public void updateDisplay() {

		if (operator.equals("<<") || operator.equals(">>") || operator.equals("~")) {
			display.setText(answer);
		}
		//Display function
		else if (answer.equals("")) {
			display.setText(operand1 + operator + operand2);
		}
		//Display error. Pressed equal with operator and no operand2
		else if (!operand1.equals("") && !operator.equals("") && operand2.equals("")) {
			display.setText("Syntax Error:" + operand1 + operator);
		}
		//Display error. Pressed equal with operator and no numbers
		else if (!operator.equals("") && operand2.equals("")) {
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
		grid.setLayout(new GridLayout(4, 5, 2, 2));
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
		BinaryCalculator game = new BinaryCalculator(500, 500);
		game.launch();
	}
}
