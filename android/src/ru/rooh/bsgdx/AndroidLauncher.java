package ru.rooh.bsgdx;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.surfaceview.RatioResolutionStrategy;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		//config.resolutionStrategy = new FixedResolutionStrategy(136,204);
		config.useImmersiveMode = true;
		config.resolutionStrategy = new RatioResolutionStrategy(144, 256);
		initialize(new Main(), config);
	}
}
