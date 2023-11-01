package com.bookstore.bookstoreapi.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

  private String username;
  private String password;
  private String name;
  private String homeAddress;
}
