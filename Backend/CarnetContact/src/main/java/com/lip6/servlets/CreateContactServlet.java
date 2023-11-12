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
import com.lip6.services.IContactService;

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
	  
		//cette classe just pour demontrer 
		
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
	    
	    
        contact.setAddress(null);
        contact.setPhones(null);
        contact.setContactGroups(null);


	    // Retrieve the ContactService from the application context
	    IContactService contactService = context.getBean(ContactService.class);
	    contactService.createContact(contact);
	    
	    
	 // create and insert the test contact
	    Contact testContact = (Contact) context.getBean("contacttest");
	    contactService.createContact(testContact);


	}
	 
	

}
