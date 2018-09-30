package hw1;

public class TelevisionTest {

	public static void main(String[] args) {
		Television tv = new Television(5);
		 // try out the volume controls
		 System.out.println(tv.getVolume()); // expected 0.5
		 tv.volumeUp();
		 tv.volumeUp();
		 System.out.println(tv.getVolume()); // expected 0.64
		 tv.volumeUp();
		 tv.volumeUp();
		 tv.volumeUp();
		 tv.volumeUp();
		 tv.volumeUp();
		 tv.volumeUp();
		 System.out.println(tv.getVolume()); // expected 1.0
		 // setting channels
		 System.out.println(tv.getChannel()); // expected 0
		 tv.setChannel(4);
		 System.out.println(tv.getChannel()); // expected 4
		 tv.channelUp();
		 tv.channelUp();
		 System.out.println(tv.getChannel()); // expected 1
		 tv.channelDown();
		 System.out.println(tv.getChannel()); // expected 0
		 
		 System.out.println("----------------------------------");
		 
		 Television tv2 = new Television(10);
		 tv2.setChannel(7);
		 //System.out.println(tv2.getChannel());
		 tv2.setChannel(2);
		 //System.out.println(tv2.getChannel());
		 tv2.resetChannelMax(5);
		 //System.out.println(tv2.getChannel());
		 tv2.goToPreviousChannel();
		 System.out.println(tv2.getChannel());
	}
}
