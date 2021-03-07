package it.course.myrecipesc4.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter@NoArgsConstructor
public class SignUpRequest {
	
	private String email;
	private String nickname;
	private String password;
	
}
