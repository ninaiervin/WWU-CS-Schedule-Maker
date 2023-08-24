import java.util.*;
public class classes{
    private String name;
    preRecsList preRecs;
    

    public classes(String newName){
        name = newName;
        preRecs = new preRecsList();
    }

    public classes(String newName, HashSet<String> set){
        name = newName;
        preRecs = new preRecsList();
        preRecs.add(set);
    }

    public String getName(){
        return name;
    }

    public void add(String cl){
        preRecs.add(cl);
    }

    public void add(HashSet<String> cls){
        preRecs.add(cls);
    }

    public String toString(){
        return "Class: " + name + " preRecs: " + preRecs.toString(); 
    }

    //this method just makes the code a little more user friendly.
    public boolean checkPreRecs(HashSet<String> classesTaken){
        return preRecs.checkNodes(classesTaken);
    }
}

//this is like a linked list that holds classNodes
class preRecsList{
    //this keeps track of the front of the list
    private classNode front;

    public preRecsList(){
        front = null;
    }
    //this method makes it so you can add a set to a node in the list
    public void add(HashSet<String> newList){
        if(front == null){
            front = new classNode(newList);
            return;
        }
        classNode current = front;
        while(current.next != null){
            current = current.next;
        }
        current.next = new classNode(newList);
    }

    //if you only have one class and not multiple you can just add the one string to a node
    public void add(String newClass){
        if(front == null){
            front = new classNode(newClass);
            return;
        }
        classNode current = front;
        while(current.next != null){
            current = current.next;
        }
        current.next = new classNode(newClass);
    }

    public String toString(){
        if(front == null){
            return " ";
        }
        String print = "";
        print += " One of: " + front.classList;
        classNode current = front.next;
        while(current != null){
            print +=", One of: " + current.classList;
            current = current.next;
        }
        return print;
    }
    
    //this checks to see if the hashSet of all the preRecs lines up with the classes you have taken
    public boolean checkNodes(HashSet<String> classesTaken){
        if(front == null){
            return true; //if the class has no pre recs then you can take the class
        }
        boolean takeClass = false;
        classNode current = front;
        while(current != null){ //this makes sure that there are still more lists to check
            for(String class1: current.classList){//this runs for each pre rec on the list
                if(classesTaken.contains(class1)){//this checks to see if you have taken that pre rec
                    takeClass = true;//if you have taken one from the list then we no longer need to check the others
                    break;
                }else{
                    takeClass = false;
                }
            }
            //this makes sure that if you don't have a class from the list then you are not allowed to take the class
            if(takeClass == false){
                return false;
            }
            current = current.next;//this moves to the next node containing the next list
        }
        return takeClass;
    }
}

//this is the code for the nodes
//these nodes holds a refrentce to the next node hashSet (chosen because I don't care about the oder and I want a fast lookup time)
//the hashset is here to hold all the or classes like class1 or class2 or class3.
class classNode{
    HashSet<String> classList;
    classNode next;

    //creates a node givin a set
    public classNode(HashSet<String> newList){
        classList = newList;
        next = null;
    }

    //creates a node givin a string
    public classNode(String newClass){
        classList = new HashSet<>();
        classList.add(newClass);
        next = null;
    }

    public void addPreRec(String cl){
        classList.add(cl);
    }
}
