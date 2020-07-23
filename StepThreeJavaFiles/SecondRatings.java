
`   
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<IRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }

    public SecondRatings(String mFile,String rFile)
    {
    	FirstRatings fr = new FirstRatings();
    	myMovies=fr.loadMovies(mFile);
    	myRaters=fr.loadIRaters(rFile);   	
    }

    public int getMovieSize()
    {
    	return myMovies.size();
    }

    public int getRaterSize()
    {
    	return myRaters.size();
    }


    private double getAverageByID(String id,int minRaters)
    {
    	double ratingsTotal=0.0;
    	int n=0;
    	for(IRater r:myRaters)
    	{
    		if(r.hasRating(id))
    		{
    			ratingsTotal+=r.getRating(id);
    			n+=1;
    		}
    	}
    	if(n>minRaters)
	    	return ratingsTotal/n;
	    else
	    	return 0.0;
    } 


    public double getAverageOneMovie(String id)
    {

    	double ratingsTotal=0.0;
    	int n=0;
    	for(IRater r:myRaters)
    	{
    		if(r.hasRating(id))
    		{
    			ratingsTotal+=r.getRating(id);
    			n+=1;
    		}
    	}
	    	return ratingsTotal/n;
    }

    public ArrayList<Rating> getAverageRatings(int minRaters)
    {
    	ArrayList<Rating>out =new ArrayList<Rating>();
    	for(Movie m:myMovies)
    	{
    		String id=m.getID();
    		double avg=getAverageByID(id,minRaters);
    		if(avg!=0.0)
    			out.add(new Rating(id,avg));
    	}
    	return out;
    }

    public String getTitle(String id)
    {
    	for(Movie m:myMovies)
    	{
    		if(m.getID().equals(id))
    			return m.getTitle();
    	}
    	return "Movie ID not found :(";
    }

    public String getID(String title)
    {
    	for(Movie m:myMovies)
    	{
    		if(m.getTitle().equals(title))
    			return m.getID();
    	}
    	return "NO SUCH TITLE.";
    }
    
}
