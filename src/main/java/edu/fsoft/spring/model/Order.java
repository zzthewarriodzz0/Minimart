package edu.fsoft.spring.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Data
@Entity
@Table(name = "[order]")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	@CreatedDate
	private LocalDate createdDate;

	@Column
	@CreatedBy
	private String createdBy;

	@Column
	private String customerPhone;

	@Column
	private float total;
}