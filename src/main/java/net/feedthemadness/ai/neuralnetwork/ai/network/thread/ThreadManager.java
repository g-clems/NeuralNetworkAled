/*
 * NeuralNetworkAled - A shitty neural network handmade in Java.
 * Copyright (C) 2021  Clément Sol
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
