import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Dispatcher {
	public static TaskQueueManager qManager = new TaskQueueManager();
	public static List<Worker> workers;

	public Dispatcher () throws InterruptedException {
		
		workers = new ArrayList<Worker>();
		
		for (int x=0;x<10;x++){
			JobImpl1 j = new JobImpl1();
			j.id="Job-"+x;
			qManager.push("Queue1",j);
		}
		
		for (int x=0;x<10;x++){
			Worker w1 = new Worker1(qManager,x);
				
			workers.add(w1);
			w1.start();
			
		}
		
		for (int x=0;x<10;x++){
			Worker w2 = new Worker2(qManager,x);
				
			workers.add(w2);
			w2.start();
			
			
		}
		
		
		
		//Thread.sleep(5000);
		/*for (int x=100;x<200;x++){
			JobImpl1 j = new JobImpl1();
			j.id="Job-"+x;
			qManager.push("Queue1",j);
		}*/
		
	
		Thread.sleep(2000);
		for (Worker ww:workers) {
			ww.interrupt();
			((Thread)ww).join();
		}
		
		System.out.println("Queue1: "+qManager.getCount("Queue1"));
		System.out.println("Queue2: "+qManager.getCount("Queue2"));
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Dispatcher d= new Dispatcher();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
