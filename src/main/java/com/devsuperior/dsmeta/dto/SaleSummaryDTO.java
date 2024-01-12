package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

public class SaleSummaryDTO {

    private String sellerName;
    private Double amount;

    public SaleSummaryDTO() {
    }

    public SaleSummaryDTO(String sellerName, Double amount) {
        this.sellerName = sellerName;
        this.amount = amount;
    }

    public SaleSummaryDTO(Sale entity) {
        amount = entity.getAmount();
        sellerName = entity.getSeller().getName();
    }

    public SaleSummaryDTO(Seller sale) {
        amount = new SaleMinDTO().getAmount();
        sellerName = sale.getName();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getTotal() {
        return amount;
    }

    public void setTotal(Double amount)
    {
        this.amount = amount;
    }
}