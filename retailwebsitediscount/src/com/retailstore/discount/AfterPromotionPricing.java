package com.retailstore.discount;

import com.retailstore.model.BaseProduct;

/**
 * This class extends pricing policy.
 * It provides net amount after applying discount percentage
 * 
 * @author nawazish
 *
 */
public class AfterPromotionPricing extends ItemLevelPricePolicy {

	private final double priceFactor;

	public AfterPromotionPricing(BaseProduct baseProduct, int percentPromotion) {
		super(baseProduct);
		if (percentPromotion < 0 || percentPromotion > 100) {
			throw new IllegalArgumentException("Invalid Discount Percentage, it should be between 0-100"
					+ "Current Discount percentage is " + percentPromotion);
		}
		this.priceFactor = (100 - percentPromotion) / 100.0;
	}
	/**
	 * 	This method calculates Net amount after applying discount percentage
	 */
	public double getPriceForTotalQuantity(int quantity) {
		// Do not apply percentage discount if product type is Grocery
		if ("GROCERY" == super.getProductType()) {
			return super.getPriceForTotalQuantity(quantity);
		}

		// else apply percentage discount
		return (super.getPriceForTotalQuantity(quantity) * priceFactor);
	}
}
