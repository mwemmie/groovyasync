package groovyasync

import java.util.concurrent.Callable
import java.util.concurrent.Executors;

import domain.Bar;
import domain.BarManager;
import domain.BazManager;
import domain.FooManager;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

class AsyncThreadManagerRunner {

	static main(args) {
		
		BarManager barManager = new BarManager()
		BazManager bazManager = new BazManager()
		FooManager fooManager = new FooManager()
	
		// Using Guava's ListeningExecutorService - may or may not need the "Listening" part
		ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
		
		// Use Closure Coercion to easily create Callables rather than verbose anonymous inner classes
		// In Groovy, no need to specify return type as it's implied
		List<Callable<Object>> calls = [ 	{ barManager.retrieve() } as Callable,  
											{ bazManager.retrieve() } as Callable, 
											{ fooManager.retrieve() } as Callable]	

		// Invokes all calls concurrently and blocks until all have completed
		List<ListenableFuture<Object>> results = executor.invokeAll(calls)

		// Want to specify a key to get the result I care about as cleanly as possible ...
		// This code doesn't quite do it yet
		def domainMap = results.collect{ result -> result.get() }.groupBy{ domain -> domain.getClass() }
		println domainMap
		
		println domainMap[Bar.class]
		
		executor.shutdown()  // shut down or it will run forever
	}

}
