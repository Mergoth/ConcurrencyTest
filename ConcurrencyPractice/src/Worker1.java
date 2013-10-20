
public class Worker1 extends Thread implements Worker {
	
	private TaskQueueManager qMananager;
	private int count=0;
	public int id;
	private JobImpl1 currentJob;
	
	
	
	public Worker1(TaskQueueManager qMananager, int id) {
		super();
		this.setDaemon(true);
		this.qMananager = qMananager;
		this.id = id;
	}


	public void setQueueManager(TaskQueueManager qMan) {
		this.qMananager = qMan;
	}
	

	@Override
	public void run() {
		super.run();
		try {
		while (true) {
			currentJob = (JobImpl1) qMananager.pull("Queue1");
			if (currentJob!=null) {
				currentJob.startWork(this);
				System.out.println("1-"+id+" start "+currentJob.id);
				Thread.sleep(500);
				currentJob.endWork(this);
				System.out.println("1-"+id+" finish "+currentJob.id);
				JobImpl2 jj = new JobImpl2();
				
				jj.name=""+currentJob.id;
				qMananager.push("Queue2", jj);
			}else{
				Thread.sleep(500);
			}
		}
		} catch (Exception e) {
			if (currentJob!=null) {
				qMananager.push("Queue1", currentJob);
			}
			e.printStackTrace();
		}
	}


	public void setId(Integer id) {
		this.id=id;
		
	}

	
	

}
