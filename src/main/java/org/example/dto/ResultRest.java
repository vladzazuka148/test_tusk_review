package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import static java.lang.String.format;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@AllArgsConstructor
public class ResultRest {
	private static final ResultRest stub = new ResultRest();

	public static ResultRest stub() {
		return stub;
	}
}
