package james.li.concurrencyinpractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * @author jamli
 *
 */
public class JavaConcurrencyInPractice_Listing1_2 {

	public static void main(String[] args) throws InterruptedException {

		Sequence newSequence = new Sequence();
		
		List<Integer> values = Collections.synchronizedList(new ArrayList<Integer>());
		CountDownLatch latch = new CountDownLatch(3);

		
		Runnable countDown = () -> {
			int nextValue = 0;
			while(nextValue < 2000) {
				nextValue = newSequence.getNext();
				values.add(nextValue);
			}
			latch.countDown();
		};

		ExecutorService executorService = Executors.newFixedThreadPool(3);
		executorService.execute(countDown);
		executorService.execute(countDown);
		executorService.execute(countDown);
		executorService.shutdown();
		
		latch.await();
		
		/**
		 * Checking if the list contains duplicate value. If it contains a duplicate, it means the result is not thread safe
		 */
		boolean hasDuplicate = values.size() != new HashSet<Integer>(values).size();
		System.out.println("Has duplicate values? " + hasDuplicate);

	}

}

class Sequence {
	
	private int value;

	public synchronized int getNext() {
		return value++;
	}
}
