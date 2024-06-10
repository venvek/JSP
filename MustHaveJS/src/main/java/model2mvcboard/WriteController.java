package model2mvcboard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.JSFunction;

public class WriteController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		req.getRequestDispatcher("/14MVCBoard/Write.jsp").forward(req, resp);
}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		String saveDirectory = req.getServletContext().getRealPath("/Uploads");
		
		String originalFileName = "";
		try {
				originalFileName = FileUtil.uploadFile(req, saveDirectory);
		}
		
		catch (Exception e) {
				e.printStackTrace();
				JSFunction.alertLocation(resp, "파일 업로드 오류입니다.", "../mvcboard/write.do");
				return;
		}
		
		MVCBoardDTO dto = new MVCBoardDTO();
		dto.setName(req.getParameter("name"));
		dto.setTitle(req.getParameter("title"));
		dto.setContent(req.getParameter("content"));
		dto.setPass(req.getParameter("pass"));
		
		if (originalFileName != "") {
			
			String saveFileName = FileUtil.renameFile(saveDirectory, originalFileName);
			
			dto.setOfile(originalFileName);
			dto.setSfile(saveFileName);
			
		}
		MVCBoardDAO dao = new MVCBoardDAO();
		int result = dao.insertWrite(dto);
		dao.close();
		
		if (result ==1) {
			resp.sendRedirect("../mvcboard/list.do");
		}
		else {
			JSFunction.alertLocation(resp, "글쓰기에 실패.", "../mvcboard/write.do");
		}
	}
		
	
}
