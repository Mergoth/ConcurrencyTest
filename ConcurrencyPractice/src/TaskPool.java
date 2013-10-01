import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;


public class TaskPool {
	private HashMap<String,TQueue> pool;
	
	
	public void push(String qname, Object value){
		
	}
	
	public Object pull(String qname) {
		TQueue q = pool.get(qname);
		
		return q.pull(); 
	}
	
	public class TQueue<T> {
		private ConcurrentLinkedQueue<T> queue;
		private String name;
		
		public void push(T value){
			queue.offer(value);
		}
		
		public T pull() {
			return queue.poll();
		}
		
	}
	
}
