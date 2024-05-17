package com.example.restdemo.controller;

import com.example.restdemo.dto.CloudVendorDto;
import com.example.restdemo.service.CloudVendorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CloudVendorController.class)
class CloudVendorControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CloudVendorService cloudVendorService;
    private CloudVendorDto cloudVendorDtoOne;
    private CloudVendorDto cloudVendorDtoTwo;
    private List<CloudVendorDto> vendorDtoList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        cloudVendorDtoOne = CloudVendorDto.builder()
                .vendorId("1")
                .vendorName("Amazon")
                .vendorAddress("USA")
                .vendorPhoneNumber("xxxxxxxx").build();
        cloudVendorDtoTwo = CloudVendorDto.builder()
                .vendorId("2")
                .vendorName("Google")
                .vendorAddress("Canada")
                .vendorPhoneNumber("xxxxxxxx").build();
        vendorDtoList.add(cloudVendorDtoOne);
        vendorDtoList.add(cloudVendorDtoTwo);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAllCloudVendors() throws Exception{
        when(cloudVendorService.getAllVendors())
                .thenReturn(vendorDtoList);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/cloud-vendor"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetCloudVendorById() throws Exception {
        when(cloudVendorService.getVendorById("1"))
                .thenReturn(cloudVendorDtoOne);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/cloud-vendor/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testCreateCloudVendor() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String response = ow.writeValueAsString(cloudVendorDtoOne);

        when(cloudVendorService.addVendor(cloudVendorDtoOne))
                .thenReturn("add Vendor successfully");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/cloud-vendor")
                        .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(response))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void updateCloudVendor() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String response = ow.writeValueAsString(cloudVendorDtoTwo);

        when(cloudVendorService.updateVendor(cloudVendorDtoTwo))
                .thenReturn("update Vendor successfully");

        this.mockMvc.perform(MockMvcRequestBuilders.put("/cloud-vendor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(response))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCloudVendor() throws Exception{
        when(cloudVendorService.deleteVendor("1"))
                .thenReturn("Delete Vendor successfully");
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/cloud-vendor/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}