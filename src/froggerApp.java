
import java.awt.Graphics2D;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dclop
 */
public class froggerApp extends Application{

    private AnimationTimer timer;
    private boolean gameOver = false;
    private boolean youWin = false;
    private Pane root;
    private int cCount =0;
    private int fLives =3;
    private Node frog;
    private HBox hbox;
    private ArrayList<Node> cars = new ArrayList();
    private Text GO;
     private Parent createContent() {
       //creation frame
         root = new Pane();
       root.setPrefSize(800, 600);
       //end creation frame
       
       frog = initFrog();
       root.getChildren().add(frog);
       
       timer = new AnimationTimer(){
           
           @Override
           public void handle(long now){
               onupdate();
           }

       };
       timer.start();
       //return main content object
       return root;
    }
    
     private void onupdate(){
        //enhanced foor loop
        //uses function operator -> instead of for(...){....}
        for(Node c: cars){
            c.setTranslateX(c.getTranslateX()+ Math.random()* 5 );
            
            // if random large enough spawn new car
        }
        if(Math.random() < 0.075){
                
                if(cCount <=20){
                cars.add(spawnCar());
                }
            }
            checkState();
         
     }
     
     private void restart(){
         if(gameOver){
             fLives = 3;
             frog.setTranslateY(600-45);
             frog.setTranslateX(800/2);
         }
     }
     
     private void checkState(){
         
         if(fLives <=0){
             gameOver = true;
             String win = "GAME OVER!!!";
              hbox = new HBox();
             hbox.setTranslateX(800/2);
             hbox.setTranslateY(600/2);
             GO = new Text(String.valueOf(win));
             GO.setFont(Font.font(50));
             root.getChildren().add(hbox);
             hbox.getChildren().add(GO);
             
             
             root.getChildren().removeAll(cars);
             cars.clear();
           
             timer.stop();
             
         }
         else{
         //checks for collision
         cars.forEach((c)->{
             if(c.getBoundsInParent().intersects(frog.getBoundsInParent())){
                 frog.setTranslateY(600-45);
                 frog.setTranslateX(800/2);
                 fLives --;
             }
              if(c.getTranslateX() > 800){
                  c.setTranslateX(0);
              }
              
         });
         
         //checks for win condition
         if(frog.getTranslateY()<=0){
             youWin = true;
             String win = "YOU WIN";
              hbox = new HBox();
             hbox.setTranslateX(800/2);
             hbox.setTranslateY(600/2);
             GO = new Text(String.valueOf(win));
             GO.setFont(Font.font(50));
             root.getChildren().add(hbox);
             hbox.getChildren().add(GO);
             
             root.getChildren().removeAll(cars);
             cars.clear();
           
             timer.stop();
         }
         }
     }
     
     private Node initFrog(){
         Rectangle rect = new Rectangle(30,30,Color.GREEN);
         rect.setTranslateY(600-30);
         rect.setTranslateX(800/2);
         return rect;
     }
     
     private Node spawnCar(){
         Rectangle rect = new Rectangle(60,38,Color.BLACK);
         
         rect.setTranslateY((int)(Math.random() * 14 ) * 40 );
         cCount ++;
         System.out.println("Cars in Game "+cCount);
         root.getChildren().add(rect);
         
         return rect;
     }
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.setTitle("JavaFrogger");
        stage.setResizable(false);
        
        stage.getScene().setOnKeyPressed(event ->{
            switch(event.getCode()){
                case W:
                   /* if(frog.getTranslateY()-50 <= 0){
                        
                    }
                    else{*/
                    frog.setTranslateY(frog.getTranslateY()-50);
                    //}
                    break;
                case A:
                    if(frog.getTranslateX()-50 <= 0){
                        
                    }
                    else{
                    frog.setTranslateX(frog.getTranslateX()-50);
                    }
                    break;
                case S:
                    if(frog.getTranslateY()+50 >= 600){
                        
                    }
                    else{
                    frog.setTranslateY(frog.getTranslateY()+50);
                    }
                    break;
                case D:
                    if(frog.getTranslateX()+50 >= 800){
                        
                    }
                    else{
                    frog.setTranslateX(frog.getTranslateX()+50);
                    }
                    break;
                case Q:
                    if(gameOver){
                        frog.setTranslateY(600-45);
                        frog.setTranslateX(800/2);
                        timer.start();
                        fLives = 3;
                        cCount = 0;
                        gameOver=false;
                       GO.setVisible(false);
                    }
                    else if(youWin){
                    frog.setTranslateY(600-45);
                        frog.setTranslateX(800/2);
                        timer.start();
                        fLives = 3;
                        cCount = 0;
                        youWin=false;
                       GO.setVisible(false);
                    }
                    break;
                default:
                    break;
               
            }
        });
        
        
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
   
    
}
