package javalife;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.Group;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    final static int chunkSize = 10;
    final static int chunkCount = 100;
    final static int canvasSize = chunkSize*chunkCount;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Group root = new Group();
        Canvas canvas = new Canvas(canvasSize, canvasSize);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        primaryStage.setTitle("Игра жизнь");
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        this.StartGame(gc);
    }


    public static void main(String[] args) {
        launch(args);
    }

    private static void StartGame(GraphicsContext gc){
        DoGameLoop(gc,new Life(chunkCount));
    }

    private static void DoGameLoop (GraphicsContext gc, Life life){
        Main.DrawNextFrap(life.getLifePole(),gc);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                Main.DrawNextFrap(life.getNextGeneration(), gc);
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private static void DrawNextFrap(boolean[][] pole,GraphicsContext gc){
        for(int x =0; x<pole.length; x++) {
            for(int y=0;y<pole[x].length;y++) {
                DrawCell(x,y,pole[x][y],gc);
            }
        }
    }

    private static void DrawCell(int a, int b, boolean value, GraphicsContext gc ){
        if( value ){
            gc.fillRect(a*chunkSize,b*chunkSize,chunkSize,chunkSize);
        } else {
            gc.clearRect(a*chunkSize,b*chunkSize,chunkSize,chunkSize);
        }
    }
}
