/**
 *
 */
package eterea.core.service.repository;

import eterea.core.service.kotlin.model.Grupo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author daniel
 */
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

    List<Grupo> findAllByVentaInternet(Byte habilitado, Sort sort);

    @Query(value = "SELECT DISTINCT GrP_Gru_ID FROM " +
            "(SELECT Vou_ID FROM voucher vou WHERE vou.Vou_FechaIn = ?1) v" +
            "JOIN voucherproducto vp" +
            "ON v.Vou_ID = vp.Vou_ID" +
            "FETCH JOIN grupoproducto gp" +
            "ON vp.VPr_Prd_ID = gp.GrP_Prd_ID", nativeQuery = true)
    List<Integer> findAllByFecha(@Param("fecha") OffsetDateTime fecha);

}
