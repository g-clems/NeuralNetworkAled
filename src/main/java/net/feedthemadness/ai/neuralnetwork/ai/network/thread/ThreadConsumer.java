package net.feedthemadness.ai.neuralnetwork.ai.network.thread;

import java.util.concurrent.Executor;

public class ThreadConsumer implements Runnable, Executor {
	
	private ThreadManager manager;
	
	public ThreadConsumer(ThreadManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void run() {
		Runnable runnable = manager.getTask();
		
		while (runnable != null) {
			execute(runnable);
			runnable = manager.getTask();
		}
		
		manager.notifyDone();
	}
	
	@Override
	public void execute(Runnable runnable) {
		runnable.run();
	}
	
}
