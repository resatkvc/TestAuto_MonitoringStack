package com.testautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class ProductPage extends BasePage {
    
    @FindBy(className = "inventory_item")
    private List<WebElement> productItems;
    
    @FindBy(className = "inventory_item_name")
    private List<WebElement> productNames;
    
    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;
    
    @FindBy(css = "[data-test^='add-to-cart']")
    private List<WebElement> addToCartButtons;
    
    @FindBy(css = "[data-test^='remove']")
    private List<WebElement> removeButtons;
    
    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;
    
    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;
    
    @FindBy(css = "[data-test='product_sort_container']")
    private WebElement sortDropdown;
    
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    
    public int getProductCount() {
        return productItems.size();
    }
    
    public String getProductName(int index) {
        if (index < productNames.size()) {
            return getElementText(productNames.get(index));
        }
        return null;
    }
    
    public String getProductPrice(int index) {
        if (index < productPrices.size()) {
            return getElementText(productPrices.get(index));
        }
        return null;
    }
    
    public void addProductToCart(int index) {
        if (index < addToCartButtons.size()) {
            clickElement(addToCartButtons.get(index));
        }
    }
    
    public void removeProductFromCart(int index) {
        if (index < removeButtons.size()) {
            clickElement(removeButtons.get(index));
        }
    }
    
    public void clickCartIcon() {
        clickElement(cartIcon);
    }
    
    public String getCartItemCount() {
        if (isElementDisplayed(cartBadge)) {
            return getElementText(cartBadge);
        }
        return "0";
    }
    
    public boolean isCartBadgeDisplayed() {
        return isElementDisplayed(cartBadge);
    }
    
    public void selectSortOption(String option) {
        // Implementation for sorting
    }
} 