package com.jpmjavatest;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class AllSales {

	static public List<StatProduct> products = new ArrayList<StatProduct>();
	static public List<Sale> allSales = new ArrayList<Sale>();

	/**
	 * return product index in the arrayList
	 * 
	 * @param productType
	 * @return
	 */
	public static int getProductIndex(String productType) {
		int index = -1;

		for (StatProduct pro : products) {
			index++;

			if (pro.getProductType().equals(productType)) {
				return index;
			}
		}

		return -1;
	}

	/**
	 * add a sale
	 * 
	 * @param inputSale
	 */
	public static void addSale(Sale inputSale) {

		allSales.add(inputSale);
	}

	/**
	 * log report on number of sailes for each product
	 */
	public static void reportSales() {
		for (StatProduct pro : products) {
			System.out.println("\t [Report] there are/is " + pro.getNumberOfSales() + " for " + pro.getProductType());
		}
	}

	/**
	 * log report on number of sailes for each product
	 */
	public static void reportSalesOnStop() {
		for (StatProduct pro : products) {
			String adjustment = pro.getAdjusmentsHistory().isEmpty() ? "NONE" : pro.getAdjusmentsHistory();
			System.out.println("\t [Report]  Adjusments for product " + pro.getProductType() + ":" + adjustment);
		}
	}

	/**
	 * adjust sales according to the given information
	 * 
	 * @param productType
	 * @param value
	 * @param operation
	 */
	public static void adjustSales(String productType, Integer value, char operation) {

		for (Sale s : allSales) {
			if (s.getProductType().equals(productType)) {
				switch (operation) {
				case '+':
					s.setProductValue(s.getProductValue() + value);
					break;
				case '*':
					s.setProductValue(s.getProductValue() * value);
					break;
				case '-':
					s.setProductValue(s.getProductValue() - value);
					break;
				default:
					throw new InvalidParameterException();
				}
			}
		}
	}
}
