package beans;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

public class CheckoutBean implements Serializable {
    private int orderId;
    private UserBean userBean;
    private Date orderDate;
    private String fullname;
    private String telephone;
    private String address;
    private String paymentMethod;
    private String orderState;

    public CheckoutBean(int orderId, UserBean userBean, Date orderDate, String fullname, String telephone, String address, String paymentMethod, String orderState) {
        this.orderId = orderId;
        this.userBean = userBean;
        this.orderDate = orderDate;
        this.fullname = fullname;
        this.telephone = telephone;
        this.address = address;
        this.paymentMethod = paymentMethod;
        this.orderState = orderState;
    }

    public CheckoutBean() {}

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
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

    @Override
    public String toString() {
        return "CheckoutBean{" +
                "orderId=" + orderId +
                ", userBean=" + userBean +
                ", orderDate=" + orderDate +
                ", fullname='" + fullname + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", orderState='" + orderState + '\'' +
                '}';
    }
}
