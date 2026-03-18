package tn.esprit.zaiem_nesrine_arctic10.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ProjectDetails {

    @Id
    Long detailsId;

    Double budget;

    String client;

}

