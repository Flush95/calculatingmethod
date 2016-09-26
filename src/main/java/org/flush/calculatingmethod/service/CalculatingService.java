package org.flush.calculatingmethod.service;

import java.util.ArrayList;
import java.util.List;

import org.flush.calculatingmethod.model.ChartData;

public class CalculatingService {
	
	private List<Double> xArray = new ArrayList<>();
	private List<Double> yArray = new ArrayList<>();
	
	private double XiYi = 0;
	private int n;
	
	
	public CalculatingService(List<Double> xArray, List<Double> yArray) {
		super();
		this.xArray = xArray;
		this.yArray = yArray;
		
		for (int i = 0; i < xArray.size(); i++) {
			XiYi += xArray.get(i) * yArray.get(i);
		}
		
		this.n = xArray.size();
		
	}
	
	/*
	 * Calculate b  
	 */
	public double calculateI(List <Double> array) {
		return array.stream().reduce((x, y) -> x + y).get();
	}
	
	public double calculateSumOfXSquared() {
		return xArray.stream().map((x) -> (x * x)).map((x) -> x + x).reduce((x, y) -> x + y).get();
	}
	
	public double getB() {
		return  ((n * XiYi) - (calculateI(xArray) * calculateI(yArray)) / 
				   ((n * calculateSumOfXSquared()) - (Math.pow(calculateI(xArray), 2))));
	}
	
	/*
	 * Calculate a
	 */
	public double getFinalA() {
		return (((calculateSumOfXSquared() * calculateI(yArray)) - (calculateI(xArray) * XiYi)) / 
				((n * calculateSumOfXSquared()) - Math.pow(calculateI(xArray), 2)));
	}
	
	/*
	 * Preparation for export into json format
	 */
	public List<ChartData> finalB() {
		double finalB = getB();

		List<ChartData> data = new ArrayList<>();
		
		xArray.forEach((obj) -> {
			data.add(new ChartData(getFinalA(), obj * finalB));
		});
		return data;
	}
	
	
	/*
	 * Checking correct result
	 */
	public double calculateQx() {
		return 
				calculateSumOfXSquared() - ((1 / n) * Math.pow(calculateI(xArray), 2));
	}
	//Qxy
	public double calculateQxy() {
		return 
				XiYi - ((1 / n) * (calculateI(xArray) * calculateI(yArray)));
	}
	//S(a)
	public double calculateSa() {
		return 
				Math.sqrt(((1 / (n - 2)) * (calculateQx() - (getFinalA() * calculateQxy()))) / calculateQx());
	}
	//ta
	public double calculateTa() {
		return 
				Math.abs(getFinalA()) / calculateSa();
	}
}
