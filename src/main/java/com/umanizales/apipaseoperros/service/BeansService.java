package com.umanizales.apipaseoperros.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class BeansService {
  private int cont=0;

  public int aumentarContador()
  {
      return ++cont;
  }

}
