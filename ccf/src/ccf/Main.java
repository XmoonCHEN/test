package ccf;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
String s=null;
try(Scanner scan=new Scanner(System.in)){
	s=scan.nextLine();
}
String regex="\\{[^\\}]*\\}";
Pattern p=Pattern.compile(regex);
Matcher m=p.matcher(s);
ArrayList<String> list=new ArrayList<>();
while(m.find())
	list.add(m.group());
s=s.replaceAll(regex, "{}");
System.out.println(s);



	}

}
