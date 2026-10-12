package net.chilacole.biometrees;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import turniplabs.halplibe.util.ConfigHandler;

public class BiomeTreesConfig {

	private static final Properties DEFAULTS = new Properties();
	private static final ConfigHandler CONFIG;
	private static final Properties VALUES = new Properties();

	static {
		// Master toggle
		DEFAULTS.setProperty("biomeAwareGrowth", "true");

		// Oak toggles
		DEFAULTS.setProperty("swampOakGrowth", "true");
		DEFAULTS.setProperty("rainforestOakGrowth", "true");
		DEFAULTS.setProperty("rainforestGiantOak", "true");
		DEFAULTS.setProperty("rainforestFancyMossyOak", "true");
		DEFAULTS.setProperty("rainforestFancyRegularOak", "true");
		DEFAULTS.setProperty("rainforestRegularOak", "true");

		// Birch toggles
		DEFAULTS.setProperty("birchForestGrowth", "true");
		DEFAULTS.setProperty("birchForestTall", "true");
		DEFAULTS.setProperty("birchForestRegular", "true");

		// Thorn toggles
		DEFAULTS.setProperty("caatingaGrowth", "true");
		DEFAULTS.setProperty("caatingaTallThorn", "true");
		DEFAULTS.setProperty("caatingaShrub", "true");
		DEFAULTS.setProperty("caatingaPlainsGrowth", "true");
		DEFAULTS.setProperty("caatingaPlainsShortThorn", "true");
		DEFAULTS.setProperty("caatingaPlainsShrub", "true");

		CONFIG = new ConfigHandler("biometrees", DEFAULTS);

		try (InputStream input = new FileInputStream(CONFIG.getFilePath())) {
			VALUES.putAll(DEFAULTS);
			VALUES.load(input);
		} catch (IOException e) {
			VALUES.putAll(DEFAULTS);
		}
	}

	// --- Getters ---
	public static boolean isBiomeAwareGrowthEnabled() { return Boolean.parseBoolean(VALUES.getProperty("biomeAwareGrowth", "true")); }

	// Oak
	public static boolean isSwampOakGrowthEnabled() { return Boolean.parseBoolean(VALUES.getProperty("swampOakGrowth", "true")); }
	public static boolean isRainforestOakGrowthEnabled() { return Boolean.parseBoolean(VALUES.getProperty("rainforestOakGrowth", "true")); }
	public static boolean isRainforestGiantOakEnabled() { return Boolean.parseBoolean(VALUES.getProperty("rainforestGiantOak", "true")); }
	public static boolean isRainforestFancyMossyOakEnabled() { return Boolean.parseBoolean(VALUES.getProperty("rainforestFancyMossyOak", "true")); }
	public static boolean isRainforestFancyRegularOakEnabled() { return Boolean.parseBoolean(VALUES.getProperty("rainforestFancyRegularOak", "true")); }
	public static boolean isRainforestRegularOakEnabled() { return Boolean.parseBoolean(VALUES.getProperty("rainforestRegularOak", "true")); }

	// Birch
	public static boolean isBirchForestGrowthEnabled() { return Boolean.parseBoolean(VALUES.getProperty("birchForestGrowth", "true")); }
	public static boolean isBirchForestTallEnabled() { return Boolean.parseBoolean(VALUES.getProperty("birchForestTall", "true")); }
	public static boolean isBirchForestRegularEnabled() { return Boolean.parseBoolean(VALUES.getProperty("birchForestRegular", "true")); }

	// Thorn
	public static boolean isCaatingaGrowthEnabled() { return Boolean.parseBoolean(VALUES.getProperty("caatingaGrowth", "true")); }
	public static boolean isCaatingaTallThornEnabled() { return Boolean.parseBoolean(VALUES.getProperty("caatingaTallThorn", "true")); }
	public static boolean isCaatingaShrubEnabled() { return Boolean.parseBoolean(VALUES.getProperty("caatingaShrub", "true")); }
	public static boolean isCaatingaPlainsGrowthEnabled() { return Boolean.parseBoolean(VALUES.getProperty("caatingaPlainsGrowth", "true")); }
	public static boolean isCaatingaPlainsShortThornEnabled() { return Boolean.parseBoolean(VALUES.getProperty("caatingaPlainsShortThorn", "true")); }
	public static boolean isCaatingaPlainsShrubEnabled() { return Boolean.parseBoolean(VALUES.getProperty("caatingaPlainsShrub", "true")); }

	// --- Setters ---
	public static void setBiomeAwareGrowth(boolean enabled) { setAndSave("biomeAwareGrowth", enabled); }

	// Oak
	public static void setSwampOakGrowth(boolean enabled) { setAndSave("swampOakGrowth", enabled); }
	public static void setRainforestOakGrowth(boolean enabled) { setAndSave("rainforestOakGrowth", enabled); }
	public static void setRainforestGiantOak(boolean enabled) { setAndSave("rainforestGiantOak", enabled); }
	public static void setRainforestFancyMossyOak(boolean enabled) { setAndSave("rainforestFancyMossyOak", enabled); }
	public static void setRainforestFancyRegularOak(boolean enabled) { setAndSave("rainforestFancyRegularOak", enabled); }
	public static void setRainforestRegularOak(boolean enabled) { setAndSave("rainforestRegularOak", enabled); }

	// Birch
	public static void setBirchForestGrowth(boolean enabled) { setAndSave("birchForestGrowth", enabled); }
	public static void setBirchForestTall(boolean enabled) { setAndSave("birchForestTall", enabled); }
	public static void setBirchForestRegular(boolean enabled) { setAndSave("birchForestRegular", enabled); }

	// Thorn
	public static void setCaatingaGrowth(boolean enabled) { setAndSave("caatingaGrowth", enabled); }
	public static void setCaatingaTallThorn(boolean enabled) { setAndSave("caatingaTallThorn", enabled); }
	public static void setCaatingaShrub(boolean enabled) { setAndSave("caatingaShrub", enabled); }
	public static void setCaatingaPlainsGrowth(boolean enabled) { setAndSave("caatingaPlainsGrowth", enabled); }
	public static void setCaatingaPlainsShortThorn(boolean enabled) { setAndSave("caatingaPlainsShortThorn", enabled); }
	public static void setCaatingaPlainsShrub(boolean enabled) { setAndSave("caatingaPlainsShrub", enabled); }

	// --- Save Helper ---
	private static void setAndSave(String key, boolean value) {
		VALUES.setProperty(key, Boolean.toString(value));
		try (OutputStream output = new FileOutputStream(CONFIG.getFilePath())) {
			VALUES.store(output, "Biome Trees configuration");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
