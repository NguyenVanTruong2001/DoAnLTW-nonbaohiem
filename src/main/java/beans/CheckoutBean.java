package beans;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class CheckoutBean implements Serializable {
    private int orderId;
    private int userId;
    private Date orderDate;
    private String fullname;
    private String telephone;
    private String address;
    private String paymentMethod;
    private String orderState;

    public CheckoutBean(int orderId, int userId, Date orderDate, String fullname, String telephone, String address, String paymentMethod, String orderState) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.fullname = fullname;
        this.telephone = telephone;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.orderState = orderState;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public static void main(String[] args) {
        System.out.println(Date.valueOf(LocalDate.now()));
    }
}
