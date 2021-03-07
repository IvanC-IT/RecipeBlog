package it.course.myrecipesc4.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter@NoArgsConstructor
public class SignInRequest {
	@Size(min=5, max=15)
	private String password;
	
	@Size(max=120, min=3, message="username length must be between 3 and 12 chars")
	@NotBlank(message="User must not be blank")
	private String nicknameOrEmail;
}
