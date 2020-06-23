/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ImageServlet", urlPatterns = {"/image/*","/frontend/image/*","/frontend/oteldetail/image/*","/frontend/turdetail/image/*","/admin/otel/image/*","/admin/tur/image/*"})
public class ImageServlet extends HttpServlet {
private String uploadPath;
 
    @Override
    public void init() {
        this.uploadPath = "C:\\Users\\Cypher\\Documents\\NetBeansProjects\\Tatil_Otomasyonu_v100\\web\\resources\\files\\";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        File f = new File(this.uploadPath + request.getPathInfo());
        response.reset();
        response.setContentType(request.getServletContext().getMimeType(f.getName()));
        Files.copy(f.toPath(), response.getOutputStream());
    }
}
