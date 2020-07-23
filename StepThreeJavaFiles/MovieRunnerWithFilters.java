import java.util.*;
import java.io.*;


public class MovieRunnerWithFilters
{
	public void printAverageRatings()
	{
		ThirdRatings sr=new ThirdRatings("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");


		int numMovies=MovieDatabase.size();
		int numRaters=sr.getRaterSize();
		System.out.println("No. of Movies:"+numMovies);
		System.out.println("No. of Raters: "+numRaters);


		ArrayList<Rating> avgRatings=sr.getAverageRatings(35);
		System.out.println("Found "+avgRatings.size()+" movies:");
		Collections.sort(avgRatings);

		//for(Rating r:avgRatings)
		//	System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
	}


	public void printAverageRatingsByYear()
	{
		ThirdRatings sr=new ThirdRatings("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int year=2000;
		Filter yearf=new YearAfterFilter(year);
		ArrayList<Rating> movies =sr.getAverageRatingsByFilter(20,yearf);
		System.out.println("Found "+movies.size()+" movies:");
		Collections.sort(movies);
		//for(Rating r:movies)
		//	System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
	}


	public void printAverageRatingsByGenre()
	{
		ThirdRatings sr=new ThirdRatings("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		String g="Comedy";
		Filter genref=new GenreFilter(g);
		ArrayList<Rating> movies =sr.getAverageRatingsByFilter(20,genref);
		System.out.println("Found "+movies.size()+" movies:");
		Collections.sort(movies);
		//for(Rating r:movies)
		//{
		//	System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
		//	System.out.println(MovieDatabase.getGenres(r.getItem()));
		//}
	}

	public void printAverageRatingsByMinutes()
	{
		ThirdRatings sr=new ThirdRatings("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int min=105;
		int max=135;
		Filter minf=new MinutesFilter(min,max);
		ArrayList<Rating> movies =sr.getAverageRatingsByFilter(5,minf);
		System.out.println("Found "+movies.size()+" movies:");
		Collections.sort(movies);
		//for(Rating r:movies)
		//	System.out.println(r.getValue()+" Time: "+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
	}


	public void printAverageRatingsByDirectors()
	{
		ThirdRatings sr=new ThirdRatings("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		String d="Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
		Filter dirf=new DirectorsFilter(d);
		ArrayList<Rating> movies =sr.getAverageRatingsByFilter(4,dirf);
		System.out.println("Found "+movies.size()+" movies:");
		Collections.sort(movies);
		//for(Rating r:movies)
		//{
		//	System.out.println(r.getValue()+" "+MovieDatabase.getTitle(r.getItem()));
		//	System.out.println(MovieDatabase.getDirector(r.getItem()));
		//}
	}


	public void printAverageRatingsByYearAfterAndGenre()
	{
		ThirdRatings sr=new ThirdRatings("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int year=1990;
		String g="Drama";
		Filter yearf=new YearAfterFilter(year);
		Filter genref=new GenreFilter(g);
		AllFilters allf=new AllFilters();
		allf.addFilter(yearf);
		allf.addFilter(genref);
		ArrayList<Rating> movies =sr.getAverageRatingsByFilter(8,allf);
		System.out.println("Found "+movies.size()+" movies:");
		Collections.sort(movies);
		//for(Rating r:movies)
		//{
		//	System.out.println(r.getValue()+" "+MovieDatabase.getYear(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
		//	System.out.println(MovieDatabase.getGenres(r.getItem()));
		//}
	}


	public void printAverageRatingsByDirectorsAndMinutes()
	{
		ThirdRatings sr=new ThirdRatings("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int min=90;
		int max=180;
		String d="Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
		Filter minf=new MinutesFilter(min,max);
		Filter dirf=new DirectorsFilter(d);
		AllFilters allf=new AllFilters();
		allf.addFilter(minf);
		allf.addFilter(dirf);
		ArrayList<Rating> movies =sr.getAverageRatingsByFilter(3,allf);
		System.out.println("Found "+movies.size()+" movies:");
		Collections.sort(movies);
		//for(Rating r:movies)
		//{
		//	System.out.println(r.getValue()+" Time: "+MovieDatabase.getMinutes(r.getItem())+" "+MovieDatabase.getTitle(r.getItem()));
	//		System.out.println(MovieDatabase.getDirector(r.getItem()));
	//	}
	}

	public static void main(String[] args) {
		MovieRunnerWithFilters mra=new MovieRunnerWithFilters();
		mra.printAverageRatings();
		mra.printAverageRatingsByYear();
		mra.printAverageRatingsByGenre();
		mra.printAverageRatingsByMinutes();
		mra.printAverageRatingsByDirectors();
		mra.printAverageRatingsByYearAfterAndGenre();
		mra.printAverageRatingsByDirectorsAndMinutes();
		//mra.getAverageRatingOneMovie();
	}
}