package com.example.data2.mapper;

import com.example.data2.entities.beauticians.RemoteBeautician;
import com.example.data2.entities.offers.RemoteOffer;
import com.example.data2.entities.products.RemoteProduct;
import com.example.data2.entities.products.RemoteProductCategory;
import com.example.data2.entities.services.RemoteService;
import com.example.data2.entities.RemoteUser;
import com.example.data2.entities.services.RemoteServiceCategory;
import com.example.domain.entities.Beautician;
import com.example.domain.entities.Offer;
import com.example.domain.entities.Product;
import com.example.domain.entities.ProductCategory;
import com.example.domain.entities.Service;
import com.example.domain.entities.ServiceCategory;
import com.example.domain.entities.User;

import java.util.ArrayList;

import javax.inject.Inject;

public class RemoteToLocalMapper {


    @Inject
    RemoteToLocalMapper() {
    }

    public User fromRemoteToLocalUser(RemoteUser remoteUser)
    {
        return new User(remoteUser.getFullName(),remoteUser.getMobile(),remoteUser.getEmail(),remoteUser.getActive(),remoteUser.getId());
    }

    private Offer fromRemoteToLocalOffer(RemoteOffer remoteOffer)
    {
        return new Offer(remoteOffer.getId(),remoteOffer.getBranchId(),remoteOffer.getName(),remoteOffer.getDiscountType(),remoteOffer.getDiscount(),remoteOffer.getExpiryDate());
    }

    public ArrayList<Offer> fromRemoteToLocalListOfOffers(ArrayList<RemoteOffer> remoteOffers)
    {
        ArrayList<Offer> offers = new ArrayList<>();
        for (RemoteOffer re:remoteOffers) {
            offers.add(fromRemoteToLocalOffer(re));
        }
        return offers;
    }

    public ArrayList<Service> fromRemoteToLocalListOfServices(ArrayList<RemoteService> remoteServices)
    {
        ArrayList<Service> services = new ArrayList<>();
        for (RemoteService re:remoteServices) {
            services.add(fromRemoteToLocalService(re));
        }
        return services;
    }

    public Service fromRemoteToLocalService(RemoteService remoteService)
    {
        return new Service(remoteService.getId(),remoteService.getCategoryId(),remoteService.getName(),remoteService.getDescription(),remoteService.getImage(),
                remoteService.getPrice(),remoteService.getDuration(),remoteService.getIsFavorite());
    }

    public ArrayList<ProductCategory> fromRemoteToLocalProductCategoryList(ArrayList<RemoteProductCategory> categories)
    {
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        for (RemoteProductCategory re:categories) {
            productCategories.add(fromRemoteToLocalProductCategory(re));
        }
        return productCategories;
    }

    private ProductCategory fromRemoteToLocalProductCategory(RemoteProductCategory productCategory)
    {
        return new ProductCategory(productCategory.getId(),productCategory.getName(),productCategory.getDescription(),productCategory.getImage());
    }

    public ArrayList<Product> fromRemoteToLocalProductList(ArrayList<RemoteProduct> products)
    {
        ArrayList<Product> productArrayList = new ArrayList<>();
        for (RemoteProduct re:products) {
            productArrayList.add(fromRemoteToLocalProduct(re));
        }
        return productArrayList;
    }

    public Product fromRemoteToLocalProduct(RemoteProduct product)
    {
        return new Product(product.getId(),product.getName(),product.getDescription(),product.getImage(),product.getProductCode(),product.getPrice());
    }

    public ArrayList<Beautician> fromRemoteToLocalBeauticiansList(ArrayList<RemoteBeautician> beauticians)
    {
        ArrayList<Beautician> beauticianArrayList = new ArrayList<>();
        for (RemoteBeautician re:beauticians) {
            beauticianArrayList.add(fromRemoteToLocalBeautician(re));
        }
        return beauticianArrayList;
    }

    private Beautician fromRemoteToLocalBeautician(RemoteBeautician beautician)
    {
        return new Beautician(beautician.getId(),beautician.getBranchId(),beautician.getName(),beautician.getJobTitle(),beautician.getDescription(),beautician.getImage(),beautician.getPhone(),
                beautician.getMobile(),beautician.getEmail());
    }

    public ArrayList<ServiceCategory> fromRemoteToLocalServiceCategoryList(ArrayList<RemoteServiceCategory> categories)
    {
        ArrayList<ServiceCategory> serviceCategories = new ArrayList<>();
        for (RemoteServiceCategory re:categories) {
            serviceCategories.add(fromRemoteToLocalServiceCategory(re));
        }
        return serviceCategories;
    }

    private ServiceCategory fromRemoteToLocalServiceCategory(RemoteServiceCategory serviceCategory)
    {
        return new ServiceCategory(serviceCategory.getId(),serviceCategory.getName(),serviceCategory.getDescription(),serviceCategory.getImage());
    }
}
