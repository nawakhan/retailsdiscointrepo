package com.retailstore.model;

public class Product implements BaseProduct {

	private final String name;
	private final double unitPrice;
	private final String type;

	public Product(String name, double priceInDollars, String type) {
		this.name = name;
		this.unitPrice = priceInDollars;
		this.type = type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getProductType() {
		return  type;
	}

	@Override
	public double getProductPrice() {
		return unitPrice;
	}

	/**
	 * Calculates basic price without discount for each product
	 */
	@Override
	public double getPriceForTotalQuantity(int quantity) {
		return quantity * unitPrice;
	}

}
