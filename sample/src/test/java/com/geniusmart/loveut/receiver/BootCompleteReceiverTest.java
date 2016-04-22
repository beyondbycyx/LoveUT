package com.geniusmart.loveut.receiver;

import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

import com.geniusmart.loveut.BuildConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.internal.Shadow;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowLog;

import java.io.PrintStream;

import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class BootCompleteReceiverTest {

    @Test
    public void registerServiceOnDeviceBootComplete() {
        Intent intent = new Intent(Intent.ACTION_BOOT_COMPLETED);

        ShadowLog.stream = System.out;
        ShadowApplication application = ShadowApplication.getInstance();

        boolean b = application.hasReceiverForIntent(intent);

        Log.d("tag", b+"");
        assertTrue("Reboot Listener not registered ",
                application.hasReceiverForIntent(intent));
    }
}
