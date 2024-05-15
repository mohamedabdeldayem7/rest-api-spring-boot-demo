package com.example.restdemo.service.impl;

import com.example.restdemo.dto.CloudVendorDto;
import com.example.restdemo.exception.CloudVendorNotFoundException;
import com.example.restdemo.mapper.CloudVendorMapper;
import com.example.restdemo.model.CloudVendor;
import com.example.restdemo.repository.CloudVendorRepository;
import com.example.restdemo.service.CloudVendorService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;


class CloudVendorServiceImplTest {

    @Mock
    private CloudVendorRepository cloudVendorRepository;
    @Mock
    private CloudVendorMapper cloudVendorMapper;
    private CloudVendorService cloudVendorService;
    private CloudVendor cloudVendor;
    private CloudVendorDto cloudVendorDto;
    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        cloudVendorService = new CloudVendorServiceImpl(cloudVendorRepository, cloudVendorMapper);
        cloudVendor = CloudVendor.builder()
                .vendorId("1")
                .vendorName("Amazon")
                .vendorAddress("USA")
                .vendorPhoneNumber("xxxxxxxxx").build();

        cloudVendorDto = CloudVendorDto.builder()
                .vendorId("1")
                .vendorName("Amazon")
                .vendorAddress("USA")
                .vendorPhoneNumber("xxxxxxxxx").build();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testSetAllVendors() {
        mock(CloudVendor.class);
        mock(CloudVendorDto.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findAll()).thenReturn(
                new ArrayList<CloudVendor>(Collections.singleton(cloudVendor))
        );

        when(cloudVendorMapper.getCloudVendorDto(cloudVendor)).thenReturn(cloudVendorDto);

        Assertions.assertThat(cloudVendorService.getAllVendors().get(0).getVendorPhoneNumber())
                .isEqualTo(cloudVendor.getVendorPhoneNumber());
    }

    @Test
    void getVendorById() {
        mock(CloudVendor.class);
        mock(CloudVendorDto.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorMapper.getCloudVendorDto(cloudVendor)).thenReturn(cloudVendorDto);
        when(cloudVendorRepository.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));

        Assertions.assertThat(cloudVendorService.getVendorById("1").getVendorPhoneNumber())
                .isEqualTo(cloudVendorDto.getVendorPhoneNumber());
    }

    @Test
    void getVendorById_WithException() {
        mock(CloudVendor.class);
        mock(CloudVendorDto.class);
        mock(CloudVendorRepository.class);

        String expectedMessage = "Requested cloud vendor does not exist";
        CloudVendorNotFoundException cloudVendorNotFoundException = new CloudVendorNotFoundException(expectedMessage);

        when(cloudVendorMapper.getCloudVendorDto(cloudVendor)).thenReturn(cloudVendorDto);
        when(cloudVendorRepository.findById("2")).thenThrow(cloudVendorNotFoundException);

        Assertions.assertThatExceptionOfType(CloudVendorNotFoundException.class)
                .isThrownBy(() -> cloudVendorService.getVendorById("2"))
                .withMessage(expectedMessage);
    }

    @Test
    void testAddVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorDto.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorMapper.getCloudVendor(cloudVendorDto)).thenReturn(cloudVendor);
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);

        Assertions.assertThat(cloudVendorService.addVendor(cloudVendorDto))
                .isEqualTo("add Vendor successfully");
    }

    @Test
    void testUpdateVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorDto.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorMapper.getCloudVendor(cloudVendorDto)).thenReturn(cloudVendor);
        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);

        Assertions.assertThat(cloudVendorService.updateVendor(cloudVendorDto))
                .isEqualTo("update Vendor successfully");
    }

    @Test
    void testDeleteVendor() {

        mock(CloudVendor.class);
        mock(CloudVendorDto.class);
        mock(CloudVendorRepository.class, Mockito.CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).when(
                cloudVendorRepository).deleteById(any());

        Assertions.assertThat(cloudVendorService.deleteVendor("1"))
                .isEqualTo("Delete Vendor successfully");
    }
}