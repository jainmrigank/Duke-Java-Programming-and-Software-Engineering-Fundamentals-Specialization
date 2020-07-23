import java.util.*;
import edu.duke.*;

public class MovieRunnerAverage
{
	public void getAverageRatingOneMovie()
	{
		SecondRatings sr=new SecondRatings("ratedmoviesfull.csv","ratings.csv");
		System.out.println(sr.getAverageOneMovie(sr.getID("Vacation")));
	}
	public void printAverageRatings()
	{
		SecondRatings sr=new SecondRatings("ratedmoviesfull.csv","ratings.csv");
		int numMovies=sr.getMovieSize();
		int numRaters=sr.getRaterSize();
		System.out.println("No. of Movies:"+numMovies);
		System.out.println("No. of Raters: "+numRaters);
		ArrayList<Rating> avgRatings=sr.getAverageRatings(49);
		Collections.sort(avgRatings);
		for(Rating r:avgRatings)
			System.out.println(r.getValue()+" "+sr.getTitle(r.getItem()));
	}

	public static void main(String[] args) {
		MovieRunnerAverage mra=new MovieRunnerAverage();
		mra.printAverageRatings();
		//mra.getAverageRatingOneMovie();
	}
}