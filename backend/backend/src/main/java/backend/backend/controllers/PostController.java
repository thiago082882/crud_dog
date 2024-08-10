package backend.backend.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.backend.helpers.Helpers;
import backend.backend.models.Post;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private Helpers helpers;

    public List<Post> posts = new ArrayList<>();

    public PostController(){
        Post post1 = new Post(UUID.randomUUID(),"Cachorro perdido no parque","Rex, um Pastor Alemão imponente de pelagem preta e marrom, é um verdadeiro exemplo de elegância e força. Seus olhos escuros transmitem inteligência e lealdade, enquanto suas orelhas eretas denotam vigilância constante. Com uma postura majestosa, Rex é mais do que um cão de porte médio; é um companheiro leal, pronto para proteger e encantar com sua presença confiante e afetuosa.","data:image/jpeg;base64,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");

        Post post2 = new Post(UUID.randomUUID(),"Labrador Retriever para Adoção Responsável","Procurando um novo membro para a família? Apresento a você o Marley, um Labrador Retriever carinhoso e brincalhão em busca de um lar amoroso. Sua pelagem densa e cor chocolate encanta a todos. Marley é vacinado, castrado e pronto para encher sua casa de alegria. Entre em contato se estiver pronto para proporcionar uma vida feliz a esse amável companheiro.","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSYaeBxgV2K5xU9v_sw5X4H8n9p_gLgDEGH3w&usqp=CAU");

        Post post3 = new Post(UUID.randomUUID(),"Bulldog Frances a Procura de um Lar Aconchegante","Este é Bruce, um Bulldog Francês charmoso e cheio de personalidade. Com sua aparência única e orelhas expressivas, Bruce está em busca de uma família amorosa. Ele é sociável, vacinado e adestrado. Se você procura um amigo fiel, considere dar a Bruce um lar aconchegante. Entre em contato para conhecê-lo e iniciar uma jornada repleta de amor.","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwQSypL74bqt_li7yOIqiUrNtJxyNhqOK_wQ&usqp=CAU");


        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
    }

    /**
     * @return
     */
    @GetMapping
    public ResponseEntity<?> getPosts(){
        Map<String,Object> response = new HashMap<>();
        //response.put("ok", true);
        //response.put("data", posts);
       // response.put("message", "lista de posts");
       response = helpers.apiResponse(new backend.backend.models.Response<>(true,posts,"Lista de post"));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    /**
     * @param id
     * @return
     */

    @GetMapping("/{id}")
     public ResponseEntity<?> getPostById(@PathVariable String id){
        Map<String,Object> response = new HashMap<>();

        try {
            UUID idConvert = UUID.fromString(id);
            Post postEncontrado = posts.stream().filter(item->item.getId().equals(idConvert)).findFirst().orElse(null);
            if(postEncontrado == null){
            response.put("ok", false);
            response.put("data", "");
            response.put("message", "post não encontrado");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
            response.put("ok", true);
            response.put("data", postEncontrado);
            response.put("message", "post encontrado");
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            response.put("ok", false);
            response.put("data", "");
            response.put("message", "id não é válido");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }

       
     }
 

    /**
     * @return
     */
    @PostMapping
    public ResponseEntity<?> savePost(@RequestBody Post post){
        Map<String,Object> response = new HashMap<>();
        post.setId(UUID.randomUUID());
        posts.add(post);
        response.put("ok", true);
        response.put("data", post);
        response.put("message", "Post criado");
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePost(@RequestBody Post post,@PathVariable String id){
        Map<String,Object> response = new HashMap<>();
        try {
            UUID idConvert = UUID.fromString(id);
            Post postEncontrado = posts.stream().filter(item->item.getId().equals(idConvert)).findFirst().orElse(null);
            if(postEncontrado == null){
                response.put("ok", false);
                response.put("data", "");
                response.put("message", "post não encontrado");
                return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
                }
                if(post.getTitle()!=null && !post.getTitle().isEmpty()){
                    postEncontrado.setTitle(post.getTitle());
                }

                if(post.getDescription()!=null && !post.getDescription().isEmpty()){
                    postEncontrado.setDescription(post.getDescription());
                }
                if(post.getImgUrl()!=null && !post.getImgUrl().isEmpty()){
                    postEncontrado.setImgUrl(post.getImgUrl());
                }
              
            
               response.put("ok", true);
               response.put("data", postEncontrado);
               response.put("message", "post atualizado");
               return new ResponseEntity<>(response,HttpStatus.OK);
            } catch (IllegalArgumentException e) {
                response.put("ok", false);
                response.put("data", "");
                response.put("message", "id não é válido");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable String id){
        Map<String,Object> response = new HashMap<>();
        try {
            UUID idConvert = UUID.fromString(id);
            Post postEncontrado = posts.stream().filter(item->item.getId().equals(idConvert)).findFirst().orElse(null);
            if(postEncontrado == null){
            response.put("ok", false);
            response.put("data", "");
            response.put("message", "post não encontrado");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
            }
            posts.remove(postEncontrado);
            response.put("ok", true);
            response.put("data", "");
            response.put("message", "post eliminado");
            return new ResponseEntity<>(response,HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            response.put("ok", false);
            response.put("data", "");
            response.put("message", "id não é válido");
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
    
}
