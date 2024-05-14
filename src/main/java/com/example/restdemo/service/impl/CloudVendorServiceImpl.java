package com.example.restdemo.service.impl;

import com.example.restdemo.dto.CloudVendorDto;
import com.example.restdemo.mapper.CloudVendorMapper;
import com.example.restdemo.model.CloudVendor;
import com.example.restdemo.repository.CloudVendorRepository;
import com.example.restdemo.service.CloudVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CloudVendorServiceImpl implements CloudVendorService {

    private CloudVendorMapper cloudVendorMapper;
    private CloudVendorRepository cloudVendorRepository;

    @Autowired
    public CloudVendorServiceImpl(CloudVendorRepository cloudVendorRepository, CloudVendorMapper cloudVendorMapper) {
        this.cloudVendorRepository = cloudVendorRepository;
        this.cloudVendorMapper = cloudVendorMapper;
    }

    @Override
    public List<CloudVendorDto> getAllVendors() {
        List<CloudVendor> cloudVendorList = cloudVendorRepository.findAll();
        List<CloudVendorDto> cloudVendorDtoList = new ArrayList<>();
        for (CloudVendor cloudVendor : cloudVendorList) {
            cloudVendorDtoList.add(cloudVendorMapper.getCloudVendorDto(cloudVendor));
        }
        return cloudVendorDtoList;
    }

    @Override
    public CloudVendorDto getVendorById(String id) {
        return cloudVendorMapper.getCloudVendorDto(cloudVendorRepository.findById(id).get());
    }

    @Override
    public void addVendor(CloudVendorDto vendor) {
        CloudVendor cloudVendor = cloudVendorMapper.getCloudVendor(vendor);
        cloudVendorRepository.save(cloudVendor);
    }

    @Override
    public void updateVendor(CloudVendorDto vendor) {
        CloudVendor cloudVendor = cloudVendorMapper.getCloudVendor(vendor);
        cloudVendorRepository.save(cloudVendor);
    }

    @Override
    public void deleteVendor(String id) {
        cloudVendorRepository.deleteById(id);
    }
}
