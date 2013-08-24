/**
 * AutenticacaoServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.sisar.service;

public class AutenticacaoServiceLocator extends org.apache.axis.client.Service implements com.sisar.service.AutenticacaoService {

    public AutenticacaoServiceLocator() {
    }


    public AutenticacaoServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public AutenticacaoServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Autenticacao
    private java.lang.String Autenticacao_address = "http://localhost:8080/SisarWebService/services/Autenticacao";

    public java.lang.String getAutenticacaoAddress() {
        return Autenticacao_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String AutenticacaoWSDDServiceName = "Autenticacao";

    public java.lang.String getAutenticacaoWSDDServiceName() {
        return AutenticacaoWSDDServiceName;
    }

    public void setAutenticacaoWSDDServiceName(java.lang.String name) {
        AutenticacaoWSDDServiceName = name;
    }

    public com.sisar.service.Autenticacao getAutenticacao() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Autenticacao_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getAutenticacao(endpoint);
    }

    public com.sisar.service.Autenticacao getAutenticacao(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.sisar.service.AutenticacaoSoapBindingStub _stub = new com.sisar.service.AutenticacaoSoapBindingStub(portAddress, this);
            _stub.setPortName(getAutenticacaoWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setAutenticacaoEndpointAddress(java.lang.String address) {
        Autenticacao_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.sisar.service.Autenticacao.class.isAssignableFrom(serviceEndpointInterface)) {
                com.sisar.service.AutenticacaoSoapBindingStub _stub = new com.sisar.service.AutenticacaoSoapBindingStub(new java.net.URL(Autenticacao_address), this);
                _stub.setPortName(getAutenticacaoWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("Autenticacao".equals(inputPortName)) {
            return getAutenticacao();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.sisar.com", "AutenticacaoService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.sisar.com", "Autenticacao"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Autenticacao".equals(portName)) {
            setAutenticacaoEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
