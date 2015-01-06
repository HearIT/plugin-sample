package info.hearit.pluginsample;

import android.app.Notification;

import info.hearit.pluginslib.BaseModule;
import info.hearit.pluginslib.BaseModuleService;

public class PluginService extends BaseModuleService {
    @Override
    protected Class getActivityClass() {
        return PluginMainActivity.class;
    }

    @Override
    protected BaseModule newModule(Notification notification) {
        return new PluginAudioModule(notification, 2, 2);
    }

    @Override
    protected String getModuleName() {
        return getString(R.string.plugin_name);
    }
}
