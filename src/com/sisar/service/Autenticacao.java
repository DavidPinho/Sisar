/**
 * Autenticacao.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sisar.service;

public interface Autenticacao extends java.rmi.Remote {
    public void autentica(java.lang.String usuario) throws java.rmi.RemoteException;
    public boolean isAutenticado(java.lang.String usuario) throws java.rmi.RemoteException;
}
