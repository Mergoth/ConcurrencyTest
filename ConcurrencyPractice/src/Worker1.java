
public class Worker1 extends Thread implements Worker {
	
	private TaskQueueManager qMananager;
	private int count=0;
	public int id;

	public void setQueueManager(TaskQueueManager qMan) {
		this.qMananager = qMan;
	}
	

	@Override
	public void run() {
		super.run();
		try {
		while (true) {
			JobImpl1 j = (JobImpl1) qMananager.pull("Queue1");
			if (j!=null) {
				System.out.println("Job "+id+" start working on "+j.id);
				
				Thread.sleep(500);
				count++;
				System.out.println("Job "+id+" finish working on "+j.id);
				JobImpl2 jj = new JobImpl2();
				
				jj.name=""+count;
				qMananager.push("Queue2", jj);
			}
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	

}
