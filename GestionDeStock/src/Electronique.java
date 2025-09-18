import java.time.LocalDate;

public class Electronique extends Produit {

    private int dureeGarentie;

    public Electronique(int code, String label, float price, int stock, int dureeGarentie, Stock addStock) {
        super(code, label, price, stock);
        this.dureeGarentie = dureeGarentie;
        addStock.addProductToList(this);
    }

    public int getDureeGarentie() {
        return dureeGarentie;
    }

    public void setDureeGarentie(int dureeGarentie) {
        this.dureeGarentie = dureeGarentie;
    }

//    @Override
//    public boolean isExpired(LocalDate datePeremption){
//
//        LocalDate dateNow = LocalDate.now();
//        if (dateNow.isBefore(datePeremption)){
//            return false;
//        }
//        return true;
//    }

    @Override
    public String toString() {
        return "[Electronique] " +
                "code=" + getCode() +
                ", label='" + getLabel() + '\'' +
                ", price=" + getPrice() + "€" +
                ", stock=" + getStock() +
                ", Durée de la garantie : " + dureeGarentie;
    }
}
