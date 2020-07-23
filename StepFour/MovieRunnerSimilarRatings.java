import java.util.*;
import java.io.*;

public class MovieRunnerSimilarRatings
{
public void printAverageRatings()
	{
		FourthRatings sr=new FourthRatings();
		MovieDatabase.initialize("ratedmovies_short.csv");
		RaterDatabase.initialize("ratings_short.csv");

		int numMovies=MovieDatabase.size();
		int numRaters=RaterDatabase.size();
		System.out.println("No. of Movies:"+numMovies);
		System.out.println("No. of Raters: "+numRaters);


		ArrayList<Rating> avgRatings=sr.getAverageRatings(1);
		System.out.println("Found "+avgRatings.size()+" movies:");
		Collections.sort(avgRatings);
		for(Rating r:avgRatings)
			System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
	}

public void printAverageRatingsByYearAfterAndGenre()
	{
		FourthRatings sr=new FourthRatings();
		MovieDatabase.initialize("ratedmovies_short.csv");
		RaterDatabase.initialize("ratings_short.csv");

		int numMovies=MovieDatabase.size();
		int numRaters=RaterDatabase.size();
		System.out.println("No. of Movies:"+numMovies);
		System.out.println("No. of Raters: "+numRaters);

		int year=1980;
		String g="Romance";
		Filter yearf=new YearAfterFilter(year);
		Filter genref=new GenreFilter(g);
		AllFilters allf=new AllFilters();
		allf.addFilter(yearf);
		allf.addFilter(genref);
		ArrayList<Rating> movies =sr.getAverageRatingsByFilter(1,allf);
		System.out.println("Found "+movies.size()+" movies:");
		Collections.sort(movies);
		for(Rating r:movies)
		{
			System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
			System.out.println(MovieDatabase.getGenres(r.getItem()));
		}
	}



	public void printSimilarRatings()
	{
		FourthRatings sr=new FourthRatings();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");

		int numMovies=MovieDatabase.size();
		int numRaters=RaterDatabase.size();
		System.out.println("No. of Movies:"+numMovies);
		System.out.println("No. of Raters: "+numRaters);
		ArrayList<Rating> simRatings=sr.getSimilarRatings("71",20,5);
System.out.println("Top recommended movies are: ");
		for(Rating r:simRatings)
		System.out.println(MovieDatabase.getTitle(r.getItem())+" "+r.getValue());
	}


	public void printSimilarRatingsByGenre()
	{
		FourthRatings sr=new FourthRatings();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");

		int numMovies=MovieDatabase.size();
		int numRaters=RaterDatabase.size();
		System.out.println("No. of Movies:"+numMovies);
		System.out.println("No. of Raters: "+numRaters);


		Filter g=new GenreFilter("Mystery");
		ArrayList<Rating> simRatings=sr.getSimilarRatingsByFilter("964",20,5,g);
System.out.println("\nTop recommended movies are: ");
		for(Rating r:simRatings)
		System.out.println(MovieDatabase.getTitle(r.getItem())+" "+r.getValue());
	}


	public void printSimilarRatingsByDirector()
	{
		FourthRatings sr=new FourthRatings();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");

		int numMovies=MovieDatabase.size();
		int numRaters=RaterDatabase.size();
		System.out.println("No. of Movies:"+numMovies);
		System.out.println("No. of Raters: "+numRaters);


		Filter g=new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
		ArrayList<Rating> simRatings=sr.getSimilarRatingsByFilter("120",10,2,g);
System.out.println("\nTop recommended movies are: ");
		for(Rating r:simRatings)
		System.out.println(MovieDatabase.getTitle(r.getItem())+" "+r.getValue());
	}


	public void printSimilarRatingsByGenreAndMinutes()
	{
		FourthRatings sr=new FourthRatings();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");

		int numMovies=MovieDatabase.size();
		int numRaters=RaterDatabase.size();
		System.out.println("No. of Movies:"+numMovies);
		System.out.println("No. of Raters: "+numRaters);

		Filter m=new MinutesFilter(80,160);
		Filter g=new GenreFilter("Drama");
		AllFilters allf=new AllFilters();
		allf.addFilter(m);
		allf.addFilter(g);
		ArrayList<Rating> simRatings=sr.getSimilarRatingsByFilter("168",10,3,allf);
System.out.println("Top recommended movies are: ");
		for(Rating r:simRatings)
		System.out.println(MovieDatabase.getTitle(r.getItem())+" "+r.getValue());
	}


	public void printSimilarRatingsByYearAfterAndMinutes()
	{
		FourthRatings sr=new FourthRatings();
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("ratings.csv");

		int numMovies=MovieDatabase.size();
		int numRaters=RaterDatabase.size();
		System.out.println("No. of Movies:"+numMovies);
		System.out.println("No. of Raters: "+numRaters);

		Filter m=new MinutesFilter(70,200);
		Filter g=new YearAfterFilter(1975);
		AllFilters allf=new AllFilters();
		allf.addFilter(m);
		allf.addFilter(g);
		ArrayList<Rating> simRatings=sr.getSimilarRatingsByFilter("314",10,5,allf);

		System.out.println("Top recommended movies are: ");
		for(Rating r:simRatings)
		System.out.println(MovieDatabase.getTitle(r.getItem())+" "+r.getValue());

	}


	public static void main(String[] args) {
		MovieRunnerSimilarRatings mrsr=new MovieRunnerSimilarRatings();
		//mrsr.printAverageRatings();
		//mrsr.printAverageRatingsByYearAfterAndGenre();	
		mrsr.printSimilarRatings();
		//mrsr.printSimilarRatingsByGenre();
		mrsr.printSimilarRatingsByDirector();
		mrsr.printSimilarRatingsByGenreAndMinutes();
		mrsr.printSimilarRatingsByYearAfterAndMinutes();
	}
}