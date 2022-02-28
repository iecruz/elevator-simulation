import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Floor {
    ArrayList<Person> person = new ArrayList<Person>();
    int floor = 0;
    JLabel label;
    
    public Floor(int floor){
        this.floor = floor;
        label = new JLabel("Floor " + floor);
        label.setBounds(150, 100*(6-floor), 500, 100);
        label.setBorder(null);
        label.setOpaque(true);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font("Calibri", Font.BOLD, 21));
        label.setForeground(Color.WHITE);
        if(floor%2 == 0)
            label.setBackground(Color.DARK_GRAY);
        else
            label.setBackground(Color.GRAY);
    }
}
