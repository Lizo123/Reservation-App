package com.example.thespa.view.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.domain.entities.Offer;
import com.example.domain.entities.ProductCategory;
import com.example.domain.entities.Response;
import com.example.domain.entities.ServiceCategory;
import com.example.domain.usecases.DefaultObserver;
import com.example.domain.usecases.offerusecases.GetAllOffersUseCase;
import com.example.domain.usecases.product_usecases.GetProductCategoriesUseCases;
import com.example.domain.usecases.service_usecases.GetServiceCategoriesUseCase;
import com.example.thespa.dependencies.AndroidApplication;
import com.example.thespa.dependencies.components.HomeComponent;
import com.example.thespa.preferences.LanguageManager;
import com.example.thespa.preferences.UserSession;
import com.example.thespa.view.BaseActivity;
import com.example.thespa.view.massage.MassageActivity;
import com.example.thespa.view.offers.CardFragmentPagerAdapter;
import com.example.thespa.view.offers.OffersActivity;
import com.example.thespa.view.offers.OffersAdapter;
import com.example.thespa.view.offers.ShadowTransformer;
import com.example.thespa.R;
import com.example.thespa.view.aboutus.AboutUsActivity;
import com.example.thespa.view.appointments.AppointmentsActivity;
import com.example.thespa.view.beauticians.BeauticiansActivity;
import com.example.thespa.view.carts.CartActivity;
import com.example.thespa.view.contact_us.ContactUsActivity;
import com.example.thespa.databinding.ActivityHomeBinding;
import com.example.thespa.view.favorite.FavoriteActivity;
import com.example.thespa.view.products.ProductCategoriesAdapter;
import com.example.thespa.view.products.ProductDetailsActivity;
import com.example.thespa.view.products.ProductsCategoriesActivity;
import com.example.thespa.view.profile.ProfileActivity;
import com.example.thespa.view.services.ServicesCategoriesActivity;
import com.example.thespa.view.services.ServicesCategoriesAdapter;
import com.example.thespa.view.sign.MainActivity;

import java.util.ArrayList;
import java.util.Locale;

import javax.inject.Inject;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    CardFragmentPagerAdapter pagerAdapter;
    ShadowTransformer fragmentCardShadowTransformer;
    ActivityHomeBinding activityHomeBinding;
    Toolbar toolbar;
    DrawerLayout drawer;
    ViewPager viewPager;

    RelativeLayout profile;
    NavigationView navigationView;
    View headerLayout ;
    TextView login;
    @Inject
    GetAllOffersUseCase getAllOffersUseCase;
    @Inject
    GetServiceCategoriesUseCase getServiceCategoriesUseCase;
    ServicesCategoriesAdapter servicesAdapter;
    ProductCategoriesAdapter productAdapter;
    @Inject
    GetProductCategoriesUseCases getProductCategoriesUseCases;
    HomeComponent homeComponent;
    @Inject
    UserSession userSession;
    String lang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initializeInjector();
        super.onCreate(savedInstanceState);
        activityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        homeComponent.inject(this);
        toolbar = activityHomeBinding.toolbar;
        viewPager = activityHomeBinding.viewpager;

        activityHomeBinding.setActivity(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        lang = LanguageManager.returnLanguage();
        drawer = activityHomeBinding.drawerLayout;
        navigationView= activityHomeBinding.navView;
        headerLayout = navigationView.getHeaderView(0);
        profile = headerLayout.findViewById(R.id.profile);
        login = headerLayout.findViewById(R.id.login);
        if(userSession.isUserLoggedIn())
        {
            profile.setVisibility(View.VISIBLE);
            headerLayout.findViewById(R.id.login).setVisibility(View.GONE);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLogin();
            }
        });

        getAllOffersUseCase.execute(new getAllOffersObserver(), lang);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_Activity(new ProfileActivity());
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        activityHomeBinding.navView.setNavigationItemSelectedListener(this);


        getServiceCategoriesUseCase.execute(new getServicesCategoriesObserver(),lang);
        getProductCategoriesUseCases.execute(new getProductCategoriesObserver(),lang);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        MenuItem item = menu.findItem(R.id.language);
        if(LanguageManager.returnLanguage().equals("ar"))
        {
            item.setTitle("English");
        }
        else
        {
            item.setTitle("Arabic");
        }
        return true;
    }

    private void initializeInjector() {
        this.homeComponent = ((AndroidApplication) getApplicationContext())
                .getApplicationComponent().homeComponent().create();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            SearchView mSearchView = (SearchView)item.getActionView();
            mSearchView.setQueryHint("Search");
            mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }
                @Override
                public boolean onQueryTextChange(String newText) {


                    return true;
                }
            });
            return true;
        }
        if(id == R.id.language)
        {
            if(item.getTitle().equals("Arabic"))
            {
                LanguageManager.addLanguage("ar");
                setAppLocale("ar");

            }
            else
            {
                LanguageManager.addLanguage("en");
                setAppLocale("en");

            }

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_services) {
            start_Activity(new ServicesCategoriesActivity());
        } else if (id == R.id.nav_products) {
            start_Activity(new ProductsCategoriesActivity());

        } else if (id == R.id.nav_offers) {
            start_Activity(new OffersActivity());

        } else if (id == R.id.nav_beauticians) {
            start_Activity(new BeauticiansActivity());

        } else if (id == R.id.nav_cart) {
            if(userSession.isUserLoggedIn()) {

            }
            else
                start_Activity(new CartActivity());
              //  showToast();
        }
        else if(id== R.id.nav_appointments)
        {
            if(userSession.isUserLoggedIn()) {

            }
            else
                start_Activity(new AppointmentsActivity());
             //   showToast();
        }
        else if(id == R.id.nav_favorite)
        {
            if(userSession.isUserLoggedIn()) {

            }
                 else
                start_Activity(new FavoriteActivity());
             //   showToast();
        }
        else if(id==R.id.nav_about_us)
        {
            start_Activity(new AboutUsActivity());
        }
        else if(id==R.id.nav_contact_us)
        {
            start_Activity(new ContactUsActivity());
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private final class getAllOffersObserver extends DefaultObserver<Response> {
        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Response response) {

            if(response.error.toString().equals("get_offers_successfully")) {

                ArrayList<Offer> offers = (ArrayList<Offer>) response.success;
                ArrayList<Offer> offerArrayList = new ArrayList<>();
                if(offers.size()>0) {

                for(int i=0;i<offers.size();i++)
                {
                    if(i<=3)
                    {
                        offerArrayList.add(offers.get(i));
                    }
                }

                    pagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), dpToPixels(2, getBaseContext()),offerArrayList);
                    fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
                    fragmentCardShadowTransformer.enableScaling(true);

                    viewPager.setAdapter(pagerAdapter);
                    viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
                    viewPager.setOffscreenPageLimit(3);
                    viewPager.setClipToPadding(false);

                    viewPager.setPageMargin(50);
                }
                else
                {
                  offerArrayList.add(new Offer("","",getString(R.string.there_is_no_offers),"","",""));
                    pagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), dpToPixels(2, getBaseContext()),offerArrayList);
                    fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
                    fragmentCardShadowTransformer.enableScaling(true);

                    viewPager.setAdapter(pagerAdapter);
                    viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
                    viewPager.setOffscreenPageLimit(3);
                    viewPager.setClipToPadding(false);

                    viewPager.setPageMargin(50);
                }
            }
        }
    }
    private final class getServicesCategoriesObserver extends DefaultObserver<Response> {
        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Response response) {

            if(response.error.toString().equals("get_services_categories_successfully")) {
                ArrayList<ServiceCategory> serviceCategories = (ArrayList<ServiceCategory>) response.success;
                ArrayList<ServiceCategory> categories = new ArrayList<>();
                for(int i=0;i<serviceCategories.size();i++)
                {
                    if(i<=3)
                    {
                        categories.add(serviceCategories.get(i));
                    }
                }
                if(categories.size()>0) {
                    activityHomeBinding.servicesProgress.setVisibility(View.GONE);
                    activityHomeBinding.servicesRecycler.setVisibility(View.VISIBLE);
                    servicesAdapter = new ServicesCategoriesAdapter(getBaseContext(), categories);
                    activityHomeBinding.servicesRecycler.setLayoutManager(new GridLayoutManager(getBaseContext()
                            , 2));
                    activityHomeBinding.servicesRecycler.setAdapter(servicesAdapter);
                }
                else
                {

                }
            }
        }
    }
    private final class getProductCategoriesObserver extends DefaultObserver<Response> {
        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(Response response) {

            if(response.error.toString().equals("get_products_categories_successfully")) {
                ArrayList<ProductCategory> productCategories = (ArrayList<ProductCategory>) response.success;
                if(productCategories.size()>0) {

                    ArrayList<ProductCategory> categories = new ArrayList<>();
                for(int i=0;i<productCategories.size();i++)
                {
                    if(i<=3)
                    {
                        categories.add(productCategories.get(i));
                    }
                }
                activityHomeBinding.productsProgress.setVisibility(View.GONE);
                activityHomeBinding.productsRecycler.setVisibility(View.VISIBLE);
                    productAdapter = new ProductCategoriesAdapter(getBaseContext(), categories,"home");
                   activityHomeBinding.productsRecycler.setLayoutManager(new GridLayoutManager(getBaseContext()
                           , 2));
                    activityHomeBinding.productsRecycler.setAdapter(productAdapter);
                }
                else
                {

                }
            }
        }
    }
    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }
    public void StartServices()
    {
        Intent i = new Intent(this, ServicesCategoriesActivity.class);
        startActivity(i);
    }

    public void goToServiceDetails()
    {
        Intent i = new Intent(this, MassageActivity.class);
        startActivity(i);
    }
    public void goToProductDetails()
    {
        Intent i = new Intent(this, ProductDetailsActivity.class);
        startActivity(i);
    }
    public void start_Activity(Activity activity)
    {
        Intent i = new Intent(this,activity.getClass());
        startActivity(i);
    }
    public void StartProducts()
    {
        Intent i = new Intent(this, ProductsCategoriesActivity.class);
        startActivity(i);
    }

    public void setAppLocale(String localeCode){
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            config.setLocale(new Locale(localeCode.toLowerCase()));
        } else {
            config.locale = new Locale(localeCode.toLowerCase());
        }
        resources.updateConfiguration(config, dm);
        recreate();
    }

    @Override
    public void recreate() {
        super.recreate();
    }

    public void startLogin()
    {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("source","home");
        startActivity(i);
    }
    public void showToast()
    {
        Toast.makeText(this, getString(R.string.not_signed), Toast.LENGTH_LONG).show();
    }
}
