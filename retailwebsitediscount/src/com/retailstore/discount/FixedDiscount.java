package com.retailstore.discount;

/*
 * This class provides total amount after deducting the discount of $5 on each $100
 * Applicable on all kind of product types
 */
public class FixedDiscount implements RetailsWebsitesDiscountPolicy {

	@Override
	public double applyDiscount(double totalAmount) {
		//No discount below $100
		if (totalAmount < 100) {
			return totalAmount;
		}
		
		int discountFactor = (int) totalAmount / 100;
		double discount = discountFactor * 5;
		return totalAmount - discount; 
	}

}
