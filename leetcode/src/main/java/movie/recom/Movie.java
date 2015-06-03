package movie.recom;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
 
public class Movie {
	
    private final int movieId;
    private final float rating;
    private List<Movie> similarMovies; // Similarity is bidirectional
 
    public Movie(int movieId, float rating) {
        this.movieId = movieId;
        this.rating = rating;
        similarMovies = new ArrayList<Movie>();
    }
 
    public int getId() {
        return movieId;
    }
 
    public float getRating() {
        return rating;
    }
 
    public void addSimilarMovie(Movie movie) {
        similarMovies.add(movie);
        movie.similarMovies.add(this);
    }
 
    public List<Movie> getSimilarMovies() {
        return similarMovies;
    }
 
    /*
     * Implement a function to return top rated movies in the network of movies 
     * reachable from the current movie
     * eg:             A(Rating 1.2)
     *               /   \
     *            B(2.4)  C(3.6)
     *              \     /
     *                D(4.8)
     * In the above example edges represent similarity and the number is rating.
     * getMovieRecommendations(A,2) should return C and D (sorting order doesn't matter so it can also return D and C)
     * getMovieRecommendations(A,4) should return A, B, C, D (it can also return these in any order eg: B,C,D,A)
     * getMovieRecommendations(A,1) should return D. Note distance from A to D doesn't matter, return the highest rated.
     *     
     *     @param movie
     *     @param numTopRatedSimilarMovies 
     *                      number of movies we want to return
     *     @return List of top rated similar movies
     */
    public static List<Movie> getMovieRecommendations(Movie movie, int numTopRatedSimilarMovies) {
        if (movie == null)
        	return null;
        
        // Author: Fiona Guoguo Lu snowsguoguo@gmail.com
        // bfs
        // O(E + V), we'll visit every movie once, and every element in the similarity list once
        // use hashset to track what we've visited
        LinkedList<Movie> queue = new LinkedList<Movie>();
        HashSet<Movie> hashset = new HashSet<Movie>();
        
        // Local class for TreeSet to compare Movies by rating
    	class MovieComparatorByRating implements Comparator<Movie> {
    		public int compare(Movie o1, Movie o2) {
    			return (o2.rating - o1.rating) > 0 ? 1:-1;
    		}
    	}
    	
        // heap to keep the n top rated movies
        TreeSet<Movie> heap = new TreeSet<Movie>(new MovieComparatorByRating());
        
        queue.addAll(movie.similarMovies);
        hashset.addAll(movie.similarMovies);
        heap.addAll(movie.similarMovies);
        while (heap.size() > numTopRatedSimilarMovies)
        	heap.pollLast();
        
        while (!queue.isEmpty()) {
        	Movie m = queue.poll();
        	for (Movie mm : m.similarMovies) {
        		if (!hashset.contains(mm)) {
        			queue.add(mm);
        			hashset.add(mm);
        			heap.add(mm);
        			
        	        while (heap.size() > numTopRatedSimilarMovies)
        	        	heap.pollLast();
        		}
        	}
        }
        
        
        // Convert tree to a list structure and return 
        // O(n), where n=numTopRatedSimilarMovies
        List<Movie> list = new ArrayList<Movie>(numTopRatedSimilarMovies);
        for (Movie m : heap)
            list.add(m);
        return list;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if (this == obj)
    		return true;
    	if (obj instanceof Movie) {
    		// Assuming the movie id is unique
    		if (this.movieId == ((Movie)obj).movieId)
    			return true;
    	}
		return false;
    }
    
    @Override
    public int hashCode() {
    	// Randomly picking the hashcode
    	int idHash = 17 * this.movieId;
    	int ratingHash = 13 * (int)this.rating;
    	return idHash + ratingHash;
    }
    
    public static void main(String args[]) {
    	Movie a = new Movie(1, (float) 1.2);
    	Movie b = new Movie(2, (float) 2.4);
    	Movie c = new Movie(3, (float) 3.6);
    	Movie d = new Movie(4, (float) 4.8);
    	
    	a.addSimilarMovie(b);
    	a.addSimilarMovie(c);
    	b.addSimilarMovie(d);
    	c.addSimilarMovie(d);
    	
    	List<Movie>	case1 = Movie.getMovieRecommendations(a, 2);
    	assert case1.contains(c);
    	assert case1.contains(d);

    	List<Movie>	case2 = Movie.getMovieRecommendations(a, 4);
    	assert case2.contains(a);
    	assert case2.contains(b);
    	assert case2.contains(c);
    	assert case2.contains(d);
    	
    	List<Movie>	case3 = Movie.getMovieRecommendations(a, 1);
    	assert case3.contains(d);
    }
}
