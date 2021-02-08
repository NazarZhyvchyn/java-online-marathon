package com.softserve.itacademy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/records/read")
public class ReadRecordServlet extends AddressBookBaseServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String firstname = request.getParameter("first-name");
        firstname = firstname != null ? firstname.strip() : "";

        String lastname = request.getParameter("last-name");
        lastname = lastname != null ? lastname.strip() : "";

        String address = dao.read(firstname, lastname);
        if (address == null) {
            request.setAttribute("error", String.format(
                    "Person with name '%s %s' has not been found in Address Book!",
                    firstname, lastname));
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            request.getRequestDispatcher("/WEB-INF/error.jsp")
                    .forward(request, response);
        } else {
            request.setAttribute("firstname", firstname);  // striped
            request.setAttribute("lastname", lastname);    // striped
            request.setAttribute("address", address);
            request.getRequestDispatcher("/WEB-INF/read-record.jsp")
                    .forward(request, response);
        }
    }
}
