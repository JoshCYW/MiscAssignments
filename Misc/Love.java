import java.util.*;

public class Love {
  
  Hashtable<String,Integer> words;
  Queue<String> minimal;
  int currentmin = 1000001;
  
  Love() {
    words = new Hashtable<String, Integer>();
    minimal = new LinkedList<String>();
  }
  
  public void run() {
    // treat this as your "main" method
    Scanner sc = new Scanner(System.in);
    int numberofwords = sc.nextInt();
    int count = 0;
    for(int i = 0; i < numberofwords; i++) {
      String currentword = sc.next();
      if (words.containsKey(currentword)){
        if(currentword.equals(minimal.peek())){
          count = function1(count);
        }
        else{
          words.put(currentword, words.remove(currentword)+1);
          minimal.offer(currentword);
          count += currentword.length();
        }
        
      }
      else{
        words.put(currentword,1);
        minimal.offer(currentword);
        count += currentword.length();
        count = function2(count);
      }
    }
    System.out.println(currentmin);
  }
 
  private int function1(int currentcount){
    String offered = minimal.poll();
    while(!minimal.isEmpty()){
      String front = minimal.peek();
      if(front.equals(offered)||words.get(front)>1){
        currentcount -= minimal.poll().length();
        words.put(front, words.remove(front)-1);
      }
      else{
        break;
      }
    }
    minimal.offer(offered);
    if(currentcount < currentmin){
      currentmin = currentcount;
    }
    return currentcount;
  }
  
  private int function2(int currentcount){
    while(!minimal.isEmpty()){
      String front = minimal.peek();
      if(words.get(front) > 1){
        currentcount -= minimal.poll().length();
        words.put(front, words.remove(front)-1);
      }
      else{
        break;
      }
    }
    currentmin = currentcount;
    return currentcount;
  }
  
  public static void main(String[] args) {
    Love myLove = new Love();
    myLove.run();
  }
  
}