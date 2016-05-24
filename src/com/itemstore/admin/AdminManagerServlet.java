package com.itemstore.admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class AdminManagerServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(AdminManagerServlet.class.getName());


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        writeJson(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        writeJson(request, response);
    }

    private void writeJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        Action action = ActionRegister.getInstance().getAction(request.getParameter("action"));
        LOG.info("Action " + request.getParameter("action") + " , Object=" + action);

        out.print(action.toJSON(request));

        out.flush();
    }
}
