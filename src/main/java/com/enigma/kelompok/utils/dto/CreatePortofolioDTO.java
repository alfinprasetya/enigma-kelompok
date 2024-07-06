package com.enigma.kelompok.utils.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePortofolioDTO {
  private Integer user_id;
  private Integer stock_id;
  private Integer quantity_lot;
}
