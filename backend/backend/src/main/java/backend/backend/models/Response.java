package backend.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// ** Gerar Construtor 
@AllArgsConstructor
// ** Deixar o construtor opcional
@NoArgsConstructor
// ** Gerar os Gets e Setters
@Data
public class Response<T> {
    boolean Ok;   
    T data;
    String message;
}
