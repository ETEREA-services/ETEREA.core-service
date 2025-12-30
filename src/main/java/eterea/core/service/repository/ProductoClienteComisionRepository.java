package eterea.core.service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import eterea.core.service.kotlin.model.ProductoClienteComision;

@Repository
public interface ProductoClienteComisionRepository extends JpaRepository<ProductoClienteComision, Long> {

   Optional<ProductoClienteComision> findByProductoIdAndClienteId(Integer productoId, Long clienteId);

   @Query("""
         SELECT DISTINCT pcc
         FROM ProductoClienteComision pcc
         JOIN ProductoArticulo pa ON pcc.productoId = pa.productoId
         JOIN ReservaArticulo ra ON pa.articuloId = ra.articuloId
         JOIN VoucherProducto vp ON ra.voucherId = vp.voucherId
               AND pa.productoId = vp.productoId
         WHERE ra.reservaId = :reservaId
               AND pcc.clienteId = :clienteId
               AND pa.articuloId = :articuloId
         ORDER BY pcc.productoClienteComisionId ASC
         """)
   List<ProductoClienteComision> findByClienteIdAndReservaIdAndArticuloId(Long clienteId, Long reservaId, String articuloId);
}
