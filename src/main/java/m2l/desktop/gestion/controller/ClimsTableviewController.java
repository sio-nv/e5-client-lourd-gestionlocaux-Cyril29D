package m2l.desktop.gestion.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import m2l.desktop.gestion.GestionLocaux;
import m2l.desktop.gestion.model.Climatiseur;
import m2l.desktop.gestion.model.Fournisseur;
import m2l.desktop.gestion.model.Model_Clim;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClimsTableviewController implements Initializable {

    @FXML
    private Label nom_fournisseur;
    @FXML
    private Label telephone_fournisseur;
    @FXML
    private Label adresse_fournisseur;
    @FXML
    private TableView<Climatiseur> listeGClims;

    @FXML
    private TableColumn marque;

    @FXML
    public void ajoutClimatiseur(MouseEvent mouseEvent) throws IOException {


        Stage stage_ajout = new Stage();
        try
        {
            FXMLLoader root = new FXMLLoader(GestionLocaux.class.getResource("formulaire_nouveau_climatiseur.fxml"));
            Scene scene = new Scene(root.load());

            stage_ajout.setScene(scene);
            stage_ajout.initOwner(listeGClims.getScene().getWindow());
            stage_ajout.setTitle("Ajout d'un nouveau climatiseur");
            stage_ajout.initModality(Modality.WINDOW_MODAL);
            stage_ajout.show();
        } catch (IOException e) {
            System.err.println(getClass().getName()+" : Il y a une erreur lors de laffichage de la fenêtre d'ajout.");
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //la marque pourra être modifiée
        marque.setCellFactory(TextFieldTableCell.forTableColumn());

        System.out.println("Initialiaze from ListClimController.");
        Model_Clim.connect_to_databse();
        Model_Clim.selectClimatiseurs();
        //association du tableView avec la liste observable
        //tout élément ajouter dans la liste observable sera automatiquement ajouté au tableView
        //tout élément supprimser de la liste observable sera automatiquement supprimé du tableView
        listeGClims.setItems(Model_Clim.getClimatiseurs());

        listeGClims.setEditable(true);
        ///lors d'un double clic sur un item (ligne) du tableView, on récupère le climatiseur sélectionné
        // et on le supprime du modèle
       listeGClims.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
               //si on a pas effectué qu'un seul clic
               if (event.getClickCount() > 1) {
                   //récupération du climatiseur sélectionné
                   Climatiseur selectedItem = (Climatiseur) listeGClims.getSelectionModel().getSelectedItem();
                   Model_Clim.deleteClimatiseur(selectedItem.getId());
                   System.out.println("Suppression de l'item double-cliqué : "+selectedItem.getMarque());
                   //mise à jour du modèle
                   Model_Clim.getClimatiseurs().remove(selectedItem);
               }
           }
       });




    }


    public void suppressionClimatiseur(MouseEvent mouseEvent) {
        //récupération du climatiseur sélectionné
        Climatiseur selectedItem = (Climatiseur) listeGClims.getSelectionModel().getSelectedItem();

        Model_Clim.deleteClimatiseur(selectedItem.getId());

        System.out.println("Suppression de l'item double-cliqué : " + selectedItem.getMarque());

        //mise à jour du modèle
        Model_Clim.getClimatiseurs().remove(selectedItem);
    }

    public void modificationClimatiseur(TableColumn.CellEditEvent cellEditEvent) {
        System.out.println("La valeur est modifiée pour : "
                + cellEditEvent.getTableColumn().getText()
                + " = " + cellEditEvent.getNewValue().toString());

    }

    public void voirFournisseur(MouseEvent mouseEvent) {

        Climatiseur selectedItem = (Climatiseur) listeGClims.getSelectionModel().getSelectedItem();
        System.out.println(getClass().getName()+" : Climatiseur sélectionné dans la liste des climatiqeurs " + selectedItem);

        //récupérer ici les informations du fournisseur à partir de la requête SQL




        //FAIRE EN SORTE QUE L ATTRIBUT FOURNISSEUR  DE L OBJET SELECTED_ITEM     AIT POUR VALEURS D ATTRIBUTS
        // LES VALEURS RECUPEREES DANS LA BASE DE DONNEES

        //tous les climateurs sont dans la liste
        //il faut donc chercher le climatiseur correpondant au climatiseur sélectionné
        
        Fournisseur fournisseur = new Fournisseur();
       // fournisseur.setAdresse(Model_Clim.selectClimatiseurs);




        selectedItem.setFournisseur(fournisseur);

        //passer les informations à la méthode popupFournisseur
        popupFournisseur(selectedItem);

    }

    private void popupFournisseur(Climatiseur c) {

        Fournisseur fournisseur = c.getFournisseur();


        nom_fournisseur.setText(fournisseur.getNom());
        adresse_fournisseur.setText(fournisseur.getAdresse());
        telephone_fournisseur.setText(fournisseur.getTelephone());


        //récupérer les éléments de la vue qui permettront l'affichage des informations du fournisseur
        // et leur affecter les valeurs (ici on affiche par exemple le nom du fournisseur en 1/définissant
        //un attribut de type Label correspondant au label du nom du fournisseur dans la vue et 2/ajouter le nom du
        //du fournisseur dans le label (attribuer la valeur nomFounisseur au texte du Label, ou encore donner la valeur nomFournissseur
        // à l'attribut texte du Label en utilisant la méthode setText)

        Stage stage_ajout = new Stage();
        try
        {
            FXMLLoader root = new FXMLLoader(GestionLocaux.class.getResource("detailsFournisseur.fxml"));
            Scene scene = new Scene(root.load());

            stage_ajout.setScene(scene);
            stage_ajout.initOwner(listeGClims.getScene().getWindow());
            stage_ajout.setTitle("Fournisseur de la marque");
            stage_ajout.initModality(Modality.WINDOW_MODAL);
            stage_ajout.show();
        } catch (IOException e) {
            System.err.println(getClass().getName()+" : Impossible de lancer la fenêtre popup.");
            e.printStackTrace();
        }
    }

}


