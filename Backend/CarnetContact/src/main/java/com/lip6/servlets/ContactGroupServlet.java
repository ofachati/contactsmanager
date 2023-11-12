package com.lip6.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lip6.entities.ContactGroup;
import com.lip6.services.IContactGroupService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ContactGroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ContactGroupServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        IContactGroupService contactGroupService = context.getBean(IContactGroupService.class);

        String idParam = request.getParameter("id");
        if (idParam != null) {
            long id = Long.parseLong(idParam);
            ContactGroup contactGroup = contactGroupService.getContactGroupById(id);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(contactGroup);
            response.setContentType("application/json");
            response.getWriter().write(json);
        } else {
            List<ContactGroup> contactGroups = contactGroupService.getAllContactGroups();
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(contactGroups);
            response.setContentType("application/json");
            response.getWriter().write(json);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        IContactGroupService contactGroupService = context.getBean(IContactGroupService.class);

        ObjectMapper objectMapper = new ObjectMapper();
        ContactGroup contactGroup = objectMapper.readValue(request.getReader(), ContactGroup.class);
        contactGroupService.createContactGroup(contactGroup);
        //response.getWriter().println("Contact group added successfully");        
        String jsonResponse = "{\"status\": \"success\", \"message\": \"Contact group added successfully\"}";
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
        
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        IContactGroupService contactGroupService = context.getBean(IContactGroupService.class);

        ObjectMapper objectMapper = new ObjectMapper();
        ContactGroup contactGroup = objectMapper.readValue(request.getReader(), ContactGroup.class);
        contactGroupService.updateContactGroup(contactGroup);
        
        String jsonResponse = "{\"status\": \"success\", \"message\": \"Contact group updated successfully\"}";
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        IContactGroupService contactGroupService = context.getBean(IContactGroupService.class);

        String idParam = request.getParameter("id");
        if (idParam != null) {
            long id = Long.parseLong(idParam);
            contactGroupService.deleteContactGroup(id);
        
            String jsonResponse = "{\"status\": \"success\", \"message\": \"Contact group deleted successfully\"}";
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse);
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID parameter is missing");
        }
    }
}
