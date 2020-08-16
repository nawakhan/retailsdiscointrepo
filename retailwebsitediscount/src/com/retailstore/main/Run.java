package com.retailstore.main;

import com.retailstore.discount.RetailsWebsitesDiscountPolicy;
import com.retailstore.discount.FixedDiscount;
import com.retailstore.model.BaseProduct;
import com.retailstore.model.ShoppingCart;
import com.retailstore.model.Product;

import com.retailstore.model.User;

public class Run {
    public static void main (String[] args)
    {
    	User employee = new User("Nawazish","EMPLOYEE");
        BaseProduct groceryItem = new Product("Rice", 20, "GROCERY");
        BaseProduct electronicsItem = new Product("TV", 222, "Electronics");
        RetailsWebsitesDiscountPolicy discountPolicy = new FixedDiscount();
        
        ShoppingCart shoppingCart = new ShoppingCart(employee, discountPolicy);
        shoppingCart.add(groceryItem, 4);
        shoppingCart.add(electronicsItem, 4);
       
        System.out.println(shoppingCart.total());        
    }
}