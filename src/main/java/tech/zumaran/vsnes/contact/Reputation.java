package tech.zumaran.vsnes.contact;

import java.util.List;
import java.util.stream.Collectors;

public enum Reputation {
	S, A, B, C, D, E, F;
	
	public static String regex() {
		return "["
				+ String.join("", List.of(Reputation.values())
						.stream().map(r -> r.name())
						.collect(Collectors.toList()))
				+ "]";
	}
}
