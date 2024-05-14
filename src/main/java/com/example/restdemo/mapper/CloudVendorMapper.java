package com.example.restdemo.mapper;

import com.example.restdemo.dto.CloudVendorDto;
import com.example.restdemo.model.CloudVendor;
import org.mapstruct.Mapper;

@Mapper(
componentModel = "spring"
)
public interface CloudVendorMapper {
    CloudVendor getCloudVendor(CloudVendorDto cloudVendorDto);
    CloudVendorDto getCloudVendorDto(CloudVendor cloudVendor);
}
