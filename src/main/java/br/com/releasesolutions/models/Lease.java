package br.com.releasesolutions.models;

import java.util.Date;
import java.util.List;

public class Lease {

    private User user;
    private List<Movie> movies;
    private Date leaseDate;
    private Date deliveryDate;
    private Double price;

    public User getUser() {

        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Date getLeaseDate() {

        return this.leaseDate;
    }

    public void setLeaseDate(Date leaseDate) {
        this.leaseDate = leaseDate;
    }

    public Date getDeliveryDate() {

        return this.deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getPrice() {

        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
