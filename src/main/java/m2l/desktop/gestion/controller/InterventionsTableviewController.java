package m2l.desktop.gestion.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import m2l.desktop.gestion.AjoutIntervention;
import m2l.desktop.gestion.model.AffichageIntervention;
import m2l.desktop.gestion.model.Intervenant;
import m2l.desktop.gestion.model.Intervention;
import m2l.desktop.gestion.model.Salle;

import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class InterventionsTableviewController implements Initializable {


    /**
     * Gestion de la connexion à la base de données
     */
    private Connection connexion;
    private Statement stmt;
    /**
     * List contenant les données à affiché dans le
     * premier onglet
     */
    private ObservableList<AffichageIntervention> donnees_interventions;

    /**
     * récupération des éléments définis dans la vue (fxml)
     */
    @FXML
    public TableView todayInt;

    @FXML
    public TableColumn<AffichageIntervention,String> salleCol;
    @FXML
    public TableColumn <AffichageIntervention,String>intervenantCol;
    @FXML
    public TableColumn <AffichageIntervention,Number>contactCol;
    @FXML
    public TableColumn <AffichageIntervention,String>motifCol;
    //SITUATION A : Finalisation de l’affichage l’onglet « Interventions du jour ».
    @FXML
    public TableColumn <AffichageIntervention,String>statutCol;
    //SITUATION A : Finalisation de l’affichage l’onglet « Interventions du jour ».
    @FXML
    public TableColumn <AffichageIntervention,String>dateCol;



    /**
     * SITUATION B : Mise en place l’onglet « Voir toutes les interventions ».
     */
    private ObservableList<AffichageIntervention> donnees_interventions_all;
    @FXML
    public TableView toutesInterventions;

    @FXML
    public TableColumn<AffichageIntervention,String> salleCol_all;
    @FXML
    public TableColumn <AffichageIntervention,String>intervenantCol_all;
    @FXML
    public TableColumn <AffichageIntervention,Number>contactCol_all;
    @FXML
    public TableColumn <AffichageIntervention,String>motifCol_all;
    @FXML
    public TableColumn <AffichageIntervention,String>dateCol_all;
    @FXML
    public Label dateJour;

    //liste contenant les salles à afficher
    private List<AffichageIntervention> liste_des_interventions = new ArrayList<AffichageIntervention>();


    /**
     * Méthode lancée lors de l'appui sur le bouton
     * sur la page d'accueil
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
        configureOngletInterventions();

    }



    private void configureOngletInterventions()
    {
        try
        {
            //configuration de la base de données
            String url1 = "jdbc:mysql://localhost:3337/sitem2l?&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
            //utilisateur pour connexion à la BDD
            String user = "gestuser";
            //mot de passe pour connexion à la BDD
            String password = "gestpass";

            //établissement de la connexion à la base de données
            connexion = (Connection) DriverManager.getConnection(url1, user, password);

            if (connexion != null)
            {
                System.out.println("Connexion à la base de donnée gestsalle");

                try
                {

                    System.out.println("Chargement des salles...");
                    //création d'un "Statement" pour exécuter la requête
                    stmt = connexion.createStatement();

                    //définition de la requête


                    String sql = "SELECT I.date, I.heure, S.nom as nomSalle, P.nom AS nomIntervenant, prenom, telephone FROM interventions I join salles S on S.numeroSalle=numSalle join intervenants P on P.numeroInter = numIntervenant";
                    System.out.println(this.getClass()+" - requête :"+sql);
                    //exécution de la requête
                    ResultSet rs = stmt.executeQuery(sql);


                    //parcours des enregistrements résultats,
                    //création de nouveaux objets "salle" et
                    //ajout de cet objet dans la liste
                    while(rs.next())
                    {
                        liste_des_interventions.add(new AffichageIntervention(new Salle(rs.getString("nomSalle")),
                                new Intervenant(rs.getString("nomIntervenant"),rs.getString("prenom"),Integer.valueOf(rs.getString("telephone"))),
                                new Intervention()
                        ));
                    }
                     //mise en correspondance de la colonne "salleCol" du tableview
                    //avec la propriété "nom" de la salle de la classe AffichageIntervention
                    salleCol_all.setCellValueFactory(cell->cell.getValue().getNomSalleProperty());
                    //mise en correspondance de la colonne "intervenantCol" du tableview
                    //avec la concaténation "prénom nom" de l'intervenant de la classe AffichageIntervention
                    intervenantCol_all.setCellValueFactory(cell->cell.getValue().getIntervenantProperty());

                    //mise en correspondance de la colonne "contactcol" du tableview
                    //avec la propriété "telephone" de l'intervention de la classe AffichageIntervention
                    contactCol_all.setCellValueFactory(cell->cell.getValue().getContactProperty());

                    //création de la liste qui correspondra au contenu
                    //du tableview
                    donnees_interventions_all=FXCollections.observableList(liste_des_interventions);
                    //mise en correspondance de la liste "donneesIntJour"
                    //avec le tableview "todayInt"
                    toutesInterventions.setItems(donnees_interventions_all);


                    rs.close();
                }
                catch(SQLException se)
                {
                    //exécuté si la requête ne s'est pas bien exécutée
                    se.printStackTrace();
                }
                finally
                {


                    if(stmt!=null)
                    {
                        try
                        {
                            stmt.close();
                        } catch (SQLException ex)
                        {
                            //Logger.getLogger(BarMain.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        catch (SQLException ex)
        {
            //s'il y a une erreur le message suivant s'affiche en ligne de commande
            System.out.println("Erreur de connexion à la bdd. Identifiant ou mot de passe invalide.");
            ex.printStackTrace();
        }
    }

    public void ajoutIntervention(MouseEvent mouseEvent)
    {
        Scene scene = (Scene) ((ImageView)mouseEvent.getSource()).getScene();
        Stage stage = (Stage) scene.getWindow();
        new AjoutIntervention(stage);
    }

}
