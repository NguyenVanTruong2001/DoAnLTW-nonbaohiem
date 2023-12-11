package beans;

import java.io.Serializable;
import java.util.Calendar;

public class CheckoutBean implements Serializable {
    private int orderId;
    private int userId;
    private Calendar orderDate;
    private String fullname;
    private String telephone;
    private String address;
    private String paymentMethod;
    private String orderState;

    public CheckoutBean(int orderId, int userId, Calendar orderDate, String fullname, String telephone, String address, String paymentMethod, String orderState) {
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

    public Calendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Calendar orderDate) {
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
        int year = Calendar.getInstance().getTime().getYear() + 1900;
        int month = Calendar.getInstance().getTime().getMonth() + 1;
        int date = Calendar.getInstance().getTime().getDate();
        System.out.println(year + "-" + month + "-" + date);
    }
}
