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

    @Transactional
    public void saveGeom(String id, String geom){
        geom = geom.replaceAll(",", " ").replaceAll("\\[", "");
        geom = geom.replaceAll("\\]",",");
        if ((geom != null) && (geom.length() > 0)) {
            geom = geom.substring(0, geom.length() - 2);
        }
        String query ="INSERT INTO public.route(id, line) VALUES (?, ST_GeomFromText('LINESTRING(" + geom + ")', 3857))";
        System.out.println(query);
        entityManager.createNativeQuery(query).setParameter(1, Long.valueOf(id)).executeUpdate();
    }
}
