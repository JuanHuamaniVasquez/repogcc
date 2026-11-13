package demo;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class Aplicacion {
    public static void main(String[] args) {
        SpringApplication.run(Aplicacion.class, args);
    }
}

@RestController
@RequestMapping("/api/libros")
class LibrosController {
    private final List<Libro> libros = new CopyOnWriteArrayList<>(List.of(
        new Libro("El principito", "Antoine de Saint-Exupery", "Fabula", 1943)
    ));

    @GetMapping
    public List<Libro> listar() {
        return List.copyOf(libros);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Libro registrar(@Valid @RequestBody Libro libro) {
        libros.add(libro);
        return libro;
    }
}

record Libro(
    @NotBlank String titulo,
    @NotBlank String autor,
    @NotBlank String genero,
    @NotNull @Positive Integer anioPublicacion
) {}
