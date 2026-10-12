package net.chilacole.biometrees;

import net.minecraft.client.gui.ButtonElement;
import net.minecraft.client.gui.Screen;

public class BiomeTreesScreen extends Screen {

	private ButtonElement toggleButton;

	public BiomeTreesScreen(Screen parent) {
		super(parent);
	}

	@Override
	public void init() {
		this.buttons.clear();

		int centerX = this.width / 2;
		int centerY = this.height / 2;

		toggleButton = new ButtonElement(
			0,
			centerX - 100,
			centerY - 10,
			200,
			20,
			getToggleText()
		);

		this.add(toggleButton);

		this.add(new ButtonElement(
			1,
			centerX - 100,
			centerY + 20,
			200,
			20,
			"Done"
		));
	}

	private String getToggleText() {
		return "Biome-aware oak growth: "
			+ (BiomeTreesConfig.isBiomeAwareGrowthEnabled()
			? "ON"
			: "OFF");
	}

	@Override
	protected void buttonClicked(ButtonElement button) {
		if (button.id == 0) {
			boolean enabled =
				!BiomeTreesConfig.isBiomeAwareGrowthEnabled();

			BiomeTreesConfig.setBiomeAwareGrowth(enabled);
			toggleButton.displayString = getToggleText();
		} else if (button.id == 1) {
			this.mc.displayScreen(this.getParentScreen());
		}
	}
}
