package com.desafioteste.desafioquality.exception;

    public class BairroNaoExisteException extends RuntimeException {
        private String message;
        public BairroNaoExisteException(String message) {
            super(message);
        }
    }

