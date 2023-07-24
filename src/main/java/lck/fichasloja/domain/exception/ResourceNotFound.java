package lck.fichasloja.domain.exception;

public class ResourceNotFound extends RuntimeException{

     public ResourceNotFound(String msg){
        super(msg);
    }

    public ResourceNotFound(Long id){
        this("Não foi possível encontrar o recurso com o id: " + id);
    }

}
