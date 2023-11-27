<h2>Introduction and Objectives</h2>

The program you complete in this project will compute a list of all unique words and count how many times they occur in a given input file. The same computation is performed using a sorted linked list and a binary search tree. These data structures are used to store the unique words and perform the computation.

The main objective of this project is to be able to compare the relative performance of these two data structures for solving this problem.

The goal of this project is for you to master (or at least get practice on) the following tasks:

- implementation of a reference based sorted list (this can be a modification of the sorted linked list you implemented for project 3, or a new implementation specifically for this project),
- implementation of a reference based binary search tree.

<h2> FrequentWords Clas</h2>
The program usage and the user interface are already implemented as part of the FrequentWords and FileParser classes. These classes obtains, parse and use the command line arguments. They open, read and parse the content of the input file and write the results to an output file.

You should not make any modifications to that class.

<h2> Data Storage and Organization</h2>
Your program needs to implement three classes described in this section. You may, but you are not required to, implement other classes. 

<h3> Word Class</h3>
This is the class that represents words and their counts. It should provide the following public methods:

- Word(String word)
One argument constructor that takes a string as the argument and creates a new Word object with the given string and count of 1.

- int incrementCount()
A method that increments the count associated with this Word object by 1 and returns the updated value of the count.

- String getWord()
A method that returns the word associated with this Word object.

- int getCount()
A method that returns the count associated with this Word object.

- String toString()
A method that returns the string representation of this Word object. The representation should consist of the count that is right aligned within a field of 5 characters, followed by two spaces, followed by the word itself. Here are some examples of such string representations:

  12345  computer
     67  hello
    890  coffee
      3  flavor
This class should implement Comparable<Word> interface. The Word objects should be compared according to the natural ordering of the words that they store. (Counts are irrelevant for comparison.)

This class should override the equals method. Two Word objects are equal if their words and counts are equal.
<h3>SortedLinkedList and BSTIndex classes</h3>
These classes should implement the Index interface.

In addition they both should provide implementation of the equals method and the toString methods inherited from the Object class.

Two Index objects are considered equal if they contain the same number of elements, and those elements are pairwise equal (when retrieved from the Index object according to the natural ordering of the Word objects).
NOTE that this implies that two different implementations of the Index interface should be able to be compared using this equals method.

The string representation of an Index object consists of a list of all the Word objects sorted by their natural ordering, enclosed in square brackets ("[]"). Adjacent elements are separated by the characters ", " (comma and space).

Since the classes implementing the Index interface need to implement the Iterable<Word> interface, both of these classes need to provide an internal class implementing an iterator. The iterator should return elements following the natural order defined by those elements (i.e., in sorted order based on the rules for the compareTo method).
NOTE, that this iterator should provide implementation for the remove method (since it is used in the FrequentWords class.)
PERFORMANCE RESTRICTION: The next() and hasNext() methods of the iterator should perform in constant time, O(1). The constructor for the iterator class may need to perform some additional work in order to make this possible.

<h2>Program Input and Output</h2>
The program input can be any text file. The interesting results will be obtained from running the program on large input files. Project Gutenberg may be a source of interesting large files. But, for testing purposes, you should work with small carefully created files for which you know the count results.

The program produces a timing report in the console window. It also writes the list of the most frequent words to the output file.

The output for the timing results will be similar to this:

INFO: Reading file took 434801 ms (~   0.435 seconds).
INFO: 565405 words read. 

Processing using Sorted Linked List
INFO: Creating index took 76474070 ms (~  76.474 seconds).
INFO: 19783 words stored in index.
INFO: Pruning index took 192947 ms (~   0.193 seconds).
INFO: 2446 words remaining after pruning.

Processing using Recursive BST
INFO: Creating index took 134945 ms (~   0.135 seconds).
INFO: 19783 words stored in index.
INFO: Pruning index took 17063 ms (~   0.017 seconds).
INFO: 2446 words remaining after pruning.

The content of the output file is too large to show, but here are a few excerpts:

10520  a
   25  abandon
   54  abandoned
   26  abandoning
  107  able
 1016  about
...
   22  olmutz
 3996  on
  356  once
 2111  one
   54  ones
 1292  only
   64  onto
   99  open
...
  297  side
  135  sides
   33  sigh
   55  sighed
   88  sight
   45  sign
   59  significance
...
  548  yes
   53  yesterday
  339  yet
 3835  you
  441  young
   32  younger
  814  your
   25  yours
  117  yourself
   44  youth
   40  zherkov
