package com.soecode.lyf.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.soecode.lyf.dto.AppointExecution;
import com.soecode.lyf.dto.Result;
import com.soecode.lyf.entity.Book;
import com.soecode.lyf.enums.AppointStateEnum;
import com.soecode.lyf.exception.NoNumberException;
import com.soecode.lyf.exception.RepeatAppointException;
import com.soecode.lyf.service.BookService;

import sun.nio.cs.US_ASCII;

@Controller
@RequestMapping("/book") // url:/模块/资源/{id}/细分 /seckill/list
public class BookController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BookService bookService;

	/**
	 * 方法说明：
	
	 *
	 * Author：        hemin                
	 * Create Date：   2019年5月30日 下午7:22:41
	 * History:  2019年5月30日 下午7:22:41   hemin   Created.
	 *
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	private String list(Model model) {
		List<Book> list = bookService.getList();
		model.addAttribute("list", list);
		// list.jsp + model = ModelAndView
		return "list";// WEB-INF/jsp/"list".jsp
	}

	/**
	 * 方法说明：
	
	 *
	 * Author：        hemin                
	 * Create Date：   2019年5月31日 下午5:48:29
	 * History:  2019年5月31日 下午5:48:29   hemin   Created.
	 *
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/bookList",method = RequestMethod.GET)
	@ResponseBody
	public List<Book> bookList(Model model){
		
		List<Book> list = bookService.getList();
		//model.addAttribute("list", list);
		
		return  list;
	}
	
	
	@RequestMapping(value = "/{bookId}/detail", method = RequestMethod.GET)
	private String detail(@PathVariable("bookId") Long bookId, Model model) {
		if (bookId == null) {
			return "redirect:/book/list";
		}
		Book book = bookService.getById(bookId);
		if (book == null) {
			return "forward:/book/list";
		}
		model.addAttribute("book", book);
		return "detail";
	}

	// ajax json
	/**
	 * 方法说明：
	
	 *
	 * Author：        hemin                
	 * Create Date：   2019年5月30日 下午7:22:46
	 * History:  2019年5月30日 下午7:22:46   hemin   Created.
	 *
	 * @param bookId
	 * @param studentId
	 * @return
	 *
	 */
	@RequestMapping(value = "/{bookId}/appoint", method = RequestMethod.POST, produces = {
			"application/json; charset=utf-8" })
	@ResponseBody
	private Result<AppointExecution> appoint(@PathVariable("bookId") Long bookId, @RequestParam("studentId") Long studentId) {
		if (studentId == null || studentId.equals("")) {
			return new Result<AppointExecution>(false, "学号不能为空");
		}
		AppointExecution execution = null;
		try {
			execution = bookService.appoint(bookId, studentId);
		} catch (NoNumberException e1) {
			execution = new AppointExecution(bookId, AppointStateEnum.NO_NUMBER);
		} catch (RepeatAppointException e2) {
			execution = new AppointExecution(bookId, AppointStateEnum.REPEAT_APPOINT);
		} catch (Exception e) {
			execution = new AppointExecution(bookId, AppointStateEnum.INNER_ERROR);
		}
		return new Result<AppointExecution>(true, execution);
	}

	/**
	 * 方法说明：
	
	 *
	 * Author：        hemin                
	 * Create Date：   2019年5月31日 下午5:44:22
	 * History:  2019年5月31日 下午5:44:22   hemin   Created.
	 *
	 * @param bookId
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = {"/queryList/{bookId}","/userList/{bookId}"})
	@ResponseBody
	public Book  queryList(@PathVariable("bookId") Long bookId, Model model) {
		if (bookId!=null) {
		
			Book	userlist=	bookService.queryUser(bookId);
			return  userlist;
		} else {
			model.addAttribute("为空");
		}
		
		
		return null;
	}
	

}
