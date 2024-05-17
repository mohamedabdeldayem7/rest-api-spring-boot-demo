package com.example.restdemo.controller;

import com.example.restdemo.dto.CloudVendorDto;
import com.example.restdemo.response.ResponseHandler;
import com.example.restdemo.service.CloudVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cloud-vendor")
public class CloudVendorController {

    private CloudVendorService cloudVendorService;

    @Autowired
    public CloudVendorController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllCloudVendors() {
        return ResponseHandler.responseBuilder("All vendors details are given here",
                HttpStatus.OK, cloudVendorService.getAllVendors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCloudVendorById(@PathVariable String id) {
        return ResponseHandler.responseBuilder("Requested vendor details are given here",
                HttpStatus.OK, cloudVendorService.getVendorById(id));
    }

    @PostMapping
    public String createCloudVendor(@RequestBody CloudVendorDto cloudVendorDto) {
        cloudVendorService.addVendor(cloudVendorDto);
        return "add Vendor successfully";
    }

    @PutMapping
    public String updateCloudVendor(@RequestBody CloudVendorDto cloudVendorDto) {
        return cloudVendorService.updateVendor(cloudVendorDto);
    }

    @DeleteMapping("/{id}")
    public String deleteCloudVendor(@PathVariable String id) {
        return cloudVendorService.deleteVendor(id);
    }


}
