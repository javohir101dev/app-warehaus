package com.appwarehaus.controller;

import com.appwarehaus.entity.Measurement;
import com.appwarehaus.payload.Result;
import com.appwarehaus.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

//    CREATE
    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurement) {
        return measurementService.addMeasurement(measurement);
    }

//    READ
    @GetMapping()
    public List<Measurement> getMeasuremenstController(){
        return measurementService.getMeasurements();
    }

    @GetMapping("/{id}")
    public Measurement getMeasurementById(@PathVariable Integer id){
        return measurementService.getMeasurementById(id);
    }

//    UPDATE
    @PutMapping("/{id}")
    public Result updateMeasurementById(@PathVariable Integer id, @RequestBody Measurement measurement){
        return measurementService.editMeasurement(id, measurement);
    }

//    DELETE
    @DeleteMapping("/{id}")
    public Result deleteMeasurementById(@PathVariable Integer id){
        return measurementService.deleteMeasurementById(id);
    }
}
