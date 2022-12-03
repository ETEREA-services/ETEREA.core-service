/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eterea.api.rest.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author Romina
 */
@Data
@Entity
@Table(name = "voucherproducto")
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class VoucherProducto extends Auditable implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5539001630148258742L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VPr_ID")
    private Long voucherProductoId;

    @Column(name = "VPr_Vou_ID")
    private Long voucherId;
    
    @Column(name = "VPr_Prd_ID")
    private Integer productoId;
   
    @Column(name = "VPr_Paxs")
    private Integer cantidadPaxs;
         
}