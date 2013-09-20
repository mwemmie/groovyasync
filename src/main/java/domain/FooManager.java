package domain;

public class FooManager {

	public Foo retrieve() throws InterruptedException {
		
		Thread.sleep(3000);  // simulate 3 second response time
		
		return new Foo("someFoo");
	}
}
