package backend.backend.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// ** Gerar Construtor 
@AllArgsConstructor
// ** Deixar o construtor opcional
@NoArgsConstructor
// ** Gerar os Gets e Setters
@Data
public class Post {
    private UUID id;
    private String title;
    private String description;
    private String imgUrl;



}
