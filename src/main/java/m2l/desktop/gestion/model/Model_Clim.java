package m2l.desktop.gestion.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Model_Clim {

    /**
     * Gestion de la connexion à la base de données gestsalle
     */
    private static Connection connexion;
    private static Statement stmt;

    private static ObservableList<Climatiseur> climatiseurs  = FXCollections.observableArrayList();

    public static ObservableList<Climatiseur> getClimatiseurs() {
        return climatiseurs;

    }

    public static void connect_to_databse()
    {
        try
        {
            //configuration de la base de données
            String url1 = "jdbc:mysql://localhost:3337/" +
                    "sitem2l?&useJDBCCompliantTimezoneShift=true" +
                    "&useLegacyDatetimeCode=false&serverTimezone=UTC";
            //utilisateur pour connexion à la BDD
            String user = "gestuser";
            //mot de passe pour connexion à la BDD
            String password = "gestpass";

            //établissement de la connexion à la base de données
            connexion = DriverManager.getConnection(url1, user, password);

            if (connexion != null)
            {
                System.out.println("La connexion est effective. Il faut faire des requêtes !");
            }
        }
        catch(SQLException ex)
        {
            //s'il y a une erreur le message suivant s'affiche en ligne de commande
            System.out.println("Erreur de connexion à la bdd. Identifiant ou mot de passe invalide.");
            ex.printStackTrace();
        }
    }


    public static void selectClimatiseurs()
    {
        try
        {

            System.out.println("Chargement des climatisuers...");
            //création d'un objet "Statement" qui permettra d'exécuter la requête
            stmt = connexion.createStatement();

            //définition de la requête
            String sql = "SELECT climatiseurs.id,marque,modele, puissance, surfaceMin,surfaceMax,nom, batiment from climatiseurs JOIN salles on salles.numeroSalle=numSalle";
            System.out.println("requête :"+sql);
            //exécution de la requête
            ResultSet rs = stmt.executeQuery(sql);

            //la liste est vidée
            climatiseurs.clear();

            //parcours des enregistrements résultats,
            //création de nouveaux objets "climatiseurs" et
            //ajout de cet objet dans la liste
            while(rs.next())
            {
                String mar = rs.getString("marque");
                String mod = rs.getString("modele");
                int pui = rs.getInt("puissance");
                int smi = rs.getInt("surfaceMin");
                int sma = rs.getInt("surfaceMax");
                int id = rs.getInt("id");
                String batiment = rs.getString("batiment");
                String nomSalle = rs.getString("nom");

                Climatiseur c = new Climatiseur(id,mar,mod,pui,smi,sma);
                c.setBatiment(batiment);
                c.setNomSalle(nomSalle);
                climatiseurs.add(c);
            }

            rs.close();
        }
        catch(SQLException se)
        {
            //exécuté si la requête ne s'est pas bien exécutée
            se.printStackTrace();
            System.err.println("La requête c'est mal déroulée.");
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
                }
            }
        }
    }


    public static void insertClimatiseur(Climatiseur c)
    {
        try
        {

            System.out.println("Insertion du climatiseur...");
            //création d'un objet "Statement" qui permettra d'exécuter la requête
            stmt = connexion.createStatement();

            String marque = c.getMarque();
            String modele = c.getModele();
            int puissance = c.getPuissance();
            int surfMin = c.getSurface_min();
            int surfMax = c.getSurface_max();
            int num = c.getNumalle();


            //définition de la requête
            String sql = "insert into climatiseurs values (null,\""+marque+"\",\""+modele+"\", "+puissance+","+surfMin+","+surfMax+","+num+")";
            System.out.println("requête :"+sql);
            //exécution de la requête
            Boolean res = stmt.execute(sql);

        }
        catch(SQLException se)
        {
            //exécuté si la requête ne s'est pas bien exécutée
            se.printStackTrace();
            System.err.println("L'insertion c'est mal déroulée.");
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
                }
            }
        }
    }


    public static void deleteClimatiseur(int id)
    {
        try
        {

            System.out.println("Suprression du climatiseur...");
            //création d'un objet "Statement" qui permettra d'exécuter la requête
            stmt = connexion.createStatement();

            //définition de la requête
            String sql = "delete from climatiseurs where id = "+id;
            System.out.println("requête :"+sql);
            //exécution de la requête
            Boolean res = stmt.execute(sql);

        }
        catch(SQLException se)
        {
            //exécuté si la requête ne s'est pas bien exécutée
            se.printStackTrace();
            System.err.println("L'insertion c'est mal déroulée.");
        }
        finally
        {
            /*if(stmt!=null)
            {
                try
                {
                    stmt.close();
                } catch (SQLException ex)
                {
                }
            }*/
        }
    }

    public static void updateClimatiseur(String attr,int id, String val)
    {
        try
        {

            System.out.println("Suprression du climatiseur...");
            //création d'un objet "Statement" qui permettra d'exécuter la requête
            stmt = connexion.createStatement();

            //définition de la requête
            String sql = "update climatiseurs  set "+attr+ "=  \""+val+"\""+"where id = "+id;
            System.out.println("requête :"+sql);
            //exécution de la requête
            Boolean res = stmt.execute(sql);

        }
        catch(SQLException se)
        {
            //exécuté si la requête ne s'est pas bien exécutée
            se.printStackTrace();
            System.err.println("La modification c'est mal déroulée.");
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
                }
            }
        }
    }
}
