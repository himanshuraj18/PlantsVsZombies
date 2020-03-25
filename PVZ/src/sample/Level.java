package sample;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Level implements Serializable {
    private final static long serialVersionUID=1L;
    @FXML
    transient AnchorPane anchor_pane;
    @FXML
    transient Pane menu_pane;
    @FXML
    transient ImageView resume;
    @FXML
    transient ImageView bar;
    @FXML
    transient ImageView peashooter_menu;
    @FXML
    transient ImageView sunflower_menu;
    @FXML
    transient ImageView walnut_menu;
    @FXML
    transient ImageView cherrybomb_menu;
    @FXML
    transient GridPane gridPane;
    @FXML
    transient TextField sun_counter;
    @FXML
    transient ImageView peashooter_lock;
    @FXML
    transient ImageView sunflower_lock;
    @FXML
    transient ImageView walnut_lock;
    @FXML
    transient ImageView cherrybomb_lock;
    @FXML
    transient TextField lost;
//    @FXML
//    transient ImageView bar_zombie;

    Boolean menu_click=false;
    String lastclick=null;
    private String name;
    private int level;
    private int score;
    private int sun_count;
    private long last_update;
    private AnimationTimer timer;
    private double elapsed_time;
    private double time_since_sun_last_added;
    transient private Stage window;
    private ArrayList<SunToken> sun_list;
    private transient ArrayList<ImageView> sun_image_list;
    transient private Parent root;
    private transient HashMap<ImageView, SunToken> sun_map;
    private Random r;
    private Sun_Counter sun_c=new Sun_Counter();
    private ArrayList<Lawnmower> lawnmower_list=new ArrayList<>();
    private int[][] trace=new int[9][5];
    private long curr_time;
    private transient HashMap<ImageView, Bullet> bullet_map;
    private ArrayList<Bullet> bullet_list;
    private ArrayList<Shooter> shooter_list;
    private transient ArrayList<ImageView> bullet_image_list;
    private transient ArrayList<ImageView> shooter_image_list;
    private transient HashMap<ImageView, Shooter> shooter_map;
    private ArrayList<Zombie> zombie_list;
    private transient ArrayList<ImageView> zombie_image_list;
    private transient HashMap<ImageView, Zombie> zombie_map;
    private double time_since_zombie_last_added;
    private double ts_peashooter;
    private boolean ps_lock;
    private double ts_sunflower;
    private boolean sf_lock;
    private double ts_walnut;
    private boolean w_lock;
    private double ts_cherrybomb;
    private boolean cb_lock;
    private ArrayList<SunTokenProducer> sunproducer_list;
    private transient ArrayList<ImageView> sunproducer_image_list;
    private transient HashMap<ImageView, SunTokenProducer> sunproducer_map;
    private ArrayList<Barrier> barrier_list;
    private transient ArrayList<ImageView> barrier_image_list;
    private transient HashMap<ImageView, Barrier> barrier_map;

    private ArrayList<Bomb> bomb_list;
    private transient ArrayList<ImageView> bomb_image_list;
    private transient HashMap<ImageView, Bomb> bomb_map;

    private ArrayList<Plant> plant_list;
    private transient ArrayList<ImageView> plant_image_list;
    private transient HashMap<ImageView, Plant> plant_map;
    private transient ArrayList<ImageView> lawnmower_image_list;
    private transient HashMap<ImageView, Lawnmower> lawnmower_map;
    private boolean[] lm=new boolean[5];

    public Level(){
        score=0;
        sun_count=0;
        elapsed_time=0;
        sun_list= new ArrayList<>();
        sun_image_list=new ArrayList<>();
        sun_map = new HashMap<>();
        r=new Random();
        lawnmower_list.add(new Lawnmower(140,80,0));
        lawnmower_list.add(new Lawnmower(140,177,1));
        lawnmower_list.add(new Lawnmower(140,274,2));
        lawnmower_list.add(new Lawnmower(140,371,3));
        lawnmower_list.add(new Lawnmower(140,468,4));
        bullet_map = new HashMap<>();
        bullet_list = new ArrayList<>();
        shooter_list = new ArrayList<>();
        bullet_image_list = new ArrayList<>();
        shooter_image_list = new ArrayList<>();
        shooter_map = new HashMap<>();
        zombie_list = new ArrayList<>();
        zombie_image_list = new ArrayList<>();
        zombie_map = new HashMap<>();
        ps_lock = false;
        sunproducer_list=new ArrayList<>();
        sunproducer_image_list= new ArrayList<>();
        sunproducer_map = new HashMap<>();
        barrier_list = new ArrayList<>();
        barrier_image_list = new ArrayList<>();
        barrier_map = new HashMap<>();

        bomb_list=new ArrayList<>();
        bomb_image_list = new ArrayList<>();
        bomb_map = new HashMap<>();
        plant_list = new ArrayList<>();
        plant_image_list = new ArrayList<>();
        plant_map = new HashMap<>();
        lawnmower_image_list = new ArrayList<>();
        lawnmower_map = new HashMap<>();
        for(int i=0;i<5;i++){
            lm[i]=false;
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void set_window(Stage window){
        this.window=window;
    }
    public void set_level(int level){
        this.level=level;
    }
    public void set_root(Parent root){ this.root=root;}

    public void menu(){
        if(menu_pane.isVisible()) {
            menu_pane.setVisible(false);
            menu_pane.setDisable(true);
            timer.start();
        }
        else {
            menu_pane.setVisible(true);
            menu_pane.setDisable(false);
            menu_pane.toFront();
            timer.stop();
        }
    }

    public void exit_button(){
        Alert alert = new Alert(Alert.AlertType.NONE, "Do you want to exit the game?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            System.exit(0);
        }
    }

    public void mouse_click(MouseEvent mouseEvent) {
        menu_click=true;
        lastclick=((ImageView) mouseEvent.getSource()).getId();
        System.out.println(mouseEvent.getX()+" "+mouseEvent.getY());
        System.out.println(lastclick);
    }

    public void startGame() throws IOException {
        Scene scene=new Scene(root,950,600);
        window.setScene(scene);
        TranslateTransition translate = new TranslateTransition();
        translate.setByX(-340);
        translate.setDuration(Duration.seconds(2));
        translate.setCycleCount(2);
        translate.setAutoReverse(true);
        translate.setNode(root);
        translate.play();
        prepare_lawnmowers();
        gameloop();
    }

    private void add_sun_from_sky() throws FileNotFoundException {
        int x1, y1, limit;
        x1 = 210 + r.nextInt(640);
        y1 = 0;
        limit = 50 + r.nextInt(470);
        SunToken new_sun = new SunToken(x1, y1,limit);
        sun_list.add(new_sun);
        ImageView sun=new ImageView();
        sun.setImage(new Image(new FileInputStream("src/sample/Sun.png")));
        sun.setLayoutX(x1);
        sun.setLayoutY(y1);
        sun.setFitWidth(75);
        sun.setFitHeight(75);
        sun_map.put(sun, new_sun);
        new_sun.setImview(sun);
        sun.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                if(menu_click){
                    double x=e.getX()-225+sun.getLayoutX();
                    double y=e.getY()-70+sun.getLayoutY();
                    helper(x,y);
                }
                else {
                    sun_map.get(sun).setDead(true);
                    e.consume();
                }
        });
        sun_image_list.add(sun);
        anchor_pane.getChildren().add(sun);

    }

    private void add_zombie(){
        int row,x1,y1;
        row=r.nextInt(5);
        y1=97*(row+1)-30;
        x1=800;
        Zombie new_zombie= new Zombie(x1,y1,-1,30+level*5,90 + level*10,System.nanoTime()-10000000000L, row, 2.0);
        zombie_list.add(new_zombie);
        ImageView zombie_image = new ImageView();
        try {
            zombie_image.setImage(new Image(new FileInputStream("src/sample/img/conehead_zombie_moving.gif")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        zombie_image.setLayoutX(x1);
        zombie_image.setLayoutY(y1);
        zombie_image.setFitWidth(100);
        zombie_image.setFitHeight(100);
        zombie_map.put(zombie_image, new_zombie);
        zombie_image_list.add(zombie_image);
        anchor_pane.getChildren().add(zombie_image);
    }

    private void move(){
        for(int i=0;i<lawnmower_list.size();i++){
            if(lawnmower_list.get(i).getActive()==true){
                Lawnmower lawnmower=lawnmower_list.get(i);
                lawnmower.act();
                int x = lawnmower.getX();
                for(int j=0;j<zombie_list.size();j++){
                    int dist=zombie_list.get(j).getX()-x;
                    if(lawnmower.getRow()==zombie_list.get(j).getRow() && dist>=0 && dist<=15){
                        zombie_list.get(j).setDead(true);
                    }
                }
            }

        }
        for(int i=0 ;i <zombie_list.size();i++){
            if(zombie_list.get(i).getX()<=165){
                if(lm[zombie_list.get(i).getRow()]==false){
                    lm[zombie_list.get(i).getRow()]=true;
                    lawnmower_list.get(zombie_list.get(i).getRow()).setActive(true);
                    return;
                }
            }
            if(zombie_list.get(i).getX()<=150){
                System.out.println("Game lost");
                lost.setText("YOU LOST");
                lost.setVisible(true);
                timer.stop();
            }
        }
        double tempp;
        for(int i = 0;i<sun_list.size();i++){
            sun_list.get(i).act();
        }
        for(int i= 0 ;i<zombie_list.size();i++){
            int x,y,flag=1,flag1=0;
            x=zombie_list.get(i).getX();
            y=zombie_list.get(i).getY();
            tempp=(System.nanoTime()-zombie_list.get(i).getLastattack())/1000000000.0;
            if(tempp>zombie_list.get(i).get_attack_waiting_time()){
                flag1=1;
            }
            Plant nearest=null;
            for(int j=0;j<shooter_list.size();j++){
                int dist=x-shooter_list.get(j).getX();
                if(zombie_list.get(i).getRow()==shooter_list.get(j).getRow() && dist>=0){
                    if(dist>=0 && dist<=15){
                        if(flag1==1) {
                            shooter_list.get(j).decreaseHealth(zombie_list.get(i).getDamage());
                        }
                        flag=0;
                    }
                }
            }
            for(int j=0;j<sunproducer_list.size();j++){
                int dist=x-sunproducer_list.get(j).getX();
                if(dist>=0 && zombie_list.get(i).getRow()==sunproducer_list.get(j).getRow()){
                    if(dist>=0 && dist<=15){
                        if(flag1==1) {
                            sunproducer_list.get(j).decreaseHealth(zombie_list.get(i).getDamage());
                        }
                        flag=0;
                    }
                }
            }

            for(int j=0;j<barrier_list.size();j++){
                int dist=x-barrier_list.get(j).getX();
                if(dist>=0 && zombie_list.get(i).getRow()==barrier_list.get(j).getRow()){
                    if(dist>=0 && dist<=15){
                        if(flag1==1) {
                            barrier_list.get(j).decreaseHealth(zombie_list.get(i).getDamage());
                        }
                        flag=0;
                    }
                }
            }

            for(int j=0;j<bomb_list.size();j++){
                int dist=x-bomb_list.get(j).getX();
                if(dist>=0 && zombie_list.get(i).getRow()==bomb_list.get(j).getRow()){
                    if(dist>=0 && dist<=15){
                        if(flag1==1) {
                            bomb_list.get(j).decreaseHealth(zombie_list.get(i).getDamage());
                        }
                        flag=0;
                    }
                }
            }

            if(flag==1) {
                zombie_list.get(i).act();
            }
            if((1-flag)*flag1==1){
                zombie_list.get(i).setLastattack(System.nanoTime());
            }
        }
        for(int i= 0 ;i<bullet_list.size();i++){
            int minn=-1,x,y;
            x=bullet_list.get(i).getX();
            y=bullet_list.get(i).getY();
            Zombie nearest=null;
            for(int j=0;j<zombie_list.size();j++){
                int dist=x-zombie_list.get(j).getX();
                if(bullet_list.get(i).getRow()==zombie_list.get(j).getRow() && dist>=0){
                    if(minn==-1 || dist<minn){
                        minn=dist;
                        nearest = zombie_list.get(j);
                    }
                }
            }

            if(minn<=-1 || minn>15) {
                bullet_list.get(i).act();
            }
            else{
                bullet_list.get(i).setDead(true);
                nearest.decreaseHealth(bullet_list.get(i).getDamage());
            }
        }
    }

    private void update(){
        for(int i = 0;i<sun_image_list.size();i++){
            sun_image_list.get(i).setLayoutX(sun_list.get(i).getX());
            sun_image_list.get(i).setLayoutY(sun_list.get(i).getY());
        }
        for(int i = 0;i<zombie_image_list.size();i++){
            zombie_image_list.get(i).setLayoutX(zombie_list.get(i).getX());
            zombie_image_list.get(i).setLayoutY(zombie_list.get(i).getY());
        }
        for(int i = 0;i<bullet_image_list.size();i++){;
            bullet_image_list.get(i).setLayoutX(bullet_list.get(i).getX());
            bullet_image_list.get(i).setLayoutY(bullet_list.get(i).getY());
        }
        for(int i = 0;i<lawnmower_image_list.size();i++){;
            lawnmower_image_list.get(i).setLayoutX(lawnmower_list.get(i).getX());
            lawnmower_image_list.get(i).setLayoutY(lawnmower_list.get(i).getY());
        }

    }

    private void check_and_remove(){
        SunToken sun_object;
        ImageView sun_image;
        for(int i=0;i<sun_image_list.size();i++){
            sun_image=sun_image_list.get(i);
            sun_object=sun_map.get(sun_image);
            if(sun_object.isDead() == true){
                anchor_pane.getChildren().remove(sun_image);
                sun_c.setCount(sun_object.getValue());
                sun_counter.setText(""+sun_c.getCount());
                sun_map.remove(sun_image);
                sun_list.remove(sun_object);
                sun_image_list.remove(sun_image);
            }
        }

        Zombie zombie_object;
        ImageView zombie_image;
        for(int i=0;i<zombie_image_list.size();i++){
            zombie_image=zombie_image_list.get(i);
            zombie_object=zombie_map.get(zombie_image);
            if(zombie_object.isDead() == true){
                anchor_pane.getChildren().remove(zombie_image);
                zombie_map.remove(zombie_image);
                zombie_list.remove(zombie_object);
                zombie_image_list.remove(zombie_image);
            }
        }

        Bullet bullet_object;
        ImageView bullet_image;
        for(int i=0;i<bullet_image_list.size();i++){
            bullet_image=bullet_image_list.get(i);
            bullet_object=bullet_map.get(bullet_image);
            if(bullet_object.isDead() == true){
                anchor_pane.getChildren().remove(bullet_image);
                bullet_map.remove(bullet_image);
                bullet_list.remove(bullet_object);
                bullet_image_list.remove(bullet_image);
            }
        }

        Plant plant_object;
        ImageView plant_image;
        for(int i=0;i<plant_image_list.size();i++){
            plant_image=plant_image_list.get(i);
            plant_object=plant_map.get(plant_image);
            if(plant_object.isDead() == true){
                gridPane.getChildren().remove(plant_image);
                trace[plant_object.getCol()][plant_object.getRow()]=0;
                if(plant_object.getClass() == Peashooter.class){
                    shooter_map.remove(plant_image);
                    shooter_list.remove((Shooter)plant_object);
                    shooter_image_list.remove(plant_image);
                }
                if(plant_object.getClass() == SunFlower.class){
                    sunproducer_map.remove(plant_image);
                    sunproducer_list.remove((SunTokenProducer) plant_object);
                    sunproducer_image_list.remove(plant_image);
                }
                if(plant_object.getClass() == Walnut.class){
                    barrier_map.remove(plant_image);
                    barrier_list.remove((Barrier) plant_object);
                    barrier_image_list.remove(plant_image);
                }
                if(plant_object.getClass() == Cherrybomb.class){
                    bomb_map.remove(plant_image);
                    bomb_list.remove((Bomb) plant_object);
                    bomb_image_list.remove(plant_image);
                }
                plant_map.remove(plant_image);
                plant_list.remove(plant_object);
                plant_image_list.remove(plant_image);
            }
        }


    }

    private void plant_action(){
        for(int i=0;i<shooter_list.size();i++){
            Shooter shooter = shooter_list.get(i);
            long time_now= System.nanoTime();
            Bullet bullet = null;
            if((time_now - shooter.getLastbulletfired())/1000000000.0 > shooter.getBulletwaitingtime()){
                shooter.setLastbulletfired(time_now);
                if(shooter.getClass() == Peashooter.class){
                    bullet = new Pea(shooter.getX()+30, shooter.getY()+15, shooter.getRow());
                    bullet_list.add(bullet);
                    ImageView bullet_image = new ImageView();
                    try {
                        bullet_image.setImage(new Image(new FileInputStream("src/sample/img/pea.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    bullet_image.setLayoutX(bullet.getX());
                    bullet_image.setLayoutY(bullet.getY());
                    bullet_image.setFitWidth(25);
                    bullet_image.setFitHeight(25);
                    bullet_map.put(bullet_image, bullet);
                    bullet_image_list.add(bullet_image);
                    anchor_pane.getChildren().add(bullet_image);
                }
            }
        }
        for(int i=0;i<sunproducer_list.size();i++){
            SunTokenProducer sunproducer=sunproducer_list.get(i);
            long time_now= System.nanoTime();
            SunToken sun_object = null;
            if((time_now - sunproducer.getLasttokenadded())/1000000000.0 > sunproducer.getTokenwaitingtime()){
                sunproducer.setLasttokenadded(time_now);
                if(sunproducer.getClass() == SunFlower.class){
                    int x;
                    x=r.nextInt(100);
                    sun_object = new SunToken(sunproducer.getX()-40, sunproducer.getY()-60);
                    sun_list.add(sun_object);

                    ImageView sun_image = new ImageView();
                    try {
                        sun_image.setImage(new Image(new FileInputStream("src/sample/Sun.png")));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    sun_image.setLayoutX(sunproducer.getX());
                    sun_image.setLayoutY(sunproducer.getY());
                    sun_image.setFitWidth(100);
                    sun_image.setFitHeight(100);
                    sun_image.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
                        if(menu_click){
                            double x1=e.getX()-225+sun_image.getLayoutX();
                            double y1=e.getY()-70+sun_image.getLayoutY();
                            helper(x1,y1);
                        }
                        else {
                            sun_map.get(sun_image).setDead(true);
                            e.consume();
                        }
                    });
                    sun_map.put(sun_image, sun_object);
                    sun_image_list.add(sun_image);
                    anchor_pane.getChildren().add(sun_image);
                }
            }

        }

        for(int i=0;i<bomb_list.size();i++){
            Bomb bomb=bomb_list.get(i);
            long time_now= System.nanoTime();
            if((time_now - bomb.get_last_used())/1000000000.0 >5.0) {
                bomb.setDead(true);
                for(int j=0;j<zombie_list.size();j++){
                    if(dist(bomb,zombie_list.get(j))<150.0){
                        zombie_list.get(j).setDead(true);
                    }
                }
            }

        }

    }

    private void gameloop(){
        timer = new AnimationTimer(){
            @Override
            public void handle(long now){
                elapsed_time+= ((now-last_update)/1000000000.0);
                double tempp;
                tempp=((now-last_update)/1000000000.0);
                time_since_sun_last_added+=tempp;
                time_since_zombie_last_added+=tempp;
                ts_peashooter+=tempp;
                ts_cherrybomb+=tempp;
                ts_sunflower+=tempp;
                ts_walnut+=tempp;
                last_update=now;
                bar.setLayoutX(719-(155/90)*elapsed_time);
                if(elapsed_time>=90.0){
                    lost.setVisible(true);
                    lost.setText("YOU WON THIS LEVEL");
                    timer.stop();
                }
                if(time_since_sun_last_added>2.0 + level*1.0){
                    time_since_sun_last_added=0.0;
                    try {
                        add_sun_from_sky();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if(time_since_zombie_last_added>9.5-level*1){
                    time_since_zombie_last_added=0.0;
                    add_zombie();
                }
                if(ps_lock==true && ts_peashooter>10.0){
                    ps_lock = false;
                    peashooter_lock.setVisible(false);

                }
                if(sf_lock==true && ts_sunflower>10.0){
                    sf_lock = false;
                    sunflower_lock.setVisible(false);

                }
                if(w_lock==true && ts_walnut>10.0){
                    w_lock = false;
                    walnut_lock.setVisible(false);

                }
                if(cb_lock==true && ts_cherrybomb>15.0){
                    cb_lock = false;
                    cherrybomb_lock.setVisible(false);

                }
                plant_action();
                move();
                update();
                check_and_remove();
            }
        };
        last_update = System.nanoTime();
        time_since_sun_last_added=0.0;
        time_since_zombie_last_added=0.0;
        ts_peashooter=0.0;
        ts_cherrybomb=0.0;
        ts_sunflower=0.0;
        ts_walnut=0.0;

        timer.start();
    }

    public Boolean addnewplant(int x, int y) throws FileNotFoundException {
        String id=lastclick;
        int _x=161+76*(x+1);
        int _y=97*y+80;
        if(id.equals("peashooter_menu")){
            curr_time = System.currentTimeMillis();
            if(sun_c.getCount()<100){
                return false;
            }
            else if(curr_time-Peashooter.getLastadded()<Peashooter.getWaitingtime()*1000){
                return false;
            }
            else {
                Peashooter p=new Peashooter(_x, _y, y,x);
                sun_c.setCount(-1*p.getPrice());
                sun_counter.setText(""+sun_c.getCount());
                ImageView iv = new ImageView();
                iv.setImage(new Image(new FileInputStream("src/sample/img/PeaShooter.gif")));
                shooter_list.add(p);
                plant_list.add(p);
                shooter_image_list.add(iv);
                plant_image_list.add(iv);
                shooter_map.put(iv,p);
                plant_map.put(iv,p);
                gridPane.add(iv, x, y);
                Peashooter.setLastadded(curr_time);
                ps_lock=true;
                ts_peashooter=0.0;
                peashooter_lock.setVisible(true);
                return true;
            }
        }
        else if(id.equals("sunflower_menu")){
            curr_time = System.currentTimeMillis();
            if(sun_c.getCount()<50){
                return false;
            }
            else if(curr_time-SunFlower.getLastadded()<SunFlower.getWaitingtime()*1000){
                return false;
            }
            else {
                SunFlower s=new SunFlower(_x, _y, y,x);
                s.setLasttokenadded(System.nanoTime());
                sun_c.setCount(-1*s.getPrice());
                sun_counter.setText(""+sun_c.getCount());
                ImageView iv = new ImageView();
                iv.setImage(new Image(new FileInputStream("src/sample/img/Sunflower.gif")));
                sunproducer_list.add(s);
                plant_list.add(s);
                sunproducer_image_list.add(iv);
                plant_image_list.add(iv);
                sunproducer_map.put(iv,s);
                plant_map.put(iv,s);
                gridPane.add(iv, x, y);
                sf_lock=true;
                ts_sunflower=0.0;
                sunflower_lock.setVisible(true);
                SunFlower.setLastadded(curr_time);
                return true;
            }
        }
        else if(id.equals("walnut_menu")){
            curr_time = System.currentTimeMillis();
            if(sun_c.getCount()<50){
                return false;
            }
            else if(curr_time-Walnut.getLastadded()<Walnut.getWaitingtime()*1000){
                return false;
            }
            else {
                Walnut w=new Walnut(_x, _y, y,x);
                sun_c.setCount(-1*w.getPrice());
                sun_counter.setText(""+sun_c.getCount());
                ImageView iv = new ImageView();
                iv.setImage(new Image(new FileInputStream("src/sample/img/Wall-nut1.png")));
                barrier_list.add(w);
                plant_list.add(w);
                barrier_image_list.add(iv);
                plant_image_list.add(iv);
                barrier_map.put(iv,w);
                plant_map.put(iv,w);
                gridPane.add(iv, x, y);
                Walnut.setLastadded(curr_time);
                w_lock=true;
                ts_walnut=0.0;
                walnut_lock.setVisible(true);
                return true;
            }
        }
        else if(id.equals("cherrybomb_menu")){
            curr_time = System.currentTimeMillis();
            if(sun_c.getCount()<150){
                return false;
            }
            else if(curr_time-Cherrybomb.getLastadded()<Cherrybomb.getWaitingtime()*1000){
                return false;
            }
            else{
                Cherrybomb c=new Cherrybomb(_x, _y, y,x, System.nanoTime());
                sun_c.setCount(-1*c.getPrice());
                sun_counter.setText(""+sun_c.getCount());
                ImageView iv=new ImageView();
                iv.setImage(new Image(new FileInputStream("src/sample/img/CherryBomb.png")));
                bomb_list.add(c);
                plant_list.add(c);
                bomb_image_list.add(iv);
                plant_image_list.add(iv);
                bomb_map.put(iv,c);
                plant_map.put(iv,c);
                gridPane.add(iv,x,y);
                cb_lock=true;
                ts_cherrybomb=0.0;
                cherrybomb_lock.setVisible(true);
                Cherrybomb.setLastadded(curr_time);
                return true;
            }
        }
        return false;
    }

    public void drop_here(MouseEvent event){
        if(!menu_click){
            return;
        }
        double x=event.getX();
        double y=event.getY();
        helper(x,y);
    }

    public void helper(double x, double y) {
        if(!menu_click)
            return;
        System.out.println(x+" - "+y);
        int row_index=0, col_index=0;
        ObservableList<ColumnConstraints> Width = gridPane.getColumnConstraints();
        ObservableList<RowConstraints> Height = gridPane.getRowConstraints();
        for(ColumnConstraints ignored : Width){
            if(x-76>0){
                x-=76;
                col_index+=1;
            }
            else{
                break;
            }
        }

        for(RowConstraints ignored : Height){
            if(y-97>0){
                y-=97;
                row_index+=1;
            }
            else{
                break;
            }
        }
        System.out.println(row_index+" "+col_index);
        if(trace[col_index][row_index]==1){
            menu_click=false;
            lastclick=null;
            return;
        }
        try {
            Boolean bb=addnewplant(col_index,row_index);
            if(bb){
                trace[col_index][row_index]=1;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        menu_click=false;
        lastclick=null;
    }

    public void prepare_lawnmowers(){
        for(int i=0;i<5;i++){
            ImageView tempp = new ImageView();
            try {
                tempp.setImage(new Image(new FileInputStream("src/sample/img/Lawnmover.png")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            tempp.setLayoutX(lawnmower_list.get(i).getX());
            tempp.setLayoutY(lawnmower_list.get(i).getY());
            tempp.setFitHeight(100);
            tempp.setFitWidth(100);
            anchor_pane.getChildren().add(tempp);
            lawnmower_image_list.add(tempp);
            lawnmower_map.put(tempp ,lawnmower_list.get(i));
        }
    }

    public void save(ActionEvent actionEvent) throws IOException {
        Login.l.serialize();
    }

    public double dist(Actor a, Actor b){
        return Math.sqrt((a.getX()-b.getX())*(a.getX()-b.getX()) + (a.getY()-b.getY())*(a.getY()-b.getY()));
    }
}