import java.util.ArrayList;

public abstract class Produit {

    private int code;
    private  String label;
    private float price;
    private int stock;


    public Produit(int code, String label, float price, int stock) {
        this.code = code;
        this.label = label;
        this.price = price;
        this.stock = stock;

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    @Override
    public String toString() {
        return "Produit{" +
                "code=" + code +
                ", label='" + label + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }


}
