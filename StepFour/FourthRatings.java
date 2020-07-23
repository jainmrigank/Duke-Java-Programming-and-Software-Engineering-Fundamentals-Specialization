import java.util.*;
import java.io.*;
import edu.duke.*;


public class FourthRatings
{
    private double getAverageByID(String id,int minRaters)
    {
    	double ratingsTotal=0.0;
    	int n=0;
    	for(IRater r:RaterDatabase.getRaters())
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


    private double dotProduct(IRater me,IRater r)
    {
        int prod=0;
        ArrayList<String> myMovies=me.getItemsRated();
        ArrayList<String> otherMovies=r.getItemsRated();
        for(String id:myMovies)
        {
            if(otherMovies.contains(id))
            {
                prod+=(me.getRating(id)-5)*(r.getRating(id)-5);
            }
        }
        return prod;
    }


    private ArrayList<Rating> getSimilarities(String id)
    {   
        ArrayList <Rating> out=new ArrayList <Rating>();
        IRater me=RaterDatabase.getRater(id);
        for(IRater r:RaterDatabase.getRaters())
        {
            if(!r.getID().equals(me.getID()))
            {
                double prod=dotProduct(me,r);
                if(prod>=0)
                    out.add(new Rating(r.getID(),prod));
            }
        }
        Collections.sort(out,Collections.reverseOrder());
        return out;
    }


    public ArrayList<Rating> getSimilarRatings(String id,int numSimilarRaters,int minRaters)
    {
        ArrayList <Rating> out=new ArrayList<Rating>();
        IRater me=RaterDatabase.getRater(id);
        ArrayList<String>moviesRatedByMe=me.getItemsRated();
        ArrayList<Rating> weights=getSimilarities(id);
        for(String movieid:MovieDatabase.filterBy(new TrueFilter()))
        {
            double ratingsTotal=0.0;
            int n=0;
            for(int i=0;i<numSimilarRaters;i++)
            {  
           
                 IRater r=RaterDatabase.getRater(weights.get(i).getItem());
                 ArrayList<String>moviesRated=r.getItemsRated();
                 if(moviesRated.contains(movieid))
                 {
                    ratingsTotal+=r.getRating(movieid)*weights.get(i).getValue();
                    n+=1;
                 }           
            }
            if(n>=minRaters)
                out.add(new Rating(movieid,ratingsTotal/n));

        }      
        //System.out.println(n);
        Collections.sort(out,Collections.reverseOrder());
        return out;
    }
    

    public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters,int minRaters,Filter f)
    {
        ArrayList <Rating> out=new ArrayList<Rating>();
        IRater me=RaterDatabase.getRater(id);
        ArrayList<String>moviesRatedByMe=me.getItemsRated();
        ArrayList<Rating> weights=getSimilarities(id);
        for(String movieid:MovieDatabase.filterBy(f))
        {
            double ratingsTotal=0.0;
            int n=0;
            for(int i=0;i<numSimilarRaters;i++)
            {  
           
                 IRater r=RaterDatabase.getRater(weights.get(i).getItem());
                 ArrayList<String>moviesRated=r.getItemsRated();
                 if(moviesRated.contains(movieid))
                 {
                    ratingsTotal+=r.getRating(movieid)*weights.get(i).getValue();
                    n+=1;
                 }           
            }
            if(n>=minRaters)
                out.add(new Rating(movieid,ratingsTotal/n));

        }      
        //System.out.println(n);
        Collections.sort(out,Collections.reverseOrder());
        return out;
    }
}
