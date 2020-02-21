package com.example.thespa.dependencies.components;


import com.example.thespa.dependencies.PerActivity;
import com.example.thespa.view.products.ProductDetailsActivity;
import com.example.thespa.view.products.ProductsActivity;
import com.example.thespa.view.products.ProductsCategoriesActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent
public interface ProductComponent {

    @Subcomponent.Factory
    interface Factory {
        ProductComponent create();
    }

    void inject(ProductsCategoriesActivity productsCategoriesActivity);

    void inject(ProductsActivity productsActivity);

    void inject(ProductDetailsActivity productDetailsActivity);
}
