import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Main extends Thread {
    static PersonGenerator personGnrt;
    static ElevatorController elevatorCtrl[] = new ElevatorController[2];
    
    static ArrayList<Person> person = new ArrayList<Person>();
    static Elevator elevator[] = new Elevator[2];
    static Floor floor[] = new Floor[6];
    
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    
    static JFrame frame = new JFrame("Elevator Simulator");
    static JLayeredPane pane = new JLayeredPane();
    int ctr = 0;
    
    Random random = new Random();
    int timeCreate = 0;
    
    public Main(){
        
        frame.setLayout(null);
        frame.setSize(800,600);
        frame.setLocation((dim.width/2) - frame.getSize().width/2, (dim.height/2) - frame.getSize().height/2);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(52,52,52));
        
        pane.setLayout(null);
        pane.setSize(800,600);
        pane.setLocation(0,0);
        frame.add(pane);
        
        /*int ctr = 0;
        while(ctr++ < 30){
            if(person.size() < 30){
                //ctr++;
                person.add(new Person(ctr));
                frame.add(person.get(ctr-1).label);
            }
        }*/
        
        for(int f = 0; f < 6; f++){
            System.out.println("Floor " + f+1 + " creating...");
            floor[f] = new Floor(f+1);
            pane.add(floor[f].label, 1, 0);
        }
        
        for(int e = 0; e < 2; e++){
            System.out.println("Elevator " + e+1 + " creating...");
            elevator[e] = new Elevator(e+1);
            pane.add(elevator[e].label, 2, 0);
        }
    }
    
    public void run(){
        while(true){
            
            /*if(person.size() < 30){
                timeCreate = random.nextInt(3) * 1000;
                try {Thread.sleep(timeCreate);}
                catch(Exception e){System.err.println(e);}
                create();
            }*/
        }
    }
    
    /*public void create(){
        ++ctr;
        person.add(new Person(ctr));
        pane.add(person.get(person.size()-1).label, 3, 0);
        System.out.println("Person " + person.get(person.size()-1).id + " created (Weight: " + person.get(person.size()-1).weight + "kg)");
    }*/
    
    public static void main(String args[]){
        Main main = new Main();
        
        main.start();
        
        frame.setVisible(true);
        
        for(int i = 0; i < 2; i ++){
            elevatorCtrl[i] = new ElevatorController(elevator[i], person, pane);
            elevatorCtrl[i].start();
            elevatorCtrl[i].setPriority(MAX_PRIORITY-i-1);
        }
        
        personGnrt = new PersonGenerator(person, pane);
        personGnrt.start();
    }
}
