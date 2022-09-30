package microservices.app.service;

import microservices.app.model.Profile;
import microservices.app.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile createProfile(Profile profile) {

        if (profile == null) {
            throw new IllegalArgumentException("profile is null");
        }

        Profile p = profileRepository.save(profile);
        return p;
    }

    public Profile getProfile(String profileId) {

        if (profileId == null || profileId.trim().length() < 1 || profileId.isEmpty()) {
            throw new IllegalArgumentException("profile id is null");
        }

        Optional<Profile> profile = profileRepository.findById(profileId);

        if (!profile.isPresent()) {
            throw new NullPointerException("profile with id: " + profileId + " not found");
        }

        return profile.get();

    }

    public Profile upateProfile(Profile profile) {

        if (profile == null) {
            throw new IllegalArgumentException("profile is null");
        }

        if (profile.getId() == null || profile.getId().trim().length() < 1 || profile.getId().isEmpty()) {
            throw new IllegalArgumentException("profile id is null");
        }

        Profile p = profileRepository.save(profile);
        return p;
    }

    public void deleteProfile(String profileId) {

        if (profileId == null || profileId.trim().length() < 1 || profileId.isEmpty()) {
            throw new IllegalArgumentException("profileId is null");
        }

        Profile profile = getProfile(profileId);
        profileRepository.delete(profile);
    }

    public void deleteProfile(Profile profile) {

        if (profile == null) {
            throw new IllegalArgumentException("profile is null");
        }

        if (profile.getId() == null || profile.getId().trim().length() < 1 || profile.getId().isEmpty()) {
            throw new IllegalArgumentException("profile id is null");
        }

        profileRepository.delete(profile);
    }
}
