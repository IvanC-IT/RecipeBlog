package it.course.myrecipesc4.controller;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import it.course.myrecipesc4.entity.User;
import it.course.myrecipesc4.payload.request.SignInRequest;
import it.course.myrecipesc4.payload.request.SignUpRequest;
import it.course.myrecipesc4.payload.response.ApiResponse;
import it.course.myrecipesc4.repository.AuthorityRepository;
import it.course.myrecipesc4.repository.UserRepository;
import it.course.myrecipesc4.security.JwtAuthenticationResponse;
import it.course.myrecipesc4.security.JwtTokenUtil;
import it.course.myrecipesc4.service.UserService;


@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	AuthorityRepository authorityRepository;

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	UserService userService;

	@Autowired
	JwtTokenUtil jwtTokenUtil;
	


	@Value("${jwt.header}")
	private String tokenHeader;
	
	
	@PostMapping("public/signup")
	@Transactional
	public ResponseEntity<ApiResponse> signUp(HttpServletRequest request,
			@Valid @RequestBody SignUpRequest signUpRequest) {

		ApiResponse apiResponse = new ApiResponse(request);
		
		long countUsers = userRepository.count(); // select count(*) from user;

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			apiResponse.setMessage("User already registered") ;
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
		}
		if (userRepository.existsByNickname(signUpRequest.getNickname())) {
			apiResponse.setMessage("Username already in use");
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
		}
		User user = new User(signUpRequest.getNickname(),signUpRequest.getEmail(), signUpRequest.getPassword());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		userRepository.save(user);
		userService.setAuthority(countUsers, user);
		apiResponse.setMessage("User successfully registered. Please Sign In now") ;
		


		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}

	@PostMapping("public/signin")
	public ResponseEntity<ApiResponse> signIn(@Valid @RequestBody SignInRequest signInRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, JsonProcessingException {
		System.out.println(new Date().toString());
		ApiResponse apiResponse = new ApiResponse(request);
		Optional<User> user = userRepository.findByNicknameOrEmail(signInRequest.getNicknameOrEmail(),
				signInRequest.getNicknameOrEmail());


		if (!user.isPresent()) {
			apiResponse.setMessage("Please register yourself before the Sign In") ;
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
		}	
		
			
			final Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(user.get().getNickname(), signInRequest.getPassword()));


			SecurityContextHolder.getContext().setAuthentication(authentication);
			final UserDetails userDetails = userDetailsService.loadUserByUsername(user.get().getNickname());
			final String token = jwtTokenUtil.generateToken(userDetails);
			response.setHeader(tokenHeader, token);
			userRepository.save(user.get());
			
			apiResponse.setMessage(new JwtAuthenticationResponse(userDetails.getUsername(), userDetails.getAuthorities(), token));
			return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.OK);
	}

}