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
		DEFAULTS.setProperty("biomeAwareGrowth", "true");

		CONFIG = new ConfigHandler("biometrees", DEFAULTS);

		try (InputStream input = new FileInputStream(CONFIG.getFilePath())) {
			VALUES.putAll(DEFAULTS);
			VALUES.load(input);
		} catch (IOException e) {
			VALUES.putAll(DEFAULTS);
		}
	}

	public static boolean isBiomeAwareGrowthEnabled() {
		return Boolean.parseBoolean(
			VALUES.getProperty("biomeAwareGrowth", "true")
		);
	}

	public static void setBiomeAwareGrowth(boolean enabled) {
		VALUES.setProperty("biomeAwareGrowth", Boolean.toString(enabled));

		try (OutputStream output =
				 new FileOutputStream(CONFIG.getFilePath())) {
			VALUES.store(output, "Biome Trees configuration");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

