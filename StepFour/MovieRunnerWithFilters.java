import java.util.*;
import java.io.*;


public class MovieRunnerWithFilters
{
	public void printAverageRatings()
	{
		ThirdRatings sr=new ThirdRatings("ratings_short.csv");
		MovieDatabase.initialize("ratedmovies_short.csv");


		int numMovies=MovieDatabase.size();
		int numRaters=sr.getRaterSize();
		System.out.println("No. of Movies:"+numMovies);
		System.out.println("No. of Raters: "+numRaters);


		ArrayList<Rating> avgRatings=sr.getAverageRatings(1);
		System.out.println("Found "+avgRatings.size()+" movies:");
		Collections.sort(avgRatings);
		for(Rating r:avgRatings)
			System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
	}


	public void printAverageRatingsByYear()
	{
		ThirdRatings sr=new ThirdRatings("ratings_short.csv");
		MovieDatabase.initialize("ratedmovies_short.csv");
		int year=2000;
		Filter yearf=new YearAfterFilter(year);
		ArrayList<Rating> movies =sr.getAverageRatingsByFilter(1,yearf);
		System.out.println("Found "+movies.size()+" movies:");
		Collections.sort(movies);
		for(Rating r:movies)
			System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
	}


	public void printAverageRatingsByGenre()
	{
		ThirdRatings sr=new ThirdRatings("ratings_short.csv");
		MovieDatabase.initialize("ratedmovies_short.csv");
		String g="Crime";
		Filter genref=new GenreFilter(g);
		ArrayList<Rating> movies =sr.getAverageRatingsByFilter(1,genref);
		System.out.println("Found "+movies.size()+" movies:");
		Collections.sort(movies);
		for(Rating r:movies)
		{
			System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
			System.out.println(MovieDatabase.getGenres(r.getItem()));
		}
	}

	public void printAverageRatingsByMinutes()
	{
		ThirdRatings sr=new ThirdRatings("ratings_short.csv");
		MovieDatabase.initialize("ratedmovies_short.csv");
		int min=110;
		int max=170;
		Filter minf=new MinutesFilter(min,max);
		ArrayList<Rating> movies =sr.getAverageRatingsByFilter(1,minf);
		System.out.println("Found "+movies.size()+" movies:");
		Collections.sort(movies);
		for(Rating r:movies)
			System.out.println(r.getValue()+" Time: "+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
	}


	public void printAverageRatingsByDirectors()
	{
		ThirdRatings sr=new ThirdRatings("ratings_short.csv");
		MovieDatabase.initialize("ratedmovies_short.csv");
		String d="Charles Chaplin,Michael Mann,Spike Jonze";
		Filter dirf=new DirectorsFilter(d);
		ArrayList<Rating> movies =sr.getAverageRatingsByFilter(1,dirf);
		System.out.println("Found "+movies.size()+" movies:");
		Collections.sort(movies);
		for(Rating r:movies)
		{
			System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
			System.out.println(MovieDatabase.getDirector(r.getItem()));
		}
	}


	public void printAverageRatingsByYearAfterAndGenre()
	{
		ThirdRatings sr=new ThirdRatings("ratings_short.csv");
		MovieDatabase.initialize("ratedmovies_short.csv");
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


	public void printAverageRatingsByDirectorsAndMinutes()
	{
		ThirdRatings sr=new ThirdRatings("ratings_short.csv");
		MovieDatabase.initialize("ratedmovies_short.csv");
		int min=30;
		int max=170;
		String d="Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola";
		Filter minf=new MinutesFilter(min,max);
		Filter dirf=new DirectorsFilter(d);
		AllFilters allf=new AllFilters();
		allf.addFilter(minf);
		allf.addFilter(dirf);
		ArrayList<Rating> movies =sr.getAverageRatingsByFilter(1,allf);
		System.out.println("Found "+movies.size()+" movies:");
		Collections.sort(movies);
		for(Rating r:movies)
		{
			System.out.println(r.getValue()+" Time: "+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
			System.out.println(MovieDatabase.getDirector(r.getItem()));
		}
	}

	public static void main(String[] args) {
		MovieRunnerWithFilters mra=new MovieRunnerWithFilters();
		//mra.printAverageRatings();
		//mra.printAverageRatingsByYear();
		//mra.printAverageRatingsByGenre();
		//mra.printAverageRatingsByMinutes();
		//mra.printAverageRatingsByDirectors();
		//mra.printAverageRatingsByYearAfterAndGenre();
		mra.printAverageRatingsByDirectorsAndMinutes();
		//mra.getAverageRatingOneMovie();
	}
}