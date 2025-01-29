package com.example.BillGenerateor.DTO;



public class billDTO {
    private Long mobileNo;
    private Integer p_id;
    private Integer quantity;
    private Boolean isGst;
    private payment_status paymentStatus;

    public billDTO(Long mobileNo, Integer p_id, Integer quantity, Boolean isGst, payment_status paymentStatus) {
        this.mobileNo = mobileNo;
        this.p_id = p_id;
        this.quantity = quantity;
        this.isGst = isGst;
        this.paymentStatus = paymentStatus;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Integer getP_id() {
        return p_id;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getGst() {
        return isGst;
    }

    public void setGst(Boolean gst) {
        isGst = gst;
    }

    public payment_status getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(payment_status paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
