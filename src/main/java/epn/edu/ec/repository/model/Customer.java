package epn.edu.ec.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data                           //Generates getters, setters, toString, equals and hashCode methods
@Builder(toBuilder = true)      //Genera un constructor con el patrón builder, permitiendo crear objetos de manera fluida y flexible
@AllArgsConstructor       //Genera un constructor con todos los campos como parámetros
@Entity
@Table(name = "customers")
public class Customer {

    public Customer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String mobileNumber;

    private String type;
}