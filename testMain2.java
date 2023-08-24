//Nina Ervin
//6/24/2023
//this file takes from main1 and adds the feature of being able to section off the classes into quarters
//this code will also start using the idea that prerecs need to come before other classes
//some things that still need to be worked on: ability to do other majors, ability to tell what cs electives are and what sicence sequence are
//
//need to know that if you have taken 124 then you dont need 115.
import java.util.*;
import java.io.*;
public class testMain2 {
    public static void main(String[] args) throws FileNotFoundException{
        HashSet<String> classesTaken = new HashSet<>();
        HashSet<String> classesToTake = new HashSet<>();
        intro();
        talkToUser(classesTaken);
        HashSet<classes> listOfClassesToTake = clacClassesToTake(classesToTake, classesTaken);
        System.out.println("Here are the CS classes that you need to take before you graduate:");
        System.out.println(classesToTake);
        makeSchedule(classesTaken, listOfClassesToTake);
        System.out.println("I hope you enjoy your new schedule!");
    }

    //this method introducice the user to the program and explains what will happen
    public static void intro(){
        System.out.println("Hi! I am here to develop a schequle for your CS classes.");
        System.out.println("Before I do that I will need to ask you a few questions.");
    }

    //this method will take user input on what classes have been taken and then it will add them to a list to refeance later
    public static void talkToUser(HashSet<String> classesTaken){
        System.out.println("Please list the classes you have taken by typing the subject and class number with no spaces and then hitting enter between each.\n If you have taken any 4 credit computer science electives please enter \"4CreditComputerScienceElective1\" (Increase the number for each class you have taken). If you have taken a any science seqence please enter \"ScienceSequence\" followed by the number in the serise like \"ScienceSequence1\".  \n When the list is complete, then hit enter.");
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
    public static HashSet<classes> clacClassesToTake(HashSet<String> classesToTake, HashSet<String> classesTaken) throws FileNotFoundException{
        HashSet<classes> listOfclassesToTake = new HashSet<>();
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
                //System.out.println(class1);//this is a practice for me to check to make sure all the class objects are coming out right
            }
            if(!classesTaken.contains(class1.getName())){ //this checks the classes to see if the user said they took the class already.
                classesToTake.add(class1.getName());
                listOfclassesToTake.add(class1);
            }
        }
        return listOfclassesToTake;
    } 
    
    public static void makeSchedule(HashSet<String> classesTaken, HashSet<classes> listOfClassesToTake){
        System.out.println("How many classes would you like to take a quarter. I recomend 4 classes each quarter.");
        Scanner scanner = new Scanner(System.in);
        int numOfClasses = scanner.nextInt(); //the number is the amount of classes they want to take per quarter
        int quarterCounter = 1;//this number keeps track of the amount of quarters
        Stack<String> noNoList = new Stack<>();
        while(!listOfClassesToTake.isEmpty()){ //this makes sure that you still have more classes to take
            int classCount = 0;//this tracks the amount of classes in one quarter
            System.out.println("quarter " + quarterCounter + ": ");
            Iterator<classes> i = listOfClassesToTake.iterator();
            while(!listOfClassesToTake.isEmpty() && classCount != numOfClasses){ //here we are putting together one quarter
                if(i.hasNext()){//making sure theres another class to look at
                    classes class1 = i.next();
                    if(class1.checkPreRecs(classesTaken)){//this calls a check in classes to see if the preRecs are already taken
                        System.out.println(class1.getName());//this prints the class if you can take it
                        noNoList.push(class1.getName());//this then puts the class name onto a list to add to classes taken after the quarter is complete
                        i.remove();//this remove the class from classes to take using iterator
                        classCount++;
                    }
                }else{ //if theres no other class then people should take a class not for cs
                    System.out.println("elective not for CS");
                    classCount++;
                }
            }
            while(!noNoList.isEmpty()){ //adds the classes that we taken that quarter to classes taken
                classesTaken.add(noNoList.pop());
            }
            quarterCounter++;
            System.out.println();
        }
        
    }
}
