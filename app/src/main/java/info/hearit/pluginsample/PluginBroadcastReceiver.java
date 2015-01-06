package info.hearit.pluginsample;

import info.hearit.pluginslib.BaseModuleBroadcastReceiver;

/**
 * In order to be found by HearIT, plugins must provide provide a Broadcast Receiver, that must be registered in
 * AndroidManifest.xml and listen the actions <code>info.hearit.action.LOAD_PLUGIN</code> and
 * <code>info.hearit.action.STOP_PLUGIN</code>. HearIT pluginsLib provides an abstract class
 * {@link info.hearit.pluginslib.BaseModuleBroadcastReceiver} that handles most of the work, so plugin simply has to
 * extend it and provide the required data:
 *
 * <ul>
 *     <li>the plugin package, through {@link #getClientPackage()}</li>
 *     <li> the class that implement {@link info.hearit.pluginslib.BaseModuleService}, and where the audio module will
 *     run, through {@link #getServiceClass()}
 *     </li>
 *     <li>the plugin name, through {@link #getModuleName()}</li>
 * </ul>
 *
 * @see {@link info.hearit.pluginsample.PluginService}
 * */
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
