package hw2;

/**
 * This class encapsulates the logic and state for a simplified
 * game of American football.  
 * 
 * Computer Science 227, Assignment 2, 2/9/17
 * @author Thomas Haddy
 */
public class FootballGame
{
	/**
	 * Number of points awarded for a touchdown.
	 */  
	public static final int TOUCHDOWN_POINTS = 6;

	/**
	 * Number of points awarded for a successful extra point attempt
	 * after a touchdown.
	 */ 
	public static final int EXTRA_POINTS = 1;

	/**
	 * Number of points awarded for a field goal.
	 */
	public static final int FIELD_GOAL_POINTS = 3;

	/**
	 * Total length of the field from goal line to goal line, in yards.
	 */
	public static final int FIELD_LENGTH = 100;

	/**
	 * Initial position of the offensive team after receiving a kickoff.
	 */ 
	public static final int STARTING_POSITION = 70;

	/**
	 * Yards required to get a first down.
	 */
	public static final int YARDS_FOR_FIRST_DOWN = 10;
	
	/**
	 * Number of downs between 1 and 4
	 */
	private int down;
	
	/**
	 * Contains the current score of team0 and team1
	 */
	private int score0, score1;
	
	/**
	 * Number of yards to the goal line
	 */
	private int yardsToGoalLine;
	
	/**
	 * Determines which team is on offense. Range is between 0 and 1
	 */
	private int offense;
	
	/**
	 * Number of yards to the first down marker
	 */
	private int yardsToFirstDown;
	
	/**
	 * Constructs a new game. Initially team 0 is offense, the ball is 
	 * STARTING_POSITION yards from the defense's goal line, and it is the first down.
	 */
	public FootballGame() {
		offense = 0;
		score0 = 0;
		score1 = 0;
		yardsToGoalLine = STARTING_POSITION;
		down = 1;
		yardsToFirstDown = YARDS_FOR_FIRST_DOWN;

	}
	
	/**
	 * Records the result of an extra point attempt, adding EXTRA_POINTS points if the attempt was successful. 
	 * Whether or not the attempt is successful, the defense gets the ball and starts with a first down, 
	 * STARTING_POSITION yards from the goal line.
	 * @param success
	 * 		true if the extra point was successful, false otherwise
	 */
	public void extraPoint(boolean success) {

		down = 1;
		yardsToGoalLine = STARTING_POSITION;

		if(success) {
			if(offense==1) {
				score1 += EXTRA_POINTS;
				offense = 0;
			}
			else {
				score0 += EXTRA_POINTS;
				offense = 1;
			}
		}
		else {
			if (offense == 1) {
				offense = 0;
			}
			else {
				offense = 1;
			}
		}
	}

	/**
	 * Records the result of a field goal attempt, adding FIELD_GOAL_POINTS points if the field goal 
	 * was successful. If the attempt is successful, the defense gets the ball and starts with a first 
	 * down, STARTING_POSITION yards from the goal line. If the attempt is unsuccessful, the defense 
	 * gets the ball at its current location.
	 * @param success
	 * 		true if the attempt was successful, false otherwise
	 */
	public void fieldGoal(boolean success) {

		if (success) {
			yardsToGoalLine = STARTING_POSITION;
			if(offense==1) {
				score1 += FIELD_GOAL_POINTS;
				offense = 0;
			}
			else {
				score0 += FIELD_GOAL_POINTS;
				offense = 1;
			}	
		}
		else {
			if (offense==1) {
				offense = 0;
				yardsToGoalLine = FIELD_LENGTH - yardsToGoalLine;
			}
			else {
				offense = 1;
				yardsToGoalLine = FIELD_LENGTH - yardsToGoalLine;
			}
		}
	}

	/**
	 * Returns the current down. Possible values are 1 through 4.
	 * @return
	 * 		the current down as a number 1 through 4
	 */
	public int getDown() {
		return down;  
	}

	/**
	 * Returns the index for the team currently playing offense.
	 * @return
	 * 		index of the team playing offense (0 or 1)
	 */
	public int getOffense() {
		return offense;
	}

	/**
	 * Returns the points for the indicated team.
	 * @param whichTeam
	 * 		team index 0 or 1
	 * @return
	 * 		score for team 0 if the given argument is 0, score for team 1 otherwise
	 */
	public int getScore(int whichTeam) {
		if (whichTeam==1) {
			return score1;
		}
		else {
			return score0;
		}
	}

	/**
	 * Returns the number of yards the offense must advance the ball to get a first down.
	 * @return
	 * 		number of yards to get a first down
	 */
	public int getYardsToFirstDown() {
		return yardsToFirstDown;
	}

	/**
	 * Returns the location of the ball as the number of yards to the opposing team's goal line.
	 * @return
	 * 		number of yards from the ball to the defense's goal line
	 */
	public int getYardsToGoalLine() {
		return yardsToGoalLine;
	}

	/**
	 * Records the result of a punt. The defense gets the ball with a first down after it has 
	 * advanced the given number of yards; however, if the ball would have advanced past the 
	 * defense's goal line, the defense starts with the ball at location FIELD_LENGTH 
	 * (i.e. it can't be behind their own goal line). The given number of yards should not be negative.
	 * @param yards
	 * 		number of yards the ball is advanced by the punt (not less than zero)	
	 */
	public void punt(int yards) {
		
		down = 1;
		if(offense == 1) {
			offense = 0;
		}
		else {
			offense = 1;
		}
		int puntDistance = yardsToGoalLine - yards;
		if(puntDistance < 0) {
			yardsToGoalLine = FIELD_LENGTH;
		}
		else {
			yardsToGoalLine = FIELD_LENGTH - puntDistance;
		}

	}

	/**
	 * Records the result of advancing the ball the given number of yards, possibly resulting in a first down, a touchdown, or a turnover.
	 * 		If the resulting distance to the goal line is less than zero, 
	 * 			then a touchdown is awarded and the offense gets TOUCHDOWN_POINTS points added. 
	 * 			(However, after a touchdown, the defending team does not get the ball and begin a first down until extraPoint is called.)
	 * 		If the ball has been advanced 10 or more yards since the first down, 
	 * 			the offense keeps the ball and gets a first down.
	 * 		If it is the fourth down, there is no touchdown, and the ball has not been advanced 10 yards or more, 
	 * 			then the defense takes possession at the ball's current location.
	 * 		Otherwise, the offense keeps the ball and the down increases by 1.
	 * The number of yards the ball is advanced may be negative. However, the distance to the goal line does not ever go over FIELD_LENGTH yards.
	 * @param yards
	 * 		number of yards (possibly negative) the ball is advanced
	 */
	public void runOrPass(int yards) {
		
		yardsToGoalLine -= yards;
		yardsToFirstDown -= yards;
		
		if (yardsToGoalLine > 100) {
			yardsToGoalLine = 100;
		}
		
		if(yardsToGoalLine < 0) {
			yardsToFirstDown = YARDS_FOR_FIRST_DOWN;
			if(offense == 1) {
				score1 += TOUCHDOWN_POINTS;
				down = 1;
			}
			else {
				score0 += TOUCHDOWN_POINTS;
				down = 1;
			}
		}
		else if(yardsToFirstDown <= 0) {
			yardsToFirstDown = 10;
			down = 1;
		}
		else if(down == 4 && yardsToFirstDown > 0) {
			down = 1;
			yardsToFirstDown = 10;
			yardsToGoalLine = FIELD_LENGTH - yardsToGoalLine;
			if (offense==1) {
				offense = 0;
			}
			else {
				offense = 1;
			}
		}
		else {
			down++;
		}
	}
}
