package com.bankingapplication.rest.domain.base;

import com.bankingapplication.rest.utils.DateAssistantUtils;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
/**
 * BaseMaster is used to define common properties for all entities.
 *
 * @author Nitesh Kumar
 */

@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseMaster{

  @Column(name = "created_by")
  private String createdBy;

  @Column(name = "updated_by")
  private String updatedBy;

  @Column(name = "created_date", nullable = false, updatable = false)
  public Long createdDate;

  @Column(name = "updated_date")
  public Long updatedDate;

  public BaseMaster(String createdBy) {
    this.createdBy = createdBy;
  }

  public BaseMaster() {
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  @PrePersist
  void createdAt() {
    this.createdDate = DateAssistantUtils.setDate();
  }

  @PreUpdate
  void updatedAt() {
    this.updatedDate = DateAssistantUtils.setDate();
  }
}
