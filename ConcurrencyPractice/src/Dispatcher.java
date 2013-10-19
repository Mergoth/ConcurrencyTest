import java.util.ArrayList;
import java.util.Collection;


public class Dispatcher {
	public static TaskQueueManager qManager = new TaskQueueManager();
	public static Collection<Worker> workers;

	public Dispatcher () throws InterruptedException {
		
		workers = new ArrayList<Worker>();
		
		for (int x=0;x<100;x++){
			JobImpl1 j = new JobImpl1();
			j.id="Job1-"+x;
			qManager.push("Queue1",j);
		}
		
		for (int x=0;x<10;x++){
			Thread w1 = new Worker1();
			((Worker) w1).setQueueManager(qManager);
			((Worker1) w1).id=x;
			workers.add((Worker)w1);
			
			//w1.join();
		}
		
		for (Worker w:workers) {
			((Thread)w).start();
		}
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
