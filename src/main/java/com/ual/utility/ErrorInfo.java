package com.ual.utility;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorInfo {
	int errorCode;
	String message;
	String path;
	LocalDateTime timestamp;
	public ErrorInfo(int errorCode, String message, String path) {
		this.errorCode = errorCode;
		this.message = message;
		this.path = path;
		this.timestamp = LocalDateTime.now();
	}
}
