package microservices.app.repository;

import microservices.app.model.Profile;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface ProfileRepository extends PagingAndSortingRepository<Profile, String> {
}
