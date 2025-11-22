package model;

public class Price {
    private String productName;
    private String shopName;
    private double priceInHryvnias;

    public Price() {
    }

    public void setPriceInHryvnias(double priceInHryvnias) throws NegativeValueOfPPriceException {
        if (priceInHryvnias < 0) {
            throw new NegativeValueOfPPriceException("Вартість не може мати негативне значення! Спробуйте ще раз.");
        }
        this.priceInHryvnias = priceInHryvnias;
    }

    public void setProductName(String productName) throws IllegalArgumentException {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Назва товару не може бути порожньою.");
        }
        this.productName = productName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) throws IllegalArgumentException {
        if (shopName == null || shopName.isBlank()) {
            throw new IllegalArgumentException("Назву магазину не може бути порожньою.");
        }
        this.shopName = shopName;
    }

    @Override
    public String toString() {
        return productName + " — " + priceInHryvnias + " грн (" + shopName + ")";
    }
}
