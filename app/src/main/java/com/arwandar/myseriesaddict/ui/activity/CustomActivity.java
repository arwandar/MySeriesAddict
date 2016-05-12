package com.arwandar.myseriesaddict.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.SharedPrefsSingleton;
import com.arwandar.myseriesaddict.api.converter.MemberComplexConverter;
import com.arwandar.myseriesaddict.api.dto.ErrorsComplexDTO;
import com.arwandar.myseriesaddict.api.dto.MemberComplexDTO;
import com.arwandar.myseriesaddict.api.model.User;
import com.arwandar.myseriesaddict.api.service.CallManager;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.String.format;

public abstract class CustomActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout mLayout;
    @Bind(R.id.nav_view)
    NavigationView mNavigationView;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void onBackPressed() {
        if (mLayout.isDrawerOpen(GravityCompat.START)) {
            mLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.custom, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(CustomActivity.this, SettingsActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.nav_friends:
                startActivity(new Intent(CustomActivity.this, FriendsActivity.class));
                break;
            case R.id.nav_archived_shows:
                intent = new Intent(CustomActivity.this, ShowsListActivity.class);
                intent.putExtra("fragmentChoose", 0);
                startActivity(intent);
                break;
            case R.id.nav_pending_shows:
                intent = new Intent(CustomActivity.this, ShowsListActivity.class);
                intent.putExtra("fragmentChoose", 1);
                startActivity(intent);
                break;
            case R.id.nav_deconnection:
                disconnection();
                break;
            case R.id.nav_episodes:
                intent = new Intent(CustomActivity.this, EpisodesListActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_quick_view:
                intent = new Intent(CustomActivity.this, QuickWatchedActivity.class);
                startActivity(intent);
                break;
        }
        mLayout.closeDrawers();
        return true;
    }

    /**
     * gesstion de la deconnexion de l'utilisateur
     */
    private void disconnection() {
        CallManager.destroyTokenAsync(new Callback<ErrorsComplexDTO>() {
            @Override
            public void onResponse(Call<ErrorsComplexDTO> call,
                    Response<ErrorsComplexDTO> response) {
                SharedPrefsSingleton.setAccessToken("");
                Intent intent = new Intent(CustomActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ErrorsComplexDTO> call, Throwable t) {
                showError();
            }
        });
    }

    /**
     * lignes commune à tous les OnCreate
     * CustomActivity ne contient pas de OnCreate car elle est Override dans les classes héritantes
     */
    protected void initActivity() {
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mLayout, mToolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mLayout.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
        setCustomNavBar();
    }

    /**
     * Permet de recuperer l'avatar de l'utilisateur et de l'afficher dans la barre latérale
     */
    protected void setCustomNavBar() {
        CallManager.getMemberInfosAsync(true, new Callback<MemberComplexDTO>() {
            @Override
            public void onResponse(Call<MemberComplexDTO> call,
                    Response<MemberComplexDTO> response) {
                MemberComplexConverter memberComplexConverter = new MemberComplexConverter();
                User user;
                user = memberComplexConverter.convertDtoToMember(response.body()).getUser();

                TextView login = (TextView) findViewById(R.id.nav_bar_login);
                TextView xp = (TextView) findViewById(R.id.nav_bar_xp);
                ImageView picture = (ImageView) findViewById(R.id.nav_bar_picture);

                login.setText(user.getmLogin());
                xp.setText(format("%s xp", user.getmXp()));
                Picasso.with(getApplicationContext()).load(user.getmAvatar()).into(picture);
            }

            @Override
            public void onFailure(Call<MemberComplexDTO> call, Throwable t) {

            }
        });
    }

    /**
     * affiche un toast pour informer l'utilisateur
     * d'un problème probablement lié à la connectivité
     */
    public void showError() {
        Toast.makeText(CustomActivity.this,
                R.string.dialog_message_error, Toast.LENGTH_SHORT)
                .show();
    }

    /**
     * affiche des informations et résout les erreurs ayant des codes http
     * Pour le moment, seule l'erreur d'authentification est implementée
     *
     * @param code -> le code de retour de la réponse serveur
     */
    public void showErrorLogin(int code) {
        if (code == 400) {
            Toast.makeText(CustomActivity.this,
                    R.string.session_expirée_message, Toast.LENGTH_SHORT)
                    .show();
            SharedPrefsSingleton.setAccessToken("");
            Intent intent = new Intent(CustomActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
