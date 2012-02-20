package app

import org.apache.shiro.SecurityUtils

class UserService {

    static transactional = true

    def getAuthenticatedUser() {
        return User.findByUsername(SecurityUtils.subject.principal)
    }

}
