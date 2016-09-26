package org.flush.calculatingmethod.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ChartData {

	private double a;
	private double b;
	
	public ChartData(double a, double b) {
		super();
		this.a = a;
		this.b = b;
	}
	
	public double getA() {
		return a;
	}
	public void setA(double a) {
		this.a = a;
	}
	public double getB() {
		return b;
	}
	public void setB(double b) {
		this.b = b;
	}
}
