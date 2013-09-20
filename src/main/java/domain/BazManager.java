package domain;

public class BazManager {

	public Baz retrieve() throws InterruptedException {
		
		Thread.sleep(2000);  // simulate 2 second response time
		
		return new Baz("someBaz");
	}
}
