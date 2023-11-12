package com.lip6.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lip6.entities.Contact;
import com.lip6.services.ContactService;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ContactServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        ContactService contactService = context.getBean(ContactService.class);

        String idParam = request.getParameter("id");
        if (idParam != null) {
            long id = Long.parseLong(idParam);
            Contact contact = contactService.getContactById(id);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(contact);
            response.setContentType("application/json");
            response.getWriter().write(json);
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(contactService.getAllContacts());
            response.setContentType("application/json");
            response.getWriter().write(json);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        ContactService contactService = context.getBean(ContactService.class);

        ObjectMapper objectMapper = new ObjectMapper();
        Contact contact = objectMapper.readValue(request.getReader(), Contact.class);
        boolean success = contactService.createContact(contact);

        if (success) {
        	String jsonResponse = "{\"status\": \"success\", \"message\": \"Contact added successfully\"}";
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add contact");
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        ContactService contactService = context.getBean(ContactService.class);

        ObjectMapper objectMapper = new ObjectMapper();
        Contact contact = objectMapper.readValue(request.getReader(), Contact.class);
        boolean success = contactService.updateContact(contact);

        if (success) {
            String jsonResponse = "{\"status\": \"success\", \"message\": \"Contact updated successfully\"}";
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse);
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update contact");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        ContactService contactService = context.getBean(ContactService.class);

        String idParam = request.getParameter("id");
        if (idParam != null) {
            long id = Long.parseLong(idParam);
            boolean success = contactService.deleteContact(id);

            if (success) {
            	 String jsonResponse = "{\"status\": \"success\", \"message\": \"Contact deleted successfully\"}";
                 response.setContentType("application/json");
                 response.getWriter().write(jsonResponse);
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete contact");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID parameter is missing");
        }
    }
}
