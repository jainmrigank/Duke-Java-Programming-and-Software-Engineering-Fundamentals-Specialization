import java.util.*;
public class DirectorsFilter implements Filter {
	private String myDir;
	
	public DirectorsFilter(String d) {
		myDir=d;
	}
	
	@Override
	public boolean satisfies(String id) {
		String[] list=myDir.split(",");
		ArrayList <String> dlist=new ArrayList<String>();
		for(String d:list)
		{
			dlist.add(d);
		}
		if(dlist.contains(MovieDatabase.getDirector(id)))
			return true;
		return false;
	}

}