import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.management.RuntimeErrorException;


public class TaskQueueManager {
	private static final HashMap<String,ConcurrentLinkedQueue<Job> > pool = new HashMap<String,ConcurrentLinkedQueue<Job>>();
	
	
	
	
	public void push(String qName, Job job){
		ConcurrentLinkedQueue<Job> q = pool.get(qName);
		if (q == null) {
			q = new ConcurrentLinkedQueue<Job>();
			pool.put(qName, q);
		}
		if (!q.offer(job)) {
			throw new RuntimeException("Couldn't add Job to pool!");
		};
		
	}
	
	public Job pull(String qName) {
		
		if (pool.containsKey(qName)) {
			ConcurrentLinkedQueue<Job> q = pool.get(qName);
			return q.poll();
		}else{
			return null;
		}
	}
	

	
	
	
}
