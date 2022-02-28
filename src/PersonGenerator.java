import java.util.*;
import java.awt.*;
import javax.swing.*;

public class PersonGenerator extends Thread {
    ArrayList<Person> person;
    Random random = new Random();
    JLayeredPane pane;
    int timeCreate = 0;
    int ctr = 0;
    
    public PersonGenerator(ArrayList<Person> person, JLayeredPane pane){
        this.person = person;
        this.pane = pane;
    }
    
    public void run(){
        while(true){
            if(person.size() < 20){
                timeCreate = random.nextInt(3) * 1000;
                try {Thread.sleep(timeCreate);}
                catch(Exception e){System.err.println(e);}
                create();
            }
        }
    }
    
    public void create(){
        ++ctr;
        person.add(new Person(ctr));
        pane.add(person.get(person.size()-1).label, 3, 0);
        System.out.println("Person " + person.get(person.size()-1).id + " created (Weight: " + person.get(person.size()-1).weight + "kg | Current Floor: " + person.get(person.size()-1).currentFloor + " | Destination Floor: " + person.get(person.size()-1).destinationFloor + ")");
    }
}
