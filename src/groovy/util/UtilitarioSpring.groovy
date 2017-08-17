package util

import grails.plugin.springsecurity.userdetails.GrailsUser
import org.springframework.security.core.context.SecurityContextHolder
import sistemaponto.Funcionario
import sistemaponto.Regra

/**
 * Created by pedro on 11/08/17.
 */
class UtilitarioSpring {

    static Funcionario getUsuarioLogado(){
        GrailsUser gUser = getGrailsUser()
        if(!gUser){
            return null
        }
        return Funcionario.get(gUser.getId() as int)
    }

    static GrailsUser getGrailsUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        try{
            return (GrailsUser) principal
        }catch(Exception ex){
            return null
        }
    }
}