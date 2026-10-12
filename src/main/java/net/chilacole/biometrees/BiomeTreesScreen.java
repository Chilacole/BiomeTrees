package net.chilacole.biometrees;

import net.minecraft.client.gui.ButtonElement;
import net.minecraft.client.gui.Screen;

public class BiomeTreesScreen extends Screen {

	private ButtonElement masterOakButton;
	private ButtonElement swampOakButton;
	private ButtonElement rainforestOakButton;

	public BiomeTreesScreen(Screen parent) {
		super(parent);
	}

	@Override
	public void init() {
		this.buttons.clear();

		int centerX = this.width / 2;
		int startY = this.height / 2 - 40;

		// Master Oak Toggle
		masterOakButton = new ButtonElement(
			0,
			centerX - 100,
			startY,
			200,
			20,
			getOakMasterText()
		);
		this.add(masterOakButton);

		// Swamp Sub-toggle (indented text)
		swampOakButton = new ButtonElement(
			1,
			centerX - 100,
			startY + 24,
			200,
			20,
			getSwampText()
		);
		this.add(swampOakButton);

		// Rainforest Sub-toggle (indented text)
		rainforestOakButton = new ButtonElement(
			2,
			centerX - 100,
			startY + 48,
			200,
			20,
			getRainforestText()
		);
		this.add(rainforestOakButton);

		// Done Button
		this.add(new ButtonElement(
			3,
			centerX - 100,
			startY + 84,
			200,
			20,
			"Done"
		));

		updateButtonStates();
	}

	private String getOakMasterText() {
		return "Biome-aware oak growth: "
			+ (BiomeTreesConfig.isBiomeAwareGrowthEnabled() ? "ON" : "OFF");
	}

	private String getSwampText() {
		return "  > Swamp trees: "
			+ (BiomeTreesConfig.isSwampOakGrowthEnabled() ? "ON" : "OFF");
	}

	private String getRainforestText() {
		return "  > Rainforest trees: "
			+ (BiomeTreesConfig.isRainforestOakGrowthEnabled() ? "ON" : "OFF");
	}

	private void updateButtonStates() {
		boolean masterEnabled = BiomeTreesConfig.isBiomeAwareGrowthEnabled();
		swampOakButton.enabled = masterEnabled;
		rainforestOakButton.enabled = masterEnabled;
	}

	@Override
	protected void buttonClicked(ButtonElement button) {
		if (button.id == 0) {
			boolean enabled = !BiomeTreesConfig.isBiomeAwareGrowthEnabled();
			BiomeTreesConfig.setBiomeAwareGrowth(enabled);
			masterOakButton.displayString = getOakMasterText();
			updateButtonStates();
		} else if (button.id == 1 && swampOakButton.enabled) {
			boolean enabled = !BiomeTreesConfig.isSwampOakGrowthEnabled();
			BiomeTreesConfig.setSwampOakGrowth(enabled);
			swampOakButton.displayString = getSwampText();
		} else if (button.id == 2 && rainforestOakButton.enabled) {
			boolean enabled = !BiomeTreesConfig.isRainforestOakGrowthEnabled();
			BiomeTreesConfig.setRainforestOakGrowth(enabled);
			rainforestOakButton.displayString = getRainforestText();
		} else if (button.id == 3) {
			this.mc.displayScreen(this.getParentScreen());
		}
	}
}
