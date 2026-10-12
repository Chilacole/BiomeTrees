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
		// Biome sub-toggles
		DEFAULTS.setProperty("swampOakGrowth", "true");
		DEFAULTS.setProperty("rainforestOakGrowth", "true");

		CONFIG = new ConfigHandler("biometrees", DEFAULTS);

		try (InputStream input = new FileInputStream(CONFIG.getFilePath())) {
			VALUES.putAll(DEFAULTS);
			VALUES.load(input);
		} catch (IOException e) {
			VALUES.putAll(DEFAULTS);
		}
	}

	// --- Getters ---

	public static boolean isBiomeAwareGrowthEnabled() {
		return Boolean.parseBoolean(
			VALUES.getProperty("biomeAwareGrowth", "true")
		);
	}

	public static boolean isSwampOakGrowthEnabled() {
		return Boolean.parseBoolean(
			VALUES.getProperty("swampOakGrowth", "true")
		);
	}

	public static boolean isRainforestOakGrowthEnabled() {
		return Boolean.parseBoolean(
			VALUES.getProperty("rainforestOakGrowth", "true")
		);
	}

	// --- Setters ---

	public static void setBiomeAwareGrowth(boolean enabled) {
		VALUES.setProperty("biomeAwareGrowth", Boolean.toString(enabled));
		saveConfig();
	}

	public static void setSwampOakGrowth(boolean enabled) {
		VALUES.setProperty("swampOakGrowth", Boolean.toString(enabled));
		saveConfig();
	}

	public static void setRainforestOakGrowth(boolean enabled) {
		VALUES.setProperty("rainforestOakGrowth", Boolean.toString(enabled));
		saveConfig();
	}

	// --- Save Helper ---

	private static void saveConfig() {
		try (OutputStream output = new FileOutputStream(CONFIG.getFilePath())) {
			VALUES.store(output, "Biome Trees configuration");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

