package com.eletronico.pontoapi.core.exceptions.interceptor;

import com.eletronico.pontoapi.core.exceptions.IDomainErrorCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class InterceptorException<T> {

    private String message;
    private HttpStatus status;
    public InterceptorException(){
    }
    public InterceptorException(IDomainErrorCode errorCode){
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
    }
}
