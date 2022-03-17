package Week02;

import java.util.LinkedList;
import java.util.concurrent.CountDownLatch;
import java.util.Scanner;

public class exampleTrainusingLatch {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(7);
        
        new Thread(new Train(latch)).start();
        
        for (int i=0; i<7; i++){
            new Thread(new Passenger(latch)).start();
            try{
                Thread.sleep(1000);
            }
            catch(Exception e) {}
        }
        
        CountDownLatch latch1 = new CountDownLatch(5);
        
        new Thread(new Train(latch1)).start();
        
        for (int i=0; i<5; i++){
            new Thread(new Passenger(latch1)).start();
            try{
                Thread.sleep(1000);
            }
            catch(Exception e) {}
        }
        
     
    }
}





class Passenger implements Runnable{

    CountDownLatch latch;

    public Passenger(CountDownLatch latch) {
        this.latch = latch;
    }
    
    
    @Override
    public void run() {
        System.out.println("passenger has arrived at the station, going to board the train  ");
        
        try{
            Thread.sleep(2000);
        }
        catch (Exception e) {}
        Train.boardTrain(this);
        
        System.out.println("passenger has boarded the train");
        latch.countDown();
    }
    
    
    
}
class Train implements Runnable {

    CountDownLatch latch;
    
    static LinkedList<Passenger> carriage = new LinkedList<>();

    public Train(CountDownLatch latch) {
        this.latch = latch;
    }
   
    
    @Override
    public void run() {
        System.out.println("train is arriving at the station, waiting for passengers");
        
        // Barrier/block  
        try{
            latch.await(); // barrier applied
        }
        catch(Exception e) {}
        
        System.out.println("train is leaving the station");
    }

    public static void boardTrain(Passenger p){
        carriage.addLast(p);
        
    }
    
}












