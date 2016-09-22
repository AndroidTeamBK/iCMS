package com.example.sev_user.final_weekone.model;

import android.graphics.Bitmap;

/**
 * Created by toan on 16-Sep-16.
 */
public class Product {
    private int idProduct;
    private String skuNumber;
    private String nameProduct;
    private String quantityProduct;
    private String stockBalance;
    private String brandProduct;
    private String supplier;
    private double priceProduct;
    private double unitPriceProduct;
    private double discountPrice;
    private String sizeProduct;
    private int[] colorProduct;
    private Bitmap imageProduct;

    public Product(int idProduct, String skuNumber, String nameProduct, String quantityProduct, String brandProduct, String stockBalance, String supplier, double priceProduct, double unitPriceProduct, double discountPrice, String sizeProduct, int[] colorProduct, Bitmap imageProduct) {
        this.idProduct = idProduct;
        this.skuNumber = skuNumber;
        this.nameProduct = nameProduct;
        this.quantityProduct = quantityProduct;
        this.brandProduct = brandProduct;
        this.stockBalance = stockBalance;
        this.supplier = supplier;
        this.priceProduct = priceProduct;
        this.unitPriceProduct = unitPriceProduct;
        this.discountPrice = discountPrice;
        this.sizeProduct = sizeProduct;
        this.colorProduct = colorProduct;
        this.imageProduct = imageProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
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

    public String getBrandProduct() {
        return brandProduct;
    }

    public void setBrandProduct(String brandProduct) {
        this.brandProduct = brandProduct;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public double getUnitPriceProduct() {
        return unitPriceProduct;
    }

    public void setUnitPriceProduct(double unitPriceProduct) {
        this.unitPriceProduct = unitPriceProduct;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getSizeProduct() {
        return sizeProduct;
    }

    public void setSizeProduct(String sizeProduct) {
        this.sizeProduct = sizeProduct;
    }

    public int[] getColorProduct() {
        return colorProduct;
    }

    public void setColorProduct(int[] colorProduct) {
        this.colorProduct = colorProduct;
    }

    public Bitmap getImageProduct() {
        return imageProduct;
    }

    public void setImageProduct(Bitmap imageProduct) {
        this.imageProduct = imageProduct;
    }
}
