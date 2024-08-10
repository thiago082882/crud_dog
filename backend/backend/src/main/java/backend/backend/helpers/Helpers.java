package backend.backend.helpers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import backend.backend.models.Response;

@Service
public class Helpers {
    public <T> Map<String,Object>apiResponse(Response<T> data){
      Map<String,Object> response = new HashMap<>();
      response.put("ok", data.isOk());
      response.put("data", data.getData());
      response.put("message", data.getMessage());
    return response;
      

    }
}
