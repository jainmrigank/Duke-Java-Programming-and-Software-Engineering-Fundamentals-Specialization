import java.util.*;
import edu.duke.*;

public class MovieRunnerAverage
{
	public void getAverageRatingOneMovie()
	{
		SecondRatings sr=new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
		System.out.println(sr.getAverageOneMovie(sr.getID("The Godfather")));
	}
	public void printAverageRatings()
	{
		SecondRatings sr=new SecondRatings("ratedmovies_short.csv","ratings_short.csv");
		int numMovies=sr.getMovieSize();
		int numRaters=sr.getRaterSize();
		System.out.println("No. of Movies:"+numMovies);
		System.out.println("No. of Raters: "+numRaters);
		ArrayList<Rating> avgRatings=sr.getAverageRatings(3);
		Collections.sort(avgRatings);
		for(Rating r:avgRatings)
			System.out.println(r.getValue()+" "+sr.getTitle(r.getItem()));
	}

	public static void main(String[] args) {
		MovieRunnerAverage mra=new MovieRunnerAverage();
		mra.printAverageRatings();
		mra.getAverageRatingOneMovie();
	}
}