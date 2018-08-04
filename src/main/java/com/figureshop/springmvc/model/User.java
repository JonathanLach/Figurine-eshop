package com.figureshop.springmvc.model;

import com.figureshop.springmvc.constants.RegexConstants;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

public class User implements Serializable {
    private Long id;

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 12)
    private String password;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @NotNull
    @NotBlank
    @Email
    private String mail;

    private Date registerDate = new Date();

    @Pattern(regexp = RegexConstants.ONLY_DIGITS)
    @NotBlank
    @NotNull
    private String phoneNumber;

    private Boolean isSubNewLetters;

    @Pattern(regexp = RegexConstants.ONLY_POSITIVE_NUMBERS, message = "Pattern.user.streetNumber")
    @NotBlank
    @NotNull
    private String streetNumber;

    @NotBlank
    @NotNull
    private String streetName;

    @NotBlank
    @NotNull
    private String postalCode;

    @NotBlank
    @NotNull
    private String locality;

    @NotBlank
    @NotNull
    private String country;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getIsSubNewLetters() {
        return isSubNewLetters;
    }

    public void setIsSubNewLetters(Boolean subNewLetters) {
        isSubNewLetters = subNewLetters;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

}
