package com.example.data2.datasource.user;

import com.example.data2.datasource.user.CloudUserDataStore;
import com.example.data2.datasource.user.UserDataStore;

public class UserDataStoreFactory  {



    public UserDataStore create() {
        UserDataStore userDataStore;


            userDataStore = createCloudDataStore();


        return userDataStore;
    }
    public UserDataStore createCloudDataStore() {
       // final UserEntityJsonMapper userEntityJsonMapper = new UserEntityJsonMapper();
       // final RestApi restApi = new RestApiImpl(this.context, userEntityJsonMapper);

        return new CloudUserDataStore();
    }


}
