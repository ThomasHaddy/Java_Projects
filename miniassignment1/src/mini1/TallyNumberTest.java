package mini1;

public class TallyNumberTest {

	public static void main(String[] args) {

//		TallyNumber n2 = new TallyNumber("|* 0 *** |||");
//		System.out.println(n2.getIntValue());
//
//		TallyNumber n3 = new TallyNumber("|||| **00|** |||| |*|*|");
//		System.out.println(n3.getIntValue());
//
//		n2.add(n3);
//		System.out.println(n2.getIntValue());
//		System.out.println(n2.getStringValue());
//
//		TallyNumber t1 = new TallyNumber("*** ||");
//		TallyNumber t2 = new TallyNumber("|||| *");
//		t1.combine(t2);
//		System.out.println(t1.getIntValue());  //197
//		System.out.println(t1.getStringValue());  //"***|||| ||*"
//
//		TallyNumber t3 = new TallyNumber("| ** 0 ||");
//		System.out.println(t3.getIntValue());
//		System.out.println(t3.getStringValue());
//		TallyNumber t4 = new TallyNumber("| *"); //"| ** 0 ||"
//		System.out.println(t4.getIntValue());
//		System.out.println(t4.getStringValue());
//		t3.combine(t4);
//		System.out.println(t3.getIntValue());  //2017
//		System.out.println(t3.getStringValue());  //"| ** |0 *||"
//		t3.normalize();
//		System.out.println(t3.getStringValue());
		
		TallyNumber t5 = new TallyNumber("** 0**0 ||* 000 *|*|");
		System.out.println("First Tally: " + t5.getStringValue());
		System.out.println("First Tally: " + t5.getIntValue());
		TallyNumber t6 = new TallyNumber("|0|0 0* |0");
		System.out.println("Second Tally: " + t6.getStringValue());
		System.out.println("Second Tally: " + t6.getIntValue());
		t5.combine(t6);
		System.out.println("After calling combine()...");
		System.out.println("Value: " + t5.getIntValue());
		System.out.println("Expected: ** 0**0 ||*|0|0 0000* *|*||0");
		System.out.println("  Actual: " + t5.getStringValue());
	}

}
