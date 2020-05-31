package net.rdrei.android.dirchooser;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Let's the user choose a directory on the storage device. The selected folder
 * will be sent back to the starting activity as an activity result.
 */
public class DirectoryChooserActivity extends AppCompatActivity implements
        DirectoryChooserFragment.OnFragmentInteractionListener {

    public static final String RESULT_SELECTED_DIR = "selected_dir";
    public static final int RESULT_CODE_DIR_SELECTED = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
        setContentView(R.layout.directory_chooser_activity);

        if (savedInstanceState == null) {
            final FragmentManager fragmentManager = getSupportFragmentManager();
            final DirectoryChooserFragment fragment = new DirectoryChooserFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.main, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            setResult(RESULT_CANCELED);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelectDirectory(@NonNull String path) {
        final Intent intent = new Intent();
        intent.putExtra(RESULT_SELECTED_DIR, path);
        setResult(RESULT_CODE_DIR_SELECTED, intent);
        finish();
    }

    @Override
    public void onCancelChooser() {
        setResult(RESULT_CANCELED);
        finish();
    }

    private void setupActionBar() {
        // there might not be an ActionBar, for example when started in Theme.Holo.Dialog.NoActionBar theme
        @SuppressLint("AppCompatMethod") final ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
