package br.com.releasesolutions.models;

public class Movie {

    private String name;
    private Integer stock;
    private Double leasePrice;

    public Movie() {

    }

    public Movie(String name, Integer stock, Double leasePrice) {
        this.name = name;
        this.stock = stock;
        this.leasePrice = leasePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getLeasePrice() {
        return leasePrice;
    }

    public void setLeasePrice(Double leasePrice) {
        this.leasePrice = leasePrice;
    }
}
