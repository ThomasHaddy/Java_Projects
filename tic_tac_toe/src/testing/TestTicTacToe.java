package testing;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import game.TicTacToe;
import javax.swing.JButton;

public class TestTicTacToe {

	private TicTacToe game;
	private JButton buttons[];
	
	@Before
	public void init() {
		game = new TicTacToe(700, 700);
		buttons = new JButton[9];
		for (int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			if (i==0 || i==3 || i==5)
				buttons[i].setText("X");
			else if (i==1 || i==2 || i==4)
				buttons[i].setText("O");
			else
				buttons[i].setText("");
		}
	}
	
	@Test
	public void testWidth() {
		int expected = 700;
		assertEquals(expected, game.getWidth());
	}
	
	@Test
	public void testHeight() {
		int expected = 700;
		assertEquals(expected, game.getHeight());
	}
	
	@Test
	public void testNumTurns() {
		int expected = 0;
		assertEquals(expected, game.getNumTurns());
	}
	
	@Test
	public void testButtons() {
		String expectX = "X";
		String expectO = "O";
		String expectEmpty = "";
		for (int i = 0; i < buttons.length; i++) {
			if (i==0 || i==3 || i==5)
				assertEquals(expectX, buttons[i].getText());
			else if (i==1 || i==2 || i==4)
				assertEquals(expectO, buttons[i].getText());
			else
				assertEquals(expectEmpty, buttons[i].getText());
		}
	}
	
	@Test
	public void testHorizontalWins() {
		JButton[] h1 = new JButton[9];
		JButton[] h2 = new JButton[9];
		JButton[] h3 = new JButton[9];
		int i;
		
		for (i = 0; i < h1.length; i++) {
			h1[i] = new JButton();
			if (i==0 || i==1 || i==2)
				h1[i].setText("X");
			else
				h1[i].setText("");
		}
		
		for (i = 0; i < h2.length; i++) {
			h2[i] = new JButton();
			if (i==0 || i==1 || i==2)
				h2[i].setText("X");
			else
				h2[i].setText("");
		}
		
		for (i = 0; i < h3.length; i++) {
			h3[i] = new JButton();
			if (i==0 || i==1 || i==2)
				h3[i].setText("X");
			else
				h3[i].setText("");
		}
		
		assertTrue(game.didWin(h1));
		assertTrue(game.didWin(h2));
		assertTrue(game.didWin(h3));
		assertFalse(game.didWin(buttons));
	}
	
	@Test
	public void testVerticalWins() {
		JButton[] v1 = new JButton[9];
		JButton[] v2 = new JButton[9];
		JButton[] v3 = new JButton[9];
		int i;
		
		for (i = 0; i < v1.length; i++) {
			v1[i] = new JButton();
			if (i==0 || i==3 || i==6)
				v1[i].setText("X");
			else
				v1[i].setText("");
		}
		
		for (i = 0; i < v2.length; i++) {
			v2[i] = new JButton();
			if (i==1 || i==4 || i==7)
				v2[i].setText("X");
			else
				v2[i].setText("");
		}
		
		for (i = 0; i < v3.length; i++) {
			v3[i] = new JButton();
			if (i==2 || i==5 || i==8)
				v3[i].setText("X");
			else
				v3[i].setText("");
		}
		
		assertTrue(game.didWin(v1));
		assertTrue(game.didWin(v2));
		assertTrue(game.didWin(v3));
		assertFalse(game.didWin(buttons));
	}
	
	@Test
	public void testDiagonalWins() {
		JButton[] d1 = new JButton[9];
		JButton[] d2 = new JButton[9];
		int i;
		
		for (i = 0; i < d1.length; i++) {
			d1[i] = new JButton();
			if (i==0 || i==4 || i==8)
				d1[i].setText("X");
			else
				d1[i].setText("");
		}
		
		for (i = 0; i < d2.length; i++) {
			d2[i] = new JButton();
			if (i==2 || i==4 || i==6)
				d2[i].setText("X");
			else
				d2[i].setText("");
		}
		
		assertTrue(game.didWin(d1));
		assertTrue(game.didWin(d2));
		assertFalse(game.didWin(buttons));
	}
	
	@Test
	public void testDraw() {
		JButton[] draw = new JButton[9];
		for (int i = 0; i < draw.length; i++) {
			draw[i] = new JButton();
			if (i == 1 || i == 2 || i==3 || i == 4 || i == 8)
				draw[i].setText("X");
			else
				draw[i].setText("O");
		}	
		assertFalse(game.didWin(draw));
	}
	
	@Test
	public void testReset() {
		game.resetButtons(buttons);
		String expected = "";
		for (int i = 0; i < buttons.length; i++)
			assertEquals(expected, buttons[i].getText());
		assertEquals(0, game.getNumTurns());
	}
}
