import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Person {
    Random random = new Random();
    int id = 0;
    int weight = 0;
    int currentFloor = 0;
    int destinationFloor = 0;
    int x = 0;
    int y = 0;
    int elevator = random.nextInt(2) + 1;
    boolean onElevator = false;
    JLabel label;
    
    public Person(int id){
        this.id = id;
        weight = random.nextInt(50)+70;
        currentFloor = random.nextInt(4) + 2;
        do{ destinationFloor = random.nextInt(6) + 1; }while(currentFloor == destinationFloor);
        
        x = random.nextInt(400)+150;
        y = 100*(6-currentFloor);
        
        label = new JLabel(new ImageIcon(new ImageIcon("img/person.png").getImage().getScaledInstance(80,100,java.awt.Image.SCALE_SMOOTH)));
        label.setBounds(x, y, 80, 100);
        label.setBorder(null);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
