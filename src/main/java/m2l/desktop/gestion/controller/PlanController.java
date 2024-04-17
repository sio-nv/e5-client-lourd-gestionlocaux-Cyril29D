/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m2l.desktop.gestion.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import m2l.desktop.gestion.model.Salle;
import m2l.desktop.gestion.SalleDescription;

/**
 *
 * @author nathalie
 */
public class PlanController implements Initializable {

    //membres liés à un élémment graphique dans le FXML
    @FXML
    public Label rezDeChaussee;
    public Label batA;

    @FXML
    public AnchorPane zonePlan;

    //Membres standards
    private Group batimentD,batimentC,batimentB,batimentA, HallAccueil, pins;

    private ArrayList<Salle> listeSalles;

    private int xleft= 50, ytop = 25, ybottom = 250;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        this.listeSalles = new ArrayList<>();
        this.pins = new Group();

        initialiserBatimentD();
        initialiserBatimentC();
        initialiserBatimentB();
        initialiserBatimentA();
        initialiserHallAccueil();

        for(int i=0;i<this.listeSalles.size();i++)
        {

            Circle c = new Circle((int)(this.listeSalles.get(i).r.getX()+this.listeSalles.get(i).r.getWidth()/2),(int)(this.listeSalles.get(i).r.getY()+this.listeSalles.get(i).r.getHeight()/2),10);
            c.setFill(Paint.valueOf("CF2140"));
            c.setId(""+i);

            ajouterGestionEvenement(c);

            this.pins.getChildren().add(c);

            this.listeSalles.get(i).r.setFill(Paint.valueOf("D8EAF5"));
            this.listeSalles.get(i).r.setStroke(Paint.valueOf("5672F5"));
            //this.listeSalles.get(i)

            switch( this.listeSalles.get(i).batiment)
            {
                case "D" :  this.batimentD.getChildren().add(this.listeSalles.get(i).r);break;
                case "C" :  this.batimentC.getChildren().add(this.listeSalles.get(i).r);break;
                case "B" :  this.batimentB.getChildren().add(this.listeSalles.get(i).r);break;
                case "A" :  this.batimentA.getChildren().add(this.listeSalles.get(i).r);break;
                case "H" :  this.HallAccueil.getChildren().add(this.listeSalles.get(i).r);break;

            }
        }

        this.zonePlan.getChildren().add(batimentD);
        this.zonePlan.getChildren().add(batimentC);
        this.zonePlan.getChildren().add(batimentB);
        this.zonePlan.getChildren().add(batimentA);
        this.zonePlan.getChildren().add(HallAccueil);
        this.zonePlan.getChildren().add(pins);

    }

    private void initialiserBatimentD()
    {
        this.batimentD = new Group();

        int x = this.xleft;
        int y = this.ytop;

        int h1 = 75;
        int h2 = 35;
        int h3 = 140;
        int h4 = 50;

        int w = 100;

        this.listeSalles.add(new Salle("Salle Majorelle",new Rectangle(x, y, w, h1),"D"));

        y = y+h1;
        this.listeSalles.add(new Salle("Cuisine",new Rectangle(x, y, w, h2),"D"));

        y = y+h2;
        this.listeSalles.add(new Salle("Salle de restauration et de convivialité",new Rectangle(x, y, w, h3),"D"));

        y = y+h3;
        this.listeSalles.add(new Salle("Reprographie",new Rectangle(x, y, w, h4),"D"));


    }

    private void initialiserBatimentC()
    {
        this.batimentC = new Group();

        int h = (int)this.listeSalles.get(0).r.getHeight();
        int w = 75;

        int x = this.xleft;
        int y1 = this.ybottom +w;
        int y2 = y1+h/2;

        int c = h/2;

        this.listeSalles.add(new Salle("Salle Grüber",new Rectangle(x, y1, w, h),"C"));

        x = x + w;
        this.listeSalles.add(new Salle("Administration MDL",new Rectangle(x, y2, c, c),"C"));

        x = x + c;
        this.listeSalles.add(new Salle("Administration MDL",new Rectangle(x, y2, c, c),"C"));

        x = x + c;
        this.listeSalles.add(new Salle("Administration MDL",new Rectangle(200, y2, c, c),"C"));

    }


    private void initialiserBatimentB()
    {
        this.batimentB = new Group();

        int x = (int)this.listeSalles.get(0).r.getX()+200;//500;
        int y = this.ytop;

        int h1 = 30;
        int h2 = 235;
        int h3 = 30;
        int h4 = h3+10;

        int w = 150;
        int w1 = 115;
        int w2 = w-w1;
        int w3 = 75;
        int w4 = w3+20;

        this.listeSalles.add(new Salle("Salle L'amour",new Rectangle(x, y, w, h1),"B"));
        y = y+h1;

        this.listeSalles.add(new Salle("Amphithéatre",new Rectangle(x, y, w, h2),"B"));

        y = y+h2;
        this.listeSalles.add(new Salle("Salle de service",new Rectangle(x, y, w1, h3),"B"));

        this.listeSalles.add(new Salle("Aucune information",new Rectangle(x+w1, y, w2, h3),"B"));

        this.listeSalles.add(new Salle("Salle Longgy",new Rectangle(x+w1+w2+20, y-20, w3, h4),"B"));

        this.listeSalles.add(new Salle("Salle multimédia",new Rectangle(x+w, y-50-h4, w4, h4+30),"B"));

    }

    private void initialiserBatimentA()
    {
        this.batimentA = new Group();

        int x = (int)this.listeSalles.get(0).r.getX()+370;
        int y = this.ybottom+75;

        int c = 80;

        this.listeSalles.add(new Salle("Salle Daum",new Rectangle(x, y, c, c),"A"));

        x = x+c;
        this.listeSalles.add(new Salle("Salle Gallé",new Rectangle(x, y, c, c),"A"));

        x = x+c;
        this.listeSalles.add(new Salle("Salle Corbin",new Rectangle(x, y, c,c),"A"));

        x = x+c;
        this.listeSalles.add(new Salle("Salle Baccarat",new Rectangle(x, y, c,c),"A"));

    }

    private void initialiserHallAccueil()
    {
        this.HallAccueil = new Group();

        this.listeSalles.add(new Salle("Ascenceur",new Rectangle(this.xleft+240, this.ybottom+125,75, 25),"H"));

    }

    private void ajouterGestionEvenement(Circle c)
    {
        final Tooltip tooltip = new Tooltip();


        //configuration du tooltip
        c.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                String crid = ((Circle)e.getSource()).getId();
                int id = Integer.valueOf(crid);
                String nomsalle= listeSalles.get(id).getNom();

                ((Circle)e.getSource()).setFill(Color.DARKSLATEBLUE);

                //récupération de la fenêtre
                Scene scene = (Scene) ((Circle)e.getSource()).getScene();
                Stage stage = (Stage) scene.getWindow();


                if(tooltip.isShowing()==false)
                {
                    tooltip.setText(nomsalle +"\n");
                    tooltip.setAnchorX(e.getScreenX()+c.getRadius()*1.8);
                    tooltip.setAnchorY(e.getScreenY()+c.getRadius()*2);
                    tooltip.show(stage);
                }
            }
        });


        c.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                tooltip.hide();
                ((Circle)e.getSource()).setFill(Paint.valueOf("CF2140"));
            }
        });

        c.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent e)
            {
                String crid = ((Circle)e.getSource()).getId();
                int id = Integer.valueOf(crid);
                String nomsalle= listeSalles.get(id).getNom();

                //récupération de la fenêtre
                Scene scene = (Scene) ((Circle)e.getSource()).getScene();
                Stage stage = (Stage) scene.getWindow();

                new SalleDescription(stage,nomsalle);
            }
        });
    }

    @FXML
    public void affichePlanBatA()
    {
        this.zonePlan.getChildren().clear();
    }

    @FXML
    public void affichePlanGlobal()
    {
        if(!this.zonePlan.getChildren().contains(batimentD)) this.zonePlan.getChildren().add(batimentD);
        if(!this.zonePlan.getChildren().contains(batimentC)) this.zonePlan.getChildren().add(batimentC);
        if(!this.zonePlan.getChildren().contains(batimentB)) this.zonePlan.getChildren().add(batimentB);
        if(!this.zonePlan.getChildren().contains(batimentA)) this.zonePlan.getChildren().add(batimentA);
        if(!this.zonePlan.getChildren().contains(HallAccueil)) this.zonePlan.getChildren().add(HallAccueil);
        if(!this.zonePlan.getChildren().contains(pins)) this.zonePlan.getChildren().add(pins);
    }

}
