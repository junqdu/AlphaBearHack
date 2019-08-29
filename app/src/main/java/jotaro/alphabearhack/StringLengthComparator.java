package jotaro.alphabearhack;

// A comparator that sorts String by their length, longer length come first
public class StringLengthComparator implements java.util.Comparator<String> {

    private int referenceLength;

    // Construct MyComapartor based on string's length
    public StringLengthComparator(String reference) {
        super();
        this.referenceLength = reference.length();
    }

    public int compare(String s1, String s2) {
        int dist1 = Math.abs(s1.length() - referenceLength);
        int dist2 = Math.abs(s2.length() - referenceLength);

        return dist2 - dist1;
    }
}