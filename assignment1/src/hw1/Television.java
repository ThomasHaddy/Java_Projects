package hw1;

/**
 * Computer Science 227, Assignment 1
 * @author Thomas Haddy 1/27/17
 *	
 *	This class models a television that has a current channel, max number of channels, and a volume setting 
 */
public class Television {
	
	/**
	 * Value of the max channel. 1 less than the constructor's givenChannelMax 
	 */
	private int channelMax;
	
	/**
	 * Value of the current channel. Ranges from 0-channelMax
	 */
	private int channel;
	
	/**
	 * Value of channel before it is changed
	 */
	private int prevChannel;
	
	/**
	 * Value of the volume. Ranges from 0.0-1.0
	 */
	private double volume;
	
	/**
	 * Final value used to increment or decrement volume 
	 */
	public static final double VOLUME_INCREMENT = 0.07; 
	
	/**
	 * Constructs a TV object with a max channel
	 * @param givenChannelMax
	 * 		Sets channelMax to the max number of channels, including 0
	 */
	public Television(int givenChannelMax) {
		
		channelMax = givenChannelMax-1;
		channel = 0;
		volume = 0.50;
	}
	
	/**
	 * Decrements the channel by 1 except when channel = 0
	 * At 0, channel instead becomes equal to the maxChannel
	 */
	public void channelDown() {
		
		prevChannel = channel;
		
		if (channel == 0) {
			channel = channelMax;
		}
		else {
			channel--;
		}	
	}
	
	/**
	 * Increments the channel by 1 except when channel = maxChannel
	 * At maxChannel, channel instead becomes equal to 0
	 */
	public void channelUp() {
		
		prevChannel = channel;
		
		if (channel == channelMax) {
			channel = 0;
		}
		else {
			channel++;
		}
	}
	
	/**
	 * This displays the current channel and volume
	 * Volume is converted as a rounded percent
	 * @return
	 * 		String representing the current channel and volume
	 */
	public String display() {
		
		int volPercent = (int)Math.round(volume * 100);
		String result = "Channel " + channel + " Volume " + volPercent + "%"; 
		return result;
	}
	
	/**
	 * Returns the current channel
	 * @return
	 * 		current channel
	 */
	public int getChannel() {
		
		return channel;
	}
	
	/**
	 * Returns the current volume as a double
	 * @return
	 * 		current volume
	 */
	public double getVolume() {
		
		return volume;
	}
	
	/**
	 * Changes the channel to its previous value
	 */
	public void goToPreviousChannel() {
		
		channel = prevChannel;
	}
	
	/**
	 * Changes the value of channelMax to givenMax-1
	 * If the given channel or prevChannel is greater than the new givenMax-1
	 * then channel now equals givenMax-1
	 * @param givenMax
	 * 		New value-1 passed to channelMax
	 */		
	public void resetChannelMax(int givenMax) {
		
		channelMax = givenMax-1;
		
		if (prevChannel > givenMax-1) {
			channel = givenMax-1;
			prevChannel = givenMax-1;
		}
		else if (channel > givenMax-1) { 
			channel = givenMax-1;
		}
	}
	
	/**
	 * Sets the channel to a new integer
	 * @param channelNumber
	 * 		New channel number
	 * 		Must be between 0 and maxChannel
	 */
	public void setChannel(int channelNumber) {
		
		prevChannel = channel;
		
		if (channelNumber > channelMax) {
			channel = channelMax;
		}
		else if(channelNumber < 0) {
			channel = 0;
		}
		else {
			channel = channelNumber;
		}
	}
	
	/**
	 * Decreases the volume by VOLUME_INCREMENT
	 * Volume can't go lower than 0.0
	 */
	public void volumeDown() {
		
		volume -= VOLUME_INCREMENT;
		if (volume < 0.0) {
			volume = 0.0;
		}
	}
	
	/**
	 * Increases the volume by VOLUME_INCREMENT
	 * Volume can't go higher than 1.0
	 */
	public void volumeUp() {
		
		volume += VOLUME_INCREMENT;
		if (volume > 1.0) {
			volume = 1.0;
		}
	}
}
