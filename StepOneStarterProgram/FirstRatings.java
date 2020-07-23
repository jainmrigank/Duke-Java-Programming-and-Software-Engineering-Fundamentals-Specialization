import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
import java.io.*;

public class FirstRatings
{
	public ArrayList<Movie> loadMovies(String fname)
	{
		FileResource fr = new FileResource(fname);
		ArrayList <Movie> out=new ArrayList<Movie>();
		//CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
		
		//initialize the CSVParser object
		CSVParser parser = fr.getCSVParser();
		//CSVParser csvp=new CSVParser(fname);
		for(CSVRecord r:parser)
		{
			Movie m=new Movie(r.get(0),r.get(1),r.get(2),r.get(3),r.get(4),r.get(5),Integer.parseInt(r.get(6)),r.get(7));
			out.add(m);
		}
		return out;
	}

	public void testLoadMovies()
	{
		ArrayList <Movie> movies=loadMovies("data/ratedmoviesfull.csv");
		System.out.println("No. of movies:"+movies.size());
		HashMap <String,Integer> directors=new HashMap<String,Integer>();
		int comedy=0;
		int ln150=0;
		for(Movie m:movies)
		{
			String dir=m.getDirector();
			if(!directors.containsKey(dir))
				directors.put(dir,1);
			else
				directors.put(dir,directors.get(dir)+1);

			System.out.println(m);
			if(m.getGenres().indexOf("Comedy")!=-1)
				comedy+=1;
			if(m.getMinutes()>150)
				ln150+=1;
		}
		System.out.println("No.of Comedy Movies:"+comedy);
		System.out.println("No.of 150 min long Movies:"+ln150);
		int max=-1;
		int n=0;
		for(String d:directors.keySet())
		{
			if(directors.get(d)>max)
				max=directors.get(d);
		}

		for(String d:directors.keySet())
		{
			if(directors.get(d)==max)
				{
					n+=1;
					System.out.println(d);
				}
			
		}
		System.out.println("Maximum movies by any director is "+max);
		System.out.println("There are "+n+" directors that directed such movie");
	}

	public ArrayList<Rater> loadRaters(String fname)
	{
		ArrayList <Rater> out=new ArrayList<Rater>();
		FileResource fr=new FileResource(fname);
		CSVParser csvp=fr.getCSVParser();
		for(CSVRecord r:csvp)
		{
			int f=0;
			String id=r.get(0);
			for(Rater rater:out)
			{
				if(rater.getID().equals(id))
				{
					rater.addRating(r.get(1),Double.parseDouble(r.get(2)));
				}
				else
				{
					f=-1;
				}
			}
			if(f==-1)
			{
					Rater m=new Rater(id);
					m.addRating(r.get(1),Double.parseDouble(r.get(2)));
					out.add(m);
			}
			
		}
		return out;
	}

	
	public void testLoadRaters()
	{
		ArrayList <Rater> raters=loadRaters("data/ratings.csv");
		//System.out.println("No. of movies:"+movies.size());
		HashMap <Integer,Integer> ratersH=new HashMap<Integer,Integer>();
		int raterid=193;
		int comedy=0;
		int ln150=0;
		for(Rater r:raters)
		{
			int id=Integer.parseInt(r.getID());
			if(!ratersH.containsKey(id))
				ratersH.put(id,1);
			else
				ratersH.put(id,ratersH.get(id)+1);
		}
		System.out.println("No of raters:"+ratersH.size()+" No.of ratings by each: "+ratersH);
		System.out.println("Their ratings with movie ids are:");
		int max=-1;
		int nmaxraters=0;
		int id1=0;
		for(int id:ratersH.keySet())
		{
			if(id==raterid)
				//System.out.println("The rater with id "+raterid+" rated "+ratersH.get(raterid)+" movies.");
				id1=ratersH.get(raterid);

			if(max<ratersH.get(id))
				max=ratersH.get(id);

			for(Rater r:raters)
			{
				if(Integer.parseInt(r.getID())==id)
				{
					System.out.println(r.getItemsRated());
					for(String item:r.getItemsRated())
						System.out.println(r.getRating(item));
				}
			}
			
		}
		System.out.println("The rater with id "+raterid+" rated "+id1+" movies.");
		System.out.println("max "+max);
		for(int id:ratersH.keySet())
		{
			if(ratersH.get(id)==max)
			{
				System.out.println("The rater "+id+" has the maximum no. of ratings.");
				nmaxraters+=1;
			}
		}
		System.out.println("The no. of raters with maximum ratings is "+nmaxraters);

		HashMap <String,Integer> moviesRatedH=new HashMap<String,Integer>();
		for(Rater r:raters)
		{   ArrayList<String> moviesRated=r.getItemsRated();
			for(String id:moviesRated)
			{	
				if(!moviesRatedH.containsKey(id))
					moviesRatedH.put(id,1);
				else
					moviesRatedH.put(id,moviesRatedH.get(id)+1);
			}
		}
		System.out.println("The no. of ratings each movie has:");
		System.out.println(moviesRatedH.get("1798709"));
		System.out.println("The no.of different movies rated are: "+moviesRatedH.size());
	}

	public static void main(String[] args)
	{
		FirstRatings fr=new FirstRatings();
		//fr.testLoadMovies();
		fr.testLoadRaters();
	}
}