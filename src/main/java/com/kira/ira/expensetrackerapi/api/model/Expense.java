package com.kira.ira.expensetrackerapi.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Expense {
    private Long id;
    private Long merchantId;
    private Long cardId;
    private String merchantName;
    private String cardName;
    private String transactionDate; // Should be in YYYY-MM-DD format
    private int month;
    private int year;
    private double price;
    @JsonProperty("isSplit") // Maps JSON "isSplit" to this field
    private Boolean isSplit;// Add this field
    private int splitMonths; // Add this field if applicable
    private Boolean willRefund;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getIsSplit() {
        return isSplit;
    }

    public void setIsSplit(Boolean isSplit) {
        this.isSplit = isSplit;
    }

    public int getSplitMonths() {
        return splitMonths;
    }

    public void setSplitMonths(int splitMonths) {
        this.splitMonths = splitMonths;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Boolean getSplit() {
        return isSplit;
    }

    public void setSplit(Boolean split) {
        isSplit = split;
    }

    public Boolean isWillRefund() {
        return willRefund;
    }

    public void setWillRefund(Boolean willRefund) {
        this.willRefund = willRefund;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", merchantId=" + merchantId +
                ", cardId=" + cardId +
                ", merchantName='" + merchantName + '\'' +
                ", cardName='" + cardName + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", month=" + month +
                ", year=" + year +
                ", price=" + price +
                ", isSplit=" + isSplit +
                ", splitMonths=" + splitMonths +
                '}';
    }
}