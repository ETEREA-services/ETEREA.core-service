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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
@Table(name = "grupo")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Grupo extends Auditable implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1203415415010189973L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gru_id")
	private Integer grupoId;

	@Column(name = "gru_nombre")
	@Size(max = 150)
	@NotNull
	private String nombre = "";

	@Column(name = "venta_internet")
	@NotNull
	private Byte ventainternet = 0;

}
