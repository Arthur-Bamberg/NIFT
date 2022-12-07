package model;

public class Global {
    Category category;
    Product product;
    Purchase purchase;
    User user;

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Purchase getPurchase() {
        return this.purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
