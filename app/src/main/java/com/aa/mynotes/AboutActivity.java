package com.aa.mynotes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // display the app version
        String versionName = BuildConfig.VERSION_NAME;
        TextView appVersionTextView = findViewById(R.id.appVersionTextView);
        appVersionTextView.setText(getString(R.string.app_version_name, versionName));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // share a small description of the app with a link of the app on play store.
                String appName = getString(R.string.app_name);
                String appDescription = getString(R.string.app_description);
                String playstoreUrl = getString(R.string.playstore_url);

                shareApp(appName, appDescription, playstoreUrl);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Opens the application's Github repo on a browser.
     * Handles 'viewOnGithubButton' clicks.
     * @param view
     */
    private void openGithubRepo(View view) {
        String githubRepoUrl = getString(R.string.github_repo_url);
        openWebPage(githubRepoUrl);
    }

    /**
     * Opens the web page with the given url in the default device's browser.
     * @param url The URL of the web page to open.
     */
    private void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Shares small description of the app associated with the link for more info (or install).
     * Supports sharing on Messenger, WhatsApp, Hangouts, Email, Twitter, Skype, Google+, etc...
     * @param appName
     * @param smallDescription
     * @param url
     */
    private void shareApp(String appName, String smallDescription, String url) {
        String sharedSubject = getString(R.string.share_app_subject, appName);
        String sharedText = getString(R.string.share_app_text, appName, smallDescription, url);

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, sharedSubject);
        sendIntent.putExtra(Intent.EXTRA_TEXT, sharedText);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getString(R.string.share_app_chooser_dialog_title)));
    }

}
