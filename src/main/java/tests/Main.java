package tests;

import Entities.User;
import services.*;
import utils.MyDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args){

        MyDatabase myDataBase = new MyDatabase();
        ServiceUser serviceUser = new ServiceUser();
        List yesser = new ArrayList<>();
        yesser.add("ROLE_ADMIN");
       // User user= new User( 1,"John Doe","exemple@example.com","12345678","ROLE_USER","motdepasse","Développeur","ABC123","chemin/vers/photo.jpg","123 rue exemple","12/6/2000",0,0,"rh",1000);
//User maryem = new User(4,"maryem","maryemmhamdi@gmail.com","hello","sbs","hshsh","hhshs");
//User maryemHobYesser = new User("maryemHobYesser","jdsjs","hshsh","yesser@gmail.com","hshshs","hdhdh","hshs","bssb","shshs","hshs",0,1,"gsgs",253);
   // try {
           // serviceUser.ajouter();
            System.out.println("edit");
       // } catch (SQLException e) {
          //  System.out.println(e.getMessage());
        }


        // Appel de la méthode ajouter pour ajouter l'utilisateur



        /* Test reclamation
        ServiceReclamation serviceReclamation = new ServiceReclamation();


        //Ajout

        Reclamation reclamation = new Reclamation();
        reclamation.setObjet_rec("Objet de la réclamation");
        reclamation.setContenu_rec("Contenu de la réclamation");
        reclamation.setAdr_rec("Adresse de la réclamation");
        reclamation.setNom_aut_rec("Nom de l'auteur de la réclamation");
        reclamation.setDep_rec("Département concerné");
        reclamation.setStatut_rec("Statut de la réclamation");
        reclamation.setPiece_jrec("Chemin vers la pièce jointe");
        reclamation.setDate_rec(LocalDateTime.now());

        try {
            serviceReclamation.ajouter(reclamation);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la réclamation : " + e.getMessage());
        }

        //Affichage

        try {
            List<Reclamation> reclamations = serviceReclamation.afficher();

            for (Reclamation reclamation : reclamations) {
                System.out.println(reclamation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            Reclamation reclamationToDelete = new Reclamation();
            reclamationToDelete.setId(17);

            serviceReclamation.supprimer(reclamationToDelete);
            System.out.println("Réclamation supprimée avec succès.");
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
       // ServiceReponse serviceReponse = new ServiceReponse();

          /*
            test reponse
            //Ajout
        try {
            Reponse reponse = new Reponse();
            reponse.setReclamation_id(2);
            reponse.setAdr_rep("Adresse de la réponse");
            reponse.setDate_rep(LocalDateTime.now());
            reponse.setContenu_rep("Contenu de la réponse");
            reponse.setPiece_jrep("Chemin vers la pièce jointe de la réponse");

            serviceReponse.ajouter(reponse);

            System.out.println("Réponse ajoutée avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la réponse : " + e.getMessage());
        }


            //Modification
        try {
            Reponse reponse = serviceReponse.getReponseById(8);
            reponse.setAdr_rep("Nouvelle adresse de la réponse");
            reponse.setDate_rep(LocalDateTime.now());
            reponse.setContenu_rep("Nouveau contenu de la réponse");
            reponse.setPiece_jrep("Nouveau chemin vers la pièce jointe de la réponse");

            serviceReponse.modifier(reponse);

            System.out.println("Réponse modifiée avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de la réponse : " + e.getMessage());
        }
        //Suppression
        try {
            Reponse reponseToDelete = new Reponse();
            reponseToDelete.setId(8);

            serviceReponse.supprimer(reponseToDelete);

            System.out.println("Réponse supprimée avec succès.");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression de la réponse : " + ex.getMessage());
        }
        //Affichage

        try {
            List<Reponse> reponses = serviceReponse.afficher();
            for (Reponse reponse : reponses) {
                System.out.println("Reponse ID: " + reponse.getId());
                System.out.println("Reclamation ID: " + reponse.getReclamation_id());
                System.out.println("Adresse rep: " + reponse.getAdr_rep());
                System.out.println("Date rep: " + reponse.getDate_rep());
                System.out.println("Contenu rep: " + reponse.getContenu_rep());
                System.out.println("Piece jointe rep: " + reponse.getPiece_jrep());
                System.out.println("---------------------------------------");
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'affichage des réponses : " + ex.getMessage());
        }
        */
        //ServiceArticle serviceArticle = new ServiceArticle();
/*
        try {

 // Test d'ajout d'un article
            Article article = new Article("Nom_auteur", "Adresse_auteur", LocalDateTime.now(), 10, "Categorie", "Titre", "Contenu", "Piece_jointe", "Image");
            serviceArticle.ajouter(article);

            // Test de modification d'un article
            Article articleModif = serviceArticle.getArticleById(1);
            articleModif.setTitreArt("Nouveau titre");
            serviceArticle.modifier(articleModif);
    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
    }

}

        } catch (SQLException e) {
            e.printStackTrace();
        }
 */
        ServiceCommentaireHadhemi serviceCommentaire = new ServiceCommentaireHadhemi();

        /*
         // test d'ajout d'un commentaire
        try {
                CommentaireHadhemi commentaire = new CommentaireHadhemi("Contenu commentaire", LocalDateTime.now(), "Auteur", 2, "Image");

            serviceCommentaire.ajouter(commentaire);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Test de modification d'un commentaire
        CommentaireHadhemi commentaire = new CommentaireHadhemi(6,"Contenu commentaire", LocalDateTime.now(), "Auteur", "Image");
        commentaire.setContenu("Nouveau contenu commentaire");
        try {
            serviceCommentaire.modifier(commentaire);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Test d'affichage des commentaires
        try {
            List<CommentaireHadhemi> commentaires = serviceCommentaire.afficher();
            for (CommentaireHadhemi c : commentaires) {
                System.out.println(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Test de suppression d'un commentaire
         CommentaireHadhemi commentaire = new CommentaireHadhemi(14,"Contenu ", LocalDateTime.now(), "Aut", 2, "Image");

        try {
            serviceCommentaire.supprimer(commentaire);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/
        ServiceReponseCommentaire service = new ServiceReponseCommentaire();


        /*
        // Test d'ajout
        ReponseCommentaire reponseCommentaireAjout = new ReponseCommentaire("Contenu test", "Nom test", LocalDateTime.now(), 3);
        try {
            service.ajouter(reponseCommentaireAjout);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Test d'affichage
        try {
            List<ReponseCommentaire> reponsesCommentaire = service.afficher();
            System.out.println("Liste des réponses aux commentaires : ");
            for (ReponseCommentaire rc : reponsesCommentaire) {
                System.out.println(rc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Test de modification
        try {
            ReponseCommentaire reponseCommentaireModif = service.getReponseCommentaireById(1);
            reponseCommentaireModif.setContenu_rep_com("Nouveau contenu");
            service.modifier(reponseCommentaireModif);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Test de suppression
        try {
            ReponseCommentaire reponseCommentaireSupp = service.getReponseCommentaireById(1);
            service.supprimer(reponseCommentaireSupp);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }



