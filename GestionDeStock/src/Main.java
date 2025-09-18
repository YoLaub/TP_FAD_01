import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Stock stock = new Stock();
        Scanner scan =new Scanner(System.in);
        String nomFichierSortie = "stock" + File.separator + "stock.bin";

        File fichierStock = new File(nomFichierSortie);

        // Vérifier si le fichier existe déjà
        if (fichierStock.exists()) {
            System.out.println("Un stock existe déjà. Voici son contenu :");
            try {
                stock.showProductsFromBinary();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.print("Voulez-vous ajouter de nouveaux produits ? (o/n) : ");
            String choix = scan.nextLine();

            if (!choix.equalsIgnoreCase("o")) {
                System.out.println("Fin du programme.");
                return;
            }
        } else {
            System.out.println("Aucun stock trouvé. Créons-en un nouveau !");
        }



        Alimentaire carotte = new Alimentaire(215, "Carotte", 2, 15, LocalDate.of(2025, 9, 29), stock);
        Alimentaire yaourt = new Alimentaire(215, "yaourt", 5, 60, LocalDate.of(2025, 9, 15), stock);
        Electronique tablette = new Electronique(512, "tablette", 350, 6, 6, stock);


        try {
            stock.saveProductsToBinary();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
