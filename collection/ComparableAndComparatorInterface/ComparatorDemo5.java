import java.util.Comparator;
import java.util.TreeSet;

// This example shows the use of Comparable and Comparator in case of Customized classes

@SuppressWarnings("rawtypes")
class Employee1 implements Comparable
{
	int eid ;
	String name ;
	public Employee1(int eid , String name)
	{
		this.eid = eid ;
		this.name = name ;
	}
	public String toString()
	{
		return "name is: "+name+"-- emp id is: " + eid ;
	}
	public int compareTo(Object obj)
	{
		int eid1 = this.eid ;
		Employee1 e =  (Employee1)obj ;
		int eid2 = e.eid ;
		
		if(eid1 < eid2)
			return -1 ;
		else if((eid1 > eid2))
			return +1 ;
		else 
			return 0 ;
	}
}

public class ComparatorDemo5 {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args)
	{
		Employee1 e1 = new Employee1(100, "Tom");
		Employee1 e2 = new Employee1(101, "Sam");
		Employee1 e3 = new Employee1(99, "Jon");
		Employee1 e4 = new Employee1(108, "Sam");
		Employee1 e5 = new Employee1(200, "Bill");
		Employee1 e6 = new Employee1(667, "Queen");
		
		// In this case default natural sorting will be applied
		//TreeSet t = new TreeSet() ;
		
		// in this it uses MyComparator6 constructor 
		TreeSet t = new TreeSet(new MyComparator6()) ;
		t.add(e1);
		t.add(e2);
		t.add(e3) ;
		t.add(e4) ;
		t.add(e5) ;
		t.add(e6);
		System.out.println(t); // default natural sorting, based on emp id 
		//[name is: Jon-- emp id is: 99, name is: Tom-- emp id is: 100, name is: Sam-- emp id is: 101, name is: Sam-- emp id is: 108, name is: Bill-- emp id is: 200, name is: Queen-- emp id is: 667]

		System.out.println(t);  // customized sorting based on String name 
		//[name is: Bill-- emp id is: 200, name is: Jon-- emp id is: 99, name is: Queen-- emp id is: 667, name is: Sam-- emp id is: 101, name is: Tom-- emp id is: 100]


	}

}


@SuppressWarnings("rawtypes")
class MyComparator6 implements Comparator 
{
	public int compare(Object obj1 , Object obj2)
	{
		Employee1 e1 = (Employee1)obj1 ;
		Employee1 e2 = (Employee1)obj2 ;
		String s1 = e1.name ;
		String s2 = e2.name ;
		return s1.compareTo(s2);
		
	}
}




