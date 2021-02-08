package com.softserve.itacademy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class AddressBookBaseServlet extends HttpServlet {
    protected static final Pattern PATTERN =
            Pattern.compile("^First name: (.+), Last name: (.+), Address: (.+)$");
    protected AddressBook dao;

    @Override
    public void init() {
        dao = AddressBook.getInstance();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        super.service(req, resp);
    }
}
