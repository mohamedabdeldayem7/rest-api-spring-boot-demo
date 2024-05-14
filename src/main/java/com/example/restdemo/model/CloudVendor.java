package com.example.restdemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name = "cloud_vendor_info")
@EqualsAndHashCode
@Builder
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
public class CloudVendor {

    @Id
    private String vendorId;
    private String vendorName;
    private String vendorAddress;
    private String vendorPhoneNumber;
}
