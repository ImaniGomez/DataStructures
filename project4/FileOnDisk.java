package project4;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a file or directory on the disk and provides various operations
 * related to files and directories. 
 */
public class FileOnDisk extends File {

    /**
     * Constructs a FileOnDisk object representing the file or directory specified by the pathname. 
     * 
     * @param pathname the path to the file or directors
     * @throws NullPointerException if the file path is null
     */
    public FileOnDisk(String pathname){
        // calls constructor of file class 
        super(pathname);
        // throws exception if pathnname is null
        if(pathname == null){
            throw new NullPointerException("File path cannot be null.");
        }
    }


    /**
     * This method computes and returns the total size of the file or directory
     * 
     * @return the total size of the file or directory in bytes. 
     */
    public long getTotalSize(){
        return exploreDir(this);
    }


    /**
     * Recursively explores the directory structure to compute the total size of files and directories.
     * 
     * @param file the file or directory to explore
     * @return the total size of the file or directory in bytes
     */
    public long exploreDir(File file){
        //variable to hold total size of file
        long totalSize = 0;

        //verify if what is given is a directory
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if(file != null){
                //iterate through each file or directory
                for(File subFile : files){
                    //recursive call 
                    totalSize += exploreDir(subFile);
                }
            }
        }else if(file.isFile()){
            //if it is a file, not a directory
            totalSize += file.length();
        }
        return totalSize;
    }


    /**
     * Retrieves a list of the largest files within the directory based on file size
     * 
     * @param numOfFiles the number of largest files to retrieve
     * @return a list of FileOnDisk objects representing the largest files
     */
    public List<FileOnDisk> getLargestFiles(int numOfFiles){
        //create empty list to store largest files
        List<FileOnDisk>files = new ArrayList<>();
        //verify it is a directory
        if(this.isDirectory()){
            //find the largest files using that method
            findLargestFiles(this, files, numOfFiles, new FileOnDiskComparatorBySize());
            //if what is given is not a directory
            if(files.isEmpty()){
                return null;
            }else{
                return files;
            }
        }else{
            return null;
        }
        
    }


    /**
     * Finds the largest files within a directory and populates the list with the specified number of files
     * 
     * @param file the file or directory to search for largest files
     * @param files the list to populate with the largest files found
     * @param numOfFiles the number of largest files to find
     * @param compartor the comparator to compare file sizes
     */
    public void findLargestFiles(File file, List<FileOnDisk> files, int numOfFiles, Comparator<FileOnDisk> compartor){
        if(file.isDirectory()){
            // array of files within directory
            File [] dirFiles = file.listFiles();

            //make sure not null
            if(dirFiles != null){
                //check each file
                for(File subFile : dirFiles){
                    findLargestFiles(subFile, files, numOfFiles, compartor);
                }
            }
        } else {
            // if it is not a directory
            files.add(new FileOnDisk(file.getPath()));
            files.sort(compartor);
            //amount of files should not be greater than numOfFiles
            if(files.size() > numOfFiles){
                while(files.size() > numOfFiles){
                    files.remove(files.size() - 1);
                }
            }
        }
    }


    /**
     * Overrides the default to String method to represent the size and path of the file
     * 
     * @return a formatted string representation of the file's size and path
     */
    @Override
    public String toString(){
        //get total size of directories and files
        long size = this.getTotalSize();
        String sizeString = formatSize(size);
        String path;
        try{
            path = this.getCanonicalPath();
        }catch(IOException e){
            e.printStackTrace();
            path = this.getPath();
            /*
             * learned this code from: 
             * https://www.geeksforgeeks.org/file-getcanonicalpath-method-in-java-with-examples/
             */
        }
        //combine all and return the string
        return String.format("%10s%s", sizeString, path);
    }


    /**
     * Formats the size of a file into a more human-readable format
     * 
     * @param size the size of the file in bytes
     * @return a formatted string representing the file size
     */
    private String formatSize(long size){
        //array for different memory sizes
        String [] sizeUnits = new String[]{"bytes  ", "KB     ", "MB     ", "GB     "};
        int i = 0;
        double fileSize = size;
        //iterate through different size units
        while (fileSize > 1024 && i < sizeUnits.length - 1){
            fileSize /= 1024;
            i++;
        }
        //return the formatted string
        return String.format("%8.2f %s", fileSize, sizeUnits[i]);
    }
}
