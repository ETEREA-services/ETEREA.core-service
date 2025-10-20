package eterea.core.service.model.dto;

import java.util.List;

import eterea.core.service.kotlin.model.Articulo;

public record ProductoDetailsDto(
      int productoId,
      String nombre,
      String observaciones,
      Byte deshabilitado,
      Byte traslado,
      Byte puntoEncuentro,
      Byte ventaMostrador,
      Byte ventaInternet,
      List<Articulo> articulos) {

}
