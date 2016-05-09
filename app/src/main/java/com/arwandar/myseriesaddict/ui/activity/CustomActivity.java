package com.arwandar.myseriesaddict.ui.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.arwandar.myseriesaddict.R;
import com.arwandar.myseriesaddict.api.SharedPrefsSingleton;
import com.arwandar.myseriesaddict.api.converter.MemberComplexConverter;
import com.arwandar.myseriesaddict.api.dto.ErrorsComplexDTO;
import com.arwandar.myseriesaddict.api.dto.MemberComplexDTO;
import com.arwandar.myseriesaddict.api.model.User;
import com.arwandar.myseriesaddict.api.service.CallManager;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;

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
            //TODO rajouter le put extra
            startActivity(intent);
        } else if (id == R.id.nav_pending_shows) {
            Intent intent = new Intent(CustomActivity.this, ShowsListActivity.class);
            //TODO rajouter le put extra
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
                //TODO ajout poop deco foirée
            }
        });
    }

    protected void setCustomNavBar() {
        CallManager.getMemberInfosAsync(new Callback<MemberComplexDTO>() {
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
}
