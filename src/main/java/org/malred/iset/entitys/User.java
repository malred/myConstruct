package org.malred.iset.entitys;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
  private long id;
  private String name;
  private long age;
  private String email;
}
