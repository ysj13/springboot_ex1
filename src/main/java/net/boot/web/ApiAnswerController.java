package net.boot.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.boot.domain.Answer;
import net.boot.domain.AnswerRepository;
import net.boot.domain.Question;
import net.boot.domain.QuestionRepository;
import net.boot.domain.Result;
import net.boot.domain.User;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@PostMapping("")
	public Answer create(@PathVariable Long questionId, String contents, HttpSession httpSession) {
		if(!HttpSessionUtils.isLoginUser(httpSession)) {
			return null;
		}
		User loginUser = HttpSessionUtils.getUserFromSession(httpSession);
		Question question = questionRepository.findById(questionId).get();
		Answer answer = new Answer(loginUser, question, contents);		
		question.addAnswer();
		return answerRepository.save(answer);
	}
	
	@DeleteMapping("/{id}")
	public Result delete(@PathVariable Long questionId, @PathVariable Long id, HttpSession httpSession) {
		if(!HttpSessionUtils.isLoginUser(httpSession)) {
			return Result.fail("로그인이 필요합니다.");		
		}
		
		Answer answer = answerRepository.findById(id).get();
		User loginUser = HttpSessionUtils.getUserFromSession(httpSession);
		if(!answer.isSameWriter(loginUser)) {
			return Result.fail("자신이 쓴 글만 삭제가 가능합니다.");
		}
		
		answerRepository.deleteById(id);
		Question question = questionRepository.findById(id).get();
		question.deleteAnswer();
		questionRepository.save(question);
		return Result.ok();
	}
	

}
