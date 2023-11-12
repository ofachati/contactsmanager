package com.lip6.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lip6.entities.Contact;
import com.lip6.services.IContactService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class GetContactsServlet
 */
public class GetContactsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetContactsServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the Spring application context
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());

        // Retrieve the ContactService bean from the application context
        IContactService contactService = context.getBean(IContactService.class);

        // Fetch the list of contacts
        List<Contact> contacts = contactService.getAllContacts();

        /*
        // You can process the contacts as needed, e.g., pass them to a JSP for rendering
        // Example: request.setAttribute("contacts", contacts);
        // Then forward to a JSP to display the contacts.

        // In this example, we are just printing the contact information to the response.
        response.setContentType("text/html");
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h2>Contacts</h2>");
        for (Contact contact : contacts) {
            response.getWriter().println("ID: " + contact.getIdContact() + "<br>");
            response.getWriter().println("First Name: " + contact.getFirstName() + "<br>");
            response.getWriter().println("Last Name: " + contact.getLastName() + "<br>");
            response.getWriter().println("Email: " + contact.getEmail() + "<br><br>");
        }
        response.getWriter().println("</body></html>");
        */
        // Convert the contacts to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String contactsJson = objectMapper.writeValueAsString(contacts);

        // Set the content type to JSON
        response.setContentType("application/json");

        // Write the JSON to the response
        response.getWriter().write(contactsJson);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
