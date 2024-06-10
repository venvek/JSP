package model2mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.BoardPage;

public class ListController extends HttpServlet {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException , IOException {
			MVCBoardDAO dao = new MVCBoardDAO();
			
			Map <String,Object> map = new HashMap<String, Object>();
			
			String searchField = request.getParameter("searchField");
			String searchWord = request.getParameter("searchWord");
			if (searchWord != null)  {
				
				map.put("searchField", searchField);
				map.put("searchWord", searchWord);
			}
			int totalCount = dao.selectCount(map);
			
			/*페이지 처리 start*/
			ServletContext application = getServletContext();
			int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
			int blockPage = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
			
			int pageNum = 1;
			String pageTemp = request.getParameter("pageNum");
			
			System.out.println("::: PageTemp :::: " + pageTemp);
			if (pageTemp != null && !pageTemp.equals(""))
				pageNum = Integer.parseInt(pageTemp);
				
			int start = (pageNum - 1) * pageSize +1;
			int end = pageNum * pageSize;
			map.put("start", start);
			map.put("end", end);
			/*페이지 처리 end*/
			
			List<MVCBoardDTO> boardLists = dao.selectListPage(map);
			
			dao.close();
			
			String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../mvcboard/list.do");
			map.put("pagingImg", pagingImg);
			map.put("totalCount", totalCount);
			map.put("pageSize", pageSize);
			map.put("pageNum", pageNum);
			
			request.setAttribute("boardLists", boardLists);
			request.setAttribute("map", map);
			request.getRequestDispatcher("/14MVCBoard/List.jsp").forward(request, response);
			

	}

}
