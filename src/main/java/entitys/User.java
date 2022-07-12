package entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
public class User {
  private long id;
  private String name;
  private long age;
  private String email;
}
