package com.duotech.service;

import com.duotech.database.DatabaseClass;
import com.duotech.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileService {

    private Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public ProfileService(){
        profiles.put("Kaushik", new Profile(1L, "Kaushik", "Kaushik", "Kothagal"));
        profiles.put("Mayur", new Profile(1L, "Mayur", "Mayur", "Agrawal"));
    }

    public List<Profile> getAllProfiles(){
        return new ArrayList<Profile>(profiles.values());
    }

    public Profile getProfile(String profName){
        return profiles.get(profName);
    }

    public Profile addProfile(Profile profile){
        profile.setId(profiles.size() + 1);
        profiles.put(profile.getProfName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile){
        if(profile.getProfName().isEmpty()){
            return null;
        }
        profiles.put(profile.getProfName(), profile);
        return profile;
    }

    public Profile removeProfile(String name){
        return profiles.remove(name);
    }
}
