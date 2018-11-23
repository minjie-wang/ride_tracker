package com.pluralsight.controller;

import com.pluralsight.model.Ride;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class RestControllerTest {

    @Test(timeout = 5000)
    public void testCreateRides() {
        RestTemplate restTemplate = new RestTemplate();

        Ride ride = new Ride();
        ride.setName("Yellow Fork Trail");
        ride.setDuration(35);

        ride = restTemplate.postForObject("http://localhost:8080/ride", ride, Ride.class);

        System.out.println("Ride: " + ride);
    }

    @Test(timeout = 5000)
    public void testGetRides() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
                "http://127.0.0.1:8080/rides", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Ride>>() {
                });
        List<Ride> rides = ridesResponse.getBody();

        for (Ride ride : rides) {
            System.out.println("Ride name: " + ride.getName());
        }
    }

    @Test(timeout = 5000)
    public void testGetRide() {
        RestTemplate restTemplate = new RestTemplate();

        Ride ride = restTemplate.getForObject(
                "http://127.0.0.1:8080/ride/1", Ride.class);
        System.out.println("Ride name: " + ride.getName());

    }

    @Test(timeout = 5000)
    public void testUpdateRide() {
        RestTemplate restTemplate = new RestTemplate();

        Ride ride = restTemplate.getForObject(
                "http://127.0.0.1:8080/ride/1", Ride.class);

        ride.setDuration(ride.getDuration() + 1);
        restTemplate.put("http://localhost:8080/ride", ride);
        System.out.println("Ride name: " + ride.getName());

    }

    @Test(timeout = 5000)
    public void testBatchUpdate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getForObject(
                "http://127.0.0.1:8080/batch", Object.class);

    }

    @Test(timeout = 5000)
    public void testDelete() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.delete(
                "http://127.0.0.1:8080/delete/4");

    }

    @Test(timeout = 5000)
    public void testException() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getForObject(
                "http://127.0.0.1:8080/test", Ride.class);

    }
}
