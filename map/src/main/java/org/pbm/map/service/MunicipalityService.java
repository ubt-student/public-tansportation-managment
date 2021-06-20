package org.pbm.map.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@RequiredArgsConstructor
public class MunicipalityService {

    @PersistenceContext
    private EntityManager entityManager;

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
}
