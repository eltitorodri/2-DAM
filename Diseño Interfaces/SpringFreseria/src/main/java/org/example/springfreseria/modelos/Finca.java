package org.example.springfreseria.modelos;
import jakarta.persistence.*;
import lombok.*;

/*Esto es lombok*/

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "finca")

public class Finca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "latitud")
    private float latitud;

    @Column(name = "longitud")
    private float longitud;

    @Column(name = "superficie")
    private float superficie;

}
