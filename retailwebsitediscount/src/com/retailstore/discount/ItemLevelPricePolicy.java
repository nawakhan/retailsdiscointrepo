package com.retailstore.discount;

import com.retailstore.model.BaseProduct;

/*
 * Class to implement pricing at item level
 */
public class ItemLevelPricePolicy implements BaseProduct {

	private final BaseProduct baseProduct;

	public ItemLevelPricePolicy(BaseProduct baseProduct) {
		this.baseProduct = baseProduct;
	}

	public String getName() {
		return baseProduct.getName();
	}

	@Override
	public String getProductType() {
		return baseProduct.getProductType();

	}

	@Override
	public double getProductPrice() {
		return baseProduct.getProductPrice();
	}

	@Override
	public double getPriceForTotalQuantity(int quantity) {
		return baseProduct.getPriceForTotalQuantity(quantity);
	}
}
