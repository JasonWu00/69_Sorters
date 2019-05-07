/**
  Implement an insertion sort, in the Sorters structure
 */
import java.util.ArrayList;

public class InsertionSorter extends Sorter {


    /**
      Construct an instance to process the user's data
     */
    public InsertionSorter(  ArrayList< String> usersData) {
        // your code here, if necessary
        super(usersData);
    }


    /**
      sort the user's data, implementing insertion sort
     */
    public void mySort() {
        // your code here
        selectionSort(elements);
    }

    private String insertOne(ArrayList<String> list, int positionToSort) {
      //from hw68, my code
      String elementToSort = list.get(positionToSort);
      if (list.size() == 0 || list.size() == 1) return "Already sorted";

      for (int index = positionToSort - 1; index >= 0; index--) {

        if (index == 0 && list.get(0).compareTo(elementToSort) > 0) {
          //exception for if elementToSort belongs as first element
          String currentElement = list.get(0);
          list.set(0, list.get(1));
          list.set(1, currentElement);
        }

        else if (list.get(index).compareTo(elementToSort) > 0) {
          String currentElement = list.get(index);
          list.set(index, list.get(index+1));
          list.set(index+1, currentElement);
          //shifts the element to the right
          //value of where the element once was can be ignored
        }

        else {
          return elementToSort;
          //the letters are already sorted by the time this occurs
        }
          //inserts the element to be sorted in its correct location
      }
      return elementToSort;
    }

    private void insertAll(ArrayList<String> unsortedList) {
      int index;
      for (index = 1; index < unsortedList.size(); index++){
        insertOne(unsortedList, index);
        System.out.println( "debug:"
          + "after inserting element " + unsortedList.get(index)
          + " at " + index
          + System.lineSeparator()
          + " elements: " + unsortedList
        );
      }
    }

    private void selectionSort(ArrayList<String> unsortedList) {
      int currentEle = 0;
      for (int loopTimes = 0; loopTimes < unsortedList.size(); loopTimes++) {
        int lowestElementIndex = champIndex(loopTimes, unsortedList);
        String lowestElement = unsortedList.get(lowestElementIndex);
        unsortedList.set(lowestElementIndex, unsortedList.get(loopTimes));
        unsortedList.set(loopTimes, lowestElement);
      }

      elements = unsortedList;
    }

    //--------------------------------------------------------------------------

    private int indexOf_recursive( String findMe //from solutionsHolmes
                                 , int low
                                 , int hi // inclusive
                                 ) {
        // System.out.println( "debug low: " + low
                          // + "   hi: " + hi);
        if( low > hi)  // detect base case
            return 0;   // solution to base case
              // value differs from while-style method, just FYI
        else{
            int pageToCheck = (low + hi) / 2;
            int comparison =
              findMe.compareTo( elements.get( pageToCheck));


            if( comparison == 0)    // detect base case
                return pageToCheck; // solution other base case
            // recursive cases
            else
                if( comparison < 0)
                    // findMe's spot precedes pageToCheck
                    return indexOf_recursive( findMe
                                             , low
                                             , pageToCheck -1);
                else
                    // findMe's spot follows pageToCheck
                    return indexOf_recursive( findMe
                                            , pageToCheck +1
                                            , hi);
        }
    }

    private int champIndex( int startAt, ArrayList<String> challengers) {
      String lowestElement = "ZZ";//ensures it is larger than any single letter
      int output = 0;
      for (int index = startAt; index < challengers.size(); index++) {
        //modified to search only parts of an ArrayList.
        if (challengers.get(index).compareTo(lowestElement) < 0) {
          lowestElement = challengers.get(index);
          output = index;
        }
      }
       return output;  // replace this line
    }
}
