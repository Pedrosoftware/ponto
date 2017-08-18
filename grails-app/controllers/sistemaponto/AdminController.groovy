package sistemaponto

import grails.plugin.springsecurity.annotation.Secured

/**
 * Created by pedro on 01/08/17.
 */
@Secured(['ROLE_ADMIN'])
class AdminController {

    static defaultAction = "home"

    def home(){

        return render (view: '/home/homeAdmin')
    }
}
