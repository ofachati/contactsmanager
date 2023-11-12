package com.lip6.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lip6.daos.IDAOContact;
import com.lip6.entities.Address;
import com.lip6.entities.Contact;
import com.lip6.entities.ContactGroup;
import com.lip6.entities.PhoneNumber;
import com.lip6.services.ContactService;

/**
 * Servlet implementation class CreateContactServlet
 */
public class CreateContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		
		
	      /*
	  String fname=request.getParameter("fname");
	  String lname=request.getParameter("lname");
	  String email=request.getParameter("email");
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       	  */
	 // ContactService sc=new ContactService();
	  //sc.createContact(fname, lname, email);
	  
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());


		 // Retrieve parameters from the HTTP request
	    String fname = request.getParameter("fname");
	    String lname = request.getParameter("lname");
	    String email = request.getParameter("email");

	 // Create a Contact object
	    Contact contact = (Contact) context.getBean("contact");

	    // Set the properties
	    contact.setFirstName(fname);
	    contact.setLastName(lname);
	    contact.setEmail(email);
	    
	    // Capture optional properties: Address, PhoneNumbers, and ContactGroups
        //Optional<Address> addressOptional = Optional.ofNullable((Address) context.getBean("address"));
        //Optional<Set<PhoneNumber>> phoneNumbersOptional = Optional.ofNullable((Set<PhoneNumber>) context.getBean("phoneNumber"));
        //Optional<Set<ContactGroup>> contactGroupsOptional = Optional.ofNullable((Set<ContactGroup>) context.getBean("contactGroup"));

        // Set optional properties if present
        //addressOptional.ifPresent(contact::setAddress);
	    
        contact.setAddress(null);
        contact.setPhones(null);
        contact.setContactGroups(null);

        //phoneNumbersOptional.ifPresent(contact::setPhones);
        //contactGroupsOptional.ifPresent(contact::setContactGroups);


	    // Capture phone numbers and contact groups data from the form
	    //String phoneNumbersInput = request.getParameter("phoneNumbers");
	    //String contactGroupsInput = request.getParameter("contactGroups");

	    // Process and split phone numbers and contact groups if provided
	    //Set<PhoneNumber> phoneNumbers = new HashSet<>();
	    //Set<ContactGroup> contactGroups = new HashSet<>();
/*
	    if (!phoneNumbersInput.isEmpty()) {
	        String[] phoneNumberArray = phoneNumbersInput.split(",");
	        for (String phoneNumberStr : phoneNumberArray) {
	            PhoneNumber phoneNumber = context.getBean(PhoneNumber.class);
	            phoneNumber.setPhoneNumber(phoneNumberStr.trim()); // Trim spaces
	            phoneNumbers.add(phoneNumber);
	        }
	    }

	    if (!contactGroupsInput.isEmpty()) {
	        String[] contactGroupsArray = contactGroupsInput.split(",");
	        for (String contactGroupName : contactGroupsArray) {
	            ContactGroup contactGroup = context.getBean(ContactGroup.class);
	            contactGroup.setGroupName(contactGroupName.trim()); // Trim spaces
	            contactGroups.add(contactGroup);
	        }
	    }

	    // Set phone numbers and contact groups in the contact
	    contact.setPhones(phoneNumbers);
	    contact.setContactGroups(contactGroups);
*/

	    // Retrieve the ContactService from the application context
	    ContactService contactService = context.getBean(ContactService.class);

	    // Call the createContact method to add the contact
	    contactService.createContact(contact);
	    
	    
	 // create and insert the test contact
	    Contact testContact = (Contact) context.getBean("contacttest");
	    contactService.createContact(testContact);


	}
	 
	

}
