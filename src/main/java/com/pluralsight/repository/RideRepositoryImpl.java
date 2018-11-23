package com.pluralsight.repository;

import com.netease.backend.db.DBConnection;
import com.pluralsight.model.Ride;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Ride createRide(Ride ride) {
        jdbcTemplate.update("insert into ride (name, duration) values (?,?)", ride.getName(), ride.getDuration());

        try (DBConnection connection = (DBConnection) jdbcTemplate.getDataSource().getConnection()) {
            long id = connection.allocateRecordId("ride");
            System.out.println(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
//
//        List<String> columns = new ArrayList<>();
//        columns.add("name");
//        columns.add("duration");
//
//        insert.setTableName("ride");
//        insert.setColumnNames(columns);
//
//        Map<String, Object> data = new HashMap<>();
//        data.put("name", ride.getName());
//        data.put("duration", ride.getDuration());
//        insert.setGeneratedKeyName("id");
//
//        Number key = insert.executeAndReturnKey(data);
//
//        System.out.println(key);

        return null;
    }

    @Override
    public List<Ride> getRides() {
        Ride ride = new Ride();
        ride.setName("Corner Canyon");
        ride.setDuration(120);
        List<Ride> rides = new ArrayList<>();
        rides.add(ride);
        return rides;
    }

}
