
public class JobImpl1 implements Job {
	public String id;
	public String path;
	private volatile Thread master;
	
	public synchronized void startWork(Thread newMaster)  {
		if (this.master == null) {
			this.master=newMaster;
		}else {
			throw new RuntimeException(""+newMaster.getName()+" wants to steal job from "+this.master.getName());
		}
	}
	
	public synchronized void endWork(Thread newMaster) {
		if (this.master == newMaster) {
			this.master = null;
		
		}else {
			throw new RuntimeException(""+newMaster.getName()+" wants to close job from "+this.master.getName());
		}
	}
	
	
	
}
