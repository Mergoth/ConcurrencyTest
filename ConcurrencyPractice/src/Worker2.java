
public class Worker2 extends Thread implements Worker {
	
	private TaskQueueManager qMananager;
	private int count=0;
	public int id;
	private JobImpl2 currentJob;

	public Worker2(TaskQueueManager qMananager, int id) {
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
			currentJob = (JobImpl2) qMananager.pull("Queue2");
			if (currentJob!=null) {
				currentJob.startWork(this);
				System.out.println("2-"+id+" start"+currentJob.name);
				Thread.sleep(1000);
				
				
				currentJob.endWork(this);
				System.out.println("2-"+id+" finish"+currentJob.name);
				JobImpl1 jj = new JobImpl1();
				
				jj.id=""+currentJob.name;
				qMananager.push("Queue1", jj);
			}else{
				Thread.sleep(500);
			}
		}
		} catch (Exception e) {
			if (currentJob!=null) {
				qMananager.push("Queue2", currentJob);
			}
			e.printStackTrace();
		}
	}


	public void setId(Integer id) {
		this.id=id;
		
	}




}
