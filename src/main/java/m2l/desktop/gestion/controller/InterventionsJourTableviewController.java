package m2l.desktop.gestion.controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import m2l.desktop.gestion.model.AffichageIntervention;

/**
 *
 * @author nathalie
 */
public class InterventionsJourTableviewController implements Initializable{

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
    private List <AffichageIntervention> liste_des_interventions_du_jour = new ArrayList<AffichageIntervention>();


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
        Date date_du_jour = new Date();
        String sd=formatter.format(date_du_jour);
         dateJour.setText(sd);
        configureOngletInterventionsJour();


    }

    private void configureOngletInterventionsJour()
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

                    System.out.println("Chargement des salles du jour...");
                    //création d'un "Statement" pour exécuter la requête
                    stmt = connexion.createStatement();

               




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


}
