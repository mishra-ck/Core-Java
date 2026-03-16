// Implementation of comparator interface 
// Different scenarios of comparator return valus

import java.util.Comparator;
import java.util.TreeSet;

public class ComparatorDemo {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		
		// myComparatot object is created
		TreeSet t = new TreeSet(new myComparator());
		
		// this will create an instance of Comparable Interface 
		// therefore the output will be in ascending order.
		//TreeSet t = new TreeSet();
		
		t.add(10);
		t.add(0);
		t.add(15);
		t.add(5);
		t.add(20) ;
		t.add(20);
		System.out.println(t);
	}

}



// myComparator sort the TreeSet object in Descending order
// myComparator implements COmparator Interface
@SuppressWarnings("rawtypes")
class myComparator implements Comparator
{
	public int compare(Object obj1, Object obj2)
	{
		Integer I1 = (Integer)obj1 ;
		Integer I2 = (Integer)obj2 ;

		// 1. case using if-else logic
//		if(I1 < I2)
//		{
//			return +1 ;
//		}
//		else if(I1 > I2)
//		{
//			return -1 ;
//		}
//		else 
//			return 0 ;
//

		// 2. case of using simple compareTo() method, default natural sorting
		// output in this case : [0, 5, 10, 15, 20]
		//return I1.compareTo(I2) ;
		
		// 3. Using compareTO() method but returning  opposite sign 
		// output in this case : [20, 15, 10, 5, 0]
		return -I1.compareTo(I2) ;
		
		// 4. case of swapping parameter in compareTo() method, same as 3 case
		// output in this case is : [20, 15, 10, 5, 0]
		// return I2.compareTo(I1);
		
		// 5. case 4 reverse, will work as default natural sort 
		// output will be : [0, 5, 10, 15, 20]
		//return -I2.compareTo(I1);
		
		// 6. Dangerous case, it will insert all elements even duplicates
		// output in this case: [10, 0, 15, 5, 20, 20]
		//return +1 ;
			
		// 7. case of reverse of insertion order
		// output in this case: [20, 20, 5, 15, 0, 10]
		//return -1 ;
		
		// 8.special case , only first element will be inserted, all remaining are duplicate
		// output in this case :[10]
		//return 0 ;
		
	}
}