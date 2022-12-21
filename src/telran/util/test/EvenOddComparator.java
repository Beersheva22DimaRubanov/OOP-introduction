package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		int res=0;
		if(isOdd(o1) && isOdd(o2)) {
			res = o1-o2;
		}else if(!isOdd(o1)&& isOdd(o2)) {
			res = 1;
		} else if(!isOdd(o1) && !isOdd(o2)) {
			res = o2-o1;
		} 
			return res;
	}
	
	
	private boolean isOdd(int number) {
		return number%2==0?true:false;
	}

}
