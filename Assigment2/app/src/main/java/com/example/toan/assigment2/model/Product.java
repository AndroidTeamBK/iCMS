package com.example.toan.assigment2.model;

/**
 * Created by toan on 16-Sep-16.
 */
public class Product {
    private String idProduct;
    private String skuNumber;
    private String nameProduct;
    private String quantityProduct;
    private String stockBalance;
    private String brandProduct;
    private String supplier;
    private Double priceProduct;
    private Double unitPriceProduct;
    private Double discountPrice;
    private String sizeProduct;
    private int colorProduct;
    private int imageProduct;

    public Product(String idProduct, String skuNumber, String nameProduct, String stockBalance, String quantityProduct, String brandProduct, String supplier, Double priceProduct,
                   Double unitPriceProduct, Double discountPrice, String sizeProduct, int colorProduct, int imageProduct) {
        this.idProduct = idProduct;
        this.skuNumber = skuNumber;
        this.nameProduct = nameProduct;
        this.stockBalance = stockBalance;
        this.quantityProduct = quantityProduct;
        this.brandProduct = brandProduct;
        this.supplier = supplier;
        this.priceProduct = priceProduct;
        this.unitPriceProduct = unitPriceProduct;
        this.discountPrice = discountPrice;
        this.sizeProduct = sizeProduct;
        this.colorProduct = colorProduct;
        this.imageProduct = imageProduct;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getSkuNumber() {
        return skuNumber;
    }

    public void setSkuNumber(String skuNumber) {
        this.skuNumber = skuNumber;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getQuantityProduct() {
        return quantityProduct;
    }

    public void setQuantityProduct(String quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public String getStockBalance() {
        return stockBalance;
    }

    public void setStockBalance(String stockBalance) {
        this.stockBalance = stockBalance;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(Double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public Double getUnitPriceProduct() {
        return unitPriceProduct;
    }

    public void setUnitPriceProduct(Double unitPriceProduct) {
        this.unitPriceProduct = unitPriceProduct;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getSizeProduct() {
        return sizeProduct;
    }

    public void setSizeProduct(String sizeProduct) {
        this.sizeProduct = sizeProduct;
    }

    public int getColorProduct() {
        return colorProduct;
    }

    public void setColorProduct(int colorProduct) {
        this.colorProduct = colorProduct;
    }

    public int getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(int imageProduct) {
        this.imageProduct = imageProduct;
    }

    public String getBrandProduct() {
        return brandProduct;
    }

    public void setBrandProduct(String brandProduct) {
        this.brandProduct = brandProduct;
    }
}
