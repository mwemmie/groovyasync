package gpars

import java.util.concurrent.Future;

import domain.Bar;
import domain.BarManager;
import domain.Baz;
import domain.BazManager;
import domain.Foo;
import domain.FooManager;
import groovyx.gpars.GParsPool;

// TODO: Try out http://gpars.org/1.1.0/guide/guide/single.html#dataParallelism_asynchronousInvocation
class GparsAsyncLibraryRunner {

	static main(args) {
		
		FooManager fooManager = new FooManager()
		BarManager barManager = new BarManager()
		BazManager bazManager = new BazManager()
		
		GParsPool.withPool() {
			
			println "Calling 3 managers asynchronously"
			
			// Execute async and get a Future as a one liner.  Double parens at the end because
			// .async() returns a closure and the next () actually executes it
			Future<Foo> fooFuture = { fooManager.retrieve() }.async()()  
			Future<Bar> barFuture = { barManager.retrieve() }.async()()
			Future<Baz> bazFuture = { bazManager.retrieve() }.async()()                  
			
			//do stuff while calculation performs. usually wouldn't use but maybe if we want
			//to start some data gathering earlier on for some reason could use it
			println "doing some other stuff while waiting"
			
			// Actually get the results. Blocks until the result is in.
			Foo foo = fooFuture.get()
			println foo
			
			Bar bar = barFuture.get()
			println bar
			
			Baz baz = bazFuture.get()
			println baz
		}
	}

}
