/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.admin.controller;

import com.admin.dao.DosyaDao;
import com.admin.dao.OtelDao;
import com.admin.entity.Dosya;
import com.admin.entity.Otel;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@SessionScoped
public class OtelBean implements Serializable {

    private Part part;
    private Otel module;
    private OtelDao moduleDao;
    private ArrayList<Otel> moduleList;
    private ArrayList<Otel> modulePageList;
    private final String uploadPath = "C:\\Users\\Cypher\\Documents\\NetBeansProjects\\Tatil_Otomasyonu_v100\\web\\resources\\files\\";
    private DosyaDao dosyaDao;
    
    
    @Inject
    OzellikBean ozellikBean;

    public OzellikBean getOzellikBean() {
        if(this.ozellikBean==null)
            this.ozellikBean=new OzellikBean();
        return ozellikBean;
    }
    
    
    

    public DosyaDao getDosyaDao() {
        if (this.dosyaDao == null) {
            this.dosyaDao = new DosyaDao();
        }
        return dosyaDao;
    }

    private int page = 1;

    private final int listItemCount = 5;

    private int pageCount;

    public void previous() {
        if (this.page == 1) {
            this.page = this.getPageCount();
        } else {
            this.page--;
        }
    }

    public void next() {
        if (this.page == this.getPageCount()) {
            this.page = 1;
        } else {
            this.page++;
        }
    }

    public void upload() {
        Dosya dosya = new Dosya();
        try {
            if (part != null) {
                InputStream input = part.getInputStream();
                File f = new File(this.uploadPath + part.getSubmittedFileName());
                java.nio.file.Files.copy(input, f.toPath(), REPLACE_EXISTING);

                String format = "dd-MM-yyyy-hhmmss";
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                String extension = f.getName().substring(f.getName().lastIndexOf("."));
                String fileName = sdf.format(new Date()) + extension;
                File newFile = new File(this.uploadPath + fileName);
                f.renameTo(newFile);
                dosya.setName(newFile.getName());
                dosya.setPath(newFile.getParent());
                dosya.setType(part.getContentType());
                dosya.setFile_Id(this.getDosyaDao().create(dosya));
                this.getModule().setDosya(dosya);
            }
            if (this.getModule().getOtel_id()== null) {
                this.getModuleDao().create(this.getModule());
            } else {
                if (dosya.getFile_Id() != null) {
                    this.getDosyaDao().clearDataOtel(this.getModule().getOtel_id());
                }
                this.getModuleDao().update(this.getModule());
            }
            this.clearForm();

        } catch (IOException ex) {
            System.out.println("Upload Fila hata=" + ex.getLocalizedMessage());
        }

    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
    

    public int getPageCount() {
        this.pageCount = (int) Math.ceil(this.getModuleDao().itemCount() / (double) listItemCount);
        return pageCount;
    }

    public void update() {
        this.getModuleDao().update(this.getModule());
        this.clearForm();
    }

    public void clearForm() {
        this.module = new Otel();
    }

    public void delete() {
        this.getModuleDao().delete(this.getModule());
        this.clearForm();
    }

    public void create() {
        this.getModuleDao().create(this.getModule());
        this.clearForm();
    }

    public Otel getModule() {
        if (this.module == null) {
            this.module = new Otel();
        }
        return this.module;
    }

    public void setModule(Otel module) {
        this.module = module;
    }

    public ArrayList<Otel> getModuleList() {
        this.moduleList = new ArrayList<>();
        this.moduleList = this.getModuleDao().list();
        return moduleList;
    }

    public ArrayList<Otel> getModulePageList() {
        this.modulePageList = new ArrayList<>();
        this.modulePageList = (ArrayList<Otel>) this.getModuleDao().pagedList(this.page, this.listItemCount);
        return modulePageList;
    }

    public OtelDao getModuleDao() {
        if (this.moduleDao == null) {
            this.moduleDao = new OtelDao();
        }
        return moduleDao;
    }

    public void deleteConfirm(Otel module) {
        this.module = module;
    }

    public void updateForm(Otel module) {
        this.module = module;
    }
}
