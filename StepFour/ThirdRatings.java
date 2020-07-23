import java.util.*;
import java.io.*;
import edu.duke.*;


public class ThirdRatings
{
	 //private ArrayList<Movie> myMovies;
    private ArrayList<IRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }

    public ThirdRatings(String rFile)
    {
    	FirstRatings fr = new FirstRatings();
    	//myMovies=fr.loadMovies(mFile);
    	myRaters=fr.loadIRaters(rFile);   	
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
    	//System.out.println(n);
    	if(n>=minRaters)
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
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    	ArrayList<Rating>out =new ArrayList<Rating>();
    	for(String m:movies)
    	{
    		//System.out.println(m);
    		double avg=getAverageByID(m,minRaters);
    		if(avg!=0.0)
    			out.add(new Rating(m,avg));
    	}
    	//for(Rating r:out)
    	//	System.out.println(r);
    	return out;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minRaters,Filter f)
    {

    	ArrayList<String> movies = MovieDatabase.filterBy(f);
    	ArrayList<Rating>out =new ArrayList<Rating>();
    	for(String m:movies)
    	{
    		//System.out.println(m);
    		double avg=getAverageByID(m,minRaters);
    		if(avg!=0.0)
    			out.add(new Rating(m,avg));
    	}
    	//for(Rating r:out)
    	//	System.out.println(r);
    	return out;	
	}
}