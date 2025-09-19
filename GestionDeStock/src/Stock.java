import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Stock {

    private ArrayList<Produit> produits = new ArrayList<>();

    public void addProductToList(Produit produit) {
        produits.add(produit);

    }

    public void saveProductsToBinary() throws IOException {
        // Création du répertoire "stock"
        File rep = new File("stock");
        rep.mkdir(); // ne fait rien si le répertoire existe déjà

        for (Produit p : this.produits) {
            System.out.println(p.getClass().getSimpleName() + " : " + p.getLabel());
        }

        // Déclaration du fichier de sortie
        String nomFichierSortie = "stock" + File.separator + "stock.bin";

        try (ObjectOutputStream ecrire = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(nomFichierSortie)))) {

            // d’abord on écrit le nombre de produits pour savoir combien relire
            ecrire.writeInt(this.produits.size());

            // puis chaque produit champ par champ
            for (Produit p : this.produits) {
                ecrire.writeInt(p.getCode());
                ecrire.writeUTF(p.getLabel());
                ecrire.writeFloat(p.getPrice());
                ecrire.writeInt(p.getStock());

                if (p instanceof Alimentaire) {
                    ecrire.writeUTF("Alimentaire");
                    Alimentaire a = (Alimentaire) p;
                    ecrire.writeUTF(a.getDatePeremption());
                } else if (p instanceof Electronique) {
                    ecrire.writeUTF("Electronique");
                    Electronique e = (Electronique) p;
                    ecrire.writeInt(e.getDureeGarentie());
                }
            }

            System.out.println("Produits sauvegardés dans " + nomFichierSortie);
        }

    }

    public void showProductsFromBinary() throws IOException {
        String nomFichierSortie = "stock" + File.separator + "stock.bin";

        try (ObjectInputStream lire = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(nomFichierSortie)))) {

            System.out.println("|" + "-".repeat(121) + "|");
            System.out.printf("|%-46s|%-27s|%-46s|","-".repeat(46),"Liste des produits en stock", "-".repeat(46));
            System.out.println();
            System.out.println("|" + "-".repeat(121) + "|");
            System.out.printf("|%-15s|%-15s|%-15s|%-15s|%-15s|%-25s|%-15s|", "Type","Code","Label", "Price", "Stock", "Date de péremption", "Périmé");
            System.out.println();
            System.out.println("|" + "-".repeat(121) + "|");

            int nbProduits = lire.readInt();
            Stock addStock = new Stock();

            for (int i = 1; i <= nbProduits; i++) {
                int code = lire.readInt();
                String label = lire.readUTF();
                float price = lire.readFloat();
                int stock = lire.readInt();

                String type = lire.readUTF(); // récupère "Alimentaire" ou "Electronique"


                if (type.equals("Alimentaire")) {
                    int year = lire.readInt();
                    int month = lire.readInt();
                    int day = lire.readInt();
                    LocalDate date = LocalDate.of(year, month, day);
                    Alimentaire aliment = new Alimentaire(code, label, price, stock, date, addStock);
                    System.out.printf(
                            "|%-15s|%-15s|%-15d|%-14.2f%-1s|%-15s|%-25s|%-15s|%n",
                            "Alimentaire",
                            aliment.getLabel(),
                            aliment.getCode(),
                            aliment.getPrice(),"€",
                            aliment.getStock(),
                            aliment.getDatePeremption(),
                            aliment.isExpired(date)// LocalDate sera converti en String via toString()// Exemple : suppose que Stock a une méthode getQuantite()


                    );
                    System.out.println("|" + "-".repeat(121) + "|");
                } else if (type.equals("Electronique")) {
                    int dureeGarantie = lire.readInt();
                    Electronique electro = new Electronique(code, label, price, stock, dureeGarantie, addStock);
                    System.out.printf("|%-15s|%-15s|%-15d|%-14.2f%-1s|%-15s|%-25s|%-15s|%n",
                            "Electronique",
                            electro.getLabel(),
                            electro.getCode(),
                            electro.getPrice(),"€",
                            electro.getStock(),
                            electro.getDureeGarentie(),
                            "**********");
                    System.out.println("|" + "-".repeat(121) + "|");

                }


            }
        }

    }


}
