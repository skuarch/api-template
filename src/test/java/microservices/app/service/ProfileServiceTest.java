package microservices.app.service;

import microservices.app.model.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")

class ProfileServiceTest {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private DynamoDbService dynamoDbService;

    @BeforeEach
    private void before() throws InterruptedException {
        String tableName = "dev-employee-recognition-profile-table";
        if (!dynamoDbService.isTableAlreadyCreated(tableName)) {
            dynamoDbService.createTable(tableName);
        }
    }

    @Test
    void createProfile() {

        // given
        Profile profile = new Profile();
        profile.setFirstName("firstName");

        // when
        Profile result = profileService.createProfile(profile);

        // then
        assertNotNull(result.getId());

    }

    @Test
    void getProfile() {
        // given
        Profile profile = new Profile();
        profile.setFirstName("firstName");
        Profile expected = profileService.createProfile(profile);

        // when
        Profile result = profileService.getProfile(expected.getId());

        // then
        assertEquals(expected.getId(), result.getId());
    }

    @Test
    void upateProfile() {

        // given
        Profile profile = new Profile();
        profile.setFirstName("firstName");
        Profile expected = profileService.createProfile(profile);
        String expectedName = "newName";

        // when
        expected.setFirstName(expectedName);
        Profile result = profileService.upateProfile(expected);

        // then
        assertEquals(expected.getFirstName(), result.getFirstName());
    }

    @Test
    void deleteProfile() {

        // given
        Profile profile = new Profile();
        profile.setFirstName("firstName");
        Profile expected = profileService.createProfile(profile);

        // when
        Exception ex = assertThrows(NullPointerException.class, () -> {
            profileService.deleteProfile(expected);
            profileService.getProfile(expected.getId());
        });

        // then
        assertNotNull(ex);
    }

    @Test
    void testDeleteProfile() {

        // given
        Profile profile = new Profile();
        profile.setFirstName("firstName");
        Profile expected = profileService.createProfile(profile);

        // when
        Exception ex = assertThrows(NullPointerException.class, () -> {
            profileService.deleteProfile(expected.getId());
            profileService.getProfile(expected.getId());
        });

        // then
        assertNotNull(ex);
    }

    @Test
    void upateProfile2() {

        // given
        Profile profile = null;

        // when
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            profileService.upateProfile(profile);
        });

        // then
        assertNotNull(ex);
    }

    @Test
    void upateProfile3() {

        // given
        Profile profile = new Profile();

        // when
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            profileService.upateProfile(profile);
        });

        // then
        assertNotNull(ex);
    }

    @Test
    void testDeleteProfile1() {

        // given
        Profile profile = null;

        // when
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            profileService.deleteProfile(profile);
        });

        // then
        assertNotNull(ex);
    }

    @Test
    void createProfile1() {

        // given
        Profile profile = null;

        // when
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            profileService.createProfile(profile);
        });

        // then
        assertNotNull(ex);
    }

    @Test
    void createProfile2() {

        // given
        Profile profile = new Profile();

        // when
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            profileService.getProfile(profile.getId());
        });

        // then
        assertNotNull(ex);
    }

    @Test
    void createProfile3() {

        // given
        Profile profile = new Profile();
        profile.setId("");

        // when
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            profileService.getProfile(profile.getId());
        });

        // then
        assertNotNull(ex);
    }

    @Test
    void createProfile4() {

        // given
        Profile profile = new Profile();
        profile.setId(" ");

        // when
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            profileService.getProfile(profile.getId());
        });

        // then
        assertNotNull(ex);
    }

    @Test
    void deleteProfile2() {

        // given
        Profile profile = new Profile();
        profile.setId(" ");

        // when
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            profileService.deleteProfile(profile.getId());
        });

        // then
        assertNotNull(ex);
    }

    @Test
    void deleteProfile3() {

        // given
        Profile profile = new Profile();
        profile.setId("");

        // when
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            profileService.deleteProfile(profile.getId());
        });

        // then
        assertNotNull(ex);
    }

    @Test
    void deleteProfile4() {

        // given
        Profile profile = new Profile();
        profile.setId(null);

        // when
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            profileService.deleteProfile(profile.getId());
        });

        // then
        assertNotNull(ex);
    }

    @Test
    void updateProfile4() {

        // given
        Profile profile = new Profile();
        profile.setId(null);

        // when
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            profileService.upateProfile(profile);
        });

        // then
        assertNotNull(ex);
    }

    @Test
    void updateProfile5() {

        // given
        Profile profile = new Profile();
        profile.setId("");

        // when
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            profileService.upateProfile(profile);
        });

        // then
        assertNotNull(ex);
    }
}
