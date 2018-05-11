package com.jpmjavatest;

public class StatProduct {
	private String productType;
	private int numberOfSales;
	private String adjusmentsHistory = "";

	/**
	 * @return the productType
	 */
	public String getProductType() {
		return productType;
	}

	/**
	 * @param productType
	 *            the productType to set
	 */
	public void setProductType(String productType) {
		this.productType = productType;
	}

	/**
	 * @return the numberOfSales
	 */
	public int getNumberOfSales() {
		return numberOfSales;
	}

	/**
	 * @param numberOfSales
	 *            the numberOfSales to set
	 */
	public void setNumberOfSales(int numberOfSales) {
		this.numberOfSales = numberOfSales;
	}

	/**
	 * @return the adjusmentsHistory
	 */
	public String getAdjusmentsHistory() {
		return adjusmentsHistory;
	}

	/**
	 * @param adjusmentsHistory
	 *            the adjusmentsHistory to set
	 */
	public void setAdjusmentsHistory(String adjusmentsHistory) {
		this.adjusmentsHistory = adjusmentsHistory;
	}

}
