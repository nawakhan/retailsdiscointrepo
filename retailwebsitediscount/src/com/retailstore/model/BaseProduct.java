package com.retailstore.model;

/**
 * Interface defining methods to be implemented by Products
 * 
 * @author nawazish
 *
 */
public interface BaseProduct {

	/**
	 * 
	 * @return Name of the product
	 * 
	 */
	String getName();
	
	/**
	 * Return type of the product e.g. groceries
	 * 
	 * @return
	 */
	String getProductType();
	
	/**
	 * 
	 * @return Return Price of a single product
	 */
	double getProductPrice();
	
	/**
	 * 
	 * @param quantity - number of the product items
	 * @return Total price for the quantity provided
	 */
	double getPriceForTotalQuantity(int quantity);
	
}
