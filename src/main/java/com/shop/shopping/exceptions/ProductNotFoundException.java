package com.shop.shopping.exceptions;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends Exception {
	public ProductNotFoundException(final String message) {
		super(message);
	}

	public ProductNotFoundException(final String message, final Object... arguments) {
		super(getMessage(message, arguments));
	}

	private static String getMessage(final String message, final Object... arguments) {
		return String.format(message.replace("{}", "%s"), arguments);
	}
}
