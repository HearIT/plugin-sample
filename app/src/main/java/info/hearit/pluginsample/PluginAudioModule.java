package info.hearit.pluginsample;

import android.app.Notification;

import info.hearit.pluginslib.BaseModule;
import info.hearit.pluginslib.profile.SettingSet;

/**
 *
 * */
public class PluginAudioModule extends BaseModule {

    /**
     * Load native library where audio processing algorithm is implemented.
     * */
    static {
        System.loadLibrary("plugin_sample");
    }

    /**
     * @param notification
     * @param inputChannels
     *      Number of input channels used by this plugin.
     * @param outputChannels
     *      Number of output channels used by this plugin.
     * */
    protected PluginAudioModule(Notification notification, int inputChannels, int outputChannels) {
        super(notification, inputChannels, outputChannels);
    }

    @Override
    protected boolean configure(String name, long handle, int sampleRate, int bufferSize) {

        if(this.mPtr != 0){
            throw new IllegalArgumentException("Module has already been configured");
        }

        this.mPtr = configureNativeComponents(handle, this.mInputChannels);
        return this.mPtr != 0;
    }

    @Override
    public void loadConfigurations(SettingSet settingSet) {
        if(settingSet == null){
            return;
        }
    }

    @Override
    protected void release() {
        if(this.mPtr != 0){
            release(this.mPtr);
            this.mPtr = 0;
        }
    }

    /**
     * Sets the gain factor to be applied to audio stream.
     *
     * @param factor
     *      Gain factor to be applied to audio stream.
     * */
    public void setGainFactor(int factor){
        if(this.mPtr == 0){
            throw new IllegalArgumentException("Plugin is not configured");
        }

        setGainFactor(this.mPtr, factor);
    }

    /**
     * @return The gain factor in use by this plugin.
     * */
    public int getGainFactor(){
        if(this.mPtr == 0){
            throw new IllegalArgumentException("Plugin is not configured");
        }

        return getGainFactor(this.mPtr);
    }

    private native long configureNativeComponents(long handle, int channels);

    private native void release(long ptr);

    private native void setGainFactor(long ptr, int factor);

    private native int getGainFactor(long pointer);
}
