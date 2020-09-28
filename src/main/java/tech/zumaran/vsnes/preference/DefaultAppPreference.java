package tech.zumaran.vsnes.preference;

public enum DefaultAppPreference implements PreferenceKey {
	CODE_FORMAT("CODE")
	;
	
	private final String defaultVAlue;
	
	DefaultAppPreference(String defaultVAlue) {
		this.defaultVAlue = defaultVAlue;
	}

	@Override
	public String getName() {
		return name();
	}

	@Override
	public String getDefaultValue() {
		return defaultVAlue;
	}

}
