package com.sisar.service;

public class AutenticacaoProxy implements com.sisar.service.Autenticacao {
  private String _endpoint = null;
  private com.sisar.service.Autenticacao autenticacao = null;
  
  public AutenticacaoProxy() {
    _initAutenticacaoProxy();
  }
  
  public AutenticacaoProxy(String endpoint) {
    _endpoint = endpoint;
    _initAutenticacaoProxy();
  }
  
  private void _initAutenticacaoProxy() {
    try {
      autenticacao = (new com.sisar.service.AutenticacaoServiceLocator()).getAutenticacao();
      if (autenticacao != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)autenticacao)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)autenticacao)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (autenticacao != null)
      ((javax.xml.rpc.Stub)autenticacao)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.sisar.service.Autenticacao getAutenticacao() {
    if (autenticacao == null)
      _initAutenticacaoProxy();
    return autenticacao;
  }
  
  public void autentica(java.lang.String usuario) throws java.rmi.RemoteException{
    if (autenticacao == null)
      _initAutenticacaoProxy();
    autenticacao.autentica(usuario);
  }
  
  public boolean isAutenticado(java.lang.String usuario) throws java.rmi.RemoteException{
    if (autenticacao == null)
      _initAutenticacaoProxy();
    return autenticacao.isAutenticado(usuario);
  }
  
  
}