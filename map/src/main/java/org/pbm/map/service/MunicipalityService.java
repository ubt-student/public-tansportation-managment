package org.pbm.map.service;

import lombok.RequiredArgsConstructor;
import org.pbm.map.domain.model.Municipality;
import org.pbm.map.repository.MunicipalityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MunicipalityService {

    @PersistenceContext
    private EntityManager entityManager;
    private final MunicipalityRepository municipalityRepository;

    @Transactional
    public List<Municipality> getAllMunicipalities() {
        return municipalityRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public String findAllGeometries() {
        String query =
                "SELECT" +
                "   CAST(Row_to_json(fc) AS TEXT) AS geojson " +
                "FROM" +
                "   (" +
                "      SELECT" +
                "         'FeatureCollection' AS type," +
                "         Array_to_json(Array_agg(f)) AS features " +
                "      FROM" +
                "         (" +
                "            SELECT" +
                "               'Feature' AS type," +
                "               name_sq AS id," +
                "               CAST(St_asgeojson(St_transform(border, 3857)) AS JSON) AS geometry " +
                "            FROM" +
                "               municipality AS m" +
                "         )" +
                "         AS f" +
                "   ) " +
                "AS fc; ";
        String geoJson = (String) entityManager.createNativeQuery(query).getSingleResult();
        return geoJson;
    }

    public String findGeometriesByCode(Integer code) {
        String query =
                "SELECT" +
                        "   CAST(Row_to_json(fc) AS TEXT) AS geojson " +
                        "FROM" +
                        "   (" +
                        "      SELECT" +
                        "         'FeatureCollection' AS type," +
                        "         Array_to_json(Array_agg(f)) AS features " +
                        "      FROM" +
                        "         (" +
                        "            SELECT" +
                        "               'Feature' AS type," +
                        "               name_sq AS id," +
                        "               CAST(St_asgeojson(St_transform(border, 3857)) AS JSON) AS geometry " +
                        "            FROM" +
                        "               municipality AS m " +
                        "            WHERE" +
                        "               m.code = '" + code + "'" +
                        "         )" +
                        "         AS f" +
                        "   ) " +
                        "AS fc; ";
        String geoJson = (String) entityManager.createNativeQuery(query).getSingleResult();
        return geoJson;
    }
}
