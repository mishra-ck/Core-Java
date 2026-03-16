import java.util.Comparator;
import java.util.TreeSet;

public class ComparatorDemo2 {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args)
	{
		
		//TreeSet t = new TreeSet(); // default natural sort  constructor will be called
		// Output in this case will be sorted alphabetically
		// [Ali, Jon, Roja, Sam, Tom, ck]
		
		TreeSet t = new TreeSet(new MyComparator1());
		// MyComparator1 class constructor will be called
		// Output in this case will in reverse alphabetical
		// [Tom, Sam, Roja, Jon, Ck, Ali]
		t.add("Roja");
		t.add("Tom");
		t.add("Jon");
		t.add("Sam");
		t.add("Ali");
		t.add("Ck");
		System.out.println(t);

	}

}
 
@SuppressWarnings("rawtypes")
class MyComparator1 implements Comparator
{
	public int compare(Object obj1 , Object obj2)
	{
		String str1 = (String)obj1 ;  // use casting when internal class is of type String 
		String str2 = obj2.toString() ;  // use this type when we have StringBuffer or any other class
		return str2.compareTo(str1) ;
		// or we can use return as 
		//return -str1.compareTo(str2) ;
	}
}