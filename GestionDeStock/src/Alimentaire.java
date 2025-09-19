import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Alimentaire extends Produit implements Perissable{

    private LocalDate datePeremption;

    public Alimentaire(int code, String label, float price, int stock, LocalDate datePeremption, Stock addStock) {
        super(code, label, price, stock);
        this.datePeremption = datePeremption;
        addStock.addProductToList(this);
    }

    public String getDatePeremption() {

       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.datePeremption.format(formatter);
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
