import java.util.*;
import java.io.*;
import edu.duke.*;

public interface IRater
{

    public boolean equals(Rater r);

    public void addRating(String item, double rating);

    public boolean hasRating(String item);

    public String getID();

    public double getRating(String item);

    public int numRatings();

    public ArrayList<String> getItemsRated();
}

