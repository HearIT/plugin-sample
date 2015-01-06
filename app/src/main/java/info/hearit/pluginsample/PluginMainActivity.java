package info.hearit.pluginsample;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.widget.SeekBar;

import info.hearit.pluginslib.BaseModuleService;

/**
 * Plugin main activity.
 * */
public class PluginMainActivity extends ActionBarActivity implements SeekBar.OnSeekBarChangeListener{

    /** Seek bar to control the gain factor. */
    private SeekBar mSeekBar;

    private int mCurrentGain;

    /** Reference to audio module implementation. */
    private PluginAudioModule mModule;

    private ServiceConnection mConnection = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BaseModuleService.ModuleServiceBinder binder =
                    (BaseModuleService.ModuleServiceBinder) service;
            mModule = (PluginAudioModule) binder.getService().getModule();
            mCurrentGain = mModule.getGainFactor();
            mSeekBar.setProgress(mModule.getGainFactor());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin_main);

        // we get a reference to seek bar and then register the callback to be notified about changes in progress.
        this.mSeekBar = (SeekBar) findViewById(R.id.seekbar_factor);
        this.mSeekBar.setOnSeekBarChangeListener(this);
        bindService(new Intent(this, PluginService.class), this.mConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(this.mConnection != null){
            unbindService(this.mConnection);
        }
    }

    // <editor-fold desc="SeekBar.OnSeekBarChangeListener implementation">

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(this.mModule != null){
            // update the value of gain factor in module
            this.mModule.setGainFactor(progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // do nothing
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // do nothing
    }

    // </editor-fold>
}
