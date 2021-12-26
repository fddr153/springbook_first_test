package com.fundamentos.springboot.fundamentos.bean;

import com.fundamentos.springboot.fundamentos.FundamentsApplication;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    private MyOperation myOperation;
    Log LOGGER= LogFactory.getLog(FundamentsApplication.class);

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        LOGGER.info("Dentro del metodo printwithdependency");
        int numero=1;
        LOGGER.debug("el numero parametro de la dependencia es: "+numero);
        System.out.println(myOperation.sum(numero));
        System.out.println("hola desde la implementacion de un bean con dependencia");
    }
}
