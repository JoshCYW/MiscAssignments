import java.util.*;

public class Radiation {
  Queue<Integer> queue;
  int min;
  int lastyear;
  int laststrength; 
  int prevstrength;
  
  
  public void run() {
    
    queue = new LinkedList<Integer>();
    Scanner sc = new Scanner(System.in);
    int input = sc.nextInt();
    min = sc.nextInt();
    prevstrength = min;
    input--;
    int max = 0;
    
    for(int i = 0; i < input; i++){
      int currentstrength = sc.nextInt();
      
      if(currentstrength <= min){
        int temp = clearQueue();
        if(temp > max){
          max = temp;
        }
        min = currentstrength;
        lastyear = 0;
        continue;
      }
      
      if(currentstrength <= prevstrength) {       
        queue.offer(currentstrength);
        prevstrength = currentstrength;
        continue;
      }
      
      int temp = clearQueue();
      
      if(temp > max){
        max = temp;
      }
      queue.offer(currentstrength);
      prevstrength = currentstrength;
    }
    
    int temp = clearQueue();
    
    if(temp > max){
      max = temp;
    }
    
    System.out.println(max);
  }
  
  private int clearQueue(){
    if(queue.isEmpty()){
      return 0;
    }
    
    int temp = 0;
    int numofyear = 0;
    boolean check = true;
    
    while(!queue.isEmpty()){
      if(check){
        
        if(queue.peek() > laststrength){
          numofyear++;
        }
        
        else{
          numofyear = lastyear + 1;
          check = false;
        }
        
      }
      
      else{
        numofyear++;
      }
      
      temp = queue.poll();
    }
    if(lastyear > numofyear) {
    }
    else {
    laststrength = temp;
    lastyear= numofyear;
    }
    return lastyear;
  }
  
  public static void main(String[] args) {
    Radiation myChemicalElements = new Radiation();
    myChemicalElements.run();
  }
}

// hint for O(N) solution...
class Element {
  private int strength;
  private int yearsBeforeDecay;
  
  public Element(int strength, int yearsBeforeDecay) {
    this.strength = strength;
    this.yearsBeforeDecay = yearsBeforeDecay;
  }
  
  public int getStrength() {
    return this.strength;
  }
  
  public int getYearsBeforeDecay() {
    return this.yearsBeforeDecay;
  }
  
  public void setYearsBeforeDecay(int yearsBeforeDecay){
    this.yearsBeforeDecay = yearsBeforeDecay;
  }
  
}
