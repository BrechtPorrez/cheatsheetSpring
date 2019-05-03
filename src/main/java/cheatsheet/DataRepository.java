package cheatsheet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DataRepository extends JpaRepository<Data, UUID> {
    List<Data> findDataByLanguage(String language);

    @Query("select distinct d.language from Data d" )
    List<String> findAllLanguages();

    @Query("select distinct d.subject from Data d where d.language= :lang")
    List<String> findAllSubject(String lang);
}
