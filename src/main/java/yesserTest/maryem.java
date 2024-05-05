package yesserTest;
import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

public class maryem {
    public static void main(String[] args) {
        // Remplacez "YOUR_CLIENT_ID" et "YOUR_CLIENT_SECRET" par votre client ID et votre client secret
        Translate.setClientId("7adafd0b-88d3-4d49-ac8a-b68f20ca9cee");
        Translate.setClientSecret("f9b9c186e1a748ae91cf01b2fc399e43");

        // Texte à traduire
        String texte = "Hello, world!";
        // Langue cible
        Language langueCible = Language.FRENCH; // Utilisation du code de langue correct

        try {
            // Traduction
            String translation = Translate.execute(texte, langueCible);

            // Affichage du texte traduit
            System.out.println("Texte original : " + texte);
            System.out.println("Texte traduit : " + translation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
