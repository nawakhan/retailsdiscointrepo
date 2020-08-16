package com.retailstore.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import com.retailstore.discount.RetailsWebsitesDiscountPolicy;
import com.retailstore.discount.AfterPromotionPricing;
import com.retailstore.discount.FixedDiscount;
import com.retailstore.model.BaseProduct;
import com.retailstore.model.Product;
import com.retailstore.model.ShoppingCart;
import com.retailstore.model.User;

public class TestRetailDiscountProject {
	
	private User affiliateUser;
	private User employeeUser;
	private User normalUser;
	private User recentlyJoinedNormatUser;

	private BaseProduct electronicsItem;
	private BaseProduct groceryItem;
	private RetailsWebsitesDiscountPolicy discountPolicy;

	@Before
	public void setValues()
	{
		affiliateUser = new User("Nawazish", "AFFILIATE");
		employeeUser = new User("Nawazish", "EMPLOYEE");
		normalUser = new User("Nawazish", "NORMAL",LocalDateTime.of(2017, 9, 01, 6, 00, 00));
		recentlyJoinedNormatUser = new User("Nawazish", "NORMAL");
		electronicsItem = new Product("TV", 20, "ELECTRONICS");
		groceryItem = new Product("Wheat", 30, "GROCERY");
		discountPolicy = new FixedDiscount();
	}
	
	@Test
	public void testAffiliateDiscountBelow100USD()
	{
		ShoppingCart cart = new ShoppingCart(affiliateUser,discountPolicy);
		cart.add(electronicsItem,5);
		
		/**
		 *  20*5 * .9 and no fixed discount as it is below USD 100
		 */
		
		assertEquals(90, cart.total(), .01);
	}
	
	
	@Test
	public void testAffiliateDiscountAbove100USD()
	{
		ShoppingCart cart = new ShoppingCart(affiliateUser,discountPolicy);
		cart.add(electronicsItem,8);
		
		/**
		 * 20 * 8 * .9 - 5 = 139
		 */
		assertEquals(139, cart.total(), .01);
	}
	
	@Test
	public void testAffiliateDiscountForGrocery()
	{
		ShoppingCart cart = new ShoppingCart(affiliateUser,discountPolicy);
		cart.add(groceryItem,8);
		
		/**
		 * No percentage discount 
		 * 240 - 5 *2 = 230
		 * 
		 */
		assertEquals(230, cart.total(), .01);
	}
	
	
	@Test
	public void testAffiliateDiscountForGroceryBelow100()
	{
		ShoppingCart cart = new ShoppingCart(affiliateUser,discountPolicy);
		cart.add(groceryItem,3);
		//No discount at all
		assertEquals(90, cart.total(), .01);
	}
	
	@Test
	public void testEmployeeDiscountBelow100USD()
	{
		ShoppingCart cart = new ShoppingCart(employeeUser,discountPolicy);
		cart.add(electronicsItem,5);
		
		/**
		 *  20*5 * .7 and no fixed discount as it is below USD 100
		 */
		
		assertEquals(70, cart.total(), .01);
	}
	
	
	@Test
	public void testEmployeeDiscountAbove100USD()
	{
		ShoppingCart cart = new ShoppingCart(employeeUser,discountPolicy);
		cart.add(electronicsItem,8);
		
		/**
		 * 20 * 8 * .7 - 5 = 107
		 */
		
		assertEquals(107, cart.total(), .01);
	}
	
	@Test
	public void testEmployeeDiscountForGrocery()
	{
		ShoppingCart cart = new ShoppingCart(employeeUser,discountPolicy);
		cart.add(groceryItem,8);
		/**
		 * No percentage discount on grocery
		 * 240 - 5 *2 = 230
		 * 
		 */
		assertEquals(230, cart.total(), .01);
	}
	
	
	@Test
	public void testEmployeeDiscountForGroceryBelow100()
	{
		ShoppingCart cart = new ShoppingCart(employeeUser,discountPolicy);
		cart.add(groceryItem,3);
		//No discount at all
		assertEquals(90, cart.total(), .01);
	}
	
	@Test
	public void testNormalDiscountBelow100USD()
	{
		ShoppingCart cart = new ShoppingCart(normalUser,discountPolicy);
		cart.add(electronicsItem,5);
		
		/**
		 *  20*5 * .95 and no fixed discount as it is below USD 100 for the user who had joined website 2 years ago
		 */
		
		assertEquals(95, cart.total(), .01);
	}
	
	
	@Test
	public void testNormalDiscountAbove100USD()
	{
		ShoppingCart cart = new ShoppingCart(normalUser,discountPolicy);
		cart.add(electronicsItem,8);
		
		/**
		 * 20 * 8 * .95 - 5 = 147
		 */
		
		assertEquals(147, cart.total(), .01);
	}
	
	@Test
	public void testNormalDiscountForGrocery()
	{
		ShoppingCart cart = new ShoppingCart(normalUser,discountPolicy);
		cart.add(groceryItem,8);
		
		assertEquals(230, cart.total(), .01);
	}
	
	
	@Test
	public void testNormalDiscountForGroceryBelow100()
	{
		ShoppingCart cart = new ShoppingCart(normalUser,discountPolicy);
		cart.add(groceryItem,3);
		//No discount at all
		assertEquals(90, cart.total(), .01);
	}
	
	@Test
	public void testRecentUserDiscountBelow100USD()
	{
		ShoppingCart cart = new ShoppingCart(recentlyJoinedNormatUser,discountPolicy);
		cart.add(electronicsItem,5);
		//No percentage discount for the unregistered user
		assertEquals(95, cart.total(), .01);
	}
	
	
	@Test
	public void testRecentUserDiscountAbove100USD()
	{
		ShoppingCart cart = new ShoppingCart(recentlyJoinedNormatUser,discountPolicy);
		cart.add(electronicsItem,8);
		//No percentage discount for the unregistered user

		assertEquals(155, cart.total(), .01);
	}
	
	@Test
	public void testRecentUserDiscountForGrocery()
	{
		ShoppingCart cart = new ShoppingCart(recentlyJoinedNormatUser,discountPolicy);
		cart.add(groceryItem,8);
		//No percentage discount for the unregistered user

		assertEquals(230, cart.total(), .01);
	}
	
	
	@Test
	public void testRecentUserDiscountForGroceryBelow100()
	{
		ShoppingCart cart = new ShoppingCart(recentlyJoinedNormatUser,discountPolicy);
		cart.add(groceryItem,3);
		//No discount at all
		assertEquals(90, cart.total(), .01);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_invalidDiscountPercentage()
	{
		new AfterPromotionPricing(new Product("TV", 20, "ELECTRONICS"), 120);
	}
}
