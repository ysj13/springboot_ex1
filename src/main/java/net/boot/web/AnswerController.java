package net.boot.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.boot.domain.Answer;
import net.boot.domain.AnswerRepository;
import net.boot.domain.Question;
import net.boot.domain.QuestionRepository;
import net.boot.domain.User;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@PostMapping("")
	public String create(@PathVariable Long questionId, String contents, HttpSession httpSession) {
		if(!HttpSessionUtils.isLoginUser(httpSession)) {
			return "/users/loginForm";
		}
		User loginUser = HttpSessionUtils.getUserFromSession(httpSession);
		Question question = questionRepository.findById(questionId).get();
		Answer answer = new Answer(loginUser, question, contents);
		answerRepository.save(answer);
		return String.format("redirect:/questions/%d", questionId);
	}
	
	

}
