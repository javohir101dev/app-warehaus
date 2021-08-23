package com.appwarehaus.service;

import com.appwarehaus.entity.Measurement;
import com.appwarehaus.payload.Result;
import com.appwarehaus.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurement(Measurement measurement) {
        boolean exists = measurementRepository.existsByName(measurement.getName());
        if (exists) {
            return new Result("This measurement is already exist", false);
        }
        measurementRepository.save(measurement);
        return new Result("Measurement is added", true);
    }

    public List<Measurement> getMeasurements() {
        return measurementRepository.findAll();
    }

    public Measurement getMeasurementById(Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        return optionalMeasurement.orElse(null);
    }

    public Result editMeasurement(Integer id, Measurement measurement){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()){
            return new Result("Measurement is not found", false);
        }
        boolean exists = measurementRepository.existsByName(measurement.getName());
        if (exists) {
            return new Result("This measurement is already exist", false);
        }
        measurement.setId(id);
        measurementRepository.save(measurement);
        return new Result("Measurement is edited", true);
    }

    public Result deleteMeasurementById(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()){
            return new Result("Measurement is not found", false);
        }
        try {
            measurementRepository.deleteById(id);
            return new Result("Measurement is deleted", true);
        }catch (Exception e){
            return new Result("Error in deletind measurement", false);
        }
    }






}


