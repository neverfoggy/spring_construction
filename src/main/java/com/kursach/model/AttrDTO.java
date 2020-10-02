package com.kursach.model;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;

public class AttrDTO {
   private Integer id;
   private Short amount;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Short getAmount() {
      return amount;
   }

   public void setAmount(Short amount) {
      this.amount = amount;
   }
}
