package net.boot.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.boot.domain.User;
import net.boot.domain.userRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private userRepository userRepository;
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession httpSession) {
		User user = userRepository.findByUserId(userId);
		
		if(user == null) {
			System.out.println("로그인 실패!");
			return "redirect:/users/loginForm";
		}
		
		if(!user.matchPassword(password)) {
			System.out.println("로그인 실패!");
			return "redirect:/users/loginForm";
		}
		
		System.out.println("로그인 성공!");
		httpSession.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession httpSession) {
		
		httpSession.removeAttribute("sessionedUser");
		System.out.println("로그아웃 성공!");
		return "redirect:/";
		
	}
	
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}
	
	@PostMapping("/create")
	public String create(User user) {
		System.out.println("user : " + user);
//		users.add(user);
		userRepository.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
	
	@GetMapping("{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession httpSession) {
		if(!HttpSessionUtils.isLoginUser(httpSession)) {
			return "redirect:/users/loginForm";
		}
		
		User sessionedUser = HttpSessionUtils.getUserFromSession(httpSession);
		if(!sessionedUser.matchId(id)) {
			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다!");
		}
		
		User user = userRepository.findById(id).get();
		model.addAttribute("user", user);
		return "/user/updateForm";
	}
		
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User updatedUser, HttpSession httpSession) {
		if(HttpSessionUtils.isLoginUser(httpSession)) {
			return "redirect:/users/loginForm";
		}
		
		User sessionedUser = HttpSessionUtils.getUserFromSession(httpSession);
		if(!sessionedUser.matchId(id)) {
			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다!");
		}
		
		User user = userRepository.findById(id).get();
		user.update(updatedUser);
		userRepository.save(user);
		return "redirect:/users";
	}

}
