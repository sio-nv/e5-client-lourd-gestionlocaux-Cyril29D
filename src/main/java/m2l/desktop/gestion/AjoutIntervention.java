package m2l.desktop.gestion;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AjoutIntervention {
    public AjoutIntervention(Stage fenprinc) {

        Parent root;
        try
        {
            root = FXMLLoader.load(getClass().getResource("/m2l/desktop/gestion/formulaire_nouvelle_intervention.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Ajouter une intervention dans une salle");
            stage.setScene(scene);
            stage.initOwner(fenprinc);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.show();
        }
        catch (IOException ex)
        {
            Logger.getLogger(SalleDescription.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
