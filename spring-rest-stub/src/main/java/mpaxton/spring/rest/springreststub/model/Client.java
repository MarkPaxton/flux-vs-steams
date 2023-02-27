package mpaxton.spring.rest.springreststub.model;

import java.io.Serial;
import java.io.Serializable;

public record Client(String id, double purchases) implements Serializable {
	@Serial
	private static final long serialVersionUID = -6358742378177948329L;
}
