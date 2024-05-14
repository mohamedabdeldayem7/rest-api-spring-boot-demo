package com.example.restdemo.service;

import com.example.restdemo.dto.CloudVendorDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CloudVendorService {
    List<CloudVendorDto> getAllVendors();
    CloudVendorDto getVendorById(String id);
    void addVendor(CloudVendorDto vendor);
    void updateVendor(CloudVendorDto vendor);
    void deleteVendor(String id);
}
