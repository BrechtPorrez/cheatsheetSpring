package cheatsheet;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@lombok.Data
@NoArgsConstructor
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    String language;
    String subject;
    String code;
}
