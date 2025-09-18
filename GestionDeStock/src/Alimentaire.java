import java.time.LocalDate;

public class Alimentaire extends Produit implements Perissable{

    private LocalDate datePeremption;

    public Alimentaire(int code, String label, float price, int stock, LocalDate datePeremption, Stock addStock) {
        super(code, label, price, stock);
        this.datePeremption = datePeremption;
        addStock.addProductToList(this);
    }

    public LocalDate getDatePeremption() {
        return datePeremption;
    }

    public void setDatePeremption(LocalDate datePeremption) {
        this.datePeremption = datePeremption;
    }

    @Override
    public boolean isExpired(LocalDate datePeremption){

        LocalDate dateNow = LocalDate.now();
        if (dateNow.isBefore(datePeremption)){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[Alimentaire] " +
                "code=" + getCode() +
                ", label='" + getLabel() + '\'' +
                ", price=" + getPrice() + "€" +
                ", stock=" + getStock() +
                ", date de péremption=" + datePeremption +
                ", périmé=" + (isExpired(datePeremption) ? "Oui" : "Non");
    }

}
