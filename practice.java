import java.util.*;
public class practice {
    public static void main(String[] args){
        /*HashSet<String> set1 = new HashSet<>();
        set1.add("ENG101");
        set1.add("ENG104");
        preRecsList preReclist1 = new preRecsList();
        preReclist1.add(set1);
        preReclist1.add("COLL101");
        System.out.println(preReclist1);

        HashSet<String> set2 = new HashSet<>();
        set2.add("Math112");
        set2.add("Math114");
        set2.add("Math115");
        set2.add("Math118");
        set2.add("Math124");
        set2.add("Math125");
        set2.add("Math134");
        set2.add("Math138");
        set2.add("Math156");
        classes CSCI141 = new classes("CSCI141");
        CSCI141.add(set2);
        System.out.print(CSCI141);
        */

        HashSet<String> pre1 = new HashSet<>();
        pre1.add("COLL101");
        HashSet<String> pre2 = new HashSet<>();
        pre2.add("ENG101");
        classes class5 = new classes("ENG102", pre1);
        class5.add(pre2);
        HashSet<String> classesTaken1 = new HashSet<>();
        classesTaken1.add("ENG101");
        classesTaken1.add("COLL101");
        System.out.println(class5.checkPreRecs(classesTaken1));
        HashSet<String> classesTaken2 = new HashSet<>();
        classesTaken2.add("COLL101");
        System.out.println(class5.checkPreRecs(classesTaken2));
    }
}
