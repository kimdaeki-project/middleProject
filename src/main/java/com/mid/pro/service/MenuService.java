package com.mid.pro.service;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mid.pro.dao.MenuDAO;
import com.mid.pro.model.FilesVO;
import com.mid.pro.model.MenuVO;
import com.mid.pro.util.FileSaver;

@Service
public class MenuService {
	
	@Inject
	private MenuDAO menuDAO;
	@Inject
	private FileSaver fileSaver;
	
	//list
	public List<MenuVO> menuList() throws Exception{
//		pager.makeRow();
//		pager.makePager(menuDAO.menuCount());
		return menuDAO.menuList();
	}
	//select One
	public MenuVO menuSelect(MenuVO menuVO) throws Exception{
		return menuDAO.menuSelect(menuVO);
	}
	//write
	public int menuWrite(MenuVO menuVO, MultipartFile[] file, HttpSession session) throws Exception{
		String realPath = session.getServletContext().getRealPath("resources/upload/menu");
		FilesVO filesVO = new FilesVO();
		int result = menuDAO.menuWrite(menuVO);
		
		for (MultipartFile multipartFile:file) {
			if (multipartFile.getSize() != 0) {
				String fileName = fileSaver.save(realPath, multipartFile);
				filesVO.setFname(fileName);
				filesVO.setMenu_num(menuVO.getMenu_num());
				filesVO.setOname(multipartFile.getOriginalFilename());
				result = menuDAO.fileWrite(filesVO);
				if (result < 1) {
					throw new SQLException();
				}
			}
		}
		return result;
	}
	//update
	public int menuUpdate(MenuVO menuVO, MultipartFile[] file, HttpSession session) throws Exception{
		String realPath = session.getServletContext().getRealPath("resources/upload/menu");
		FilesVO filesVO = new FilesVO();
		int result = menuDAO.menuUpdate(menuVO);
		
		for (MultipartFile multipartFile:file) {
			if (multipartFile.getSize() != 0) {
				String fileName = fileSaver.save(realPath, multipartFile);
				filesVO.setFname(fileName);
				filesVO.setMenu_num(menuVO.getMenu_num());
				filesVO.setOname(multipartFile.getOriginalFilename());
				result = menuDAO.fileWrite(filesVO);
				if (result < 1) {
					throw new SQLException();
				}
			}
		}
		return result;
	}
	//delete
	public int menuDelete(MenuVO menuVO) throws Exception{
		return menuDAO.menuDelete(menuVO);
	}
	//fileSelect
	public FilesVO fileSelect(FilesVO filesVO) throws Exception{
		return menuDAO.fileSelect(filesVO);
	}
	//fileDelete
	public int fileDelete(FilesVO filesVO) throws Exception{
		return menuDAO.fileDelete(filesVO);
	}
	//summerFile
	public String summerFile(MultipartFile file, HttpSession session) throws Exception{
		String realPath = session.getServletContext().getRealPath("resources/upload/summerFile");
		return fileSaver.save(realPath, file);
	}
	//summerFileDelete
	public Boolean summerFileDelete(String file, HttpSession session) throws Exception{
		String realPath = session.getServletContext().getRealPath("resources/upload/summerFile");
		return fileSaver.fileDelete(realPath, file);
	}
	
}
