package info.hearit.pluginsample;

import info.hearit.pluginslib.BaseModuleBroadcastReceiver;

public class PluginBroadcastReceiver extends BaseModuleBroadcastReceiver{
    @Override
    protected String getClientPackage() {
        return "info.hearit.pluginsample";
    }

    @Override
    protected Class getServiceClass() {
        return PluginService.class;
    }

    @Override
    protected String getModuleName() {
        return "PluginSample";
    }
}
