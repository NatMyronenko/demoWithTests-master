package com.example.demowithtests.dto;

import lombok.experimental.Accessors;

@Accessors(chain = true)
public class AddressDTO {
    public long id;
    public Boolean addresses_active = Boolean.TRUE;
    public String country;
    public String city;
    public String street;

}
