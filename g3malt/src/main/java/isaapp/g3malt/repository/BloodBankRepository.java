package isaapp.g3malt.repository;

import isaapp.g3malt.model.BloodBank;

import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BloodBankRepository extends JpaRepository<BloodBank, Integer>{
    @Query("from BloodBank b where lower(b.name) like CONCAT('%', lower(:name), '%') and lower(b.city) like CONCAT('%', lower(:city), '%') and b.rating >= :rating")
    List<BloodBank> searchFilterSort(@Param("name") String searchName, @Param("city") String searchCity, @Param("rating") Double filterValue);
}
