package com.alimahrus25.betaadmob;

import android.content.Context;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

@DesignerComponent(
    version = 1,
    description = "BetaAdmob Engine Component oleh alimahrus25",
    category = ComponentCategory.EXTENSION,
    nonVisible = true,
    iconName = "assets/admob.png"
)
@SimpleObject(external = true)
@UsesLibraries(libraries = "play-services-ads.jar, play-services-ads-lite.jar, play-services-basement.jar, play-services-tasks.jar, play-services-ads-identifier.jar")
public class BetaAdmob extends AndroidNonvisibleComponent {

    private Context context;

    public BetaAdmob(ComponentContainer container) {
        super(container.$form());
        this.context = container.$context();
    }

    @SimpleFunction(description = "Inisialisasi AdMob SDK")
    public void InitializeSdk() {
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                // Jalankan event di thread UI agar aman
                form.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        SdkInitialized();
                    }
                });
            }
        });
    }

    @SimpleEvent(description = "Dipanggil saat SDK AdMob berhasil diinisialisasi")
    public void SdkInitialized() {
        EventDispatcher.dispatchEvent(this, "SdkInitialized");
    }
}
