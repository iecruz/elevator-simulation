import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Elevator {
    int id = 0;
    int totalWeight = 0;
    int totalPerson = 0;
    int weightCapacity = 0;
    int personCapacity = 0;
    int floor = 0;
    boolean stop[] = new boolean[6];
    boolean up = true;
    boolean isOpen = false;
    JLabel label;
    ArrayList<Person> passenger;
    
    public Elevator(int id){
        this.id = id;
        totalWeight = 0;
        totalPerson = 0;
        weightCapacity = 1000;
        personCapacity = 16;
        floor = id;
        isOpen = true;
        for(int i = 0; i < 6; i++)
            stop[i] = false;
        up = true;
        
        passenger = new ArrayList<Person>();
        
        label = new JLabel("" + floor);
        label.setBounds(650*(id-1), 100*(6-floor), 150, 100);
        label.setBorder(null);
        label.setOpaque(true);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Calibri", Font.BOLD, 42));
        label.setForeground(Color.WHITE);
        label.setBackground(Color.BLACK);
    }
}
