package br.com.deveficiente.novoautor.compartilhado;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.List;

public class CampoExistenteValidator implements ConstraintValidator<CampoExistente,Object> {

    private String domainAttribute;
    private Class<?> klass;
    @PersistenceContext
    private EntityManager manager;


    @Override
    public void initialize(CampoExistente params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null){
            return true;
        }

        Query query = manager.createQuery("SELECT 1 FROM "+klass.getName()+" where "+domainAttribute+"=:value");
        query.setParameter("value",value);

        List<?> list = query.getResultList();
        Assert.isTrue(list.size() <= 1,"aconteceu algo birrazo. tem mais de um "+klass+"com o abtributo"+domainAttribute);

        return !list.isEmpty();
    }
}
