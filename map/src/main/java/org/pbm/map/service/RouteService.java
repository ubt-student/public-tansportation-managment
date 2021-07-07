package org.pbm.map.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class RouteService {

    @PersistenceContext
    private EntityManager entityManager;

    public String findGeometriesById(Long id) {
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
                        "               id AS id," +
                        "               CAST(St_asgeojson(line) AS JSON) AS geometry " +
                        "            FROM" +
                        "               route AS r " +
                        "            WHERE" +
                        "               id = " + id +
                        "         )" +
                        "         AS f" +
                        "   ) " +
                        "AS fc; ";
        String geoJson = (String) entityManager.createNativeQuery(query).getSingleResult();
        return geoJson;
    }
}
