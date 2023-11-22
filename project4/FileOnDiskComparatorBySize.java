package project4;
import java.util.Comparator;

/**
 * This class implements comparison between FileOnDisk objects that is different 
 * than the comparison provided by the File class. 
 * 
 * This class implements Comparator<FileOnDisk> interface and only has one method. 
 * 
 * @author Imani Gomez
 * 
 */
public class FileOnDiskComparatorBySize implements Comparator<FileOnDisk>{
    /**
     * Compares the two FileOnDisk objects by their size (number of bytes), and, if 
     * the sizes are equal by their path names (using lexicographic orgering)
     * @param o1 the first FileOnDisk object
     * @param o2 the second FileOnDisk object
     * @return returns true if the objects are equal, false if otherwise
     */
    public int compare(FileOnDisk o1, FileOnDisk o2){
        //check if file sizes are not equal
        if(o1.length() != o2.length()){
            return Long.compare(o2.length(), o1.length());
        }else{
            //if file sizes are equal
            return o1.getPath().compareTo(o2.getPath());
        }
    }
}
