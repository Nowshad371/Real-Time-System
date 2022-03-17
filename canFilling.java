package week03;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class canFilling {
    
    public static void main(String[] args) throws Exception {
        
        FillingSection filling = new FillingSection();
        SealingSection sealing = new SealingSection();
        LabelingSection labeling = new LabelingSection();
        
        ExecutorService factory = Executors.newCachedThreadPool();
        
        while (true) {
            // 1. create can
            can c = new can();
            // 2. submit can to filling section
            filling.setC(c);
            //3.  submit filling callable to threadpool
            Future<can> filledCan = factory.submit(filling);
            
            System.out.println("CAN STATUS: Filled-" + filledCan.get().isFilled);
            System.out.println("CAN STATUS: Sealed-" + filledCan.get().isSealed);
            System.out.println("CAN STATUS: Labeled-" + filledCan.get().isLabeled);
            
            //4. pass filled can to the sealing section
            sealing.setC(filledCan.get());
            
            Future<can> sealedCan = factory.submit(sealing);
            
            System.out.println("CAN STATUS: Filled-" + sealedCan.get().isFilled);
            System.out.println("CAN STATUS: Sealed-" + sealedCan.get().isSealed);
            System.out.println("CAN STATUS: Labeled-" + sealedCan.get().isLabeled);
            //5 pass sealed can to labeling section
            labeling.setC(sealedCan.get());
            
            Future<can> labeledCan = factory.submit(labeling);
            
            System.out.println("CAN STATUS: Filled-" + labeledCan.get().isFilled);
            System.out.println("CAN STATUS: Sealed-" + labeledCan.get().isSealed);
            System.out.println("CAN STATUS: Labeled-" + labeledCan.get().isLabeled);
            
            Thread.sleep(2000);
            
        }
        
    }
    
}  

class can {
    boolean isFilled=false;
    boolean isSealed=false;
    boolean isLabeled=false;
}

class FillingSection implements Callable<can> {

    can c;

    public void setC(can c) {
        this.c = c;
    }
    
    
    @Override
    public can call() throws Exception {
        
        System.out.println("FILLING SECTION: received empty can....processing");
        Thread.sleep(1000);
        c.isFilled=true;
        System.out.println("FILLING SECTION: processing complete - can is filled");
        return c;
    }
    
    
}
class SealingSection implements Callable<can> {

       can c;

    public void setC(can c) {
        this.c = c;
    }
    
    
    @Override
    public can call() throws Exception {
        
        System.out.println("SEALING SECTION: received filled can....processing");
        Thread.sleep(2000);
        c.isSealed=true;
        System.out.println("SEALING SECTION: processing complete - can is sealed");
        return c;
    }
    
    
    
}
class LabelingSection implements Callable<can> {

   
       can c;

    public void setC(can c) {
        this.c = c;
    }
    
    
    @Override
    public can call() throws Exception {
        
        System.out.println("LABELING SECTION: received sealed can....processing");
        Thread.sleep(1000);
        c.isLabeled=true;
        System.out.println("LABELING SECTION: processing complete - can is now labeled");
        return c;
    }
    
}