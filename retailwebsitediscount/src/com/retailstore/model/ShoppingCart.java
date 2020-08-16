package com.retailstore.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import com.retailstore.discount.RetailsWebsitesDiscountPolicy;
import com.retailstore.discount.AfterPromotionPricing;

/**
 * Class to implement shopping cart
 * 
 * @author nawazish
 *
 */
public class ShoppingCart {

	private Map<BaseProduct, Integer> quantities;
	private RetailsWebsitesDiscountPolicy discountPolicy;
	private User user;

	public ShoppingCart(User user, RetailsWebsitesDiscountPolicy discountPolicy) {
		quantities = new HashMap<BaseProduct, Integer>();
		this.user = user;
		this.discountPolicy = discountPolicy;
	}

	/**
	 * This method calculates total net amount by going through all the products
	 * added to the cart and applies discount percentage if applicable
	 * (getPriceForTotalQuantity) then applies additional discount policy if
	 * applicable
	 * 
	 * @return total net amount
	 */
	public double total() {
		double result = 0;
		for (BaseProduct each : quantities.keySet()) {
			result = result + each.getPriceForTotalQuantity(quantities.get(each));
		}
		//Apply discount of 5$ for each 100$
		if (discountPolicy != null) {
			result = discountPolicy.applyDiscount(result);
		}

		return result;
	}

	public void add(BaseProduct itemToBuy) {
		add(itemToBuy, 1);
	}

	/**
	 * Adds products to the card and responsible for identifying the Promotion
	 * percentage 30% for an employee 10 % for affiliate 5% if user has joined 2
	 * years ago
	 * 
	 * @param itemToBuy
	 * @param quantity
	 */
	public void add(BaseProduct itemToBuy, int quantity) {
		BaseProduct item;

		if ("EMPLOYEE" == user.getUserType()) {
			item = new AfterPromotionPricing(itemToBuy, 30);
		}
		else if ("AFFILIATE" == user.getUserType()) {
			item = new AfterPromotionPricing(itemToBuy, 10);
		}

		else if ("NORMAL" == user.getUserType()
				&& user.getJoiningDateTime() != null && ChronoUnit.YEARS.between(user.getJoiningDateTime(), LocalDateTime.now()) >= 2) {
			item = new AfterPromotionPricing(itemToBuy, 5);
		}

		else {
			item = itemToBuy;
		}

		int previousQuantity = quantities.containsKey(item) ? quantities.get(item) : 0;
		quantities.put(item, previousQuantity + quantity);
	}
}
