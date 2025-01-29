import com.example.BillGenerateor.DTO.payment_status;
import com.example.BillGenerateor.Entity.Customer;
import com.example.BillGenerateor.Entity.Items;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long billNo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "c_id")
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "p_id")
    private Items items;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    @Column(name = "purchase_date")
    private LocalDate date;

    @Column(name = "payment_status")
    private payment_status paymentStatus;

    public payment_status getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(payment_status paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    private boolean isGst;
    private int quantity;
    private double amount;

    public long getBillNo() {
        return billNo;
    }

    public void setBillNo(long billNo) {
        this.billNo = billNo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    public boolean isGst() {
        return isGst;
    }

    public void setGst(boolean gst) {
        isGst = gst;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
