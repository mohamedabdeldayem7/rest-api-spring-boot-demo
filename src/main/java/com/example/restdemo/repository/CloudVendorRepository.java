package com.example.restdemo.repository;

import com.example.restdemo.model.CloudVendor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CloudVendorRepository extends JpaRepository<CloudVendor, String> {

}
