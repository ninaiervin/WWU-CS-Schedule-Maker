//Nina Ervin
//6/20/2023
//this is the start of my scheduale project
//this code should take user input on what cs classes they have taken and then tell them how many more 
//classes will be needed to complete the cs major
import java.util.*;
import java.io.*;
public class main1 {
    public static void main(String[] args) throws FileNotFoundException{
        HashSet<String> classesTaken = new HashSet<>();
        ArrayList<String> classesToTake = new ArrayList<>();
        intro();
        talkToUser(classesTaken);
        clacClassesToTake(classesToTake, classesTaken);
        System.out.println("here are all the cs classes that you need to take before you graduate:");
        System.out.println(classesToTake);
    }

    //this method introducice the user to the program and explains what will happen
    public static void intro(){
        System.out.println("Hi! I am here tell you all the cs classes you need to take.");
        System.out.println("before I do that I will need to ask you a few questions.");
    }

    //this method will take user input on what classes have been taken and then it will add them to a list to refeance later
    public static void talkToUser(HashSet<String> classesTaken){
        System.out.println("please list out all the classes you have taken by typing the subject and class number with no spaces and then hiting enter between each. \n if there is no more then hit enter.");
        Scanner console = new Scanner(System.in); //creating a scanner to read from comsole
        String cl = console.nextLine();
        while(!cl.isBlank()){ //making sure that the line isn't just blank
            classesTaken.add(cl);//adding the class to the list
            cl = console.nextLine(); //calling the next line to make sure that it isnt blank
        }
        System.out.println("here are all the classes you have taken:");
        System.out.println(classesTaken);//printing out all the classes they just entered
    }

    //this method is here to read from a file all the classes that are needed for a cs major anf then turN each one into a classes object (this was more to test the classes object) and then compare to the class that still need to be taken
    public static void clacClassesToTake(ArrayList<String> classesToTake, HashSet<String> classesTaken) throws FileNotFoundException{
        Scanner csText = new Scanner(new File("cs.txt"));//this scanner reads the text doc
        while(csText.hasNextLine()){
            String csClassLine = csText.nextLine();
            Scanner csLine = new Scanner(csClassLine);//this scanner reads one line of the text doc
            String cl = csLine.next(); //this is the name of the class
            classes class1 = new classes(cl); //the creates a new classes object
            while(csLine.hasNext()){ //checks to make sure there is more on the line for preRecs
                String pre = csLine.next(); //this is one of the preRecs
                if(pre.equals("[")){ //this part checks for the case: class1 or class2 or class3... in the text doc it should be listed className [ class1 class2 class3 ... ]
                    HashSet<String> set1 = new HashSet<>(); //creates a nee set to store all the or's
                    pre = csLine.next();
                    while(!pre.equals("]")){ //makes sure that we are still looking at an or class
                        set1.add(pre);
                        pre = csLine.next();
                    }
                    class1.add(set1); //adds a new preRec set to the class
                }else{
                    class1.add(pre); //adds just one class to the list of prerecs
                }
                System.out.println(class1);//this is a practice for me to check to make sure all the class objects are coming out right
            }
            if(!classesTaken.contains(class1.getName())){ //this checks the classes to see if the user said they took the class already.
                classesToTake.add(class1.getName());
            }
        }
    }
}
