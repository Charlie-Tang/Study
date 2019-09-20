package Executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
* @author 作者 tangqichang
* @version 创建时间：2019年9月19日 下午2:49:04
* 类说明 
*/
public class FirstTest {
	
	ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();
	
	
	/**
	 * FixedThreadPool 定长线程池
	 * @param nThreads
	 * @return
	 * 它是一种固定大小的线程池；
	 * corePoolSize和maximunPoolSize都为用户设定的线程数量nThreads；
	 * keepAliveTime为0，意味着一旦有多余的空闲线程，就会被立即停止掉；但这里keepAliveTime无效；
	 * 阻塞队列采用了LinkedBlockingQueue，它是一个无界队列；
	 * 由于阻塞队列是一个无界队列，因此永远不可能拒绝任务；
	 * 由于采用了无界队列，实际线程数量将永远维持在nThreads，因此maximumPoolSize和keepAliveTime将无效。
	 */
	public static ExecutorService newFixedThreadPool(int nThreads) { 
		
		return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()); 
		 
	}
	
	/**
	 * CachedThreadPool 可缓存线程池
	 * @return
	 * 它是一个可以无限扩大的线程池；
	 * 它比较适合处理执行时间比较小的任务；
	 * corePoolSize为0，maximumPoolSize为无限大，意味着线程数量可以无限大；
	 * keepAliveTime为60S，意味着线程空闲时间超过60S就会被杀死；
	 * 采用SynchronousQueue装等待的任务，这个阻塞队列没有存储空间，这意味着只要有请求到来，就必须要找到一条工作线程处理他，如果当前没有空闲的线程，那么就会再创建一条新的线程。
	 */
	public static ExecutorService newCachedThreadPool(){
		
	    return new ThreadPoolExecutor(0,Integer.MAX_VALUE,60L,TimeUnit.MILLISECONDS,new SynchronousQueue<Runnable>());
	}
	
	/**
	 * SingleThreadExecutor 单一线程池
	 * @return
	 * 它只会创建一条工作线程处理任务；
	 * 采用的阻塞队列为LinkedBlockingQueue；
	 */
	public static ExecutorService newSingleThreadExecutor(){
		
	    return new ThreadPoolExecutor(1,1,0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
	}
	
	/**
	 * ScheduledExecutor 可调度的线程池
	 * 它用来处理延时任务或定时任务。
	 * 它接收SchduledFutureTask类型的任务，有两种提交任务的方式：
	 * scheduledAtFixedRate
	 * scheduledWithFixedDelay
	 * SchduledFutureTask接收的参数：
	 * time：任务开始的时间
	 * sequenceNumber：任务的序号
	 * period：任务执行的时间间隔
	 * 它采用DelayQueue存储等待的任务
	 * DelayQueue内部封装了一个PriorityQueue，它会根据time的先后时间排序，若time相同则根据sequenceNumber排序；
	 * DelayQueue也是一个无界队列；
	 * 工作线程的执行过程：
	 * 工作线程会从DelayQueue取已经到期的任务去执行；
	 * 执行结束后重新设置任务的到期时间，再次放回DelayQueue
	 */
	ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
	
	//当一个任务需要使用线程时 先看线程池中的核心线程数是否大于corePoolSize，如果小于corePoolSize则直接创建核心线程来跑
	//如果大于或等于corePoolSize，,则将任务加入队列中，即BlockingQueue
	//如果此时队列都满了，则需要创建额外的非核心线程，创建新的非核心线程时需要全局锁
	//如果此时线程数已经大于maximumPoolSize，当提交任务数超过maxmumPoolSize+workQueue时则调用RejectedExecutionHandler.rejectedExecution()方法。
	
	//maximumPoolSize包含核心线程数和非核心线程数
	//可以向ThreadPoolExecutor提交两种任务：Callable和Runnable。
	//1.Callable 该类任务有返回结果，可以抛出异常。 通过submit函数提交，返回Future对象。 可通过get获取执行结果。
	//2.Runnable 该类任务只执行，无法获取返回结果，并在执行过程中无法抛异常。 通过execute提交。
	
	//shutdown()和shutdownNow()一个是结束执行中的任务  而另一个则是停止
	
	//测试
	/**
	 * 异常策略
	 * AbortPolicy 默认。直接抛异常。
	 * CallerRunsPolicy 只用调用者所在的线程执行任务,重试添加当前的任务，它会自动重复调用execute()方法
	 * DiscardOldestPolicy  丢弃任务队列中最久的任务。
	 * DiscardPolicy 丢弃当前任务。
	 * @param args
	 */
	public static void main(String[] args) {
		int corePoolSize = 5;
		int maxPoolSize = 10;
		long keepAliveTime = 5;
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>(10);
		RejectedExecutionHandler handler = 
//		        new ThreadPoolExecutor.AbortPolicy();
//				new ThreadPoolExecutor.CallerRunsPolicy();
//				new ThreadPoolExecutor.DiscardOldestPolicy();
				new ThreadPoolExecutor.DiscardPolicy();
		ThreadPoolExecutor executor = new ThreadPoolExecutor
				(corePoolSize, maxPoolSize, 
				keepAliveTime, TimeUnit.SECONDS, 
				queue, handler);
		
		for(int i=0; i<100; i++) {
			executor.execute(new Worker());
			}
			executor.shutdown();
		
	}
	
}

class Worker implements Runnable {

	public void run() {
	System.out.println(Thread.currentThread().getName() + " is running");
		}
	}



