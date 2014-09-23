/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package cp.rach.poltergeist;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 *
 * @author rcolley
 */
public class JavaFXApplication1 extends Application {
    
    int mWidth = 500;
    int mHeight = 500;
    StackPane root;
    GraphicsContext gc;
    Canvas c;
    Paint col;
    Map<Integer,Map<Integer,String>> tMap;
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        root = new StackPane();
        root.getChildren().add(btn);
        c = new Canvas(mWidth, mHeight);
        root.getChildren().add(c);
        gc = c.getGraphicsContext2D();
        
        
        startDraw();
        
        Scene scene = new Scene(root, mWidth, mHeight);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void startDraw() {
        
        
        AnimationTimer timer = new AnimationTimer() {
            
            public void handle(long now) {
                
                Iterator<Map.Entry<Integer,Map<Integer,String>>> i = makeMap().entrySet().iterator();
                while (i.hasNext()) {
                    Map.Entry<Integer,Map<Integer,String>> e = i.next();
                    Map<Integer,String> cm = e.getValue();
                    int currentY = e.getKey();
                    
                    Iterator<Map.Entry<Integer,String>> ii = cm.entrySet().iterator();
                    while(ii.hasNext()) {
                        Map.Entry<Integer,String> ee = ii.next();
                        int currentX = ee.getKey();
                        String col = ee.getValue();
                        Paint p = Paint.valueOf(col);
                        gc.setFill(p);
                        gc.fillOval(currentX, currentY, 10, 10);
                    }
                    
                    
                }
                
            }
        };
        timer.start();
        
    }
    /**
     * The main() method is ignored in correctly deployed JavaFX
     * application. main() serves only as fallback in case the application
     * can not be launched through deployment artifacts, e.g., in IDEs with
     * limited FX support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
        
        
        
    }
    
    public Map<Integer,Map<Integer,String>> makeMap() {
        int parentMapSize = mHeight;
        int childMapSize = mWidth;
        tMap = new TreeMap<Integer,Map<Integer,String>>();
        
        for (int parentMap = 0; parentMap < parentMapSize; parentMap ++) {
            Map<Integer,String> m = new TreeMap<Integer,String>();
            
            for (int childMap = 0; childMap < childMapSize; childMap ++) {
                m.put(childMap, makeSimpleRandomColor());
            }
            
            tMap.put(parentMap, m);
        }
        return tMap;
    }
    
    public String makeSimpleRandomColor() {
        Random r = new Random();
        int num = r.nextInt(2)+1;
        if (num%2 == 0) return "#000000";
        return "#FFFFFF";
    }
    
    public int getRandomNum() {
        Random r  = new Random();
        int i = r.nextInt(5000) + 1;
        return i;
    }
    
    //Iterator<Map.Entry<Integer,Map<Integer,String>>> i = m.entrySet().iterator();
    
    
}
