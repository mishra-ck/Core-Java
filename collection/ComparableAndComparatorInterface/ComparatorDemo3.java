import java.util.Comparator;
import java.util.TreeSet;

public class ComparatorDemo3 {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args)
	{
		TreeSet t = new TreeSet(new MyComparator3());
		t.add(new StringBuffer("A"));
		t.add(new StringBuffer("Z"));
		t.add(new StringBuffer("D"));
		t.add(new StringBuffer("J"));
		t.add(new StringBuffer("Q"));
		System.out.println(t);
		// [A, D, J, Q, Z]

	}

}

@SuppressWarnings("rawtypes")
class MyComparator3 implements Comparator
{
	public int compare(Object obj1 , Object obj2)
	{
		String s1 = obj1.toString();
		String s2 = obj2.toString() ;
		
		return s1.compareTo(s2);
	}
}