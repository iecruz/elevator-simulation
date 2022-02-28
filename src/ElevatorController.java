import java.util.*;
import javax.swing.*;

public class ElevatorController extends Thread {
    ArrayList<Person> person;
    Elevator elevator;
    Random random = new Random();
    JLayeredPane pane;
    
    public ElevatorController(Elevator elevator, ArrayList<Person> person, JLayeredPane pane){
        this.person = person;
        this.elevator = elevator;
        this.pane = pane;
    }
    
    public void run(){
        while(true){
            if(elevator.up){
                if(elevator.floor == 6){
                    elevator.up = false;
                    down();
                }
                else
                    up();
            }

            else{
                if(elevator.floor == 1){
                    elevator.up = true;
                    up();
                }
                else
                    down();
            }
            
        }
    }
    
    public void up(){
        elevator.label.setText("" + elevator.floor);
        for(int i = 1; i <= 30; i++){
            try{ Thread.sleep(10); } catch(Exception ex){ System.err.println(ex); }
            elevator.label.setLocation(650*(elevator.id-1), 100*(6-elevator.floor)-i);
            if(i == 50)
                elevator.label.setText("" + (elevator.floor+1));
            for(int p = 0; p < elevator.passenger.size(); p++){
                elevator.passenger.get(p).y = 100*(6-elevator.floor)-i;
                elevator.passenger.get(p).label.setLocation(elevator.passenger.get(p).x, elevator.passenger.get(p).y);
            }
        }
        for(int i = 0; i < elevator.passenger.size(); i++)
            ++elevator.passenger.get(i).currentFloor;
        System.out.println("Elevator " + elevator.id + " | " + "Floor: " + elevator.floor + " | Weight: " + elevator.totalWeight + "kg | Person: " + elevator.totalPerson + " going up");
        ++elevator.floor;
        floorStop(elevator);
    }
    
    public void down(){
        elevator.label.setText("" + elevator.floor);
        for(int i = 1; i <= 30; i++){
            try{ Thread.sleep(10); } catch(Exception ex){ System.err.println(ex); }
            elevator.label.setLocation(650*(elevator.id-1), 100*(6-elevator.floor)+i);
            if(i == 50)
                elevator.label.setText("" + (elevator.floor-1));
            for(int p = 0; p < elevator.passenger.size(); p++){
                elevator.passenger.get(p).y = 100*(6-elevator.floor)+i;
                elevator.passenger.get(p).label.setLocation(elevator.passenger.get(p).x, elevator.passenger.get(p).y);
            }
        }
        for(int i = 0; i < elevator.passenger.size(); i++)
            --elevator.passenger.get(i).currentFloor;
        System.out.println("Elevator " + elevator.id + " | " + "Floor: " + elevator.floor + " | Weight: " + elevator.totalWeight + "kg | Person: " + elevator.totalPerson + " going down");
        --elevator.floor;
        floorStop(elevator);
    }
    
    public void loading(){
        if(elevator.isOpen){
            for(int p = 0; p < person.size(); p++){
                if(elevator.floor != person.get(p).currentFloor)
                    continue;
                if(elevator.id != person.get(p).elevator)
                    continue;
                if(elevator.up){
                    if(person.get(p).destinationFloor > person.get(p).currentFloor)
                        load(person.get(p));
                }
                else{
                    if(person.get(p).destinationFloor < person.get(p).currentFloor)
                        load(person.get(p));
                }
            }
        }
    }
    
    public void unloading(){
        if(elevator.isOpen){
            for(int p = 0; p < elevator.passenger.size(); p++){
                if(elevator.floor == elevator.passenger.get(p).destinationFloor)
                    unload(elevator.passenger.get(p));
            }
        }
    }
    
    public void load(Person passenger){
        elevator.passenger.add(passenger);
        person.remove(passenger);
        if(elevator.totalPerson + 1 > elevator.personCapacity)
            return;
        if(elevator.totalWeight + passenger.weight > elevator.weightCapacity)
            return;
        int x = 0;
        //int x = 150+650*(elevator.id-1);
        if(elevator.id == 1){
            x = 115;
            while(passenger.x-- >= x){
                try{ Thread.sleep(5); } catch(Exception ex){ System.err.println(ex); }
                passenger.label.setLocation(passenger.x, passenger.y);
            }
        }
        else{
            x = 600;
            while(passenger.x++ <= x){
                try{ Thread.sleep(5); } catch(Exception ex){ System.err.println(ex); }
                passenger.label.setLocation(passenger.x, passenger.y);
            }
        }
        elevator.totalWeight += passenger.weight;
        ++elevator.totalPerson;
        pane.remove(passenger.label);
        elevator.stop[passenger.destinationFloor-1] = true;
        passenger.onElevator = true;
        System.out.println("Person " + passenger.id + " rides Elevator " + elevator.id + " at Floor " + elevator.floor);
    }
    
    public void unload(Person passenger){
        pane.add(passenger.label);
        int x = random.nextInt(400)+150;
        if(elevator.id == 1)
            while(passenger.x++ <= x){
                try{ Thread.sleep(5); } catch(Exception ex){ System.err.println(ex); }
                passenger.label.setLocation(passenger.x, passenger.y);
            }
        else
            while(passenger.x-- >= x){
                try{ Thread.sleep(5); } catch(Exception ex){ System.err.println(ex); }
                passenger.label.setLocation(passenger.x, passenger.y);
            }
        elevator.totalWeight -= passenger.weight;
        --elevator.totalPerson;
        elevator.stop[passenger.destinationFloor-1] = false;
        passenger.onElevator = false;
        pane.remove(passenger.label);
        elevator.passenger.remove(passenger);
        System.out.println("Person " + passenger.id + " leaves Elevator " + elevator.id + " at Floor " + elevator.floor);
    }
    
    public void floorStop(Elevator elevator){
        boolean floorEmpty = true;
        for(int p = 0; p < person.size(); p++)
            if(person.get(p).currentFloor == elevator.floor)
                floorEmpty = false;
        
        if(floorEmpty && !elevator.stop[elevator.floor-1])
            return;
        
        System.out.println("Elevator " + elevator.id + " stops at " + elevator.floor);
        elevator.isOpen = true;
        try{ Thread.sleep(500); } catch(Exception ex){ System.err.println(ex); }
        unloading();
        loading();
        try{ Thread.sleep(500); } catch(Exception ex){ System.err.println(ex); }
        elevator.isOpen = false;
        System.out.println("Elevator " + elevator.id + " continues at " + elevator.floor);
    }
}
