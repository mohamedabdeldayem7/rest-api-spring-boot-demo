package com.example.restdemo.repository;

import com.example.restdemo.model.CloudVendor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class CloudVendorRepositoryTest {

    @Autowired
    CloudVendorRepository cloudVendorRepository;
    CloudVendor cloudVendor;

    @BeforeEach
    void setUp() {
        cloudVendor = CloudVendor.builder()
                .vendorId("1")
                .vendorName("Google vendor")
                .vendorAddress("USA")
                .vendorPhoneNumber("xxxxxxxxx")
                .build();

        cloudVendorRepository.save(cloudVendor);
    }

    @AfterEach
    void tearDown() {
        cloudVendor = null;
        cloudVendorRepository.deleteAll();
    }

    @Test
    void testFindByVendorName_Found(){
        List<CloudVendor> vendors = this.cloudVendorRepository.findByVendorName("Google vendor");

        Assertions.assertThat(vendors.get(0).getVendorId()).isEqualTo("1");
        Assertions.assertThat(vendors.get(0).getVendorName()).isEqualTo("Google vendor");
        Assertions.assertThat(vendors.get(0).getVendorAddress()).isEqualTo("USA");
        Assertions.assertThat(vendors.get(0).getVendorPhoneNumber()).isEqualTo("xxxxxxxxx");

    }

}
