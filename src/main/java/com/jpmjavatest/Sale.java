package com.jpmjavatest;

/**
 * 
 * @author maher
 * 
 *         A sale is described by the product type and its value
 *
 */
public class Sale {

	private long saleID;
	private String productType;
	private Integer productValue;
	private int numberOfOccurences;

	/**
	 * @return the saleID
	 */
	public long getSaleID() {
		return saleID;
	}

	/**
	 * @param saleID
	 *            the saleID to set
	 */
	public void setSaleID(long saleID) {
		this.saleID = saleID;
	}

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
	 * @return the productValue
	 */
	public Integer getProductValue() {
		return productValue;
	}

	/**
	 * @param productValue
	 *            the productValue to set
	 */
	public void setProductValue(Integer productValue) {
		this.productValue = productValue;
	}

	/**
	 * @return the numberOfOccurences
	 */
	public int getNumberOfOccurences() {
		return numberOfOccurences;
	}

	/**
	 * @param numberOfOccurences
	 *            the numberOfOccurences to set
	 */
	public void setNumberOfOccurences(int numberOfOccurences) {
		this.numberOfOccurences = numberOfOccurences;
	}

}
