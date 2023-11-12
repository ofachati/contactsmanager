package com.lip6.entities;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PhoneNumbers")
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_phoneNumber")
    private long idPhoneNumber;

    @Column(name = "phoneKind")
    private String phoneKind;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_contact")
    private Contact contact;

    public PhoneNumber() {
    }

    public PhoneNumber(String phoneKind, String phoneNumber) {
        this.phoneKind = phoneKind;
        this.phoneNumber = phoneNumber;
    }

    public long getIdPhoneNumber() {
        return idPhoneNumber;
    }

    public void setIdPhoneNumber(long idPhoneNumber) {
        this.idPhoneNumber = idPhoneNumber;
    }

    public String getPhoneKind() {
        return phoneKind;
    }

    public void setPhoneKind(String phoneKind) {
        this.phoneKind = phoneKind;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

}
