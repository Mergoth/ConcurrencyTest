
public interface Worker {

public void start();	
 public void interrupt();
 public void setQueueManager(TaskQueueManager qMan);
 public void setId(Integer id);
}
