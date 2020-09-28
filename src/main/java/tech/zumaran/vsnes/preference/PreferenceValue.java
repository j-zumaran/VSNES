package tech.zumaran.vsnes.preference;

public interface PreferenceValue<T> {
	String stringValue();
	T fromString(String string);
}
