package it.course.myrecipesc4.payload.response;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ApiResponse {

	private Object message;
	private Date date;
	private String path;
	
	public ApiResponse(HttpServletRequest request) {
		this.date = new Date();
		this.path = request.getRequestURI();
	}
}
