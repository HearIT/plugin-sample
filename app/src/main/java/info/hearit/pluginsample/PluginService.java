package info.hearit.pluginsample;

import android.app.Notification;

import info.hearit.pluginslib.BaseModule;
import info.hearit.pluginslib.BaseModuleService;

/**
 * Classes that implement {@link info.hearit.pluginslib.BaseModuleService} are responsible for provide the plugin main
 * activity class (through {#getActivityClass}), instantiate the plugin audio module (through
 * {@link #newModule(android.app.Notification)}) and for providing the plugin name (through {@link #getModuleName()}).
 *
 * @see {@link info.hearit.pluginsample.PluginMainActivity}
 * @see {@link info.hearit.pluginsample.PluginAudioModule}
 * */
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
