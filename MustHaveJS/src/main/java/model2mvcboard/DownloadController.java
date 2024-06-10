package model2mvcboard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mvcboard/download.do")
public class DownloadController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException , IOException {
		
		String ofile = request.getParameter("ofile");
		String sfile = request.getParameter("sfile");		
		String idx = request.getParameter("idx");
		
		FileUtil.download(request, response, "/Uploads", sfile, ofile);
		
		MVCBoardDAO dao = new MVCBoardDAO();
		dao.downCountPlus(idx);
		dao.close();
}
}
