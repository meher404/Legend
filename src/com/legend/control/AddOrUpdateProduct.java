package com.legend.control;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.legend.lib.helpFunctions;

/**
 * Servlet implementation class AddOrUpdateProduct
 */
@WebServlet("/AddOrUpdateProduct")
public class AddOrUpdateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		String fileSavePath="",fileName="";
		
		String pname=request.getParameter("pname");
		String mfgname=request.getParameter("mfgname");
		String category=request.getParameter("category");
		String description=request.getParameter("desc");
		String cost=request.getParameter("price");
		String qty=request.getParameter("qty");
		String disc=request.getParameter("disc");
		
	
		
		try {
            /* TODO output your page here   */
            response.setContentType("text/html;charset=UTF-8");
            DiskFileItemFactory factory = new DiskFileItemFactory();
            int maxMemSize = 40000*100;
			// maximum size that will be stored in memory
            factory.setSizeThreshold(maxMemSize);
            // Location to save data that is larger than maxMemSize.

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            long maxFileSize = 4000*100;
			// maximum file size to be uploaded.
            upload.setSizeMax(maxFileSize );
            //Specify the path of the file here fileSavePath
            fileSavePath = request.getServletContext().getRealPath(""); //+  File.separator;
          
           fileSavePath =fileSavePath+"/img/products/"+category+File.separator; //+  File.separator;
            
            //String realPath="/images/";
           // String fileSavePath=getServletContext().getRealPath(realPath);
            
            
            System.out.println("fileSavePath: "+fileSavePath);
            request.setAttribute("filePath", fileSavePath);
            // Parse the request to get file items.
            List<?> fileItems = upload.parseRequest(request);
            // Process the uploaded file items
            Iterator<?> i = fileItems.iterator();
            
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    fileName = fi.getName();
                    System.out.println("File Name is : " + fileName);
                    File file;
					// Write the file
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(fileSavePath
                                + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(fileSavePath
                                + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    factory.setRepository(file);
                    fi.write(file);
                    System.out.println(fileSavePath);
                    out.println("<h3>Uploaded Filename: " + fileName + "<br></h3>");
                    request.setAttribute("fileName", fileName);
                }
            }
         
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		String path=fileSavePath+fileName;
	
		helpFunctions.crudProduct(pname,path,cost,description,qty, disc, category, mfgname);
		
	}

}
