package groovyasync

import java.util.concurrent.Callable
import java.util.concurrent.Executors;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * Possibly utilize GPars?
 * 
 * @author mattwemmie
 *
 */
class StandardGroovyAsyncRunner {

	static main(args) {

		ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));

		List<Callable> calls = Lists.newArrayList(
			
			// verbose
			new Callable<String>() {
				public String call() {
				  return yell("hi");
				}
			  },
		  
			  { question("hi") } as Callable,
			  { "swear" } as Callable
			
		)

		List<ListenableFuture<String>> results = service.invokeAll(calls)
		
		println "doing other stuff while waiting ..."
	
		results.each { futureResult -> println futureResult.get() }
	}
	
	static String yell(String val) {
		return val + "!"
	}
	
	static String question(String val) {
		return val + "?"
	}

}
