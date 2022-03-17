package week03;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class TrainExample2 {
    
    public static void main(String[] args) {
        CountDownLatch latch1 = new CountDownLatch(7);
        CountDownLatch latch2 = new CountDownLatch(5);
        
        
        
        Train2 train = new Train2(latch1, latch2); //barrier at 7 and 5
        new Thread(train).start();
        
        Scanner in = new Scanner(System.in);
        
        int count=0;
        
        while (true){
            in.nextLine();
            if (count<8){
                latch1.countDown();
                count++;
            }
            else {
                latch2.countDown();
            }
        }
    }
}
class Passenger implements Runnable{

 

    CountDownLatch latch;
    Train train;

 

    public Passenger(CountDownLatch latch, Train train) {
        this.latch = latch;
        this.train = train;
    }
    
    
    
    @Override
    public void run() {
//        System.out.println("passenger has arrived at the station, going to board the train");
//        
//        try{
//            Thread.sleep(2000);
//        }
//        catch (Exception e) {}
//        train.boardTrain(this);
//        
//        System.out.println("passenger has boarded the train");
//        latch.countDown();
    }
    
    
    
}
class Train implements Runnable {

 

    CountDownLatch latch;
    
    LinkedList<Passenger> carriage = new LinkedList<>();

 

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
        
        //another latch
    }

 

    public void boardTrain(Passenger p){
        carriage.addLast(p);
    }
    
}

 

class Train2 implements Runnable {

 

    CountDownLatch latch1;
    CountDownLatch latch2;

 

    //CyclicBarrier barrier = new CyclicBarrier(7);
    public Train2(CountDownLatch latch1, CountDownLatch latch2) {
        this.latch1 = latch1;
        this.latch2 = latch2;
    }
    
    
    
    @Override
    public void run() {
        try{
            // start phase 1 **************************************************************
            System.out.println("train has arrived at station 1...waiting");
            
            latch1.await();
            //barrier.await();
            
             // end  phase 1 **************************************************************
             
              // start phase 2 **************************************************************
            System.out.println("Training is leaving station 1.. now arriving at station 2");
            //barrier.reset();
            
            //barrier.await();
            
            latch2.await();
            
            System.out.println("training is departing station 2......");
            
             // end phase 2 **************************************************************
        }
        catch(Exception e) {}
    }
    
    
}