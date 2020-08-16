package com.retailstore.discount;

/*
 * Interface for Overall Discount Policy for all items of cart
 */
public interface RetailsWebsitesDiscountPolicy {
	double applyDiscount(double totalAmount);
}
