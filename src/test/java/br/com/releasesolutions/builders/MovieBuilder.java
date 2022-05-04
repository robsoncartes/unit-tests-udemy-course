package br.com.releasesolutions.builders;

import br.com.releasesolutions.models.Movie;

public class MovieBuilder {

    private Movie movie;

    private MovieBuilder() {

    }

    public static MovieBuilder getMovieBuilderInstance() {

        MovieBuilder builder = new MovieBuilder();
        builder.movie = new Movie();
        builder.movie.setName("Movie 1");
        builder.movie.setStock(2);
        builder.movie.setLeasePrice(4.0);

        return builder;
    }

    public MovieBuilder getWithoutStock() {

        movie.setStock(0);

        return this;
    }

    public MovieBuilder getWithLeasePriceEqualsTo(Double leasePrice) {

        movie.setLeasePrice(leasePrice);

        return this;
    }

    public Movie getMovie() {

        return movie;
    }
}
