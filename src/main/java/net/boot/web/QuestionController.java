package net.boot.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.boot.domain.AnswerRepository;
import net.boot.domain.Question;
import net.boot.domain.QuestionRepository;
import net.boot.domain.Result;
import net.boot.domain.User;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/form")
	public String form(HttpSession httpSession) {
		if(!HttpSessionUtils.isLoginUser(httpSession)) {
			return "/users/loginForm";
		}
		return "qna/form";
	}
	
	@PostMapping("")
	public String create(String title, String contents, HttpSession httpSession) {
		if(!HttpSessionUtils.isLoginUser(httpSession)) {
			return "/users/loginForm";
		}
		User sessionUser = HttpSessionUtils.getUserFromSession(httpSession);
		Question newQuestion = new Question(sessionUser, title, contents);
		questionRepository.save(newQuestion);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model) {	
		model.addAttribute("question", questionRepository.findById(id).get());
		return "qna/show";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession httpSession) {
		Question question = questionRepository.findById(id).get();
		Result result = valid(httpSession, question);
		if(!result.isValid()) {
			model.addAttribute("errorMessage",result.getErrorMessage());
			return "/user/login";
		}	
		
		model.addAttribute("question", question);
		return "/qna/updateForm";			
	}
	
	private Result valid(HttpSession httpSession, Question question) {
		if(!HttpSessionUtils.isLoginUser(httpSession)) {
			return Result.fail("로그인이 필요합니다.");
		}

		User loginUser = HttpSessionUtils.getUserFromSession(httpSession);
		if(!question.isSameWriter(loginUser)) {
			return Result.fail("자신이 쓴 글만 수정, 삭제가 가능합니다.");
		}
		
		return Result.ok();
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents, Model model, HttpSession httpSession) {	
		Question question = questionRepository.findById(id).get();
		Result result = valid(httpSession, question);
		if(!result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage());
			return "/user/login";
		}
		
		question.update(title, contents);
		questionRepository.save(question);
		return String.format("redirect:/questions/%d", id);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, Model model, HttpSession httpSession) {
		Question question = questionRepository.findById(id).get();
		Result result = valid(httpSession, question);
		if(!result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage());
			return "/user/login";
		} 
		
		questionRepository.deleteById(id);
		return "redirect:/";
	}
}

