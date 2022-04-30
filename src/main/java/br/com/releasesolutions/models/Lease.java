package br.com.releasesolutions.models;

import java.util.Date;

public class Lease {

    private User user;
    private Movie movie;
    private Date leaseDate;
    private Date deliveryDate;
    private Double price;

    public User getUser() {

        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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
