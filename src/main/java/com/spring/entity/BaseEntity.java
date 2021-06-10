package com.spring.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
	public abstract class BaseEntity {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@Column(unique = true)
		private String alias;
		
		@Column
		private String status;
		
		@Column(name = "create_by")
		@CreatedBy
		private String createdBy;
		
		@Column(name = "create_date")
		@CreatedDate
		private Date createdDate;
		
		@Column(name = "updated_by")
		@LastModifiedBy
		private String updatedBy;
		
		@Column(name = "updated_date")
		@LastModifiedDate
		private Date updatedDate;
		
		public Long getId() {
			return id;
		}

		public String getAlias() {
			return alias;
		}

		public void setAlias(String alias) {
			this.alias = alias;
		}

		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public String getUpdatedBy() {
			return updatedBy;
		}

		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}

		public Date getUpdatedDate() {
			return updatedDate;
		}

		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public void setId(Long id) {
			this.id = id;
		}

		
}
