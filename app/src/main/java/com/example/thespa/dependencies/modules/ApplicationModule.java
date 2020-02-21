package com.example.thespa.dependencies.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.data2.JobExecutor;
import com.example.data2.reprositoryimpl.BeauticianRepositoryImpl;
import com.example.data2.reprositoryimpl.FavoriteRepositoryImpl;
import com.example.data2.reprositoryimpl.OfferRepositoryImpl;
import com.example.data2.reprositoryimpl.ProductRepositoryImpl;
import com.example.data2.reprositoryimpl.ServiceRepositoryImpl;
import com.example.data2.reprositoryimpl.UserRepositoryImpl;
import com.example.domain.executors.PostExecutionThread;
import com.example.domain.executors.ThreadExecutor;
import com.example.domain.repository.BeauticianRepository;
import com.example.domain.repository.FavoriteRepository;
import com.example.domain.repository.OfferRepository;
import com.example.domain.repository.ProductRepository;
import com.example.domain.repository.ServiceRepository;
import com.example.domain.repository.UserRepository;
import com.example.domain.usecases.beautician_usescases.GetBeauticiansUseCase;
import com.example.domain.usecases.favorite_usecases.AddFavoriteUseCase;
import com.example.domain.usecases.favorite_usecases.GetUserFavorites;
import com.example.domain.usecases.favorite_usecases.RemoveFavoriteUseCase;
import com.example.domain.usecases.product_usecases.GetAllProductsUseCase;
import com.example.domain.usecases.product_usecases.GetProductCategoriesUseCases;
import com.example.domain.usecases.product_usecases.GetProductDetailsUseCase;
import com.example.domain.usecases.service_usecases.GetAllServicesUseCase;
import com.example.domain.usecases.user_usecases.RegisterAccountUseCase;
import com.example.domain.usecases.user_usecases.ResetPasswordUseCase;
import com.example.domain.usecases.user_usecases.SetPasswordUseCase;
import com.example.domain.usecases.user_usecases.SignInUseCase;
import com.example.domain.usecases.user_usecases.VerificationUseCase;
import com.example.domain.usecases.user_usecases.forgetPasswordUseCase;
import com.example.domain.usecases.offerusecases.GetAllOffersUseCase;
import com.example.thespa.UIThread;
import com.example.thespa.dependencies.AndroidApplication;
import com.example.thespa.preferences.UserSession;
import com.example.thespa.view.sign.SignViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor() {
        return new JobExecutor();
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread() {
        return new UIThread();
    }

    //@Provides @Singleton UserCache provideUserCache(UserCacheImpl userCache) {
     //   return userCache;
 //   }

    @Provides
    @Singleton
    UserRepository provideUserRepository(UserRepositoryImpl userRepository) {
        return userRepository;
    }

    @Provides
    @Singleton
    OfferRepository provideOfferRepository(OfferRepositoryImpl offerRepository)
    {
        return offerRepository;
    }

    @Provides
    @Singleton
    ServiceRepository provideServiceRepository(ServiceRepositoryImpl serviceRepository)
    {
        return serviceRepository;
    }
    @Provides
    @Singleton
    ProductRepository provideProductRepository(ProductRepositoryImpl productRepository)
    {
        return productRepository;
    }

    @Provides
    @Singleton
    FavoriteRepository provideFavoriteRepository(FavoriteRepositoryImpl favoriteRepository)
    {
        return favoriteRepository;
    }
    @Provides
    @Singleton
    public BeauticianRepository provideBeauticianRepository(BeauticianRepositoryImpl beauticianRepository)
    {
        return beauticianRepository;
    }
    public SignViewModelFactory provideSignViewModelFactory()
    {
        return new SignViewModelFactory(providesSignInUseCase(),provideRegisterAccountUseCase());
    }

     private SignInUseCase providesSignInUseCase()
    {
        return new SignInUseCase(provideUserRepository(new UserRepositoryImpl()),provideThreadExecutor(),providePostExecutionThread());
    }

     private RegisterAccountUseCase provideRegisterAccountUseCase()
    {
        return new RegisterAccountUseCase(provideUserRepository(new UserRepositoryImpl()),provideThreadExecutor(),providePostExecutionThread());
    }

    private VerificationUseCase provideVerificationUseCase()
    {
        return new VerificationUseCase(provideUserRepository(new UserRepositoryImpl()),provideThreadExecutor(),providePostExecutionThread());
    }

    private SetPasswordUseCase provideSetPasswordUseCase()
    {
        return new SetPasswordUseCase(provideUserRepository(new UserRepositoryImpl()),provideThreadExecutor(),providePostExecutionThread());

    }
    private forgetPasswordUseCase provideForgetPasswordUseCase()
    {
        return new forgetPasswordUseCase(provideUserRepository(new UserRepositoryImpl()),provideThreadExecutor(),providePostExecutionThread());

    }
    private ResetPasswordUseCase provideResetPasswordUseCase()
    {
        return new ResetPasswordUseCase(provideUserRepository(new UserRepositoryImpl()),provideThreadExecutor(),providePostExecutionThread());

    }

    private GetAllOffersUseCase provideGetAlOffersUseCase()
    {
        return new GetAllOffersUseCase(provideOfferRepository(new OfferRepositoryImpl()),provideThreadExecutor(),providePostExecutionThread());
    }

    private GetAllServicesUseCase provideGetAllServicesUseCase()
    {
        return new GetAllServicesUseCase(provideServiceRepository(new ServiceRepositoryImpl()),provideThreadExecutor(),providePostExecutionThread());
    }

    private GetProductCategoriesUseCases provideGetProductsCategoriesUseCase()
    {
        return new GetProductCategoriesUseCases(provideProductRepository(new ProductRepositoryImpl()),provideThreadExecutor(),providePostExecutionThread());
    }
    private GetAllProductsUseCase provideGetAllProductsUseCase()
    {
        return new GetAllProductsUseCase(provideProductRepository(new ProductRepositoryImpl()),provideThreadExecutor(),providePostExecutionThread());
    }

    private GetProductDetailsUseCase provideGetProductDetailsUseCase()
    {
        return new GetProductDetailsUseCase(provideProductRepository(new ProductRepositoryImpl()),provideThreadExecutor(),providePostExecutionThread());
    }

    private GetBeauticiansUseCase provideBeauticiansUseCase()
    {
        return new GetBeauticiansUseCase(provideBeauticianRepository(new BeauticianRepositoryImpl()),provideThreadExecutor(),providePostExecutionThread());
    }

    private AddFavoriteUseCase provideAddFavoriteUseCase()
    {
        return new AddFavoriteUseCase(provideFavoriteRepository(new FavoriteRepositoryImpl()),provideThreadExecutor(),providePostExecutionThread());
    }

    private RemoveFavoriteUseCase provideRemoveFavoriteUseCase()
    {
        return new RemoveFavoriteUseCase(provideFavoriteRepository(new FavoriteRepositoryImpl()),provideThreadExecutor(),providePostExecutionThread());
    }

    private GetUserFavorites provideUserFavoritesUseCase()
    {
        return new GetUserFavorites(provideFavoriteRepository(new FavoriteRepositoryImpl()),provideThreadExecutor(),providePostExecutionThread());
    }
    @Provides
   UserSession getUserSession()
    {
        return new UserSession(provideApplicationContext());
    }
    @Provides
    SharedPreferences getPreferences()
    {
        return provideApplicationContext().getSharedPreferences("Reg", 0);
    }

}
