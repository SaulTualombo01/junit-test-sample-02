package epn.edu.ec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import epn.edu.ec.repository.model.Cake;

public interface CakeRepository extends JpaRepository<Cake, Long>{
    Optional<Cake> findByTitle(String title); //evita null pointer exception, devuelve un Optional que puede contener el pastel encontrado o estar vacío si no se encuentra ningún pastel con ese título.
    //clase de consulta personalizada, busca un pastel por su título y devuelve un Optional que puede contener el pastel encontrado o estar vacío si no se encuentra ningún pastel con ese título.
}
