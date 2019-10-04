package com.bankingapplication.rest.domain.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.bankingapplication.rest.utils.DateAssistantUtils;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BaseMaster{

  @Column(name = "created_by")
  @CreatedBy
  private String createdBy;

  @Column(name = "updated_by")
  @CreatedBy
  private String updatedBy;

  @Column(name = "created_date", nullable = false, updatable = false)
  public Date createdDate;

  @Column(name = "updated_date")
  public Date updatedDate;

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
