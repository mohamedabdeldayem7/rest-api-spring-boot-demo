package com.example.restdemo.controller;

import com.example.restdemo.dto.CloudVendorDto;
import com.example.restdemo.service.CloudVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cloud-vendor")
public class CloudVendorController {

    private CloudVendorService cloudVendorService;

    @Autowired
    public CloudVendorController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
    }

    @GetMapping
    public List<CloudVendorDto> getAllCloudVendors() {
        return cloudVendorService.getAllVendors();
    }

    @GetMapping("/{id}")
    public CloudVendorDto getCloudVendorById(@PathVariable String id) {
        return cloudVendorService.getVendorById(id);
    }

    @PostMapping
    public String createCloudVendor(@RequestBody CloudVendorDto cloudVendorDto) {
        cloudVendorService.addVendor(cloudVendorDto);
        return "add Vendor successfully";
    }

    @PutMapping
    public String updateCloudVendor(@RequestBody CloudVendorDto cloudVendorDto) {
        cloudVendorService.updateVendor(cloudVendorDto);
        return "update Vendor successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteCloudVendor(@PathVariable String id) {
        cloudVendorService.deleteVendor(id);
        return "Delete Vendor successfully";
    }


}
