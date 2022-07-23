package com.advocates.kdba.response;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class GenericResponse<response, ticket, obj> {

    private response message;
    private ticket token;
    private obj object;
}
