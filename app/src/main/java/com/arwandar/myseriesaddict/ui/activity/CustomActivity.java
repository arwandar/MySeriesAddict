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

public class CustomActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_friends) {
            startActivity(new Intent(CustomActivity.this, FriendsActivity.class));
        } else if (id == R.id.nav_archived_shows) {
            Intent intent = new Intent(CustomActivity.this, ShowsListActivity.class);
            intent.putExtra("fragmentChoose", 0);
            startActivity(intent);
        } else if (id == R.id.nav_pending_shows) {
            Intent intent = new Intent(CustomActivity.this, ShowsListActivity.class);
            intent.putExtra("fragmentChoose", 1);
            startActivity(intent);
        } else if (id == R.id.nav_deconnection) {
            disconnection();
        }
        drawer.closeDrawers();
        return true;
    }

    private void disconnection() {
        CallManager.destroyTokenAsync(new Callback<ErrorsComplexDTO>() {
            @Override
            public void onResponse(Call<ErrorsComplexDTO> call, Response<ErrorsComplexDTO> response) {
                SharedPrefsSingleton.setAccessToken("");
                Intent intent = new Intent(CustomActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ErrorsComplexDTO> call, Throwable t) {
                Toast.makeText(CustomActivity.this, "Pas d'accès à internet, veuillez réessayer plus tard.", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(CustomActivity.this);
                builder.setMessage(R.string.dialog_message_error)
                        .setTitle(R.string.dialog_title_error);
                builder.setNeutralButton(R.string.ok_error, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    protected void setCustomNavBar() {
        CallManager.getMemberInfosAsync(true, new Callback<MemberComplexDTO>() {
            @Override
            public void onResponse(Call<MemberComplexDTO> call, Response<MemberComplexDTO> response) {
                MemberComplexConverter memberComplexConverter = new MemberComplexConverter();
                User user = memberComplexConverter.convertDtoToMember(response.body()).getUser();

                TextView login = (TextView) findViewById(R.id.nav_bar_login);
                TextView xp = (TextView) findViewById(R.id.nav_bar_xp);
                ImageView picture = (ImageView) findViewById(R.id.nav_bar_picture);

                login.setText(user.getmLogin());
                xp.setText(user.getmXp() + " xp");
                Picasso.with(getApplicationContext()).load(user.getmAvatar()).into(picture);

            }

            @Override
            public void onFailure(Call<MemberComplexDTO> call, Throwable t) {

            }
        });
    }

    protected void initActivity() {
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        setCustomNavBar();
    }

    public void showError() {
        Toast.makeText(CustomActivity.this, "Pas d'accès à internet, veuillez réessayer plus tard.", Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(CustomActivity.this);
        builder.setMessage(R.string.dialog_message_error)
                .setTitle(R.string.dialog_title_error);
        builder.setNeutralButton(R.string.ok_error, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showErrorLogin(int code) {
        if (code == 400) {
            Toast.makeText(CustomActivity.this, "Votre session a expiré, veuillez vous reconnecter.", Toast.LENGTH_SHORT).show();
            SharedPrefsSingleton.setAccessToken("");
            Intent intent = new Intent(CustomActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
