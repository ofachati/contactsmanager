package com.lip6.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lip6.entities.PhoneNumber;
import com.lip6.services.IPhoneNumberService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PhoneNumberServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PhoneNumberServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        IPhoneNumberService phoneNumberService = context.getBean(IPhoneNumberService.class);

        String idParam = request.getParameter("id");
        if (idParam != null) {
            long id = Long.parseLong(idParam);
            PhoneNumber phoneNumber = phoneNumberService.getPhoneNumberById(id);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(phoneNumber);
            response.setContentType("application/json");
            response.getWriter().write(json);
        } else {
            List<PhoneNumber> phoneNumbers = phoneNumberService.getAllPhoneNumbers();
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(phoneNumbers);
            response.setContentType("application/json");
            response.getWriter().write(json);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        IPhoneNumberService phoneNumberService = context.getBean(IPhoneNumberService.class);

        ObjectMapper objectMapper = new ObjectMapper();
        PhoneNumber phoneNumber = objectMapper.readValue(request.getReader(), PhoneNumber.class);
        phoneNumberService.createPhoneNumber(phoneNumber);
        response.getWriter().println("Phone number added successfully");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        IPhoneNumberService phoneNumberService = context.getBean(IPhoneNumberService.class);

        ObjectMapper objectMapper = new ObjectMapper();
        PhoneNumber phoneNumber = objectMapper.readValue(request.getReader(), PhoneNumber.class);
        phoneNumberService.updatePhoneNumber(phoneNumber);
        response.getWriter().println("Phone number updated successfully");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        IPhoneNumberService phoneNumberService = context.getBean(IPhoneNumberService.class);

        String idParam = request.getParameter("id");
        if (idParam != null) {
            long id = Long.parseLong(idParam);
            phoneNumberService.deletePhoneNumber(id);
            response.getWriter().println("Phone number deleted successfully");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID parameter missing");
        }
    }
}
