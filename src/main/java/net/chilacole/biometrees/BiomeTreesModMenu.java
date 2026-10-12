package net.chilacole.biometrees;

import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.Screen;

import java.util.function.Function;

public class BiomeTreesModMenu implements ModMenuApi {

	@Override
	public String getModId() {
		return "biometrees";
	}

	@Override
	public Function<Screen, ? extends Screen> getConfigScreenFactory() {
		return parent -> new BiomeTreesScreen(parent);
	}
}


