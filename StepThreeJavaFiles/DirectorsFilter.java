import java.util.*;
public class DirectorsFilter implements Filter {
	private String myDir;
	
	public DirectorsFilter(String d) {
		myDir=d;
	}
	
	@Override
	public boolean satisfies(String id) {
		ArrayList<String> dirList= new ArrayList(Arrays.asList(myDir.split(",")));
		for(String dir:dirList){
			if(MovieDatabase.getDirector(id).contains(dir)){
				return true;
			}
		}
		return false;
	}

}