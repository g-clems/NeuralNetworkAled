package net.feedthemadness.ai.neuralnetwork.ai.network.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadManager {
	
	private List<Thread> threads = new ArrayList<>();
	private Queue<Runnable> tasks = new ConcurrentLinkedQueue<Runnable>();
	
	private volatile int threadRunning = 0;
	
	public ThreadManager(int threadAmmount) {
		for(int i = 0 ; i < threadAmmount ; i++) {
			this.threads.add(new Thread(new ThreadConsumer(this)));
		}
	}
	
	public void run() {
		start();
		waitForStop();
	}
	
	public void start() {
		for(Thread thread : threads) {
			thread.start();
			this.threadRunning++;
		}
	}
	
	public void waitForStop() {
		while (threadRunning > 0) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addTask(Runnable r) {
		tasks.add(r);
	}
	
	public synchronized Runnable getTask() {
		return tasks.poll();
	}
	
	public synchronized void notifyDone() {
		this.threadRunning--;
	}

}
