package e4;
//an enum is created which holds all the possible ratings
public enum MovieRating {
    NOT_RATED(-1),
    AWFUL(0),
    BAD(2),
    MEDIOCRE(4),
    GOOD(6),
    EXCELLENT(8),
    MASTERPIECE(10);

    public final int rating;
    //this method returns the rating of a certain movie
    public int getNumericRating(){
        return this.rating;
    }
    //this compares the movie ratings using the previous method
    public boolean isBetterThan(MovieRating other){
        return this.getNumericRating()> other.getNumericRating();
    }
    //Basic constructor
    MovieRating (int rating){
        this.rating=rating;
    }
}
