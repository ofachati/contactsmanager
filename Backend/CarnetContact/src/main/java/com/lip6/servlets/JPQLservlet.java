
package com.lip6.servlets;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lip6.daos.DAOContact;
import com.lip6.entities.Contact;
import com.lip6.util.JpaUtil;


public class JPQLservlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Example usage of findContactsWithMostPhoneNumbers method
        List<Contact> contactsWithMostPhoneNumbers = findContactsWithMostPhoneNumbers();
        printContacts(response, "Contacts with the most phone numbers:", contactsWithMostPhoneNumbers);

        // Example usage of findContactsByCity method
        String city = "Paris"; // Replace with the desired city
        List<Contact> contactsInCity = findContactsByCity(city);
        printContacts(response, "Contacts in " + city + ":", contactsInCity);
    }

    private List<Contact> findContactsWithMostPhoneNumbers() {
        return new DAOContact().findContactsWithMostPhoneNumbers();
    }

    private List<Contact> findContactsByCity(String city) {
        return new DAOContact().findContactsByCity(city);
    }

    private void printContacts(HttpServletResponse response, String header, List<Contact> contacts)
            throws IOException {
        response.getWriter().println("<h2>" + header + "</h2>");
        response.getWriter().println("<ul>");
        for (Contact contact : contacts) {
            response.getWriter().println("<li>" + contact.getFirstName() + " " + contact.getLastName() + "</li>");
        }
        response.getWriter().println("</ul>");
    }
}
