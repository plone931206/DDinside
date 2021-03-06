package com.DDinside.Board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DDinside.action.Action;
import com.DDinside.dao.DDinsideDAO;
import com.DDinside.dao.Suv_DDinsideDAO;

public class BoardDeleteAction implements Action {
	String board_id;
	public BoardDeleteAction(String board_id) {
		this.board_id = board_id;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String url = "controller?command=BoardList&board_id="+board_id;
		String num = request.getParameter("num");
		
		DDinsideDAO boardDAO = DDinsideDAO.getInstance();
		Suv_DDinsideDAO suv_boardDAO = Suv_DDinsideDAO.getInstance();

		if(board_id.equals("高評価") || board_id.equals("低評価") || board_id.equals("アホ") || board_id.equals("統合")) {
			suv_boardDAO.boardDelete(num, board_id);
		} else {
			boardDAO.boardDelete(num, board_id);
		}

		request.setAttribute("board_id", board_id);
		
		request.setAttribute("message", "スレッドを削除しました。");
		response.setCharacterEncoding("UTF-8");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		
	}

}
