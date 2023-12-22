package beans;

import java.io.Serializable;

public class ReviewBean implements Serializable {
    private UserBean userBean;
    private ProductBean productBean;
    private int rating;
    private String comment;

    public ReviewBean() {}

    public ReviewBean(UserBean userBean, ProductBean productBean, int rating, String comment) {
        this.userBean = userBean;
        this.productBean = productBean;
        this.rating = rating;
        this.comment = comment;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public ProductBean getProductBean() {
        return productBean;
    }

    public void setProductBean(ProductBean productBean) {
        this.productBean = productBean;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ReviewBean{" +
                "userBean=" + userBean +
                ", productBean=" + productBean +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
