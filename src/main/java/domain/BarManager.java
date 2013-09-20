package domain;

public class BarManager {

	public Bar retrieve() throws InterruptedException {
		
		Thread.sleep(1000);  // simulate 1 second response time
		
		return new Bar("someBar");
	}
}
