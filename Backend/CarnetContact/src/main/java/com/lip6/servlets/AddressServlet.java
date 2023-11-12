package com.lip6.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lip6.entities.Address;
import com.lip6.services.IAddressService;

/**
 * Servlet implementation class AddressServlet
 */
public class AddressServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddressServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        IAddressService addressService = context.getBean(IAddressService.class);

        String idParam = request.getParameter("id");
        if (idParam != null) {
            long id = Long.parseLong(idParam);
            Address address = addressService.getAddressById(id);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(address);
            response.setContentType("application/json");
            response.getWriter().write(json);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        IAddressService addressService = context.getBean(IAddressService.class);

        ObjectMapper objectMapper = new ObjectMapper();
        Address address = objectMapper.readValue(request.getReader(), Address.class);
        addressService.createAddress(address);
        response.getWriter().println("Address added successfully");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        IAddressService addressService = context.getBean(IAddressService.class);

        ObjectMapper objectMapper = new ObjectMapper();
        Address address = objectMapper.readValue(request.getReader(), Address.class);
        addressService.updateAddress(address);
        response.getWriter().println("Address updated successfully");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        IAddressService addressService = context.getBean(IAddressService.class);

        String idParam = request.getParameter("id");
        if (idParam != null) {
            long id = Long.parseLong(idParam);
            addressService.deleteAddress(id);
            response.getWriter().println("Address deleted successfully");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID parameter missing");
        }
    }
}
