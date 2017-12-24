import java.util.*;

public class Storage {
  
  public void run() {
    // treat this as your "main" method
    
    Scanner sc = new Scanner(System.in);
    
    int numberofboxes = sc.nextInt();
    int cancarry  = sc.nextInt();
    int numberperbox  = sc.nextInt();
    int queries  = sc.nextInt();
    
    ArrayList<Box> boxes = new ArrayList<Box>();
    
    Box hand = new Box(cancarry);
    
    for(int i= 0; i < numberofboxes; i++) {
      boxes.add(new Box(numberperbox));
    }
    
    for(int i = 0; i < queries; i ++) {
      String query = sc.next();
      String itemName;
      boolean itemFound = false;
      
      switch(query) {
        case "withdraw":
          itemName = sc.next();
          if(hand.itemInStorage(itemName)) {
            System.out.println("item " + itemName + " is already being held");
            break;
          }
        for(Box box: boxes) {
          if(box.itemInStorage(itemName)){
            itemFound = true;
            if(!hand.isFull()){
              hand.deposit(box.withdraw(itemName));
          System.out.println("item " + itemName + " has been withdrawn");
              break;
            }
            else {
              System.out.println("cannot hold any more items");
            }
          }
        }
        
        if(!itemFound) {
          System.out.println("item " + itemName + " does not exist");
        }
        
        break;
        
        case "purchase":
          itemName = sc.next();
          int itemValue = sc.nextInt();
          Item purchased = new Item(itemName, itemValue);
          if(!hand.isFull()){
            hand.deposit(purchased);
            System.out.println("item " + purchased.getName() + " is now being held");
          }
          else {
            store(boxes,purchased);
          }
          break;
          
        case "deposit":
          itemName = sc.next();
        for(Box box: boxes) {
          if(box.itemInStorage(itemName)) {
            System.out.println("item " + itemName + " is already in storage");
            itemFound = true;
            break;
          }
        }
        
        if(!itemFound) {
          if(hand.itemInStorage(itemName)) {
            Item toDeposit = hand.withdraw(itemName);
            store(boxes, toDeposit);
            itemFound = true;
          }
        }
        
        if(!itemFound) {
          System.out.println("item " + itemName + " does not exist");
        }
        break;
        
        case "location":
          itemName = sc.next();
          for(int j = 0; j < boxes.size(); j++) {
            int number = j + 1;
            if(boxes.get(j).itemInStorage(itemName)) {
              System.out.println("item " + itemName + " is in box " + number);
              itemFound = true;
              break;
            }
          }
          
          if(!itemFound) {
            if(hand.itemInStorage(itemName)) {
              System.out.println("item " + itemName + " is being held");
              itemFound = true;
            }
          }
          if(!itemFound) {
            System.out.println("item " + itemName + " does not exist");
          }
          
          break;
          
        case "valuable":
          printMostValuable(hand,boxes);
          
      }
    }
    
    
  }
  
  public static void main(String[] args) {
    Storage myStorageSystem = new Storage();
    myStorageSystem.run();
  }
  
  public static void store(ArrayList<Box> boxes, Item item) {
 
    for(int  j = 0; j < boxes.size(); j++) {
      int number = j + 1;
      if(!boxes.get(j).isFull()) {
        boxes.get(j).deposit(item);
        System.out.println("item " + item.getName() + " has been deposited to box " + number);
        return;
      }
    }
    
  }
  
  public static void printMostValuable(Box hand, ArrayList<Box> boxes) {
    Item mostValuable = hand.mostValuable();
    for(int i = 0; i < boxes.size(); i++) {
      Item toCompare = boxes.get(i).mostValuable();
      if(toCompare.getValue() > mostValuable.getValue()) {
        mostValuable = toCompare;
      }
      else if (toCompare.getValue() == mostValuable.getValue()) {
        if(toCompare.getName().compareTo(mostValuable.getName()) <0){
          mostValuable = toCompare;
        }
      }
    }
    
    System.out.println(mostValuable.getName());
    
  }
  
}


class Box {
  // define appropriate attributes, constructor, and methods
  private int size;
  private ArrayList<Item> items;
  
  public Box(int size) {
    this.size = size;
    items = new ArrayList<Item>();
  }
  
  public void deposit(Item item) {
    if(!this.isFull() && !this.itemInStorage(item.getName())) {
      items.add(item);
    }
  }
  
  public Item withdraw(String itemName) {
    Item removed = new Item("x",  1);
    for(int i = 0; i<items.size(); i++) {
      if(items.get(i).getName().equals(itemName)) {
        removed = items.get(i);
        items.remove(i);
      }
    }
    return removed;
  }
  
  public Item mostValuable() {
    Item mostValuable = new Item("x",  1);
    for(Item item: items) {
      if(mostValuable == null) {
        mostValuable = item;
      }
      else if(item.getValue() > mostValuable.getValue()) {
        mostValuable = item;
      }
      else if(item.getValue() == mostValuable.getValue()){
        if(item.getName().compareTo(mostValuable.getName()) < 0) {
          mostValuable = item;
        }
      }
    }
    return mostValuable;
  }
  
  public boolean itemInStorage(String itemName) {
    for(Item item: items) {
      if(item.getName().equals(itemName)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isFull() {
    if(items.size() == size) {
      return true;
    }
    return false;
  }
  
}

class Item {
  // define appropriate attributes, constructor, and methods
  private String name;
  private int value;
  
  public Item(String name,int value) {
    this.name = name;
    this.value = value;
  }
  public String getName() {
    return name;
  }
  public int getValue(){
    return value;
  }
}

