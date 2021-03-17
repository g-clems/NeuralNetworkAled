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

package net.feedthemadness.ai.neuralnetwork.ai.utils;

import java.util.Scanner;

public class TerminalWrapper {
	
	private Scanner sc;
	
	private boolean newLine = true;
	
	public TerminalWrapper() {
		sc = new Scanner(System.in);
	}
	
	public void close() {
		sc.close();
	}
	
	public void log(String message) {
		if (!this.newLine) System.out.println();
		System.out.println(message);
		this.newLine = true;
	}
	
	public void update(String message) {
		System.out.print('\r' + message);
		this.newLine = false;
	}
	
	public void info(String message) {
		StringBuilder sb = new StringBuilder();
		if (!this.newLine) sb.append('\n');
		
		sb.append("[INFO] - ").append(message);
		
		System.out.println(sb.toString());
		this.newLine = true;
	}
	
	public void warning(String message) {
		StringBuilder sb = new StringBuilder();
		if (!this.newLine) sb.append('\n');
		
		sb.append("[WARNING] - ").append(message);
		
		System.out.println(sb.toString());
		this.newLine = true;
	}
	
	public void severe(String message) {
		StringBuilder sb = new StringBuilder();
		if (!this.newLine) sb.append('\n');
		
		sb.append("[SEVERE] - ").append(message);
		
		System.out.println(sb.toString());
		this.newLine = true;
	}
	
	public void error(String error) {
		StringBuilder sb = new StringBuilder();
		if (!this.newLine) sb.append('\n');
		
		sb.append("[ERROR] - ").append(error);
		
		System.err.println(sb.toString());
		this.newLine = true;
	}
	
	public boolean requestBoolean(String message) {
		StringBuilder sb = new StringBuilder();
		if (!this.newLine) sb.append('\n');
		
		sb.append(message).append(" > ");
		System.out.print(sb.toString());
		this.newLine = true;
		
		boolean result = sc.nextBoolean();
		sc.nextLine();
		return result;
	}
	
	public int requestInt(String message) {
		StringBuilder sb = new StringBuilder();
		if (!this.newLine) sb.append('\n');
		
		sb.append(message).append(" > ");
		System.out.print(sb.toString());
		this.newLine = true;
		
		int result = sc.nextInt();
		sc.nextLine();
		return result;
	}
	
	public float requestFloat(String message) {
		StringBuilder sb = new StringBuilder();
		if (!this.newLine) sb.append('\n');
		
		sb.append(message).append(" > ");
		System.out.print(sb.toString());
		this.newLine = true;
		
		float result = sc.nextFloat();
		sc.nextLine();
		return result;
	}
	
	public long requestLong(String message) {
		StringBuilder sb = new StringBuilder();
		if (!this.newLine) sb.append('\n');
		
		sb.append(message).append(" > ");
		System.out.print(sb.toString());
		this.newLine = true;
		
		long result = sc.nextLong();
		sc.nextLine();
		return result;
	}
	
	public double requestDouble(String message) {
		StringBuilder sb = new StringBuilder();
		if (!this.newLine) sb.append('\n');
		
		sb.append(message).append(" > ");
		System.out.print(sb.toString());
		this.newLine = true;
		
		double result = sc.nextDouble();
		sc.nextLine();
		return result;
	}
	
	public String requestString(String message) {
		StringBuilder sb = new StringBuilder();
		if (!this.newLine) sb.append('\n');
		
		sb.append(message).append(" > ");
		System.out.print(sb.toString());
		this.newLine = true;
		
		String result = sc.nextLine();
		return result;
	}
	
}
