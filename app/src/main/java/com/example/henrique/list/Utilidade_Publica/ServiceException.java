package com.example.henrique.list.Utilidade_Publica;

/**
 * Created by michael on 14/05/2015.
 */
public class ServiceException extends Exception {

    public ServiceException() {
        super();
    }

    public ServiceException(String detailMessage) {
        super(detailMessage);
    }

    public ServiceException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ServiceException(Throwable throwable) {
        super(throwable);
    }
}