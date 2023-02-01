package e4;

import java.util.*;

public class Movie {
    String title;
    List <MovieRating> Grades;

    //constructor: creates a new triangle
    public Movie(String title) {
        Grades = new ArrayList<>();
        this.title=title;
    }
    //getter: gets the title
    public String getTitle() {
        return (this.title);
    }
    //inserts a new rating in the list
    public void insertRating(MovieRating movieRating) {
        this.Grades.add(movieRating);
    }
    //checks for ratings different from NOT_RATED, if it hasn't got ratings return false
    private boolean isRated() {
        if(this.Grades.isEmpty()) return false;
        else {
            for (MovieRating movieRating : this.Grades) {
                if (movieRating.rating != -1) {
                    return true;
                }
            }
        }
        return false;
    }
    //these methods return the maximum rating of a movie. If not rated, return NOT_RATED
    public MovieRating maximumRating() {
        if(!this.isRated()) return MovieRating.NOT_RATED;
        MovieRating max = this.Grades.get(0);
        for (int i = 1; i< this.Grades.size(); i++) {
            if(this.Grades.get(i).isBetterThan(max)){
                max = this.Grades.get(i) ;
            }
        }
        return max;
    }
    //these methods return the average rating of a movie. If not rated, return exception
    public double averageRating() {
        double add=0, length=0;
        if(!this.isRated()) throw new NoSuchElementException();
        //checks through the list adding the numbers assigned to the rating
        for (MovieRating movieRating : this.Grades) {
                if (movieRating.rating != -1) {
                    length++;
                    add += movieRating.rating;
                }
        }
        //returns the average rating
        return (add/length);
    }
}
