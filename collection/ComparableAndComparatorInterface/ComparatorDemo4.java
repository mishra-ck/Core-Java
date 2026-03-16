import java.util.Comparator;
import java.util.TreeSet;

// Sorting order is increasing length, if length same then alphabetical order

public class ComparatorDemo4 {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) 
	{
	TreeSet t = new TreeSet(new MyComparator4()) ;
	// If we do not use MyComparator4 constructor we will get error: ClassCastException
	
	t.add("A");
	t.add(new StringBuffer("Z"));
	t.add(new StringBuffer("ABC"));
	t.add(new StringBuffer("XYZ"));
	t.add("BB");
	t.add("XY");
	
	System.out.println(t);
	// [A, Z, BB, XY, ABC, XYZ]
	
	
	}

}

@SuppressWarnings("rawtypes")
class MyComparator4 implements Comparator
{
	public int compare(Object obj1, Object obj2)
	{
		String s1 = obj1.toString();
		String s2 = obj2.toString();
		int l1 = s1.length();
		int l2 = s2.length() ;
		
		if(l1 < l2)
			return -1 ;
		else if (l1 > l2)
			return +1 ;
		else
			return s1.compareTo(s2);
	}
}
