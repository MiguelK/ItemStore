package com.itemstore.api;

import com.itemstore.service.datastore.ServiceDataStorage;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

public class ItemGroupsZipServlet extends HttpServlet {
    private final static Logger LOG = Logger.getLogger(ItemGroupsZipServlet.class.getName());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceDataStorage serviceDataStorageDisk = ServiceDataStorage.useDefault();

        File zipFile = serviceDataStorageDisk.getCurrentVersion().orElseGet(null).getLangJSONZipped();//FIXME Only SWE

        if (zipFile == null) {
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Loading in progress?");
            return;
        }

        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=" + zipFile.getName());

        ServletOutputStream outputStream = response.getOutputStream();

        IOUtils.copy(new FileInputStream(zipFile), outputStream);

        outputStream.flush();
    }
}

