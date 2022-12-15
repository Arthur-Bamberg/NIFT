package model;

import java.util.ArrayList;

public class Global {
    int idPurchase, idUser;
    ArrayList<Integer> categoriesIds = new ArrayList<>();
    ArrayList<Integer> productsIds = new ArrayList<>();

    public int getIdPurchase() {
        return this.idPurchase;
    }

    public void setIdPurchase(int idPurchase) {
        this.idPurchase = idPurchase;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public ArrayList<Integer> getCategoriesIds() {
        return this.categoriesIds;
    }

    public void setCategoriesIds(ArrayList<Integer> categoriesIds) {
        this.categoriesIds = categoriesIds;
    }

    public ArrayList<Integer> getProductsIds() {
        return this.productsIds;
    }

    public void setProductsIds(ArrayList<Integer> productsIds) {
        this.productsIds = productsIds;
    }
}
