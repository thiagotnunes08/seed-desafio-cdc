package br.com.deveficiente.novoautor.compartilhado;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class CampoUnicoValitador implements ConstraintValidator<CampoUnico,Object> {

    private String domainAttribute;
    private Class<?> klass;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(CampoUnico constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Query query = manager.createQuery("SELECT 1 FROM "+klass.getName()+" WHERE "+ domainAttribute+"=:value");
        query.setParameter("value",value);
        List<?> list = query.getResultList();

        Assert.state(list.size() <= 1,"Foi encontrado mais de um atributo com esse nome, possivel bug");


        return list.isEmpty();
    }
}
