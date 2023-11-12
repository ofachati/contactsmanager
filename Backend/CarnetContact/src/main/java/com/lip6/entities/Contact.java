package com.lip6.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;



@Component
@Entity
@Table(name = "Contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact")
    private long idContact;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "Email")
    private String email;
    
   
    @Qualifier("address")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    private Address address;

 
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contact",fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<PhoneNumber> phones;

    
    /*
    @JsonIgnore
    @Autowired
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "Contact_ContactGroup",
        joinColumns = @JoinColumn(name = "contact_id"),
        inverseJoinColumns = @JoinColumn(name = "contactgroup_id")
    )
    */
    @JsonIgnore
    @ManyToMany(mappedBy = "contacts",fetch = FetchType.EAGER)
    private Set<ContactGroup> contactGroups;
	public Contact(){
		
	}
	

	public Contact(String firstName, String lastName, String email, long idContact) {
		this(firstName, lastName, email);
		this.idContact = idContact;
	}


	public Contact(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}


	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	
	public String getFirstName(){
		return firstName;
	}
	
	public void setFirstName(String firstname){
		this.firstName = firstname;
	}
	
	
	public String getLastName(){
		return lastName;
	}
	
	public void setLastName(String lastname){
		this.lastName = lastname;
	}

	public long getIdContact() {
		return idContact;
	}

	public void setIdContact(long idContact) {
		this.idContact = idContact;
	}	
	public Address getAddress() {
	    return address;
	}

	public void setAddress(Address address) {
	    this.address = address;
	}

	public Set<PhoneNumber> getPhones() {
		
        return phones;
	}


	public void setPhones(Set<PhoneNumber> phones) {
		
		this.phones = phones;
	}


	public Set<ContactGroup> getContactGroups() {
		return contactGroups;
	}


	public void setContactGroups(Set<ContactGroup> contactGroups) {
		this.contactGroups = contactGroups;
	}
	
	
	
	public void addPhoneNumber(PhoneNumber phoneNumber) {
	    phoneNumber.setContact(this); // Set the reference to the parent contact
        this.phones.add(phoneNumber);
    }

    public void removePhoneNumber(PhoneNumber phoneNumber) {
        this.phones.remove(phoneNumber);
    }

    public void addContactGroup(ContactGroup contactGroup) {

        this.contactGroups.add(contactGroup);
    }

    public void removeContactGroup(ContactGroup contactGroup) {
        this.contactGroups.remove(contactGroup);
    }

}
